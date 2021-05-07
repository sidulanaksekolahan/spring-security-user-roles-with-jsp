package com.irfan.springsecurity.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/** 
	 * WebSecurityConfigurerAdapter
	 * 
	 * Provides a convenient base class for creating a WebSecurityConfigurer instance. 
	 * The implementation allows customization by overriding methods.
	 * 
	 * 
	 * WebSecurityConfigurer
	 * 
	 * Allows customization to the WebSecurity. In most instances users will use EnableWebSecurity 
	 * and a create Configuration that extends WebSecurityConfigurerAdapter which 
	 * will automatically be applied to the WebSecurity by the EnableWebSecurity annotation.
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		System.out.println("Inside configure(AuthenticationManagerBuilder) method!!!");

		// add our users for in memory authentication
		
		/*
		 * UserBuilder
		 * 
		 *Builds the user to be added. At minimum the username, password, and authorities 
		 *should provided. The remaining attributes have reasonable defaults.
		 */
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		
//		auth.inMemoryAuthentication()
//			.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
//			.withUser(users.username("mary").password("test123").roles("MANAGER"))
//			.withUser(users.username("susan").password("test123").roles("ADMIN"));
		
		auth.inMemoryAuthentication()
			.withUser(users.username("john").password("test123").roles("EMPLOYEE"));
		
		auth.inMemoryAuthentication()
		.withUser(users.username("mary").password("test123").roles("EMPLOYEE", "MANAGER"));
		
		auth.inMemoryAuthentication()
		.withUser(users.username("susan").password("test123").roles("EMPLOYEE", "ADMIN"));
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// configure security of web paths in application, login, logout etc
		
//		http.authorizeRequests() // Allows restricting access based upon the HttpServletRequest
//			.anyRequest().authenticated() // any requests must be authenticated
//			.and()
//			.formLogin() // Specifies to support form based authentication
//				.loginPage("/showMyLoginPage") // Specifies the URL to send users to login
//				.loginProcessingUrl("/authenticateTheUser") // Specifies the URL to validate the credentials.
//				.permitAll() // Permit all users to access form login
//			.and()
//			.logout().permitAll(); // Permit all users to logout
		
		// Test custom logoutUrl
		http.authorizeRequests() // Allows restricting access based upon the HttpServletRequest
//		.anyRequest().authenticated() // any requests must be authenticated
		.antMatchers("/").hasRole("EMPLOYEE")
		.antMatchers("/leaders/**").hasRole("MANAGER")
		.antMatchers("/systems").hasRole("ADMIN")
		.and()
		.formLogin() // Specifies to support form based authentication
			.loginPage("/showMyLoginPage") // Specifies the URL to send users to login
			.loginProcessingUrl("/authenticateTheUser") // Specifies the URL to validate the credentials.
			.permitAll() // Permit all users to access form login
		.and()
		.logout()
		.logoutUrl("/perform_logout").permitAll() // Permit all users to /perform_logout
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied"); // Error page
	}	
	

}





















