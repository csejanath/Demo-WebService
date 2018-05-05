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
	
	public static RpcResult issue(ChainUrl chainUrl, String addressFrom, String assetID, Integer qty, Boolean verbose,
			Integer count, Integer start) throws IOException, URISyntaxException {
		return invokeRpc(chainUrl, BasicMethods.COMMAND_CREATE_ASSETS, addressFrom, assetID, qty);
	}
	
	public static RpcResult getBalance(ChainUrl chainUrl, String addressFrom) throws IOException, URISyntaxException {
		return invokeRpc(chainUrl, BasicMethods.COMMAND_GET_ADDRESS_BALANCE, addressFrom);
	}
	
	public static RpcResult getMultiBalance(ChainUrl chainUrl, String addressFrom, String assetID) throws IOException, URISyntaxException {
		return invokeRpc(chainUrl, BasicMethods.COMMAND_GET_MULTI_BALANCE, addressFrom, assetID);
	}
	
	public static RpcResult transferAssetFrom(ChainUrl chainUrl, String addressFrom, String addressTo, String assetID, Integer qty) throws IOException, URISyntaxException {
		return invokeRpc(chainUrl, BasicMethods.COMMAND_SEND_ASSETS_FROM, addressFrom, addressTo, assetID, qty);
	}
	
	public static RpcResult transferAsset(ChainUrl chainUrl, String addressTo, String assetID, Integer qty) throws IOException, URISyntaxException {
		return invokeRpc(chainUrl, BasicMethods.COMMAND_SEND_ASSETS, addressTo, assetID, qty);
	}

}
