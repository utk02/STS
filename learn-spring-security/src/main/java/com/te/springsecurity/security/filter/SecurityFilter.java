package com.te.springsecurity.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.te.springsecurity.jwt.utils.JwtUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
	private final JwtUtils jwtUtils;
	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		/*
		 * in the header part of the request from the postman(or browser), there is
		 * various data stored in the form of key-value pair which is sent with the
		 * request
		 * 
		 * in that key-value pair header there is a key named "Authorization" which has
		 * value of our JWT token (Bearer token) along with "Bearer" word prefixed to
		 * it, we use this token for authorization.
		 * 
		 * hence using getHeader() method we tell framework to give me value from the
		 * header of request which has key "Authorization"
		 */
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			// Remove "Bearer" word from bearer token
			String token = bearerToken.substring(7);
			// Find out username from token
			String username = jwtUtils.getUsername(token);

			// SecurityContextHolder holds the authentication for current request and
			// becomes empty with new request
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				// finding user deatils by username we will require this for creating UPAT
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				// Validating token on the basis of username
				boolean validateToken = jwtUtils.validateToken(token, userDetails.getUsername());

				if (validateToken) {
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}
		}
		filterChain.doFilter(request, response);
	}
}
