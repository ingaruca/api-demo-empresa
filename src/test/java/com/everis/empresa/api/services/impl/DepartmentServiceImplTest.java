package com.everis.empresa.api.services.impl;

import com.everis.empresa.api.entities.Department;
import com.everis.empresa.api.exceptions.NotFoundException;
import com.everis.empresa.api.repositories.DepartmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceImplTest {

  @InjectMocks
  private DepartmentServiceImpl departmentService;

  @Mock
  private DepartmentRepository departmentRepository;

  private Department department;

  @Test
  public void findAll() {
    department = new Department();
    department.setDepartmentId((long) 1);
    department.setName("Technology");
    department.setUbication("2A");

    List<Department> departments = Collections.singletonList(department);
    given(departmentRepository.findAll()).willReturn(departments);
    assertEquals(departmentService.findAll(), departments);
  }

  @Test
  public void findById() {
    department = new Department();
    department.setDepartmentId((long)1);
    department.setName("Technology");
    department.setUbication("2A");

    given(departmentRepository.findById((long) 1)).willReturn(Optional.of(department));
    assertEquals(departmentService.findById((long)1), department);
  }

  @Test(expected = NotFoundException.class)
  public void findByIdNotFound() {
    departmentService.findById(1L);
  }

  @Test
  public void save() {
    department = new Department();
    department.setName("Technology");
    department.setUbication("2A");
    given(departmentRepository.save(department)).willReturn(department);
    assertEquals(departmentService.save(department), department);
  }

  @Test
  public void update() {
    department = new Department();
    department.setName("Technology");
    department.setUbication("2A");
    given(departmentRepository.findById(department.getDepartmentId())).willReturn(Optional.of(department));
    given(departmentRepository.save(department)).willReturn(department);
    assertEquals(departmentService.update(department), department);
  }

  @Test(expected = NotFoundException.class)
  public void updateNotFound() {
    departmentService.update(new Department());
  }

  @Test
  public void delete() {
    given(departmentRepository.findById(1L)).willReturn(Optional.of(new Department()));
    departmentService.delete(1L);
  }

  @Test(expected = NotFoundException.class)
  public void deleteNotFound() {
    departmentService.delete(1L);
  }
}