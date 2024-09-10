package com.zervladpy.spring_security_module.models;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Role
 */
@Entity
@Table(name = "security_roles")
public class Role implements GrantedAuthority {

  @Id
  private String id;

  private String name;

  public  Role() {
  }

  public Role(String name) {
    this.id = String.join("_", name.split(" "));
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getAuthority() {
    return id;
  }

}
