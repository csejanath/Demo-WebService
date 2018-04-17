/**
 * 
 */
package com.halialab.demo.util;

import static com.halialab.demo.util.ChainUtils.invokeRpc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.halialab.demo.model.ChainUrl;
import com.halialab.demo.model.RpcResult;
import com.halialab.demo.util.enums.StreamMethods;

/**
 * @author Janath
 *
 */
public class StreamUtils {
	
	  private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtils.class);
	  
	  private StreamUtils() {
	    // do nothing
	  }
	  
	  public static RpcResult create(ChainUrl chainUrl, String name, boolean open, Map<String, String> customFields) throws IOException, URISyntaxException {
	    return create(chainUrl,
	        null,
	        name,
	        open,
	        customFields);
	  }
	  
	  public static RpcResult create(ChainUrl chainUrl, String addressFrom, String name, boolean open, Map<String, String> customFields) throws IOException, URISyntaxException {
	    // 1st: address
	    // 2nd: type=stream
	    // 3rd: name
	    // 4th: open
	    // 5th: custom fields
	    
	    List<Object> params = new ArrayList<Object>();
	    if (addressFrom != null) {
	      params.add(addressFrom);
	    }
	    params.add("stream");
	    params.add(name);
	    params.add(open);
	    params.add(customFields);
	    
	    
	    return invokeRpc(chainUrl, 
	        addressFrom != null ? StreamMethods.CREATE_FROM : StreamMethods.CREATE,
	        params
	        );
	  }
	  
	  public static RpcResult listStreams(ChainUrl chainUrl, String streams, Boolean verbose, Integer count, Integer start) throws IOException, URISyntaxException {
	    return invokeRpc(chainUrl, StreamMethods.LIST_STREAMS,
	        // 1st: stream name
	        // 2nd: verbose
	        streams,
	        verbose,
	        count,
	        start);
	  }
	  
	  
	  public static RpcResult publish(ChainUrl chainUrl, String streamName, String key, String data) throws IOException, URISyntaxException {
	    return publish(chainUrl, null, streamName, key, data);
	  }


	  public static RpcResult publish(ChainUrl chainUrl, String addressFrom, String streamName, String key, String data)
	      throws IOException, URISyntaxException {
		    LOGGER.info("publish - data: " + data);

	    // 1st: from address
	    // 2nd: stream name
	    // 3rd: key
	    // 4th: hex data
	    List<Object> params = new ArrayList<Object>();
	    if (addressFrom != null) {
	      params.add(addressFrom);
	    }
	    params.add(streamName);
	    params.add(key);
	    params.add(HexUtils.encode(data));
	    
	    return invokeRpc(chainUrl,
	        addressFrom != null ? StreamMethods.PUBLISH_FROM : StreamMethods.PUBLISH,
	        params
	        );
	  }
	  
	  public static RpcResult listStreamKeyItems(ChainUrl chainUrl, String streamName, String key, Boolean verbose, Integer count, Integer start) throws IOException, URISyntaxException {
	    return invokeRpc(chainUrl,
	        StreamMethods.LIST_STREAM_KEY_ITEMS, 
	        // 1st: stream
	        // 2nd: key
	        // 3rd: verbose
	        // 4th: count
	        // 5th: start
	        streamName,
	        key,
	        verbose,
	        count,
	        start);
	  }
	  
	  public static RpcResult subscribe(ChainUrl chainUrl, String streamName, Boolean rescan) throws IOException, URISyntaxException {
	    return invokeRpc(chainUrl, 
	        StreamMethods.SUBSCRIBE, 
	        streamName,
	        rescan
	        );
	    
	  }
	  
	  public static RpcResult liststreampublisheritems(ChainUrl chainUrl, String streamName, String addressFrom, Boolean verbose, Integer count, Integer start) throws IOException, URISyntaxException {
		    return invokeRpc(chainUrl, 
		        StreamMethods.LIST_STREAM_PUBLISHER_ITEMS, 
		        // 1st: stream
		        // 2nd: address
		        // 3rd: verbose
		        // 4th: count
		        // 5th: start
		        streamName,
		        addressFrom,
		        verbose,
		        count,
		        start);
		    
		  }

	}

