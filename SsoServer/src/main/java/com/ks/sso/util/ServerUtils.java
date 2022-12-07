package com.ks.sso.util;

import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.appengine.repackaged.com.google.common.hash.Hashing;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Calendar;
import java.util.Map;
import java.util.Scanner;
import java.util.TimeZone;

public class ServerUtils {
    private static final String keyMain = "aPKyaDO4h4F8MQd3";
    private static final String initVector = "TbALdMOhDS6aec1U";

    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(keyMain.getBytes("UTF-8"), "AES");
//			System.out.println("Native Output…!!!");
//			System.out.println("IV: " + Arrays.toString(iv.getIV()));
//			System.out.println("KEY: " + Arrays.toString(skeySpec.getEncoded()));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
//			System.out.println("Encrypted: " + Arrays.toString(encrypted));
//			System.out.println("Base64: " + Base64.getEncoder().encodeToString(encrypted));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String encriptPass(String password) {
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }
    
    public static String request3(String urlString, Map<String, String> params, String method) throws IOException {
		String urlParameters = "";
		if (params != null) {
			for (String key : params.keySet()) {
				if (!urlParameters.isEmpty()) {
					urlParameters += "&";
				}
				urlParameters += key + "=" + params.get(key); //URLEncoder.encode(params.get(key), "UTF-8");
			}
		}
		String response = null;

		HttpURLConnection conn = null;
		DataOutputStream dos = null;
		InputStream is = null;
		try {
			URL url = new URL(urlString);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(30 * 1000);
			conn.setRequestMethod(method);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
			dos = new DataOutputStream(conn.getOutputStream());
			dos.writeBytes(urlParameters);
			dos.flush();

			is = conn.getInputStream();
			Scanner s = new Scanner(is).useDelimiter("\\A");
			response = s.hasNext() ? s.next() : null;
		} finally {
			if (dos != null) {
				dos.close();
			}

			if (is != null) {
				is.close();
			}

			if (conn != null) {
				conn.disconnect();
			}
		}
		return response;
	}
	
	public static String request2(String url, Map<String, String> params, Map<String, String> headers, HTTPMethod method, boolean inBody) throws Exception {
		FetchOptions fetchOptions = FetchOptions.Builder.withDefaults();
		fetchOptions.setDeadline((double) 120);
		fetchOptions.validateCertificate();
		URL url3 = new URL(url);
		HTTPRequest urlConnection = new HTTPRequest(url3, method, fetchOptions);
		if (headers != null && !headers.isEmpty()) {
			for (String key : headers.keySet()) {
				urlConnection.addHeader(new HTTPHeader(key, headers.get(key)));
			}
		}
		if (params != null) {
			JSONObject obj = new JSONObject();
			for (String key : params.keySet()) {
				obj.put(key, URLEncoder.encode(params.get(key), "UTF-8"));
			}
			if (inBody) {
				HttpURLConnection conn = (HttpURLConnection) url3.openConnection();
//				conn.setRequestProperty("Content-Type", "application/json; utf-8");
//				conn.setRequestProperty("Accept", "application/json");
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				OutputStream outputStream = conn.getOutputStream();
				//OutputStreamWriter osw = new OutputStreamWriter(outputStream, "UTF-8");
				String json = obj.toString();
				byte[] input = json.getBytes("utf-8");
				outputStream.write(input, 0, input.length);
				outputStream.flush();
				//osw.close();
				outputStream.close();  //don't forget to close the OutputStream
				conn.connect();
			}
			else {
				byte[] postData = obj.toString().getBytes("utf-8");
				urlConnection.setPayload(postData);
			}
		}
		URLFetchService urlFetchService = URLFetchServiceFactory.getURLFetchService();
		HTTPResponse response = urlFetchService.fetch(urlConnection);
		int responseCode = response.getResponseCode();
		return new String(response.getContent());
	}
	
	public static long getCurrentTime() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+7"));
		return cal.getTimeInMillis();
	}
	
	
}
