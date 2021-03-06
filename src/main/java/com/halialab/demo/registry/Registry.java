/**
 * 
 */
package com.halialab.demo.registry;

import static com.halialab.demo.util.StreamUtils.create;
import static com.halialab.demo.util.StreamUtils.listStreamKeyItems;
import static com.halialab.demo.util.StreamUtils.publish;
import static com.halialab.demo.util.StreamUtils.liststreampublisheritems;
import static com.halialab.demo.util.AssetUtils.issue;
import static com.halialab.demo.util.AssetUtils.getBalance;
import static com.halialab.demo.util.AssetUtils.getMultiBalance;
import static com.halialab.demo.util.AssetUtils.transferAssetFrom;
import static com.halialab.demo.util.AssetUtils.transferAsset;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.halialab.demo.model.ChainUrl;
import com.halialab.demo.model.RpcResult;
import com.halialab.demo.util.GsonUtils;
import com.halialab.demo.util.StreamUtils;
import com.halialab.demo.util.exception.RpcException;

/**
 * @author Janath
 *
 */
public class Registry extends ChainUrl {
	  
	  private static final Logger LOGGER = LoggerFactory.getLogger(Registry.class);
	  
	  public static final String DEFAULT_STREAM_NAME = "TC_REG";
	  
	  private boolean init = false;
	  private String streamName;
	  private String publicKey;
	  private String burnAddress;
	  
	  public Registry(String host, int port, String chainName, String username, String password) {
	    this(host, port, chainName, username, password, null);
	  }
	  
	  public Registry(String host, int port, String chainName, String username, String password, String streamName) {
	    super(host, port, chainName, username, password);
	    this.streamName = streamName == null ? DEFAULT_STREAM_NAME : streamName;
	  }
	  
	  public boolean streamExists() throws IOException, URISyntaxException {
	    try {
	      RpcResult result = StreamUtils.listStreams(this, streamName, null, null, null);
	      List<Map<String, Object>> listResult = result.getResultAsList();
	      for (Map<String, Object> map : listResult) {
	        if (streamName.equals(map.get("name"))) {
	          return true;
	        }
	      }
	    } catch (RpcException e) {
	      LOGGER.error("Error while invoking stream exists: " + e.getMessage(), e);
	    }
	    return false;
	  }
	  
	  public void createStream() throws IOException, URISyntaxException {
	    create(this, publicKey, streamName, false, null);
	    subscribe();
	  }
	  
	  public void subscribe() throws IOException, URISyntaxException {
	    StreamUtils.subscribe(this, streamName, null);
	  }

	  public void initStream() throws IOException, URISyntaxException {
	    if (!init) {
	      if (!streamExists()) {
	        createStream();
	      }
	      init = true;
	    }
	  }
	  
	  public void safeInitStream() {
	    try {
	      initStream();
	    } catch (Exception e) {
	      LOGGER.error(e.getMessage(), e);
	    }
	  }
	  
	  /**
	   * 
	   * @param hash
	   * @param metadata will be converted into Json String before saving to Multichain.
	   *   In theory, Multichain can save the serialized version of the object (converted into hex-string); however, we all know that Object serialization is not so stable. 
	   * @throws IOException
	   * @throws URISyntaxException
	   */
	  public RpcResult registerFile(String hash, Object metadata, String address) throws IOException, URISyntaxException {
	    initStream();
	    return publish(this, address, streamName, hash.toLowerCase(), GsonUtils.toJson(metadata));
	  }
	  
	  public RpcResult registerFile(String hash, String metadata, String address) throws IOException, URISyntaxException {
		    initStream();
		    return publish(this, address, streamName, hash.toLowerCase(), metadata);
	  }
	  
	  
	  public boolean hashExists(String hash) throws IOException, URISyntaxException {
	    RpcResult result = query(hash.toLowerCase());
	    return !result.getResultAsList().isEmpty();
	  }
	  
	  public RpcResult query(String hash) throws IOException, URISyntaxException {
	    return listStreamKeyItems(this, streamName, hash.toLowerCase(), true, null, null);
	  }
	  
	  public RpcResult list(String address) throws IOException, URISyntaxException {
		return liststreampublisheritems(this, streamName, address, true, null, null);
	  }
	  
	  public RpcResult registerAsset(String address, String assetID, Integer qty) throws IOException, URISyntaxException {
		    return issue(this, address, assetID, qty, true, null, null);
	  }
	  
	  public RpcResult getBalace(String address) throws IOException, URISyntaxException {
		    return getBalance(this, address);
	  }

	  public RpcResult getAssetBalance(String address, String assetID) throws IOException, URISyntaxException {
		    return getMultiBalance(this, address, assetID);
	  }
	  
	  public RpcResult SendAsset(String addressFrom, String addressTo, String assetID, Integer qty) throws IOException, URISyntaxException {
		    return transferAssetFrom(this, addressFrom, addressTo, assetID, qty);
	  }
	  
	  public RpcResult cancelAsset(String addressTo, String assetID, Integer qty) throws IOException, URISyntaxException {
		    return transferAsset(this, addressTo, assetID, qty);
	  }

	  public String getStreamName() {
	    return streamName;
	  }

	  /**
	 * @param streamName the streamName to set
	 */
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public String getPublicKey() {
	    return publicKey;
	  }

	  public void setPublicKey(String publicKey) {
	    this.publicKey = publicKey;
	  }

	/**
	 * @param init the init to set
	 */
	public void setInit(boolean init) {
		this.init = init;
	}

	/**
	 * @return the burnAddress
	 */
	public String getBurnAddress() {
		return burnAddress;
	}

	/**
	 * @param burnAddress the burnAddress to set
	 */
	public void setBurnAddress(String burnAddress) {
		this.burnAddress = burnAddress;
	}

	}
