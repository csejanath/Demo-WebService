/**
 * 
 */
package com.halialab.demo.util.enums;

import com.halialab.demo.model.BaseRpcResult;

/**
 * @author Janath
 *
 */
public interface ChainMethod {

	  public String getName();
	  
	  public Class<? extends BaseRpcResult<?>> getResultClass();
	}

