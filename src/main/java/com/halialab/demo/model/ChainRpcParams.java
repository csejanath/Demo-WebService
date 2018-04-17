/**
 * 
 */
package com.halialab.demo.model;

import java.util.List;
import java.util.UUID;

import com.google.gson.annotations.SerializedName;

/**
 * @author Janath
 *
 */
public class ChainRpcParams {

	  private String id;
	  private String method;
	  @SerializedName("custom_naming")
	  private String chainName;
	  private List<Object> params;
	  
	  public ChainRpcParams() {
	    this(System.getProperty("multichain.chainName"));    
	  }
	  
	  public ChainRpcParams(String chainName) {
	    id = UUID.randomUUID().toString();
	    this.chainName = chainName;    
	  }

	  public String getId() {
	    return id;
	  }

	  public void setId(String id) {
	    this.id = id;
	  }

	  public String getMethod() {
	    return method;
	  }

	  public void setMethod(String method) {
	    this.method = method;
	  }

	  public String getChainName() {
	    return chainName;
	  }

	  public void setChainName(String chainName) {
	    this.chainName = chainName;
	  }

	  public List<Object> getParams() {
	    return params;
	  }

	  public void setParams(List<Object> params) {
	    this.params = params;
	  }

	}

