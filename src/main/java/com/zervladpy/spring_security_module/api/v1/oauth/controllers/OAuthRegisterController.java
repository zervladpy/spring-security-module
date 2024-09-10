package com.zervladpy.spring_security_module.api.v1.oauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zervladpy.spring_security_module.api.v1.oauth.dto.OAuthRegisterDto;
import com.zervladpy.spring_security_module.api.v1.oauth.services.OAuthRegisterService;

import jakarta.servlet.http.HttpServletResponse;

/**
 * OAuthRegisterController
 */
@RestController()
public class OAuthRegisterController {

  private final String AUTH_PREFIX = "Bearer ";

  private final OAuthRegisterService serivce;

  @Autowired
  public OAuthRegisterController(OAuthRegisterService service) {
    this.serivce = service;
  }

  @PostMapping("api/v1/oauth/register")
  public void oAuthRegister(
      HttpServletResponse response,
      @RequestBody OAuthRegisterDto dto) {

    String jwtToken = serivce.oAuthRegister(dto);

    response.setHeader(HttpHeaders.AUTHORIZATION, AUTH_PREFIX + jwtToken);

  }

}
