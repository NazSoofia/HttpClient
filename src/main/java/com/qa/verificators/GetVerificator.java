package com.qa.verificators;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import com.qa.client.BaseClass;
import com.qa.client.TestUtil;

public class GetVerificator extends BaseClass {


	
	public static void verifyResponseContainingArrayValues(CloseableHttpResponse httpResponse) throws Exception {

		prop.load(new FileInputStream(responseFile));
		
		JSONObject jsonResponse = httpToJsonResponse(httpResponse);

		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), RESPONSE_STATUS_CODE_200);
		assertionsOfPages(jsonResponse, "page", "per_page", "total", "total_pages");
		
		int sizeOfArray= ((JSONArray) jsonResponse.get("data")).length();	
		
		String[] id = prop.get("id").toString().split("#");
		String[] first_name = prop.get("first_name").toString().split("#");
		String[] last_name = prop.get("last_name").toString().split("#");
		String[] avatar = prop.get("avatar").toString().split("#");
		
		for (int i = 0; i < sizeOfArray; i++) {
			
			Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data["+i+"]/id"), id[i]);
			Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data["+i+"]/first_name"), first_name[i]);
			Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data["+i+"]/last_name"), last_name[i]);
			Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data["+i+"]/avatar"), avatar[i]);
		}
		}
	
	public static void verifyIndividualResponseOfUser(CloseableHttpResponse httpsResponse,String id,String first_name , String last_name, String avatar) throws Exception {
		
		JSONObject jsonResponse = httpToJsonResponse(httpsResponse);
		Assert.assertEquals(httpsResponse.getStatusLine().getStatusCode(), RESPONSE_STATUS_CODE_200);
		assertionsOfUserInfo(jsonResponse, id, first_name, last_name, avatar);
	}
	
	
	
	public static void verifyInvalidResponse(CloseableHttpResponse httpResponse) throws Exception {
	
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), RESPONSE_STATUS_CODE_404);
		Assert.assertEquals(httpToJsonResponse(httpResponse).toString(), "{}");
	}
	
	
	
	public static void verifyListResources(CloseableHttpResponse httpResponse) throws Exception{
		
		prop.load(new FileInputStream(responseFile));
		
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), RESPONSE_STATUS_CODE_200);

		JSONObject jsonResponse = httpToJsonResponse(httpResponse);
		assertionsOfPages(jsonResponse, "page2", "per_page2", "total2", "total_pages2");
		
        int sizeOfArray= ((JSONArray) jsonResponse.get("data")).length();	
		
		String[] id = prop.get("id2").toString().split(",");
		String[] name = prop.get("name2").toString().split(",");
		String[] year = prop.get("year2").toString().split(",");
		String[] color = prop.get("color2").toString().split(",");
		String[] pantone_value = prop.get("pantone_value2").toString().split(",");
		
		for (int i = 0; i < sizeOfArray; i++) {
			
			Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data["+i+"]/id"), id[i]);
			Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data["+i+"]/name"), name[i]);
			Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data["+i+"]/year"), year[i]);
			Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data["+i+"]/color"), color[i]);
			Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data["+i+"]/pantone_value"), pantone_value[i]);
		}
	}
	
	
public static void verifySingleResource(CloseableHttpResponse httpsResponse, String id, String name, String year, String color, String pantone_value)
		throws Exception {
		
		JSONObject jsonResponse = httpToJsonResponse(httpsResponse);
		Assert.assertEquals(httpsResponse.getStatusLine().getStatusCode(), RESPONSE_STATUS_CODE_200);
		assertionsOfSingleResource(jsonResponse, id, name, year, color, pantone_value);
	}
	
	
	public static void assertionsOfUserInfo(JSONObject jsonResponse, String id,String first_name , String last_name, String avatar) throws Exception {
		
		prop.load(new FileInputStream(responseFile));
		
		Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data/id"), prop.get(id));
		Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data/first_name"), prop.get(first_name));
		Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data/last_name"), prop.get(last_name));
		Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data/avatar"), prop.get(avatar));	
      
	}
	

  public static void assertionsOfSingleResource(JSONObject jsonResponse, String id, String name, String year, String color, String pantone_value)
		  throws Exception {

		prop.load(new FileInputStream(responseFile));
		
		Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data/id"), prop.get(id));
		Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data/name"), prop.get(name));
		Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data/year"), prop.get(year));
		Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data/color"), prop.get(color));	
		Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/data/pantone_value"), prop.get(pantone_value));	
	}

	public static void assertionsOfPages(JSONObject jsonResponse, String page,String per_page , String total, String total_pages) throws Exception {
	
		prop.load(new FileInputStream(responseFile));
		
		Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/page"),prop.get(page));
		Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/per_page"), prop.get(per_page));
		Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/total"), prop.get(total));
		Assert.assertEquals(TestUtil.getValueByJPath(jsonResponse, "/total_pages"), prop.get(total_pages));
	}

}	
