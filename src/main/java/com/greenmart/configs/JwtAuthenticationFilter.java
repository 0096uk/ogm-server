package com.greenmart.configs;

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

import com.greenmart.services.CustomUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CustomUserDetailsService cds;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//get token
		String requestToken = request.getHeader("Authorization");
		
		System.out.println(requestToken);
		
		String username = null;
		
		String token = null;
		
		if(requestToken != null && requestToken.startsWith("Bearer"))
		{
			token = requestToken.substring(7);
			try
			{
				
				System.out.println(token);
				
			username = this.jwtTokenHelper.getUsernameFromToken(token);
			System.out.println("inside sysout 1 success");
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("inside sysout 1");
		}
		
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserDetails userDetails = null;
			
			userDetails = this.userDetailsService.loadUserByUsername(username);
			
			System.out.println("inside sysout 2 success");
			
			
			
			
			if(this.jwtTokenHelper.validateToken(token, userDetails))
			{
				UsernamePasswordAuthenticationToken authenticationToken = new  UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				 System.out.println("inside sysout 3 success");
				
			}
			else
			{
				System.out.println("inside sysout 2");
			}
		}
		else
		{
			System.out.println("inside sysout 3");
		}
		
		filterChain.doFilter(request, response);
		
	}

}
