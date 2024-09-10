package com.zervladpy.spring_security_module.models;

import java.util.Collection;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * User class for handling user data
 */
@Entity
@Table(name = "security_users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @ManyToMany
  private Collection<Role> roles = List.of();

  private boolean accountExpired = false;

  private boolean accountLocked = false;

  private boolean credentialsExpired = false;

  public User() {
  }

  public User(
      String username,
      String email,
      String password,
      Collection<Role> roles,
      boolean accountExpired,
      boolean accountLocked,
      boolean credentialsExpired) {

    this.id = "";
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = roles;
    this.accountLocked = accountLocked;
    this.accountExpired = accountExpired;
    this.credentialsExpired = credentialsExpired;
  }

  public User(
      String username,
      String email,
      String password,
      Collection<Role> roles) {
    this.id = "";
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  public User(
      String username,
      String email,
      String password) {
    this.id = "";
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Collection<Role> getRoles() {
    return roles;
  }

  public void setRoles(Collection<Role> roles) {
    this.roles = roles;
  }

  public boolean isAccountExpired() {
    return accountExpired;
  }

  public void setAccountExpired(boolean accountExpired) {
    this.accountExpired = accountExpired;
  }

  public boolean isAccountLocked() {
    return accountLocked;
  }

  public void setAccountLocked(boolean accountLocked) {
    this.accountLocked = accountLocked;
  }

  public boolean isCredentialsExpired() {
    return credentialsExpired;
  }

  public void setCredentialsExpired(boolean credentialsExpired) {
    this.credentialsExpired = credentialsExpired;
  }

}
