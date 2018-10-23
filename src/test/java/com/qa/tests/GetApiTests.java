package com.qa.tests;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.Test;

import com.qa.client.BaseClass;
import com.qa.client.RestClient;
import com.qa.verificators.GetVerificator;

public class GetApiTests extends BaseClass{

	CloseableHttpResponse httpResponse;
	
	@Test
	public void getUsers() throws Exception {

		httpResponse = RestClient.get(BaseClass.setUp("users_endpoint"));
        Header[] headers = httpResponse.getAllHeaders();
		
		Map<String, String> map = new HashMap<String, String>();
		for (Header header : headers) {
			map.put(header.getName(), header.getValue());
		}
		
		System.out.println("Headers : " + map);
	    GetVerificator.verifyResponseContainingArrayValues(httpResponse);
	}    
	
	
	
	@Test
	public void getUserWithValidId() throws Exception {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Content-Type","application/json");
		map.put("Accept","application/json");
		
		httpResponse = RestClient.getWithHeaders(BaseClass.setUp("user2_endpoint"), map);
		GetVerificator.verifyIndividualResponseOfUser(httpResponse,"id1","first_name1","last_name1","avatar1");
			
	}
	
	
	@Test
	public void getUserWithInvalidId() throws Exception {
		httpResponse = RestClient.get(BaseClass.setUp("user23_endpoint"));	
		GetVerificator.verifyInvalidResponse(httpResponse);
	}
	
	@Test
	public void getListResources() throws Exception {
	    httpResponse = RestClient.get(BaseClass.setUp("listresource_endpoint"));
	    GetVerificator.verifyListResources(httpResponse);   
	}
	
	
	@Test
	public void getSingleResource() throws Exception {
	    httpResponse = RestClient.get(BaseClass.setUp("singleresource_endpoint"));
	    GetVerificator.verifySingleResource(httpResponse, "id3", "name3", "year3", "color3", "pantone_value3");
	    
	}
	
	@Test
	public void getInvalidResource() throws Exception {
	    httpResponse = RestClient.get(BaseClass.setUp("invalid_endpoint")); 
	    GetVerificator.verifyInvalidResponse(httpResponse);
	    
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
