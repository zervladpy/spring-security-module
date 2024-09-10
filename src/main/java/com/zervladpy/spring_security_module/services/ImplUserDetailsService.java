package com.zervladpy.spring_security_module.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zervladpy.spring_security_module.config.details.ImplUserDetails;
import com.zervladpy.spring_security_module.models.User;
import com.zervladpy.spring_security_module.repositories.UserRepository;

/**
 * ImplUserDetailService
 */
@Service
public class ImplUserDetailsService implements UserDetailsService {

  private final UserRepository repository;

  public ImplUserDetailsService(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

    Optional<User> user = repository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

    if (user.isEmpty()) {
      throw new UsernameNotFoundException(usernameOrEmail + " : not found");
    }

    User foundUser = user.get();

    ImplUserDetails details = ImplUserDetails.builder()
        .username(foundUser.getUsername())
        .email(foundUser.getEmail())
        .password(foundUser.getPassword())
        .accountLocked(foundUser.isAccountLocked())
        .accountExpired(foundUser.isAccountExpired())
        .credentialsExpired(foundUser.isCredentialsExpired())
        .build();

    return details;

  }

}
