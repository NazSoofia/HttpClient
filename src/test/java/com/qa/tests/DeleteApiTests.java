package com.qa.tests;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.client.BaseClass;
import com.qa.client.RestClient;

public class DeleteApiTests extends BaseClass{

	CloseableHttpResponse httpResponse;
	
	@Test
	public void deteleUser() throws Exception {

		httpResponse = RestClient.delete(BaseClass.setUp("user2_endpoint"));
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),RESPONSE_STATUS_CODE_204 );
	
		JSONObject jsonResponse = new JSONObject(httpResponse);
		System.out.println(jsonResponse);
		Assert.assertTrue(jsonResponse.isEmpty());
	}
}
