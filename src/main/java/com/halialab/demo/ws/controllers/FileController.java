package com.halialab.demo.ws.controllers;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.halialab.demo.domain.User;
import com.halialab.demo.domain.UserRepository;
import com.halialab.demo.model.FileMetadata;
import com.halialab.demo.model.RpcResult;
import com.halialab.demo.registry.Asset;
import com.halialab.demo.registry.Registry;
import com.halialab.demo.service.ChainService;
import com.halialab.demo.util.GsonUtils;
import com.halialab.demo.util.HexUtils;
import com.halialab.demo.util.exception.HttpException;

/**
 * @author Janath
 *
 */
@RestController("/registry")
@RequestMapping("/registry")
@CrossOrigin
public class FileController {
	
	@Autowired
	private ChainService chainService;

	@Autowired
	private UserRepository repository;

	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

	@RequestMapping(path = "/{hash}", method = { RequestMethod.POST, RequestMethod.PUT })
	public String registerFile(@PathVariable String hash, @RequestBody FileMetadata fileMetadata)
			throws IOException, URISyntaxException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			LOGGER.info("############### registerFile - Athenticated ##################### " + auth.getName());
		}

		User curruser = repository.findByUsername(auth.getName());

		return toJson(chainService.registerFile(hash, fileMetadata, curruser.getAddress()));
	}
	  
	  @RequestMapping(path="/{hash}/{size}", method={RequestMethod.GET})
	  public String verify(@PathVariable String hash, @PathVariable long size) throws IOException, URISyntaxException {
		  
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				LOGGER.info("############### verify - Athenticated ##################### " + auth.getName());
		        // userDetails = auth.getPrincipal()
			}
			
//	    RpcResult result = registry.query(hash);
//	    List<Map<String, Object>> resultList = result.getResultAsList();
//	    List<Map<String, Object>> output = new ArrayList<>();
//	    resultList.forEach(map -> {
//	      if (map.containsKey("data")) {
//	        try {
//	          String data = HexUtils.decode((String) map.get("data"));
//	          Map<String, Object> dataMap = GsonUtils.fromJsonToMap(data);
//	          if (dataMap.containsKey("size") && ((Number)dataMap.get("size")).longValue() == size) {
//	            output.add(dataMap);
//	          }
//	        } catch (UnsupportedEncodingException | DecoderException e) {
//	          LOGGER.error(e.getMessage(), e);
//	        }
//	      }
//	    });
//	    if (output.isEmpty()) {
//	      throw new HttpException("Not Found", HttpStatus.NOT_FOUND.value());
//	    }
//	    return GsonUtils.toJson(output);
			return null;
	  }
	  
	@RequestMapping(path = "/list", method = { RequestMethod.GET })
	public String list() throws IOException, URISyntaxException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			LOGGER.info("############### list - Athenticated ##################### " + auth.getName());
		}

		User curruser = repository.findByUsername(auth.getName());

		return GsonUtils.toJson(chainService.list(curruser.getAddress()));
	}
	
	@RequestMapping(path = "/transfer/{hash}", method = { RequestMethod.POST })
	public String transfer(@PathVariable String hash, @RequestBody FileMetadata fileMetadata) throws IOException, URISyntaxException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			LOGGER.info("############### list - Athenticated ##################### " + auth.getName());
		}

		User curruser = repository.findByUsername(auth.getName());
		User otheruser = repository.findByUsername(fileMetadata.getOtherUsername());

		return GsonUtils.toJson(chainService.SendAsset(hash, curruser.getAddress(), otheruser.getAddress(), Integer.parseInt(fileMetadata.getQuantity())));
	}

}
