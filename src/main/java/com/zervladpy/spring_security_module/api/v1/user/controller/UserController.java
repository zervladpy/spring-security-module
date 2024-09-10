package com.zervladpy.spring_security_module.api.v1.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zervladpy.spring_security_module.api.v1.user.service.UserService;

/**
 * UserController
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService service;

  @Autowired
  public UserController(UserService service) {
    this.service = service;
  };

  @GetMapping("")
  public String getUsers() {
    System.out.println("Reached");
    return "users endpoint";
  }

  @GetMapping("/{username}")
  public String getByUsername(@PathVariable("username") String username) {
    return username;
  }

}
