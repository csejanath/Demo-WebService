/**
 * 
 */
package com.halialab.demo.util.enums;

import com.halialab.demo.model.BaseRpcResult;
import com.halialab.demo.model.RpcResult;

/**
 * @author Janath
 *
 */
public enum BasicMethods implements ChainMethod {
	  COMMAND_GET_BALANCE("getbalance"),
	  COMMAND_GET_INFO("getinfo"),
	  COMMAND_GET_NEW_ADDRESS("getnewaddress"),
	  COMMAND_GRANT_ADDRESS("grant"),
	  COMMAND_SEND_METADATA("sendwithmetadata"),
	  COMMAND_SEND_METADATA_FROM("sendwithmetadatafrom"),
	  COMMAND_SEND_FROM_ADDRESS("sendfromaddress"),
	  COMMAND_CREATE_ASSETS("issue"),
	  COMMAND_PREPARE_LOCK_UNSPENT("preparelockunspent"),
	  COMMAND_CREATE_RAW_EXCHANGE("createrawexchange"),
	  COMMAND_DECODE_RAW_EXCHANGE("decoderawexchange"),
	  COMMAND_APPEND_RAW_EXCHANGE("appendrawexchange"),
	  COMMAND_SEND_RAW_TRANSACTION("sendrawtransaction"),
	  COMMAND_LIST_WALLET_TRANSACTIONS("listwallettransactions"),
	  COMMAND_LIST_ADDRESS_TRANSACTIONS("listaddresstransactions"),
	  COMMAND_GET_WALLET_TRANSACTION("getwallettransaction"),
	  COMMAND_GET_ADDRESS_TRANSACTION("getaddresstransaction"),
	  COMMAND_GET_ADDRESSES("getaddresses"),
	  COMMAND_PREPARE_LOCK_UNSPENT_FROM("preparelockunspentfrom"),
	  COMMAND_CREATE_RAW_TRANSACTION("createrawtransaction"),
	  COMMAND_APPEND_RAW_METADATA("appendrawmetadata"),
	  COMMAND_SIGN_RAW_TRANSACTION("signrawtransaction"),
	  COMMAND_GET_BLOCK_HASH("getblockhash"),
	  COMMAND_GET_BLOCK("getblock"),
	  COMMAND_PUBLISH_FROM("publishfrom"),
	  COMMAND_LIST_STREAM_KEY_ITEMS("liststreamkeyitems"),
	  ;
	  
	  private final String name;
	  
	  private BasicMethods(String name) {
	    this.name = name;
	  }
	  
	  public String getName() {
	    return name;
	  }

	  public Class<? extends BaseRpcResult<?>> getResultClass() {
	    return RpcResult.class;
	  }
	}
