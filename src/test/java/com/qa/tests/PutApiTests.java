package com.qa.tests;

import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.BaseClass;
import com.qa.client.RestClient;
import com.qa.pojo.User;
import com.qa.verificators.PutVerificator;

public class PutApiTests extends BaseClass{

	CloseableHttpResponse httpResponse;
	User user;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void putUser() throws Exception {
		
		user = new User("morpheus", "zion resident");
		String entity = mapper.writeValueAsString(user);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Content-Type", "application/json");
		map.put("Accept", "application/json");
		
		System.out.println("Request Headers : " + entity);
		httpResponse = RestClient.put(BaseClass.setUp("user2_endpoint"), entity, map);
		
		PutVerificator.verifyPutUser(httpResponse, user);
	}
}
