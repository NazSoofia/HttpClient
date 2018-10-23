package com.qa.verificators;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.BaseClass;
import com.qa.pojo.User;


public class PutVerificator extends BaseClass {
	
	static ObjectMapper mapper = new ObjectMapper();
			
	public static void verifyPutUser(CloseableHttpResponse httpResponse, User user) throws Exception {
		
		String stringResponse = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		System.out.println("Response : " + stringResponse);
		
		User actualResponse = mapper.readValue(stringResponse, User.class);
		
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), RESPONSE_STATUS_CODE_200);
		Assert.assertEquals(actualResponse.getName(), user.getName());
		Assert.assertEquals(actualResponse.getJob(), user.getJob());
		Assert.assertNotNull(actualResponse.getUpdatedAt());
		Assert.assertNull(actualResponse.getCreatedAt());
	
	}
}
