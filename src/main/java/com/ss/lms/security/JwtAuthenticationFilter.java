package com.ss.lms.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ss.lms.model.UserPrincipal;
import com.ss.lms.services.UserPrincipalService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {


	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserPrincipalService principalService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {		

		String jwt = getJwtFromRequest(request);

		if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
			Integer userId = tokenProvider.getUserIdFromJwt(jwt);


			UserPrincipal principal = principalService.findById(userId);

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					principal, null, principal.getAuthorities());

			authentication.setDetails(
					new WebAuthenticationDetailsSource().buildDetails(request)
					);

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}


		filterChain.doFilter(request, response);

	}

	private String getJwtFromRequest(HttpServletRequest request) {

		String bearerToken = request.getHeader("Authorization");

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}
