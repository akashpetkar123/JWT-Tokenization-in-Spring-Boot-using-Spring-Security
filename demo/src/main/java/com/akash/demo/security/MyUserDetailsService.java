/**
 * 
 */
package com.akash.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.akash.demo.filter.JwtRequestFilter;

import java.util.*;

/**
 * @author Akash
 *
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	final static Logger log=LoggerFactory.getLogger(MyUserDetailsService.class);
	
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.info("Username Is ======="+arg0);
		return new org.springframework.security.core.userdetails.User("akash", "akash", new ArrayList<>());
	}

}
