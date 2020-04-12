/**
 * 
 */
package com.akash.demo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.akash.demo.DemoApplication;
import com.akash.demo.security.MyUserDetailsService;
import com.akash.demo.util.JwtUtil;

/**
 * @author Akash
 *
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	final static Logger log=LoggerFactory.getLogger(JwtRequestFilter.class);
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest arg0, HttpServletResponse arg1, FilterChain arg2)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.info("Entered in doFilterInternal() of JwtRequestFilter");
		final String authorizationHeader=arg0.getHeader("Authorization");
		log.info("AuthHeader====="+authorizationHeader);
		
		String jwt=null,username=null;
		
		if(authorizationHeader!=null && authorizationHeader.startsWith("auth")) {
			jwt=authorizationHeader.substring(5);
			username=jwtUtil.extractUsername(jwt);
		}
		
		log.info(jwt);
		if(username!=null /*&& SecurityContextHolder.getContext().getAuthentication()!=null*/) {
			UserDetails userDetails=myUserDetailsService.loadUserByUsername(username);
			if(jwtUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken
						(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(arg0));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		arg2.doFilter(arg0, arg1);
		log.info("Leaveing doFilterInternal() of JwtRequestFilter");
	}

}
