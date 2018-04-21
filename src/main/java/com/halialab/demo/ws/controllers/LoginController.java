/**
 * 
 */
package com.halialab.demo.ws.controllers;

import static com.halialab.demo.util.GsonUtils.toJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.halialab.demo.util.exception.HttpException;

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

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			LOGGER.info("############### loginSuccess - Athenticated #####################" + auth.getName());
//	         userDetails = auth.getPrincipal()
			return toJson(auth.getPrincipal());
		}

		return null;
	}
	
//	@RequestMapping("/login")
//	public ModelAndView loginPage(){
//		ModelAndView model = new ModelAndView();
//		model.setViewName("/");
//		LOGGER.info("############### loginPage ##################### " + SecurityContextHolder.getContext().getAuthentication().getName());
//		return model;
//	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String logout(){
		
		LOGGER.info("############### logout #####################");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			LOGGER.info("############### logout - Athenticated #####################" + auth.getName());
//	         userDetails = auth.getPrincipal()
			return auth.getName();
		}
		
		return "redirect:/login?logout";

	}

}
