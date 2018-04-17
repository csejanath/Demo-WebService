/**
 * 
 */
package com.halialab.demo.util.enums;

import com.halialab.demo.model.BaseRpcResult;
import com.halialab.demo.model.RpcResult;
import com.halialab.demo.model.StreamResult;

/**
 * @author Janath
 *
 */
public enum StreamMethods implements ChainMethod {
	  CREATE("create"),
	  CREATE_FROM("createfrom"),
	  LIST_STREAMS("liststreams"),
	  PUBLISH("publish"),
	  PUBLISH_FROM("publishfrom"),
	  SUBSCRIBE("subscribe"),
	  UNSUBSCRIBE("unsubscribe"),
	  GET_STREAM_ITEM("getstreamitem"),
	  GET_TXOUT_DATA("gettxoutdata"),
	  LIST_STREAM_KEY_ITEMS("liststreamkeyitems"),
	  LIST_STREAM_KEYS("liststreamkeys"),
	  LIST_STREAM_ITEMS("liststreamitems", StreamResult.class),
	  LIST_STREAM_PUBLISHER_ITEMS("liststreampublisheritems"),
	  LIST_STREAM_PUBLISHERS("liststreampublishers");
	  
	  private final String name;
	  private final Class<? extends BaseRpcResult<?>> resultClass;
	  
	  private StreamMethods(String name) {
	    this(name, RpcResult.class);
	  }
	  
	  private StreamMethods(String name, Class<? extends BaseRpcResult<?>> resultClass) {
	    this.name = name;
	    this.resultClass = resultClass;
	  }
	  
	  public String getName() {
	    return name;
	  }

	  public Class<? extends BaseRpcResult<?>> getResultClass() {
	    return resultClass;
	  }
	}

