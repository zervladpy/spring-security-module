package com.zervladpy.spring_security_module.api.v1.user.dto;

/**
 * UserDto
 */
public record UserDto(
    String username,
    String email,
    boolean accountExpired,
    boolean accountLocked,
    boolean credentialsExpired) {
}
