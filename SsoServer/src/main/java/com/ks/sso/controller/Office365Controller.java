package com.ks.sso.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ks.sso.config.CacheTokenInfo;
import com.ks.sso.config.Constant;
import com.ks.sso.exception.ServerException;
import com.ks.sso.model.UUID;
import com.ks.sso.model.UserSession;
import com.ks.sso.model.basic.IBasic;
import com.ks.sso.office365.AuthHelper;
import com.ks.sso.office365.HttpClientHelper;
import com.ks.sso.service.CacheService;
import com.ks.sso.service.DataServiceImpl;
import com.ks.sso.service.SsoService;
import com.ks.sso.util.CookieUtil;
import com.ks.sso.util.ServerUtils;

@Controller
public class Office365Controller {
    public static final Logger log = Logger.getLogger("hungnt");
    @Autowired
    private CookieUtil cookieUtil;
    @Autowired
    private CacheService cacheService;
	private AuthHelper authHelper;

	 @RequestMapping(value = "/login-office365", method = RequestMethod.GET)
	    public String loginOffice365(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
		 log.warning("request.getServerName() "+ request.getServerName());
		 Map<String, String> infos = AuthHelper.getMapConfig(request.getServerName());
			authHelper = new AuthHelper(infos);
			authHelper.init();
			authHelper.sendAuthRedirect(request, response, null, authHelper.getRedirectUriSignIn());
		 return "redirect:/sso/login";
	    }
	
	 @RequestMapping(value = "/office365api", method = RequestMethod.GET)
	    public String loginOffice365Code(HttpServletRequest request, HttpServletResponse response, @RequestParam String code) throws ServerException, IOException {
		System.out.println("code "+ code);
		 if (code != null && !code.isEmpty()) {
			 	Map<String, String> infos = AuthHelper.getMapConfig(request.getServerName());
				authHelper = new AuthHelper(infos);
				authHelper.init();
				Map<String, String> params = new HashMap<String, String>();
				params.put("client_id", authHelper.getClientId());
				params.put("scope", "openid profile user.read");
				params.put("redirect_uri", authHelper.getRedirectUriSignIn());
				params.put("grant_type", "authorization_code");
				params.put("client_secret", authHelper.getClientSecret());
				params.put("code", code);
				String data;
				String email = "";
				log.warning("params "+ params);
				try {
					data = ServerUtils.request3(
							"https://login.microsoftonline.com/" + authHelper.getTenant() + "/oauth2/v2.0/token", params,
							"POST");
					log.warning("SingleSignOnOffice365: " + data);

					if (data != null && !data.isEmpty()) {
						JsonObject object = new JsonParser().parse(data).getAsJsonObject();
						if (object.has("access_token")) {
							String access_token = object.get("access_token").getAsString();
							JSONObject jsonObject = (JSONObject) getUserInfoFromGraph(access_token, true);
							if (jsonObject != null) {
								int responseCode = jsonObject.getInt("responseCode");
								jsonObject = jsonObject.getJSONObject("responseMsg");
//								DataServiceImpl.log.warning("jsonObject2 "+ jsonObject);
								if (responseCode == 200) {
									email = jsonObject.getString("userPrincipalName");
								}
							}
						}
					}
				} catch (Exception e1) {
					log.warning("e1 "+ e1);
					email = "loginerror";
				}
				loginByEmailSSO(email, authHelper.getDomain(), request, response);
			}
		 return null;
	    }

	 private void loginByEmailSSO(String email, String baseUrl, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		log.warning("email "+ email);
		String sessionId = "";
		if (email.isEmpty()) {
			email = "loginerror";
		}
		else {
			email = email.toLowerCase();
			DataServiceImpl dataService = new DataServiceImpl();
			//new DataServiceImpl().setSessionIdToCookie(email, request, response);
			if (email != null && !email.isEmpty()) {
				IBasic iBasic = dataService.getUserByEmail(email);
//				if (iBasic == null) {
//					List<IBasic> items = dataService.search(Student.class.getSimpleName(), Config.NULL_ID, email, -1);
//					if (items == null || items.isEmpty()) {
//						items = dataService.search(Teacher.class.getSimpleName(), Config.NULL_ID, email, -1);
//					}
//					if (items != null && items.size() == 1) {
//						iBasic = items.get(0);
//					}
//				}
				log.warning("iBasic "+ iBasic);
				if (iBasic != null) {
					sessionId = UUID.uuid();
					UserSession session = dataService.setUserSession(iBasic, sessionId, false);
				}
			}
		}
		 String state = cookieUtil.GetCookieWithName(Constant.decodedState, request);
		System.out.println("state "+ state);
		 String tokenInCache = cacheService.GetValueFromCache(state);
		 System.out.println("tokenInCache "+ tokenInCache);
         CacheTokenInfo cacheTokenInfo =new SsoService().DecodeExchangeToken(tokenInCache);
         String redirectUrl = cacheTokenInfo.getRedirectUrl();
//         String url = String.format("%s/callback?token=%s", redirectUrl, tokenInCache);
//         new SsoService().Redirect302(response, url);
		response.setStatus(302);
		response.sendRedirect(redirectUrl + "?sessionId=" + sessionId);
	}

	public Object getUserInfoFromGraph(String accessToken, boolean thowsException) throws Exception {
		// Microsoft Graph user endpoint
		if (authHelper == null) {
			authHelper = new AuthHelper();
			authHelper.init();
		}
		URL url = new URL(authHelper.getMsGraphEndpointHost() + "v1.0/me");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		// Set the appropriate header fields in the request header.
		conn.setRequestProperty("Authorization", "Bearer " + accessToken);
		conn.setRequestProperty("Accept", "application/json");

		String response = HttpClientHelper.getResponseStringFromConn(conn);
		System.out.println("SingleSignOnOffice365-userInfoFromGraph: " + response);
		int responseCode = conn.getResponseCode();
		if (responseCode != HttpURLConnection.HTTP_OK) {
			if (thowsException) {
				throw new IOException(response);
			} else {
				return response;
			}
		}

		JSONObject responseObject = HttpClientHelper.processResponse(responseCode, response);
		return responseObject;
	}
}