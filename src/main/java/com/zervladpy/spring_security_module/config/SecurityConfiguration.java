package com.zervladpy.spring_security_module.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.zervladpy.spring_security_module.config.entry_points.JwtEntryPoint;
import com.zervladpy.spring_security_module.config.entry_points.JwtErrorHandler;
import com.zervladpy.spring_security_module.config.filters.JwtAuthenticationFilter;

/**
 * SecurityConfiguration
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

  private final JwtErrorHandler jwtErrorHandler;
  private final JwtEntryPoint jwtEntryPoint;
  private final JwtAuthenticationFilter jwtFilter;

  public SecurityConfiguration(
      JwtErrorHandler jwtErrorHandler,
      JwtAuthenticationFilter jwtFilter,
      JwtEntryPoint jwtEntryPoint) {
    this.jwtErrorHandler = jwtErrorHandler;
    this.jwtEntryPoint = jwtEntryPoint;
    this.jwtFilter = jwtFilter;

  }

  /**
   * Manages security access
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    // CSRF
    // TODO: Enable in prodiction
    http.csrf(CsrfConfigurer::disable);

    // CORS
    // TODO: Enable in prodiction
    http.cors(CorsConfigurer::disable);

    // Exception Handler
    http.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtEntryPoint));

    //
    http.httpBasic(Customizer.withDefaults());

    // Set Authorzed Paths
    http.authorizeHttpRequests((request) -> {
      request
          .requestMatchers(HttpMethod.POST, "/api/v1/oauth").permitAll()
          .requestMatchers(HttpMethod.POST, "/api/v1/oauth/register").permitAll()
          .anyRequest().authenticated();
    });

    // Add Filters
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    // http.addFilterBefore(jwtErrorHandler, JwtAuthenticationFilter.class);

    return http.build();
  }

  /**
   * @return
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

}
