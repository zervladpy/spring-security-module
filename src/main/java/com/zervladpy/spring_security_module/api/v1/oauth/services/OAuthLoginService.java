package com.zervladpy.spring_security_module.api.v1.oauth.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.zervladpy.spring_security_module.api.v1.oauth.controllers.OAuthLoginController;
import com.zervladpy.spring_security_module.api.v1.oauth.dto.OAuthLoginDto;
import com.zervladpy.spring_security_module.config.jwt.JwtEncoder;
import com.zervladpy.spring_security_module.models.User;
import com.zervladpy.spring_security_module.repositories.UserRepository;

/**
 * OAuthLoginService - Manages Login {@link OAuthLoginController} requests.
 */
@Service
public class OAuthLoginService {

  private final UserRepository repository;
  private final AuthenticationManager authManager;
  private final JwtEncoder jwtEncoder;

  @Autowired
  public OAuthLoginService(
      UserRepository repository,
      AuthenticationManager authManager,
      JwtEncoder jwtEncoder) {
    this.repository = repository;
    this.authManager = authManager;
    this.jwtEncoder = jwtEncoder;
  }

  /**
   * @param dto - Login Parameters.
   * @return new Jason Web Token.
   */
  public String oAuthLogin(OAuthLoginDto dto) {

    Optional<User> user = repository.findByUsernameOrEmail(dto.username(), dto.username());

    if (!user.isPresent()) {
      throw new RuntimeException("username-or-email-not-present");
    }

    UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
        dto.username(),
        dto.password());

    Authentication auth = authManager.authenticate(credentials);

    SecurityContextHolder.getContext().setAuthentication(auth);

    return jwtEncoder.encode(auth);

  }
}
