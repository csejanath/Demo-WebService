/**
 * 
 */
package com.halialab.demo.ws.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Janath
 *
 */
@RestController("/login")
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
	
	  private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/success", method = RequestMethod.POST)
	public String loginSuccess(){

		System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			LOGGER.info("############### loginSuccess - Athenticated #####################" + auth.getName());
//	         userDetails = auth.getPrincipal()
			return auth.getName();
		}

		return null;
	}

}
