package com.zervladpy.spring_security_module.config.entry_points;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JwtEntryPoint
 */
@Component
public class JwtEntryPoint extends OncePerRequestFilter implements AuthenticationEntryPoint {

  private final HandlerExceptionResolver resolver;

  @Autowired
  public JwtEntryPoint(
      @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
    this.resolver = resolver;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {
  
    try {
      filterChain.doFilter(request, response);
    } catch (Exception ex) {
      resolver.resolveException(request, response, null, ex);
    }

  }

@Override
public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
		throws IOException, ServletException {
    
    resolver.resolveException(request, response, null, authException);

}
  
}
