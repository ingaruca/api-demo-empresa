package com.everis.empresa.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long departmentId;

  @Column(name = "name")
  private String name;

  @Column(name = "ubication")
  private String ubication;

  public Department() {

  }

  public Department(Long departmentId, String name, String ubication) {
    this.departmentId = departmentId;
    this.name = name;
    this.ubication = ubication;
  }

  public Long getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Long departmentId) {
    this.departmentId = departmentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUbication() {
    return ubication;
  }

  public void setUbication(String ubication) {
    this.ubication = ubication;
  }
}
