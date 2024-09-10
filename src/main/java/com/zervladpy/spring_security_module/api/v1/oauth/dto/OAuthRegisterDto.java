package com.zervladpy.spring_security_module.api.v1.oauth.dto;

/**
 * OAuthRegisterDto
 */
public record OAuthRegisterDto(String username, String email, String password) {
}
