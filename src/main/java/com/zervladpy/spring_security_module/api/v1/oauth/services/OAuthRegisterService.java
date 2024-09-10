package com.zervladpy.spring_security_module.api.v1.oauth.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zervladpy.spring_security_module.api.v1.oauth.dto.OAuthRegisterDto;
import com.zervladpy.spring_security_module.config.jwt.JwtEncoder;
import com.zervladpy.spring_security_module.models.User;
import com.zervladpy.spring_security_module.repositories.UserRepository;

/**
 * OAuthRegisterService
 */
@Service
public class OAuthRegisterService {

  private final AuthenticationManager authManager;
  private final JwtEncoder jwtEncoder;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository repository;

  @Autowired
  public OAuthRegisterService(
      AuthenticationManager authManager,
      JwtEncoder jwtEncoder,
      PasswordEncoder passwordEncoder,
      UserRepository repository) {
    this.authManager = authManager;
    this.jwtEncoder = jwtEncoder;
    this.passwordEncoder = passwordEncoder;
    this.repository = repository;
  }

  public String oAuthRegister(OAuthRegisterDto dto) {

    Optional<User> registerdUser = repository.findByUsernameOrEmail(dto.username(), dto.email());

    if (registerdUser.isPresent()) {
      throw new IllegalArgumentException("Username or Email already registerd");
    }

    User user = new User(dto.username(), dto.email(), passwordEncoder.encode(dto.password()));

    repository.save(user);

    UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
        dto.username().isEmpty() ? dto.email() : dto.username(),
        dto.password());

    Authentication auth = authManager.authenticate(credentials);

    SecurityContextHolder.getContext().setAuthentication(auth);

    return jwtEncoder.encode(auth);
  }

}
