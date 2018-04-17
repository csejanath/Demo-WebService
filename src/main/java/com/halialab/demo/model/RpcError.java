/**
 * 
 */
package com.halialab.demo.model;

/**
 * @author Janath
 *
 */
public class RpcError {
	  private Double code;
	  private String message;

	  public Double getCode() {
	    return code;
	  }

	  public void setCode(Double code) {
	    this.code = code;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }
	  
	  @Override
	  public String toString() {
	    return "Error while doing transaction with the blockchain. Code: " + code + " Message: " + message; 
	  }

	}

