package com.halialab.demo.ws.controllers;

import static com.halialab.demo.util.GsonUtils.toJson;

import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.halialab.demo.domain.User;
import com.halialab.demo.domain.UserRepository;
import com.halialab.demo.model.FileMetadata;
import com.halialab.demo.service.ChainService;
import com.halialab.demo.util.GsonUtils;

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
			LOGGER.info("############### registerFile - fileMetadata ##################### " + toJson(fileMetadata));
		}

		User curruser = repository.findByUsername(auth.getName()).get(0);

		return toJson(chainService.registerFile(hash, fileMetadata, curruser.getAddress()));
	}
	  
	@RequestMapping(path="/{hash}/{size}", method={RequestMethod.GET})
	public String verify(@PathVariable String hash, @PathVariable long size) throws IOException, URISyntaxException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			LOGGER.info("############### verify - Athenticated ##################### " + auth.getName());
			// userDetails = auth.getPrincipal()
		}

		User curruser = repository.findByUsername(auth.getName()).get(0);
		
		return GsonUtils.toJson(chainService.query(hash, size, curruser.getAddress()));
	}
	  
	@RequestMapping(path = "/list", method = { RequestMethod.GET })
	public String list() throws IOException, URISyntaxException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			LOGGER.info("############### list - Athenticated ##################### " + auth.getName());
		}

		User curruser = repository.findByUsername(auth.getName()).get(0);

		return GsonUtils.toJson(chainService.list(curruser.getAddress()));
	}
	
	@RequestMapping(path = "/transfer/{hash}", method = { RequestMethod.POST })
	public String transfer(@PathVariable String hash, @RequestBody FileMetadata fileMetadata) throws IOException, URISyntaxException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			LOGGER.info("############### transfer - Athenticated ##################### " + auth.getName());
		}

		User curruser = repository.findByUsername(auth.getName()).get(0);
		User otheruser = repository.findByUsername(fileMetadata.getOtherUsername()).get(0);

		return GsonUtils.toJson(chainService.SendAsset(hash, curruser.getAddress(), otheruser.getAddress(), Integer.parseInt(fileMetadata.getQuantity())));
	}
	
	@RequestMapping(path = "/cancel/{hash}", method = { RequestMethod.POST })
	public String cancel(@PathVariable String hash, @RequestBody FileMetadata fileMetadata) throws IOException, URISyntaxException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			LOGGER.info("############### cancel - Athenticated ##################### " + auth.getName());
		}

		User curruser = repository.findByUsername(auth.getName()).get(0);

		return GsonUtils.toJson(chainService.cancelAsset(hash, Integer.parseInt(fileMetadata.getQuantity())));
	}
	
	@RequestMapping(path = "/assertion/{hash}", method = { RequestMethod.GET })
	public String assertion(@PathVariable String hash) throws IOException, URISyntaxException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			LOGGER.info("############### cancel - Athenticated ##################### " + auth.getName());
		}

		return GsonUtils.toJson(chainService.assertionList(hash));
	}
	
	@RequestMapping(path = "/link/{hash}", method = { RequestMethod.POST, RequestMethod.PUT })
	public String registerFileWithLink(@PathVariable String hash, @RequestBody FileMetadata fileMetadata)
			throws IOException, URISyntaxException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			LOGGER.info("############### registerFileWithLink - Athenticated ##################### " + auth.getName());
			LOGGER.info("############### registerFileWithLink - fileMetadata ##################### " + toJson(fileMetadata));
		}

		User curruser = repository.findByUsername(auth.getName()).get(0);

		return toJson(chainService.registerFileWithLink(hash, fileMetadata, curruser.getAddress()));
	}
	
	@RequestMapping(path = "/linklist/{hash}", method = { RequestMethod.GET })
	public String linkList(@PathVariable String hash)
			throws IOException, URISyntaxException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			LOGGER.info("############### registerFileWithLink - Athenticated ##################### " + auth.getName());
		}

		return toJson(chainService.linkList(hash));
	}

}
