// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.ks.sso.office365;

import static com.ks.sso.office365.SessionManagementHelper.FAILED_TO_VALIDATE_MESSAGE;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.naming.ServiceUnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.aad.msal4j.*;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.openid.connect.sdk.AuthenticationErrorResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationResponseParser;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;

/**
 * Helpers for acquiring authorization codes and tokens from AAD
 */
public class AuthHelper {
//	public static String BASE_URL = "https://qldt.hust.edu.vn/";
    public static final Logger log = Logger.getLogger("hungnt");

	public static final String PRINCIPAL_SESSION_NAME = "principal";
	static final String TOKEN_CACHE_SESSION_ATTRIBUTE = "token_cache";

	public static final String CLIENT_ID = "8f4bff6f-0acf-42f6-a8f4-4b129919d902";
//	public static final String URL_REDIRECT = BASE_URL + "office365api";
	public static final String SECRET_KEY = "x_XZMR=lbdcsVIV[MKEAYHXyKCV8:874";
	public static final String MS_GRAPH_ENDPOINT_HOST = "https://graph.microsoft.com/";
	public static final String AUTHORITY = "https://login.microsoftonline.com/0aa16d8a-a396-4e21-aa14-2a68a45786bc/";
//    public static final String TENANT = "06f1b89f-07e8-464f-b408-ec1b45703f31";

//    Application (client) ID: 927c2f13-ca45-4ab3-817c-df43d072f18d
//
//    Client Secret ID: c5d33a78-e2d7-40ef-87e9-2f125532babe
//
//    Directory (tenant) ID: 0aa16d8a-a396-4e21-aa14-2a68a45786bc
//
//    Object ID: be6d32b4-5b48-46c1-9b33-85a9f0f918fd
//
//    Vulue: NDx8Q~8KB9OuO164BDvnug-ZelRQngUTsCUMUad-
//
//    Secret ID: 1b12b9e5-7dd8-4982-9a68-c1e0b02d33f6
//

	public static Map<String, String> getMapConfig(String url) {
		
//		Display name:portalhmu
//		Application (client) ID:c5353cf6-0442-4b1d-b71e-2cb83fe4ae12
//		Object ID:5cdce905-057d-4d1f-ad37-9b1eb0393971
//		Directory (tenant) ID:1f3df81d-eae2-4455-b67d-8709bd40a1f7
		
		
		Map<String, String> infos = new HashMap<>();
		if (url.contains("sso-dot-hmu-edu.appspot.com")) {
			infos.put("domain", "https://sso-dot-hmu-edu.appspot.com/");
			infos.put("clientId", "c5353cf6-0442-4b1d-b71e-2cb83fe4ae12");// ClientID
			infos.put("secret", "zPM8Q~I01YiETMHMHlFBhSHEYgOSvYB2AgkYCbrX");// Secret
			infos.put("tenant", "1f3df81d-eae2-4455-b67d-8709bd40a1f7");// Secret

		}  else if (url.contains("dev-dot-hvktmm.appspot.com")) {
			infos.put("domain", "https://dev-dot-hvktmm.appspot.com/");
			infos.put("clientId", "927c2f13-ca45-4ab3-817c-df43d072f18d");// ClientID
			infos.put("secret", "NDx8Q~8KB9OuO164BDvnug-ZelRQngUTsCUMUad-");// Secret
			infos.put("tenant", "0aa16d8a-a396-4e21-aa14-2a68a45786bc");// Secret

		} else if (url.contains("hvktmm.appspot.com")) {
			infos.put("domain", "https://hvktmm.appspot.com/");
			infos.put("clientId", "927c2f13-ca45-4ab3-817c-df43d072f18d");// ClientID
//			infos.put("secret", "c5d33a78-e2d7-40ef-87e9-2f125532babe");//Secret
//			infos.put("secret", "1b12b9e5-7dd8-4982-9a68-c1e0b02d33f6");//Secret
			infos.put("secret", "NDx8Q~8KB9OuO164BDvnug-ZelRQngUTsCUMUad-");// Secret
			infos.put("tenant", "0aa16d8a-a396-4e21-aa14-2a68a45786bc");// Secret

		}else {
//			infos.put("domain", "https://test-dot-hust-edu.appspot.com/");
//			infos.put("clientId", "8f4bff6f-0acf-42f6-a8f4-4b129919d902");// ClientID
//			infos.put("secret", "x_XZMR=lbdcsVIV[MKEAYHXyKCV8:874");// Secret
			infos.put("domain", "https://sso-dot-hmu-edu.appspot.com/");
			infos.put("clientId", "c5353cf6-0442-4b1d-b71e-2cb83fe4ae12");// ClientID
			infos.put("secret", "zPM8Q~I01YiETMHMHlFBhSHEYgOSvYB2AgkYCbrX");// Secret
			infos.put("tenant", "1f3df81d-eae2-4455-b67d-8709bd40a1f7");// Secret
		}
		return infos;
	}

	static final String STATE = "state";

	private String clientId;
	private String clientSecret;
	private String authority;
	private String redirectUriSignIn;
	private String redirectUriGraph;
	private String msGraphEndpointHost;
	private String tenant;
	private String domain;

	public BasicConfiguration configuration = new BasicConfiguration(CLIENT_ID, AUTHORITY, "", "",
			SECRET_KEY, MS_GRAPH_ENDPOINT_HOST, "");

	public AuthHelper() {
		// TODO Auto-generated constructor stub
	}

	public AuthHelper(Map<String, String> infos) {
		String authoriry = "https://login.microsoftonline.com/"
				+ ( usingTenant(infos.get("domain")) ? infos.get("tenant"): "common" );
		this.domain = infos.get("domain");
		configuration = new BasicConfiguration(infos.get("clientId"), authoriry, domain + "office365api",
				domain + "office365api", infos.get("secret"), MS_GRAPH_ENDPOINT_HOST, infos.get("tenant"));
	}

	@PostConstruct
	public void init() {
		clientId = configuration.getClientId();
		authority = configuration.getAuthority();
		clientSecret = configuration.getSecretKey();
		redirectUriSignIn = configuration.getRedirectUriSignin();
		redirectUriGraph = configuration.getRedirectUriGraph();
		msGraphEndpointHost = configuration.getMsGraphEndpointHost();
		tenant = configuration.getTenant();
	}

	public void processAuthenticationCodeRedirect(HttpServletRequest httpRequest, String currentUri, String fullUrl)
			throws Throwable {

		Map<String, List<String>> params = new HashMap<>();
		for (String key : httpRequest.getParameterMap().keySet()) {
			params.put(key, Collections.singletonList(httpRequest.getParameterMap().get(key)[0]));
		}
		// validate that state in response equals to state in request
		validateState(CookieHelper.getCookie(httpRequest, CookieHelper.MSAL_WEB_APP_STATE_COOKIE),
				params.get(STATE).get(0));

		AuthenticationResponse authResponse = AuthenticationResponseParser.parse(new URI(fullUrl), params);
		if (AuthHelper.isAuthenticationSuccessful(authResponse)) {
			AuthenticationSuccessResponse oidcResponse = (AuthenticationSuccessResponse) authResponse;
			// validate that OIDC Auth Response matches Code Flow (contains only requested
			// artifacts)
			validateAuthRespMatchesAuthCodeFlow(oidcResponse);

			IAuthenticationResult result = getAuthResultByAuthCode(httpRequest, oidcResponse.getAuthorizationCode(),
					currentUri);

			// validate nonce to prevent reply attacks (code maybe substituted to one with
			// broader access)
			validateNonce(CookieHelper.getCookie(httpRequest, CookieHelper.MSAL_WEB_APP_NONCE_COOKIE),
					getNonceClaimValueFromIdToken(result.idToken()));

			SessionManagementHelper.setSessionPrincipal(httpRequest, result);
		} else {
			AuthenticationErrorResponse oidcResponse = (AuthenticationErrorResponse) authResponse;
			throw new Exception(String.format("Request for auth code failed: %s - %s",
					oidcResponse.getErrorObject().getCode(), oidcResponse.getErrorObject().getDescription()));
		}
	}

	public IAuthenticationResult getAuthResultBySilentFlow(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws Throwable {

		IAuthenticationResult result = SessionManagementHelper.getAuthSessionObject(httpRequest);

		IConfidentialClientApplication app = createClientApplication();

		Object tokenCache = httpRequest.getSession().getAttribute("token_cache");
		if (tokenCache != null) {
			app.tokenCache().deserialize(tokenCache.toString());
		}

		SilentParameters parameters = SilentParameters.builder(Collections.singleton("User.Read"), result.account())
				.build();

		CompletableFuture<IAuthenticationResult> future = app.acquireTokenSilently(parameters);
		IAuthenticationResult updatedResult = future.get();

		// update session with latest token cache
		SessionManagementHelper.storeTokenCacheInSession(httpRequest, app.tokenCache().serialize());

		return updatedResult;
	}

	private void validateState(String cookieValue, String state) throws Exception {
		if (StringUtils.isEmpty(state) || !state.equals(cookieValue)) {
			throw new Exception(FAILED_TO_VALIDATE_MESSAGE + "could not validate state");
		}
	}

	private void validateNonce(String cookieValue, String nonce) throws Exception {
		if (StringUtils.isEmpty(nonce) || !nonce.equals(cookieValue)) {
			throw new Exception(FAILED_TO_VALIDATE_MESSAGE + "could not validate nonce");
		}
	}

	private String getNonceClaimValueFromIdToken(String idToken) throws ParseException {
		return (String) JWTParser.parse(idToken).getJWTClaimsSet().getClaim("nonce");
	}

	private void validateAuthRespMatchesAuthCodeFlow(AuthenticationSuccessResponse oidcResponse) throws Exception {
		if (oidcResponse.getIDToken() != null || oidcResponse.getAccessToken() != null
				|| oidcResponse.getAuthorizationCode() == null) {
			throw new Exception(FAILED_TO_VALIDATE_MESSAGE + "unexpected set of artifacts received");
		}
	}

	public void sendAuthRedirect(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String scope,
			String redirectURL) throws IOException {

		// state parameter to validate response from Authorization server and nonce
		// parameter to validate idToken
		String state = UUID.randomUUID().toString();
		String nonce = UUID.randomUUID().toString();

		CookieHelper.setStateNonceCookies(httpRequest, httpResponse, state, nonce);

		httpResponse.setStatus(302);
		String authorizationCodeUrl = "";
		if(usingTenant(redirectURL)){
			authorizationCodeUrl =  getAuthorizationCodeUrlForHVKTMM(httpRequest.getParameter("claims"), scope, redirectURL,
					state, nonce);
		} else {
			authorizationCodeUrl =  getAuthorizationCodeUrlHUST(httpRequest.getParameter("claims"), scope, redirectURL,
					state, nonce);
		}
		log.warning("authorizationCodeUrl " + authorizationCodeUrl);
		log.warning("domain " + domain);
		httpResponse.sendRedirect(authorizationCodeUrl);
	}

	public String getAuthorizationCodeUrlHUST(String claims, String scope, String registeredRedirectURL, String state,
			String nonce) throws UnsupportedEncodingException {

		String urlEncodedScopes = scope == null ? URLEncoder.encode("openid profile user.read", "UTF-8")
				: URLEncoder.encode("openid profile" + " " + scope, "UTF-8");
//        String authorUrl2 = "https://login.microsoftonline.com/0aa16d8a-a396-4e21-aa14-2a68a45786bc/oauth2/v2.0/authorize?client_id=927c2f13-ca45-4ab3-817c-df43d072f18d&response_type=code&redirect_uri=https://hvktmm.appspot.com/office365api&response_mode=query&scope="
//                +URLEncoder.encode("openid profile user.read", "UTF-8")
//                +"&state="+state;

		String authorizationCodeUrl = authority + "oauth2/v2.0/authorize?" + "response_type="
				+ URLEncoder.encode("id_token code", "UTF-8") + "&" + "response_mode=form_post&" + "redirect_uri="
				+ URLEncoder.encode(registeredRedirectURL, "UTF-8") + "&client_id=" + clientId + "&scope="
				+ urlEncodedScopes + (StringUtils.isEmpty(claims) ? "" : "&claims=" + claims) + "&prompt=select_account"
				+ "&state=" + state + "&nonce=" + nonce;
		return authorizationCodeUrl;
	}
	
	public String getAuthorizationCodeUrlForHVKTMM(String claims, String scope, String registeredRedirectURL, String state,
			String nonce) throws UnsupportedEncodingException {

		String urlEncodedScopes = scope == null ? URLEncoder.encode("openid profile user.read", "UTF-8")
				: URLEncoder.encode("openid profile" + " " + scope, "UTF-8");
//        String authorUrl2 = "https://login.microsoftonline.com/0aa16d8a-a396-4e21-aa14-2a68a45786bc/oauth2/v2.0/authorize?
//		client_id=927c2f13-ca45-4ab3-817c-df43d072f18d
//		&response_type=code
//		&redirect_uri=https://hvktmm.appspot.com/office365api
//		&response_mode=query&scope="
//                +URLEncoder.encode("openid profile user.read", "UTF-8")
//                +"&state="+state;

		String authorizationCodeUrl = authority + "oauth2/v2.0/authorize?" 
				+ "response_type=code"
				 + "&response_mode=query&" 
				+ "redirect_uri="
				+ URLEncoder.encode(registeredRedirectURL, "UTF-8") + "&client_id=" + clientId + "&scope="
				+ urlEncodedScopes + (StringUtils.isEmpty(claims) ? "" : "&claims=" + claims) + "&prompt=select_account"
				+ "&state=" + state + "&nonce=" + nonce;
		return authorizationCodeUrl;
	}

	private IAuthenticationResult getAuthResultByAuthCode(HttpServletRequest httpServletRequest,
			AuthorizationCode authorizationCode, String currentUri) throws Throwable {

		IAuthenticationResult result;
		ConfidentialClientApplication app;
		try {
			app = createClientApplication();

			String authCode = authorizationCode.getValue();
			AuthorizationCodeParameters parameters = AuthorizationCodeParameters.builder(authCode, new URI(currentUri))
					.build();

			Future<IAuthenticationResult> future = app.acquireToken(parameters);

			result = future.get();
		} catch (ExecutionException e) {
			throw e.getCause();
		}

		if (result == null) {
			throw new ServiceUnavailableException("authentication result was null");
		}

		SessionManagementHelper.storeTokenCacheInSession(httpServletRequest, app.tokenCache().serialize());

		return result;
	}

	private ConfidentialClientApplication createClientApplication() throws MalformedURLException {
		return ConfidentialClientApplication.builder(clientId, ClientCredentialFactory.createFromSecret(clientSecret))
				.authority(authority).build();
	}

	private static boolean isAuthenticationSuccessful(AuthenticationResponse authResponse) {
		return authResponse instanceof AuthenticationSuccessResponse;
	}

	public String getRedirectUriSignIn() {
		return redirectUriSignIn;
	}

	public String getRedirectUriGraph() {
		return redirectUriGraph;
	}

	public String getMsGraphEndpointHost() {
		return msGraphEndpointHost;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public String getTenant() {
		return tenant;
	}

	public String getDomain() {
		return domain;
	}
	
	public static boolean usingTenant(String domain) {
		return domain.contains("hvktmm.appspot.com") || domain.contains("sso-dot-hmu-edu.appspot.com");
	}
	
	
}
