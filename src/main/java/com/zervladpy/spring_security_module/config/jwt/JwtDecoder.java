package com.zervladpy.spring_security_module.config.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

/**
 * JwtDecoder
 */
@Component
public class JwtDecoder {

  protected final JwtKey key;

  public JwtDecoder(JwtKey key) {
    this.key = key;
  }

  public String decode(String token) {

    if (isTokenExpired(token)) {
      throw new IllegalArgumentException("token expired");
    }
    return readUsername(token);
  }

  private String readUsername(String token) {
    return Jwts.parser().verifyWith(key.getKey()).build().parseSignedClaims(token).getPayload()
        .getOrDefault("username", "").toString();
  }

  private Date readIssuedDate(String token) {
    return Jwts.parser().verifyWith(key.getKey()).build().parseSignedClaims(token).getPayload().getIssuedAt();
  }

  private Date readExpirationDate(String token) {
    return Jwts.parser().verifyWith(key.getKey()).build().parseSignedClaims(token).getPayload().getExpiration();
  }

  private boolean isTokenExpired(String token) {
    return new Date().compareTo(readExpirationDate(token)) <= 0;
  }
}
