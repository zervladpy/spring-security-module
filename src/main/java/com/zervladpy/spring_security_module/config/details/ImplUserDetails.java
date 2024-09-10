package com.zervladpy.spring_security_module.config.details;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.zervladpy.spring_security_module.models.Role;
import com.zervladpy.spring_security_module.models.User;

/**
 * ImplUserDetails
 */
public class ImplUserDetails extends User implements UserDetails {

  private ImplUserDetails() {
  }

  public ImplUserDetails(String username, String email, String password) {
    super(username, email, password);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.getRoles();
  }

  static public Builder builder() {
    return new Builder();
  }

  static public class Builder {

    private ImplUserDetails obj;

    public Builder() {
      obj = new ImplUserDetails();
    }

    public Builder username(String username) {
      obj.setUsername(username);
      return this;
    }

    public Builder password(String password) {
      obj.setPassword(password);
      return this;
    }

    public Builder email(String email) {
      obj.setEmail(email);
      return this;
    }

    public Builder accountLocked(boolean accountLocked) {
      obj.setAccountLocked(accountLocked);
      return this;
    }

    public Builder accountExpired(boolean accountExpired) {
      obj.setAccountExpired(accountExpired);
      return this;
    }

    public Builder credentialsExpired(boolean credentialsExpired) {
      obj.setCredentialsExpired(credentialsExpired);
      return this;
    }

    public Builder roles(List<Role> roles) {
      obj.setRoles(roles);
      return this;
    }

    public ImplUserDetails build() {
      return obj;
    }

  }
}
