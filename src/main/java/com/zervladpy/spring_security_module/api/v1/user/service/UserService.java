package com.zervladpy.spring_security_module.api.v1.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zervladpy.spring_security_module.repositories.UserRepository;

/**
 * UserService
 */
@Service
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }


}
