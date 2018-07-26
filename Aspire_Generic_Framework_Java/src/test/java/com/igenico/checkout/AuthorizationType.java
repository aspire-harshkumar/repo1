package com.igenico.checkout;

public enum AuthorizationType {

	V1HMAC("v1HMAC");

	private final String signatureString;

	private AuthorizationType(String signatureString){
		this.signatureString = signatureString;
	}

	String getSignatureString() {
		return signatureString;
	}
	
}
