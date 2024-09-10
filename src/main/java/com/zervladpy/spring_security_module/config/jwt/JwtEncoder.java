package com.zervladpy.spring_security_module.config.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.zervladpy.spring_security_module.config.details.ImplUserDetails;
import com.zervladpy.spring_security_module.config.jwt.properties.JwtTimeProperties;
import io.jsonwebtoken.Jwts;

/**
 * JwtEncoder creates a Token for Authentication
 */
@Component
public class JwtEncoder {

  private final JwtTimeProperties jwtTime;
  protected final JwtKey key;

  @Autowired
  public JwtEncoder(
      JwtTimeProperties jwtTime,
      JwtKey key) {
    this.jwtTime = jwtTime;
    this.key = key;
  }

  public String encode(Authentication auth) {

    Date issued = new Date();
    Date expiration = new Date(issued.getTime() + jwtTime.getTime());

    ImplUserDetails principal = (ImplUserDetails) auth.getPrincipal();

    Map<String, Object> claims = buildClaims(principal);

    return Jwts.builder().subject(principal.getUsername())
        .claims(claims)
        .issuedAt(issued)
        .expiration(expiration)
        .signWith(key.getKey())
        .compact();
  }

  private HashMap<String, Object> buildClaims(UserDetails details) {

    HashMap<String, Object> claims = new HashMap<>();

    claims.put("username", details.getUsername());
    claims.put("roles", details.getAuthorities());

    return claims;
  }

}
