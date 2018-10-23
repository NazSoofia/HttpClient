package com.qa.verificators;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.BaseClass;
import com.qa.pojo.Registration;
import com.qa.pojo.User;
import com.qa.tests.PostApiTests;

public class PostVerificator extends BaseClass{
	
	static ObjectMapper mapper = new ObjectMapper();

	public static void verifyUserCreated(CloseableHttpResponse httpResponse,User user) throws Exception {
	
		String stringResponse = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		System.out.println("Response : " + stringResponse);
	
		User actualResponse = mapper.readValue(stringResponse, User.class);
		
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), RESPONSE_STATUS_CODE_201);
		Assert.assertEquals(actualResponse.getName(), user.getName());
		Assert.assertEquals(actualResponse.getJob(), user.getJob());
		Assert.assertNotNull(actualResponse.getId());
		Assert.assertNotNull(actualResponse.getCreatedAt());
	}
	
	public static void verifyValidRegistration(CloseableHttpResponse httpResponse, Registration register) throws Exception {
		
		String stringResponse = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		System.out.println("Response : " + stringResponse);
		
		Registration actualResponse = mapper.readValue(stringResponse, Registration.class);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), RESPONSE_STATUS_CODE_201);
		Assert.assertEquals(actualResponse.getToken() , "QpwL5tke4Pnpja7X");
		
	}
	
   public static void verifyValidLogin(CloseableHttpResponse httpResponse, Registration register) throws Exception {
		
		String stringResponse = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		System.out.println("Response : " + stringResponse);
		
		Registration actualResponse = mapper.readValue(stringResponse, Registration.class);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), RESPONSE_STATUS_CODE_200);
		Assert.assertEquals(actualResponse.getToken() , "QpwL5tke4Pnpja7X");
		
	}
   
   
   public static void verifyInvalidRegistration(CloseableHttpResponse httpResponse, String error) throws Exception {
	 
	    prop.load(new FileInputStream(responseFile));
	    
	    String stringResponse = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		System.out.println("Response : " + stringResponse);
		
		Registration actualResponse = mapper.readValue(stringResponse, Registration.class);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), RESPONSE_STATUS_CODE_400);
		Assert.assertEquals(actualResponse.getError() , prop.get(error));
   }
    
}
