/**
 * 
 */
package com.halialab.demo.util.exception;

/**
 * @author Janath
 *
 */
public class HttpException extends RuntimeException {

	  private static final long serialVersionUID = 1698552935860849994L;
	  
	  private final int httpStatus;

	  public HttpException(String message, Throwable cause, int httpStatus) {
	    super(message, cause);
	    this.httpStatus = httpStatus;
	  }

	  public HttpException(String message, int httpStatus) {
	    this(message, null, httpStatus); 
	  }

	  public int getHttpStatus() {
	    return httpStatus;
	  }


	}