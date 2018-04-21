/**
 * 
 */
package com.halialab.demo.registry;

import static com.halialab.demo.util.StreamUtils.publish;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;

import com.halialab.demo.model.ChainUrl;
import com.halialab.demo.model.RpcResult;
import com.halialab.demo.util.GsonUtils;

/**
 * @author Janath
 *
 */
public class Asset extends ChainUrl {
	
	private final String streamName = "ETR_REG";
	
	  @Autowired
	  private Registry registry;
	
	  /**
	 * @param host
	 * @param port
	 * @param chainName
	 * @param username
	 * @param password
	 */
	public Asset(String host, int port, String chainName, String username, String password) {
		super(host, port, chainName, username, password);
		// TODO Auto-generated constructor stub
	}

	public RpcResult registerFile(String hash, Object metadata, String address) throws IOException, URISyntaxException {
		
		registry.setInit(false);
		registry.setStreamName(streamName);
			
		return registry.registerFile(hash, metadata, address);
	}

}
