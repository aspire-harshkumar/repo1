package com.igenico.checkout;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.json.JSONArray;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.igenico.checkout.CheckoutRequestFormat.AmountOfMoney;
import com.igenico.checkout.CheckoutRequestFormat.BillingAddress;
import com.igenico.checkout.CheckoutRequestFormat.Checkout;
import com.igenico.checkout.CheckoutRequestFormat.Customer;
import com.igenico.checkout.CheckoutRequestFormat.HostedCheckoutSpecificInput;
import com.igenico.checkout.CheckoutRequestFormat.Order;
import com.jayway.restassured.http.Method;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.ExtentTest;


public class CheckOutRequest 
{
	URI resourceUri = null;
	List<RequestHeader> httpHeaders = null;	
	Method _method = null;
	String _requestBody = null;
	public Response response = null;	
	public RestClient restClient = null;
    JSONArray jsonArray = new JSONArray(); 

	
	/*
	 * Chekcout constructor
	 */
	public CheckOutRequest(ExtentTest extentedReport)
	{
		resourceUri = URI.create("https://api-sandbox.globalcollect.com/v1/2270/hostedcheckouts");
		_method = Method.POST;
		httpHeaders = getRequestHeaders();
		_requestBody = getCheckoutServiceRequestBody();
	}
	
	public void callCheckoutService(ExtentTest extentedReport)
	{		
		//Get Authentication Signature
		String authenticationSignature = getAuthenticationSignature(_method,resourceUri,httpHeaders);
		
		//Add Authentication signature to the headers for checkout service
		httpHeaders.add(0, new RequestHeader("Authorization", authenticationSignature));	
		
		//Make service request
		restClient = new RestClient(resourceUri.toString(), _requestBody, _method, httpHeaders);
		response = restClient.sendRequest(extentedReport);
	}	
	
	public String getValueFromResponse(String value)
	{
		return restClient.getValueFromResponse(this.response, value);
	}
	
	private String getAuthenticationSignature(Method method,URI uri, List<RequestHeader> headers )
	{
		AuthenticationRequest authRequest = new AuthenticationRequest();
		return authRequest.getAutheticationSignature(method, uri, headers);		
	}
	
	/**
	 * create pojo class for checkout request 
	 * 
	 */	
	private String getCheckoutServiceRequestBody(){
		
		ObjectMapper obmap = new ObjectMapper();
		obmap.enable(SerializationFeature.INDENT_OUTPUT);
		String _requestBody = null;
		
		try {
			 _requestBody = obmap.writeValueAsString(getCheckoutDefaultValue());
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}	
		return _requestBody;
	}
		
	
	/**
	 * Set checkout request value 
	 * 
	 */	
	public Checkout getCheckoutDefaultValue()
	{
		CheckoutRequestFormat ch =new CheckoutRequestFormat();
		
		AmountOfMoney amo = ch.new AmountOfMoney();
		amo.setAmount(100);
		amo.setCurrencyCode("EUR");
		
		BillingAddress bil = ch.new BillingAddress();
		bil.setCountryCode("NL");
		
		Customer cus = ch.new Customer();
		cus.setMerchantCustomerId(2281);
		cus.setBillingAddress(bil);
		
		Order ord = ch.new Order();		
		ord.setAmountOfMoney(amo);
		ord.setCustomer(cus);
				
		HostedCheckoutSpecificInput hos = ch.new HostedCheckoutSpecificInput();
		hos.setVariant("testVariant");
		hos.setLocale("en_GB");
		
		Checkout checkout = ch.new Checkout();		
		checkout.setHostedCheckoutSpecificInput(hos);
		checkout.setOrder(ord);
		
		return checkout;
	}
	
	/**
	 * Set Request headers here 
	 * 
	 */	
	private List<RequestHeader> getRequestHeaders(){
		
		Date date = new Date();
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		date = cal.getTime();
		String DATE_FORMAT = "EEE, dd MMM yyyy HH:MM"; // Tue, 17 Jul 2018 13:06:36 GMT
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	   
		List<RequestHeader> requestHeader = new ArrayList<RequestHeader>();		
		requestHeader.add(new RequestHeader("X-GCS-RequestId", "1cc6daff-a305-4d7b-94b0-c580fd5ba6b4"));	
		requestHeader.add(new RequestHeader("Content-Type",	"application/json; charset=utf-8"));
		requestHeader.add(new RequestHeader("X-GCS-MessageId", "6480071e-039d-4dca-a966-4ce3c1bc201b"));		
		requestHeader.add(new RequestHeader("Date",sdf.format(date)+":36 GMT"));
		//requestHeader.add(new RequestHeader("Cache-Control","no-cache"));
		return requestHeader;
	}
	
}


