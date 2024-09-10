package com.zervladpy.spring_security_module.api.v1.oauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zervladpy.spring_security_module.api.v1.oauth.dto.OAuthLoginDto;
import com.zervladpy.spring_security_module.api.v1.oauth.services.OAuthLoginService;

import jakarta.servlet.http.HttpServletResponse;

/**
 * OAuthLoginController
 */
@RestController()
public class OAuthLoginController {

  private final String AUTH_PREFIX = "Bearer ";

  private final OAuthLoginService service;

  @Autowired
  public OAuthLoginController(OAuthLoginService service) {
    this.service = service;
  }

  @PostMapping("api/v1/oauth")
  public void oAuthLogin(
      HttpServletResponse response,
      @RequestBody OAuthLoginDto dto) {

    String jwtToken = service.oAuthLogin(dto);

    response.setHeader(HttpHeaders.AUTHORIZATION, AUTH_PREFIX + jwtToken);
  }

}
