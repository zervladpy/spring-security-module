package com.zervladpy.spring_security_module.config.jwt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * JwtTimeProperties
 */
@Validated
@Configuration
@ConfigurationProperties(prefix = "jwt.properties.time")
public class JwtTimeProperties {

  private Integer days = 0;
  private Integer hours = 0;
  private Integer minutes = 0;
  private Integer seconds = 0;

  public JwtTimeProperties() {
  }

  public JwtTimeProperties(
      Integer days,
      Integer hours,
      Integer minutes,
      Integer seconds) {
    this.days = days;
    this.hours = hours;
    this.minutes = minutes;
    this.seconds = seconds;
  }

  public void setDays(Integer days) {
    this.days = days;
  }

  public void setHours(Integer hours) {
    this.hours = hours;
  }

  public void setMinutes(Integer minutes) {
    this.minutes = minutes;
  }

  public void setSeconds(Integer seconds) {
    this.seconds = seconds;
  }

  public long getTime() {

    long time = ((long) days * 24 * 60 * 60 * 60);
    time += (hours * 60 * 60 * 60);
    time += (minutes * 60 * 60);
    time += (seconds * 60);

    return time;
  }

}
