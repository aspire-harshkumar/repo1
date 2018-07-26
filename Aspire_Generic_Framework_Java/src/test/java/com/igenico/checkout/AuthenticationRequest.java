package com.igenico.checkout;

import java.net.URI;
import java.util.List;

import com.genric.support.Log;
import com.igenico.pages.APIKeysPage;
import com.jayway.restassured.http.Method;


public class AuthenticationRequest {

	private String apiKeyId;
	private String secretApiKey;
	AuthorizationType authroizationType ;
	String httpMethod = null;
	URI resourceUri = null;	
	List<RequestHeader> httpHeaders;
	
	public AuthenticationRequest( )	
	{
		this.authroizationType = AuthorizationType.V1HMAC;
		this.apiKeyId = APIKeysPage.APIKey; //"f8a2dea0a45989cd";
		this.secretApiKey = APIKeysPage.SeceretKey; //"xTuPyrrNs0pYF60QzL3KgmIFNatDaerg0WaRCDSx+Ls=";		
	}
	
	public AuthenticationRequest( AuthorizationType _authorizationType, String _apiKeyId,String _secretApiKey)	
	{
		this.authroizationType = _authorizationType;
		this.apiKeyId = _apiKeyId;
		this.secretApiKey = _secretApiKey;		
	}
	
	public String getAutheticationSignature(Method _method,URI _resourceUri, List<RequestHeader> _httpHeaders)
	{
		httpMethod = _method.toString();
		resourceUri = _resourceUri;
		httpHeaders = _httpHeaders;
		
		//Generate Authentication Signature		
		APIAuthenticator auth = new APIAuthenticator(authroizationType, apiKeyId, secretApiKey);
		
		String authenticationSignature = auth.createAuthenticationSignature(httpMethod, resourceUri, httpHeaders);
		
		Log.message("The Created Authentication Signature  :"+authenticationSignature);
		
		return authenticationSignature;
	}

}
