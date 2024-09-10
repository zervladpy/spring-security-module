package com.zervladpy.spring_security_module.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zervladpy.spring_security_module.models.User;

/**
 * UserDetailRepository
 */
public interface UserRepository extends JpaRepository<User, String> {
 
  public Optional<User> findByUsernameOrEmail(String username, String email);

}
