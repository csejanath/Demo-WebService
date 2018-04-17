/**
 * 
 */
package com.halialab.demo.util;

import java.io.IOException;
import java.net.URI;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Janath
 *
 */
public class HttpUtils {
	  
	  private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

	  // convert to Spring bean (or maybe not?)
	  private static final PoolingHttpClientConnectionManager CONNECTION_MANAGER = new PoolingHttpClientConnectionManager();
	  private static final CloseableHttpClient HTTP_CLIENT;
	  private static final CredentialsProvider CREDENTIALS_PROVIDER = new BasicCredentialsProvider();
	  
	  static {
	    // Increase max total connection to 200
	    CONNECTION_MANAGER.setMaxTotal(200);
	    // Increase default max connection per route to 20
	    CONNECTION_MANAGER.setDefaultMaxPerRoute(20);
	    
	    HTTP_CLIENT = HttpClients.custom()
	            .setConnectionManager(CONNECTION_MANAGER)
	            .build();
	  }
	  
	  private HttpUtils() {
	    // do nothing
	  }

	  /**
	   * Bare HTTP post with connection manager.
	   * @param url the URL to post to
	   * @param toPost the data to post
	   * @return the returned response in String
	   * @throws ClientProtocolException
	   * @throws IOException
	   */
	  public static String post(URI uri, String username, String password, String toPost) throws IOException {
	    HttpPost httpPost = new HttpPost(uri);
	    httpPost.setEntity(new StringEntity(toPost));
	    LOGGER.info("Posting: " + toPost);
	    
	    try (CloseableHttpResponse response = HTTP_CLIENT.execute(
	        httpPost, createContext(uri, username, password))) {
	      return EntityUtils.toString(response.getEntity());
	    }
	  }
	  
	  private static HttpClientContext createContext(URI uri, String username, String password) {
	    HttpClientContext context = HttpClientContext.create();
	    if (username != null) {
	      CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
	      credentialsProvider.setCredentials(
	          new AuthScope(uri.getHost(), uri.getPort()), 
	          new UsernamePasswordCredentials(username, password));
	      context.setCredentialsProvider(credentialsProvider);
	    }
	    return context;
	  }
	  
	  public static void addCredentials(String host, int port, String username, String password) {
	    CREDENTIALS_PROVIDER.setCredentials(
	          new AuthScope(host, port), 
	          new UsernamePasswordCredentials(username, password));

	  }
	}

