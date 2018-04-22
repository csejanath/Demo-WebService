/**
 * 
 */
package com.halialab.demo.util;

import static com.halialab.demo.util.ChainUtils.invokeRpc;

import java.io.IOException;
import java.net.URISyntaxException;

import com.halialab.demo.model.ChainUrl;
import com.halialab.demo.model.RpcResult;
import com.halialab.demo.util.enums.BasicMethods;
import com.halialab.demo.util.enums.StreamMethods;

/**
 * @author Janath
 *
 */
public class AssetUtils {
	
	  public static RpcResult issue(ChainUrl chainUrl, String addressFrom, String assetID, Integer qty, Boolean verbose, Integer count, Integer start) throws IOException, URISyntaxException {
		    return invokeRpc(chainUrl, 
		    	BasicMethods.COMMAND_CREATE_ASSETS, 
		    	addressFrom,
		    	assetID,
		    	qty);
		  }

}
