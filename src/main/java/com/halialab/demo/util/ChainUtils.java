/**
 * 
 */
package com.halialab.demo.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.halialab.demo.model.ChainRpcParams;
import com.halialab.demo.model.ChainUrl;
import com.halialab.demo.model.RpcResult;
import com.halialab.demo.util.enums.BasicMethods;
import com.halialab.demo.util.enums.ChainMethod;
import com.halialab.demo.util.exception.RpcException;

/**
 * @author Janath
 *
 */
public class ChainUtils {
	  
	  private ChainUtils() {
	    // do nothing
	  }
	    
	  public static RpcResult getAddressTransaction(ChainUrl chainUrl, String address, String txId) throws IOException, URISyntaxException {
	    return invokeRpc(chainUrl,
	        BasicMethods.COMMAND_GET_ADDRESS_TRANSACTION,
	        // 1st: address
	        // 2nd: txId
	        address,
	        txId
	        );
	  }

	  public static RpcResult listStreamKeyItems(ChainUrl chainUrl, String key, String streamName) throws IOException, URISyntaxException {
	    return invokeRpc(chainUrl,
	        BasicMethods.COMMAND_LIST_STREAM_KEY_ITEMS, 
	        // 1st: stream
	        // 2nd: key
	        // 3rd: verbose
	        streamName,
	        key,
	        true,
	        Integer.MAX_VALUE);
	  }

	  public static RpcResult sendMetaDataFrom(ChainUrl chainUrl, String fromAddress, String toAddress, String quantity, String metadata)
	      throws IOException, URISyntaxException {
	    return invokeRpc(chainUrl,
	        BasicMethods.COMMAND_SEND_METADATA_FROM,
	        // 1st: from address
	        // 2nd: to address
	        // 3rd: amount/qty
	        // 4th: hex data
	        fromAddress,
	        toAddress,
	        quantity,
	        HexUtils.encode(metadata)
	        );
	  }
	  
	  public static RpcResult invokeRpc(ChainUrl chainUrl, ChainMethod chainMethod, Object... params) throws IOException, URISyntaxException {
	    return invokeRpc(chainUrl, chainMethod, new ArrayList<Object>(Arrays.asList(params)));
	  }
	  
	  public static RpcResult invokeRpc(ChainUrl chainUrl, ChainMethod chainMethod, List<Object> params) throws IOException, URISyntaxException {
	    ChainRpcParams chainRpcParam = new ChainRpcParams(chainUrl.getChainName());
	    chainRpcParam.setMethod(chainMethod.getName());
	    chainRpcParam.setParams(params);
	    return invokeRpc(chainUrl, chainMethod, chainRpcParam);
	  }
	  
	  public static RpcResult invokeRpc(ChainUrl chainUrl, ChainMethod chainMethod, ChainRpcParams rpcParams) throws IOException, URISyntaxException {
	    for (int i = rpcParams.getParams().size() - 1; i>=0; i--) {
	      if (rpcParams.getParams().get(i) != null) {
	        break;
	      }
	      rpcParams.getParams().remove(i);
	    }
	    String stringRpcParam = GsonUtils.toJson(rpcParams);
	    String result = HttpUtils.post(chainUrl.getAsUri(), chainUrl.getUsername(), chainUrl.getPassword(), stringRpcParam);
	    RpcResult rpcResult = GsonUtils.fromJson(result, RpcResult.class);
	    if (rpcResult.getError() != null) {
	      throw new RpcException(rpcResult.getError(), null);
	    }
	    return rpcResult;
	  }

	}

