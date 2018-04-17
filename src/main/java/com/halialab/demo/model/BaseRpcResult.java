/**
 * 
 */
package com.halialab.demo.model;

import java.util.List;
import java.util.Map;

/**
 * @author Janath
 *
 */
public class BaseRpcResult<T> {

	  private T result;
	  private RpcError error;
	  private String id;

	  public T getResult() {
	    return result;
	  }

	  public void setResult(T result) {
	    this.result = result;
	  }
	  
	  public String getResultAsString() {
	    return (String) result;
	  }
	  
	  @SuppressWarnings("unchecked")
	  public Map<String, Object> getResultAsMap() {
	    return (Map<String, Object>) result;
	  }
	  
	  @SuppressWarnings("unchecked")
	  public List<Map<String, Object>> getResultAsList() {
	    return (List<Map<String, Object>>) result;
	  }

	  public RpcError getError() {
	    return error;
	  }

	  public void setError(RpcError error) {
	    this.error = error;
	  }

	  public String getId() {
	    return id;
	  }

	  public void setId(String id) {
	    this.id = id;
	  }
	  
	  @Override
	  public String toString() {
	    return id + " " + result + " " + error;
	  }
	}

