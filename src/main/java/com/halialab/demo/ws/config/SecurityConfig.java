/**
 * 
 */
package com.halialab.demo.ws.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.halialab.demo.domain.UserRepository;
import com.halialab.demo.ws.controllers.UserDetailServiceImpl;

/**
 * @author Janath
 *
 */
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses= UserRepository.class)
@ComponentScan(basePackages = { "com.halialab.demo.*" })
@EntityScan("com.halialab.demo.*")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private UserDetailServiceImpl userDetailsService;	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/registry/**").authenticated()
			.anyRequest().permitAll()
			.and().formLogin().loginPage("/login").successForwardUrl("/login/success").permitAll()
			.and().logout().logoutSuccessUrl("/login").deleteCookies("JSESSIONID");
	}
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
  
//  @Bean
//  @Override
//  public UserDetailsService userDetailsService() {
//      UserDetails user =
//           User.withDefaultPasswordEncoder()
//              .username("user")
//              .password("password")
//              .roles("USER")
//              .build();
//
//      return new InMemoryUserDetailsManager(user);
//  }
  
  @Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}	

}