package com.zervladpy.spring_security_module.config.jwt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * JwtDataProperties
 */
@Validated
@Configuration
@ConfigurationProperties(prefix = "jwt.properties.data")
public class JwtDataProperties {

  private String secret;

  public JwtDataProperties() {
  }

  public JwtDataProperties(String secret) {
    this.secret = secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getSecret() {
    return secret;
  }

}
