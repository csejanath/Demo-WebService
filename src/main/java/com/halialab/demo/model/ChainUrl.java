/**
 * 
 */
package com.halialab.demo.model;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Janath
 *
 */
public class ChainUrl {
	  
	  private String protocol = "http";
	  private final String host;
	  private final int port;
	  private final String chainName;
	  private final String username;
	  private final String password;
	  
	  public ChainUrl(String host, int port, String chainName, String username, String password) {
	    super();
	    this.host = host;
	    this.port = port;
	    this.chainName = chainName;
	    this.username = username;
	    this.password = password;
	  }

	  public String getProtocol() {
	    return protocol;
	  }

	  public void setProtocol(String protocol) {
	    this.protocol = protocol;
	  }

	  public String getHost() {
	    return host;
	  }

	  public int getPort() {
	    return port;
	  }

	  public String getChainName() {
	    return chainName;
	  }

	  public String getUsername() {
	    return username;
	  }

	  public String getPassword() {
	    return password;
	  }
	  
	  public URI getAsUri() throws URISyntaxException {
	    return  new URI(getProtocol() + "://" + getHost() + ":" + getPort());
	  }
	  
	}
