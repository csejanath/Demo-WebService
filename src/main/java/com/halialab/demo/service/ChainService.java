/**
 * 
 */
package com.halialab.demo.service;

import static com.halialab.demo.util.GsonUtils.toJson;
import static com.halialab.demo.util.StreamUtils.liststreampublisheritems;
import static com.halialab.demo.util.StreamUtils.publish;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.DecoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.halialab.demo.domain.ETRDetail;
import com.halialab.demo.domain.ETRRepository;
import com.halialab.demo.model.FileMetadata;
import com.halialab.demo.model.RpcResult;
import com.halialab.demo.registry.Registry;
import com.halialab.demo.util.AppUtils;
import com.halialab.demo.util.GsonUtils;
import com.halialab.demo.util.HexUtils;
import com.halialab.demo.ws.Main;
import com.halialab.demo.ws.pojo.ChainRpcProperties;

/**
 * @author Janath
 *
 */
public class ChainService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ChainService.class);
	
	public enum Streams {
		TC_REG,
		ETR_REG1
	}
	
	private Registry registry;
	
	@Autowired
	ETRRepository eTRRepository;
	
	public ChainService (Registry registry) {
		this.registry = registry;
	}
	
//	@Bean
//	public Registry registry(ChainRpcProperties chainRpcProperties) throws IOException, URISyntaxException {
//		Registry registry = new Registry(chainRpcProperties.getHost(), chainRpcProperties.getPort(),
//				chainRpcProperties.getChainName(), chainRpcProperties.getUsername(), chainRpcProperties.getPassword(),
//				chainRpcProperties.getStreamName());
//		if (chainRpcProperties.getProtocol() != null) {
//			registry.setProtocol(chainRpcProperties.getProtocol());
//		}
//
//		if (chainRpcProperties.getPublicKey() != null) {
//			registry.setPublicKey(chainRpcProperties.getPublicKey());
//		}
//		
//		for (Streams stm : Streams.values()) {
//			  // do what you want
//			registry.setStreamName(stm.toString());
//			try {
//				registry.initStream();
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage(), e);
//				// maybe it's not yet created?
//				registry.safeInitStream();
//			}
//		}
	    
//	    try {
//	      registry.initStream();
//	    } catch (Exception e) {
//	      LOGGER.error(e.getMessage(), e);
//	      
//	      // maybe it's not yet created?
//	      
//	      
//	      registry.safeInitStream();
//	      
//	    }
//	    
//	    return registry;
//	  }
	
	public void initStream() throws IOException, URISyntaxException {
		
		for (Streams stm : Streams.values()) {
			  // do what you want
			registry.setStreamName(stm.toString());
			registry.setInit(false);
			try {
				registry.initStream();
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				// maybe it's not yet created?
				registry.safeInitStream();
			}
		}

	}
	
	public RpcResult registerFile(String hash, FileMetadata metadata, String address) throws IOException, URISyntaxException {
		
		if ("TC".equals(metadata.getType())) {
			registry.setStreamName(Streams.TC_REG.toString());
			return (registry.registerFile(hash, metadata, address));
		} else if ("ETR".equals(metadata.getType())) {
			
	        // Creating a random UUID (Universally unique identifier).
	        UUID uuid = UUID.randomUUID();
	        LOGGER.info("registerFile GUID: " + uuid.toString());
	        String assetID = uuid.toString().replace("-","");
	        
	        registry.registerAsset(address, assetID, Integer.parseInt(metadata.getQuantity()));
	        
	        // Save in Cassandra
	        ETRDetail eTRDetail = new ETRDetail();
	        eTRDetail.setAssetID(assetID);
	        eTRDetail.setFilename(metadata.getFileName());
	        eTRDetail.setQuantity(metadata.getQuantity());
	        eTRRepository.save(eTRDetail);
	        
			registry.setStreamName(Streams.ETR_REG1.toString());
			return (registry.registerFile(hash, assetID, address));
		}
		return null;
	}
	
	public List<Map<String, Object>> list(String address) throws IOException, URISyntaxException {
		
		List<Map<String, Object>> output = new ArrayList<>();
		
		registry.setStreamName(Streams.TC_REG.toString());
		AppUtils.processList(registry.list(address), output);
		LOGGER.info("TC count: " + output.size());
		
		List<String> output1 = new ArrayList<>();
		
		registry.setStreamName(Streams.ETR_REG1.toString());
		AppUtils.processETRList(registry.list(address), output1);
		LOGGER.info("ETR count: " + output1.size());
		
		output1.forEach( assetID -> {
			try {
				LOGGER.info("Get Balance count: " + toJson(registry.getAssetBalance(address, assetID)));
				
				String data = toJson(eTRRepository.findByAssetID(assetID));
				LOGGER.info("Get ETR Details: " + data);
				
				if (data != null && !"null".equals(data)) {
					Map<String, Object> dataMap = GsonUtils.fromJsonToMap(data);
					output.add(dataMap);
				}

			} catch (IOException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		return output;
	}
	
	public RpcResult SendAsset(String hash, String addressFrom, String addressTo, Integer qty) throws IOException, URISyntaxException {
		
		List<String> output = new ArrayList<>();
		
		registry.setStreamName(Streams.ETR_REG1.toString());
		AppUtils.processETRList(registry.query(hash), output);
		
		return registry.SendAsset(addressFrom, addressTo, output.get(0), qty);
		
	}
	
	
}
