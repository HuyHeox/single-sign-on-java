package com.ks.sso.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomainConfig {
	
	public static Map<String, List<String>> mapClientIdDomain() {
		Map<String, List<String>> mapClientIDDomain = new HashMap<>();
		mapClientIDDomain.put("clientId_hvktmm", Arrays.asList("https://sso-dot-hvktmm.appspot.com/", "https://1-dot-hvktmm.appspot.com/", "https://test-dot-hvktmm.appspot.com/"));
		mapClientIDDomain.put("clientId_hmu", Arrays.asList("https://sso-dot-hmu-edu.appspot.com/", "https://portal.hmu.edu.vn/", "https://test-dot-hmu-edu.appspot.com"));

			
		return mapClientIDDomain;
	}
	
	public static String getClienIdByDomain(String domain) {
		for (String clientId : mapClientIdDomain().keySet()) {
			for (String url: mapClientIdDomain().get(clientId)) {
				if(url.contains(domain) || domain.contains(url)) {
					return clientId;
				}
			}
		}
		return null;
	}

	public static String getURLSSO(String domain) {
		if(domain.contains("hvktmm")) {
			return "https://sso-dot-hvktmm.appspot.com/";
		}else if(domain.contains("hmu")) {
			return "https://sso-dot-hmu-edu.appspot.com/";
		}
		return null;
	}
	
}
 