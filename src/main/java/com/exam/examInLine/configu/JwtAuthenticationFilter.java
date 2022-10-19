package com.exam.examInLine.configu;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.examInLine.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl UserDetailsService;
	
	@Autowired
	private JwtUtils jwtUtil;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestTokenHeader =  request.getHeader("Authorization");
		System.out.println(requestTokenHeader);
		String username = null;
		String jwtToken = null;
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
			//yes 
			jwtToken = requestTokenHeader.substring(7);
			
			try
			{
			username = this.jwtUtil.extractUsername(jwtToken);
			}catch(ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("Jwt token/jeton a expiré");
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("erreur");
			}
			
			
		}else {
			System.out.println("Jeton Invalide, Il ne démarre pas avec la chaine Bearer");
		}
		
		
		
		//Validation
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			final UserDetails userDetails = this.UserDetailsService.loadUserByUsername(username);
			
			
			if(this.jwtUtil.validateToken(jwtToken, userDetails))
			{
				// token est valide
				
				UsernamePasswordAuthenticationToken UsernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				UsernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthentication);
			}
		}else {
			System.out.println("Le Token/jeton est Valide");
		}
		
		filterChain.doFilter(request, response);
		
	}

}
