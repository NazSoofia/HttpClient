package com.qa.tests;

import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.Test;

import com.qa.client.BaseClass;
import com.qa.client.RestClient;
import com.qa.pojo.Registration;
import com.qa.pojo.User;
import com.qa.verificators.PostVerificator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PostApiTests extends BaseClass{

    User user;
    CloseableHttpResponse httpResponse;
    Registration registration;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void createUser() throws Exception {
		
		user = new User("morpheus","leader");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Content-Type","application/json");
		map.put("Accept","application/json");
		
		String entity = mapper.writeValueAsString(user);
	    httpResponse = RestClient.post(BaseClass.setUp("post_endpoint"), entity, map);
		
		PostVerificator.verifyUserCreated(httpResponse,user);
		
	}
	
	@Test
	public void registerValidUser() throws Exception{
		
		registration = new Registration("sydney@fife","pistol");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Content-Type","application/json");
		map.put("Accept","application/json");
		
		String entity = mapper.writeValueAsString(registration);
	    httpResponse = RestClient.post(BaseClass.setUp("register_endpoint"), entity, map);
	    
	    PostVerificator.verifyValidRegistration(httpResponse, registration);
	}
	
	
	@Test
	public void loginValidUser() throws Exception{

		registration = new Registration("peter@klaven","cityslicka");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Content-Type","application/json");
		map.put("Accept","application/json");
		
		String entity = mapper.writeValueAsString(registration);
	    httpResponse = RestClient.post(BaseClass.setUp("login_endpoint"), entity, map);
	    
	    PostVerificator.verifyValidLogin(httpResponse, registration);
		
	}
	
	
	@Test
	public void registerInvalidUser() throws Exception {
		
		registration = new Registration("sydney@fife",null);
		String entity = mapper.writeValueAsString(registration);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Content-Type","application/json");
		map.put("Accept","application/json");
		
		httpResponse = RestClient.post(BaseClass.setUp("register_endpoint"), entity, map);
		PostVerificator.verifyInvalidRegistration(httpResponse, "pwdError");
		
	}	
	
	
	@Test
	public void loginInvalidUser() throws Exception {
		
		registration = new Registration(null , "pistol");
		String entity = mapper.writeValueAsString(registration);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Content-Type","application/json");
		map.put("Accept","application/json");
		
		httpResponse = RestClient.post(BaseClass.setUp("login_endpoint"), entity, map);
		PostVerificator.verifyInvalidRegistration(httpResponse, "mailError");
		
	}	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
}
