/**
 * 
 */
package com.halialab.demo.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.DecoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.halialab.demo.model.RpcResult;

/**
 * @author Janath
 *
 */
public class AppUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppUtils.class);

	public static List<Map<String, Object>> processList(RpcResult result, List<Map<String, Object>> output) {
		
		List<Map<String, Object>> resultresultList = result.getResultAsList();

		resultresultList.forEach(map -> {
			if (map.containsKey("data")) {
				try {
					String data = HexUtils.decode((String) map.get("data"));
					Map<String, Object> dataMap = GsonUtils.fromJsonToMap(data);
					// if (dataMap.containsKey("size") &&
					// ((Number)dataMap.get("size")).longValue() == size) {
					output.add(dataMap);
					// }
				} catch (UnsupportedEncodingException | DecoderException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		});
		return output;
	}

}
