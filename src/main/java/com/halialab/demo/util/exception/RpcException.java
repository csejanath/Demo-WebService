/**
 * 
 */
package com.halialab.demo.util.exception;

import com.halialab.demo.model.RpcError;

/**
 * @author Janath
 *
 */
public class RpcException extends RuntimeException {

	  private static final long serialVersionUID = -3736055429587978233L;
	  
	  private final RpcError rpcError;
	  
	  public RpcException(RpcError error, Throwable cause) {
	    super(error.toString(), cause);
	    this.rpcError = error;
	  }

	  public RpcException(String message) {
	    super(message);
	    this.rpcError = null;
	  }

	  public RpcError getRpcError() {
	    return rpcError;
	  }

	}
