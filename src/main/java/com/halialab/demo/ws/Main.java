package com.halialab.demo.ws;

import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.halialab.demo.domain.User;
import com.halialab.demo.domain.UserRepository;
import com.halialab.demo.registry.Registry;
import com.halialab.demo.ws.pojo.ChainRpcProperties;

/**
 * @author Janath
 *
 */
@SpringBootApplication
@EnableConfigurationProperties(ChainRpcProperties.class)
public class Main extends SpringBootServletInitializer {
	
	  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Main.class, args);
	}
	
	  @Bean
	  public Registry registry(ChainRpcProperties chainRpcProperties) throws IOException, URISyntaxException {
	    Registry registry = new Registry(
	        chainRpcProperties.getHost(), 
	        chainRpcProperties.getPort(), 
	        chainRpcProperties.getChainName(), 
	        chainRpcProperties.getUsername(), 
	        chainRpcProperties.getPassword(), 
	        chainRpcProperties.getStreamName());
	    if (chainRpcProperties.getProtocol() != null) {      
	      registry.setProtocol(chainRpcProperties.getProtocol());
	    }
	    
	    if (chainRpcProperties.getPublicKey()  != null) {
	      registry.setPublicKey(chainRpcProperties.getPublicKey());
	    }
	    
	    try {
	      registry.initStream();
	    } catch (Exception e) {
	      LOGGER.error(e.getMessage(), e);
	      
	      // maybe it's not yet created?
	      
	      
	      registry.safeInitStream();
	      
	    }
	    
	    return registry;
	  }
	  
		@Bean
		public CommandLineRunner studentDemo(UserRepository urepository) {
			return (args) -> {
				User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
				User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
				urepository.save(user1);
				urepository.save(user2);
		    	
		    };
		}	

}
