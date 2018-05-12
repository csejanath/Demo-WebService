/**
 * 
 */
package com.halialab.demo.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.halialab.demo.domain.ETRDetail;
import com.halialab.demo.domain.ETRRepository;
import com.halialab.demo.domain.LinkDetail;
import com.halialab.demo.domain.LinkRepository;
import com.halialab.demo.domain.TCDetail;
import com.halialab.demo.domain.TCRepository;
import com.halialab.demo.model.FileMetadata;
import com.halialab.demo.model.RpcResult;
import com.halialab.demo.registry.Registry;
import com.halialab.demo.util.AppUtils;

/**
 * @author Janath
 *
 */
public class ChainService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ChainService.class);
	
	public enum Streams {
		TC_REG1,
		ETR_REG1,
		ASSERTION_REG1,
		LINK_F_REG1,
		LINK_B_REG1
	}
	
	private Registry registry;
	
	@Autowired
	ETRRepository eTRRepository;
	
	@Autowired
	TCRepository tcRepository;
	
	@Autowired
	LinkRepository linkRepository;
	
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
			
	        TCDetail tcDetail = new TCDetail();
	        tcDetail.setFileName(metadata.getFileName());
	        tcDetail.setFileSize(metadata.getFileSize());
	        tcDetail.setNickname(metadata.getNickname());
	        tcDetail.setRemarks(metadata.getRemarks());
	        tcDetail.setDoc_type(metadata.getDoc_type());
	        tcRepository.save(tcDetail);
	        
	        Long id = tcDetail.getCid();
	        LOGGER.info("registerFile TC id: " + id);
			
			registry.setStreamName(Streams.TC_REG1.toString());
			return (registry.registerFile(hash, id, address));
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
	        eTRDetail.setFileSize(metadata.getFileSize());
	        eTRDetail.setNickname(metadata.getNickname());
	        eTRDetail.setRemarks(metadata.getRemarks());
	        eTRDetail.setDoc_type(metadata.getDoc_type());
	        eTRDetail.setQuantity(metadata.getQuantity());
	        eTRRepository.save(eTRDetail);
	        
			registry.setStreamName(Streams.ETR_REG1.toString());
			return (registry.registerFile(hash, assetID, address));
		}
		return null;
	}
	
	public List<Map<String, Object>> list(String address) throws IOException, URISyntaxException {
		
		List<Map<String, Object>> output = new ArrayList<>();
		
		registry.setStreamName(Streams.TC_REG1.toString());
		AppUtils.processList(tcRepository, registry.list(address), output);
		LOGGER.info("TC count: " + output.size());
		
//		List<String> output1 = new ArrayList<>();
//		List<Map<String, Object>> output1 = new ArrayList<>();
		
		registry.setStreamName(Streams.ETR_REG1.toString());
		AppUtils.processETRList(registry, address, eTRRepository, registry.list(address), output);
		LOGGER.info("TC + ETR count: " + output.size());

		return output;
	}
	
	public RpcResult SendAsset(String hash, String addressFrom, String addressTo, Integer qty) throws IOException, URISyntaxException {
		
		List<String> output = new ArrayList<>();
		
		registry.setStreamName(Streams.ETR_REG1.toString());
		AppUtils.processETRList(registry.query(hash), output);
		
		// for cancel
		if ("CANCEL".equals(addressTo)) {
			addressTo = registry.getBurnAddress();
			return registry.SendAsset(addressFrom, addressTo, output.get(0), qty);
		}
		
		String assetID = output.get(0);
		
		registry.SendAsset(addressFrom, addressTo, assetID, qty);
				
		return registry.registerFile(hash, assetID, addressTo);
		
	}
	
	public RpcResult cancelAsset(String hash, Integer qty) throws IOException, URISyntaxException {

		List<String> output = new ArrayList<>();

		registry.setStreamName(Streams.ETR_REG1.toString());
		AppUtils.processETRList(registry.query(hash), output);

		String assetID = output.get(0);

		// for cancel
		String addressTo = registry.getBurnAddress();
		return registry.cancelAsset(addressTo, assetID, qty);

	}
	
	public List<Map<String, Object>> query(String hash, long size, String address) throws IOException, URISyntaxException {
		
		List<Map<String, Object>> output = new ArrayList<>();
		registry.setStreamName(Streams.TC_REG1.toString());
		AppUtils.processList(tcRepository, registry.query(hash.toLowerCase()), output);
		LOGGER.info("query - TC count: " + output.size());
		
		if (output.size() > 0) {
			Map<String, Object> dataMap = output.get(0);
			if (dataMap.containsKey("fileSize") && (Long.parseLong((String)dataMap.get("fileSize")) == size)) {
				String status = "Verified";
				dataMap.put("status", status);
				registry.setStreamName(Streams.ASSERTION_REG1.toString());
				registry.registerFile(hash, status, address);
			}
			
		}
		
		return output;
	}
	
	public List<Map<String, Object>> assertionList(String hash) throws IOException, URISyntaxException {
		
		List<Map<String, Object>> output = new ArrayList<>();
		registry.setStreamName(Streams.ASSERTION_REG1.toString());
		AppUtils.processAssertionList(registry.query(hash.toLowerCase()), output);
		LOGGER.info("assertionList - count: " + output.size());
		
		return output;
	}
	
	public RpcResult registerFileWithLink(String hash, FileMetadata metadata, String address) throws IOException, URISyntaxException {

		registerFile(hash, metadata, address);
		
		//save link details in db
        LinkDetail linkDetail = new LinkDetail();
        linkDetail.setFileName(metadata.getLinkFilename());
        linkDetail.setFileSize(metadata.getLinkFilesize());
        linkDetail.setType(metadata.getLinkType());
        linkDetail.setHash(metadata.getLinkHash());
        
        linkRepository.save(linkDetail);
        
        Long id = linkDetail.getLid();
        LOGGER.info("registerFileWithLink id : " + id);
		
		//publish in the link stream
		registry.setStreamName(Streams.LINK_F_REG1.toString());
		return (registry.registerFile(hash, id, address));

	}

	public List<Map<String, Object>> linkList(String hash) throws IOException, URISyntaxException {
		
		List<Map<String, Object>> output = new ArrayList<>();
		registry.setStreamName(Streams.LINK_F_REG1.toString());
		AppUtils.processLinkList(registry.query(hash.toLowerCase()), output, linkRepository);
		LOGGER.info("linkList - count: " + output.size());
		
		return output;
	}
	

}
