// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.ks.sso.office365;

/**
 * Object containing configuration data for the application. Spring will automatically wire the
 * values by grabbing them from application.properties file
 */
public class BasicConfiguration {

    private String clientId;
    private String authority;
    private String redirectUriSignin;
    private String redirectUriGraph;
    private String secretKey;
    private String msGraphEndpointHost;
    private String tenant;
    
    public BasicConfiguration(String clientId, String authority, String redirectUriSignin, String redirectUriGraph,
    		String secretKey, String msGraphEndpointHost, String tenant) {
    	this.setClientId(clientId);
    	this.setAuthority(authority);
    	this.setRedirectUriSignin(redirectUriSignin);
    	this.setRedirectUriGraph(redirectUriGraph);
    	this.setSecretKey(secretKey);
    	this.setMsGraphEndpointHost(msGraphEndpointHost);
    	this.setTenant(tenant);
    }

    public String getAuthority(){
        if (!authority.endsWith("/")) {
            authority += "/";
        }
        return authority;
    }

    public String getClientId() {
        return clientId;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRedirectUriSignin() {
        return redirectUriSignin;
    }

    public void setRedirectUriSignin(String redirectUriSignin) {
        this.redirectUriSignin = redirectUriSignin;
    }

    public String getRedirectUriGraph() {
        return redirectUriGraph;
    }

    public void setRedirectUriGraph(String redirectUriGraph) {
        this.redirectUriGraph = redirectUriGraph;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setMsGraphEndpointHost(String msGraphEndpointHost) {
        this.msGraphEndpointHost = msGraphEndpointHost;
    }

    public String getMsGraphEndpointHost(){
        return msGraphEndpointHost;
    }
    
    public String getTenant() {
		return tenant;
	}
    
    public void setTenant(String tenant) {
		this.tenant = tenant;
	}
}