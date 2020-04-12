/**
 * 
 */
package com.akash.demo.controller;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.akash.demo.reqres.AuthenticationRequest;
import com.akash.demo.reqres.AuthenticationResponse;
import com.akash.demo.security.MyUserDetailsService;
import com.akash.demo.util.JwtUtil;

/**
 * @author Akash
 *
 */

@RestController
public class HomeController {
	
	final Logger log=LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@RequestMapping("/")	
	public String sayHii() {
		log.info("Entered in sayHii()");
		return "HIIIIII";
	}
	
	@RequestMapping("/hello")
	public String sayHello() {
		log.info("Entered in sayHello()");
		return "Hello World";
	}
	
	@RequestMapping(value="/authenticate",method=RequestMethod.POST)
	public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		log.info("Entered in createAuthToken()");
		try {
			authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
					);
		}catch(BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Username and Password ",e);
		}
		
		final UserDetails userDetails=myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt=jwtUtil.generateToken(userDetails);
		System.out.println(jwt);
		
		log.info("Leaveing createAuthToken()");
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
}
