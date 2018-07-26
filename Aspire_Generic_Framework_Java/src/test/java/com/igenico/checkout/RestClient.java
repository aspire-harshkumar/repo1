package com.igenico.checkout;

import java.util.List;
import com.genric.support.*;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.Method;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.relevantcodes.extentreports.ExtentTest;

enum httpMethod {
	Get,
	Put,
	post,
	Delete
}

public class RestClient {
	
	//private static Logger logger = LoggerFactory.getLogger(RestClient.class);	
	public String BaseURI;
	public String RequestBody;	
	public Method httpMethod;
	public List<RequestHeader> httpHeaders;
	public RestClient(String _uRI){
		BaseURI = _uRI;
		RequestBody = "";		
		//httpMethod = httpMethod.GET;
	}
	public RestClient(String _uRI,String _requestBody,Method _method,List<RequestHeader> _requestHeaders){
		BaseURI = _uRI;
		RequestBody = _requestBody;		
		httpMethod = _method;
		httpHeaders = _requestHeaders;
	}	
	public Response sendRequest(ExtentTest extentedReport)
	{   
	   extentedReport = Log.testCaseInfo("", "Calling RestAPI checkout service to generate payment redirect URL!!", "", "");

		// Specify the base URL to the RESTful web service
		RestAssured.baseURI =  BaseURI;
		Log.messageExtentReport("Request URI is =>  " + RestAssured.baseURI,extentedReport);
		Log.message("Request URI is =>  " + RestAssured.baseURI);
		
		//Create Request
		RequestSpecification httpRequest = RestAssured.given().relaxedHTTPSValidation();
		
		//Set header values
		Log.message("Request Headers Are:");
		Log.messageExtentReport("Request Headers Are:" ,extentedReport);
		
		for(RequestHeader reqHeader : httpHeaders){				
			Log.message(reqHeader.toString());
			Log.messageExtentReport(reqHeader.toString() ,extentedReport);
			httpRequest.header(reqHeader.getName(),reqHeader.getValue());	
		}
		
		//Set request Body message	
		httpRequest.body(RequestBody);	
		Log.messageExtentReport("Request Body is =>  " + RequestBody ,extentedReport);
		Log.message("Request Body is =>  " + RequestBody);
		
		// Make a request to the server by specifying the method Type and the method URL.
		Response response = (Response) httpRequest.post();
		
		//Response body
		String responseBody = response.getBody().asString();
		Log.messageExtentReport("Response Body is =>  " + responseBody ,extentedReport);
		Log.message("Response Body is =>  " + responseBody);
		
		return response;
	}
	
	
	/*
	 * Method to fetch Value from the response received
	 */	
	public String getValueFromResponse(Response response, String value) 
	{
		String valueInResponse = null;
		JsonPath path = response.jsonPath();
		try{
			valueInResponse = path.get(value);	
		}catch(Exception e){
			e.printStackTrace();
		}		
		return valueInResponse;
	}

}
