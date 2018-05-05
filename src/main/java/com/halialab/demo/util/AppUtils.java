/**
 * 
 */
package com.halialab.demo.util;

import static com.halialab.demo.util.GsonUtils.toJson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.DecoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.halialab.demo.domain.ETRRepository;
import com.halialab.demo.domain.TCRepository;
import com.halialab.demo.model.RpcResult;
import com.halialab.demo.registry.Registry;

/**
 * @author Janath
 *
 */
public class AppUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppUtils.class);

	public static List<Map<String, Object>> processList(TCRepository tcRepository, RpcResult result, List<Map<String, Object>> output) {
		
		List<Map<String, Object>> resultresultList = result.getResultAsList();

		resultresultList.forEach(map -> {
			if (map.containsKey("data")) {
				try {
					String key = ((String) map.get("key"));
					String id = HexUtils.decode((String) map.get("data"));
					LOGGER.info("processList: " + key + " " + id);
					
//					Map<String, Object> dataMap = GsonUtils.fromJsonToMap(data);
					// if (dataMap.containsKey("size") &&
					// ((Number)dataMap.get("size")).longValue() == size) {
					String data;
					try {
						data = toJson(tcRepository.findByCid(Long.parseLong(id)));
					} catch (NumberFormatException e) {
						data = id;
						LOGGER.error(e.getMessage());
					}
					
					LOGGER.info("Get TC Details: " + data);
					
					if (data != null && !"null".equals(data)) {
						Map<String, Object> dataMap = GsonUtils.fromJsonToMap(data);
						dataMap.put("hash", key);
						output.add(dataMap);
					}
					
//					output.add(dataMap);
					// }
				} catch (UnsupportedEncodingException | DecoderException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		});
		return output;
	}
	
	public static List<String> processETRList(RpcResult result, List<String> output) {
		
		List<Map<String, Object>> resultresultList = result.getResultAsList();

		resultresultList.forEach(map -> {
			if (map.containsKey("data")) {
				try {
					String data = HexUtils.decode((String) map.get("data"));
					LOGGER.info("processETRList: " + data);
//					Map<String, Object> dataMap = GsonUtils.fromJsonToMap(data);
					// if (dataMap.containsKey("size") &&
					// ((Number)dataMap.get("size")).longValue() == size) {
					output.add(data);
					// }
				} catch (UnsupportedEncodingException | DecoderException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		});
		return output;
	}
	
	public static List<Map<String, Object>> processETRList(Registry registry, String address, ETRRepository eTRRepository, RpcResult result, List<Map<String, Object>> output) {
		
		List<Map<String, Object>> resultresultList = result.getResultAsList();

		resultresultList.forEach(map -> {
			if (map.containsKey("data")) {
				try {
					String hash = ((String) map.get("key"));
					String assetID = HexUtils.decode((String) map.get("data"));
					LOGGER.info("processETRList: " + hash + " " + assetID);
//					Map<String, Object> dataMap = GsonUtils.fromJsonToMap(data);
					// if (dataMap.containsKey("size") &&
					// ((Number)dataMap.get("size")).longValue() == size) {
					
					RpcResult balance = registry.getAssetBalance("*", assetID);
					Map<String, Object>	balancemap = balance.getResultAsMap();
					List<Map<String, Object>>	mybalance = (ArrayList)balancemap.get(address);
					List<Map<String, Object>>	totalbalance = (ArrayList) balancemap.get("total");
					
					Double myqty = 0D;
					Double totalqty = 0D;
					if (mybalance != null && totalbalance != null) {
						Map<String, Object> mybalancemap = (Map<String, Object>) mybalance.get(0);
						Map<String, Object> totalbalancemap = (Map<String, Object>) totalbalance.get(0);
						myqty = (Double) mybalancemap.get("qty");
						totalqty = (Double) totalbalancemap.get("qty");
					}
//					
					LOGGER.info("Get Balance count: " + toJson(balancemap));
//					LOGGER.info("Get Balance count1: " + toJson(mybalancemap.get("myqty")));
//					LOGGER.info("Get Balance count2: " + toJson(totalbalancemap.get("totalqty")));
//					balance.getResultAsList();

					String data = toJson(eTRRepository.findByAssetID(assetID));
					LOGGER.info("Get ETR Details: " + data);
					
					if (data != null && !"null".equals(data)) {
						Map<String, Object> dataMap = GsonUtils.fromJsonToMap(data);
						dataMap.put("hash", hash);
						dataMap.put("myqty", myqty);
						dataMap.put("totalqty", totalqty);
						output.add(dataMap);
					}
					
//					output.add(assetID);
					// }
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		});
		return output;
	}

}
