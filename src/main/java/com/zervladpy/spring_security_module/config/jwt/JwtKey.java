package com.zervladpy.spring_security_module.config.jwt;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.zervladpy.spring_security_module.config.jwt.properties.JwtDataProperties;

/**
 * JwtKey
 */
@Component
public class JwtKey {

  private final JwtDataProperties data;

  public JwtKey(JwtDataProperties data) {
    this.data = data;
  }
   
  public SecretKey getKey() {
    byte[] decodedKey = Base64.getDecoder().decode(data.getSecret());
    return new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA384");
  }
}
