package com.zervladpy.spring_security_module.config.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zervladpy.spring_security_module.config.details.ImplUserDetails;
import com.zervladpy.spring_security_module.config.jwt.JwtDecoder;
import com.zervladpy.spring_security_module.services.ImplUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JwtAuthenticationFilter
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final String AUTH_PREFIX = "Bearer ";

  private final JwtDecoder decoder;
  private final ImplUserDetailsService detailsService;

  @Autowired
  public JwtAuthenticationFilter(JwtDecoder decoder, ImplUserDetailsService detailsService) {
    this.decoder = decoder;
    this.detailsService = detailsService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

    /// EdgeCases
    if (header == null || header.isEmpty() || !header.startsWith(AUTH_PREFIX)) {
      filterChain.doFilter(request, response);
      return;
    }

    /// Retrive the token
    final String jwtToken = header.substring(AUTH_PREFIX.length());
    final String usernameOrEmail = decoder.decode(jwtToken);

    /// Find the user on the storage
    final ImplUserDetails details = (ImplUserDetails) detailsService.loadUserByUsername(usernameOrEmail);

    if (details == null) {
      filterChain.doFilter(request, response);
      return;
    }

    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(details, null,
        details.getAuthorities());

    SecurityContextHolder.getContext().setAuthentication(auth);

    filterChain.doFilter(request, response);

  }

}
