package com.chatapp.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		//1. get token
		 String requestToken= request.getHeader("Authorization");
		 
		 //Bearer 2352533sdgsg
		 
		 System.out.println(requestToken);
		 
		 String username =null;
		 
		 String token =null;
		 if(requestToken!=null && requestToken.startsWith("Bearer")) {
			 token = requestToken.substring(7);
			 
			 try {
			 username=this.jwtTokenHelper.getUsernameFromToken(token);
			 }
			 catch(IllegalArgumentException ex) {
				 System.out.println("Unable to get jwt token");
			 }
			 catch(ExpiredJwtException ex) {
				 System.out.println("jwt token has expired");
			 }
			 catch(MalformedJwtException ex) {
				 System.out.println("invalid jwt");
			 }
			 
		 }
		 else {
			 System.out.println("Jwt token does not begin with Bearer");
		 }
		 //once we get token will do validation
		 if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			 //it  means security in authentication is null and it means 
			 //spring security is not authenticating anyone
			 
			 UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
			 if(this.jwtTokenHelper.validateToken(token, userDetails)) {
				 //shi chal raha
				 //authentication karna hai
				 UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				 
				 authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				 
				 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			 }
			 else {
				 System.out.println("invalid jwt token");
			 }
			  
		 }else {
			 System.out.println("username is null or context is not null");
		 }
		 
		 filterChain.doFilter(request, response);
	}

}
