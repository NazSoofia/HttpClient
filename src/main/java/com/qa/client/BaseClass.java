package com.qa.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class BaseClass {

	public final static String responseFile= "./src/main/java/com/qa/config/response.properties";
	public final String configFile= "./src/main/java/com/qa/config/config.properties";
	private static Properties properties;
	public static Properties prop = new Properties();
	public static final int RESPONSE_STATUS_CODE_200 = 200;
	public static final int RESPONSE_STATUS_CODE_201 = 201;
	public static final int RESPONSE_STATUS_CODE_400 = 400;
	public static final int RESPONSE_STATUS_CODE_404 = 404;
	public static final int RESPONSE_STATUS_CODE_204 = 204;
	
	public BaseClass() {
	
	    properties = new Properties();
		try {
			properties.load(new FileInputStream(configFile));
		} catch (Exception e) {
			e.printStackTrace();
		}}
	
		
   public static String setUp(String endpoint) {
		
		String serviceUrl = properties.getProperty("url");
		String endpointUrl = properties.getProperty(endpoint);
		
		String uri = serviceUrl + endpointUrl;
		return uri;
	}
   
   
   public static JSONObject httpToJsonResponse(CloseableHttpResponse httpsResponse) throws ParseException, IOException {

	    String stringResponse = EntityUtils.toString(httpsResponse.getEntity(),"UTF-8");
		JSONObject jsonResponse = new JSONObject(httpsResponse);
		System.out.println("Response : " + jsonResponse);
		return jsonResponse;
	}
}
