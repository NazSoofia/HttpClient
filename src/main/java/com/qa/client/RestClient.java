package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	
	//GET Method
	public static CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		
		System.out.println(httpGet.getRequestLine().getMethod() + " : " + httpGet.getURI());
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		
		System.out.println("Status Code : " + httpResponse.getStatusLine().getStatusCode());
		
		return httpResponse;
	}
	
	
	public static CloseableHttpResponse getWithHeaders(String url, HashMap<String, String> map) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		
		System.out.println(httpget.getMethod() +  " : " + httpget.getURI());
		
		for (Map.Entry<String, String> map1 : map.entrySet()) {
			httpget.addHeader(map1.getKey(), map1.getValue());
		}
		
		CloseableHttpResponse httpResponse = httpClient.execute(httpget);

		System.out.println("Status Code : " + httpResponse.getStatusLine().getStatusCode());
		
		return httpResponse;
		
	}
	
	public static CloseableHttpResponse post(String url, String entity, HashMap<String, String> map ) throws Exception {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(entity));
		
		System.out.println(post.getRequestLine().getMethod() + " : " + post.getURI());
		System.out.println("Payload : " +  entity);
		for (Map.Entry<String, String> map1 : map.entrySet()) {
			post.addHeader(map1.getKey(), map1.getValue());	
		}
		
		System.out.println("Request Headers : " + map);
		CloseableHttpResponse httpResponse = httpClient.execute(post);
		
		System.out.println("Status Code : " + httpResponse.getStatusLine().getStatusCode());
		return httpResponse;	
	}
	
	
	public static CloseableHttpResponse put(String url, String entity, HashMap<String, String> map) throws Exception {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPut put = new HttpPut(url);
		put.setEntity(new StringEntity(entity));
		
		System.out.println(put.getRequestLine().getMethod() + " : " + put.getURI() );
		
		for (Map.Entry<String, String> map1 : map.entrySet()) {
			put.addHeader(map1.getKey(), map1.getValue());
		}
		System.out.println("Request Headers : " + map);
		CloseableHttpResponse httpResponse =  httpClient.execute(put);
		
		System.out.println("Status Code : " + httpResponse.getStatusLine().getStatusCode());
		return httpResponse;	
	}
	
	
public static CloseableHttpResponse delete(String url) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpDelete httpDelete = new HttpDelete(url);
		
		System.out.println(httpDelete.getRequestLine().getMethod() + " : " + httpDelete.getURI());
		CloseableHttpResponse httpResponse = httpClient.execute(httpDelete);
		
		System.out.println("Status Code : " + httpResponse.getStatusLine().getStatusCode());
		
		return httpResponse;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
