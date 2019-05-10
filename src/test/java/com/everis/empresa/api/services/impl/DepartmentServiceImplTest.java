package com.everis.empresa.api.services.impl;

import com.everis.empresa.api.entities.Department;
import com.everis.empresa.api.repositories.DepartmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceImplTest {

  @InjectMocks
  private DepartmentServiceImpl departmentService;

  @Mock
  private DepartmentRepository departmentRepository;

  @Test
  public void findAll() {
    Department department = new Department();
    department.setDepartmentId((long) 1);
    department.setName("Technology");
    department.setUbication("2A");

    List<Department> departments = Collections.singletonList(department);
    given(departmentRepository.findAll()).willReturn(departments);
    assertEquals(departmentService.findAll(), departments);
  }

  @Test
  public void findById() {
    Department department = new Department();
    department.setDepartmentId((long) 1);
    department.setName("Technology");
    department.setUbication("2A");

    given(departmentRepository.findById((long) 1)).willReturn(Optional.of(department));
    assertEquals(departmentService.findById((long)1), department);
  }

  @Test
  public void save() {
    Department department = new Department();
    department.setName("Technology");
    department.setUbication("2A");

    departmentRepository.save(department);
  }

  @Test
  public void update() {
    Department department = new Department();
    department.setDepartmentId((long) 1);
    department.setName("Technology");
    department.setUbication("2A");

    Department updatedDepartment = new Department();
    updatedDepartment.setName("Mechanic");
    updatedDepartment.setUbication("2B");

    departmentService.update(updatedDepartment, (long) 1);
  }

  @Test
  public void delete() {
    Department department = new Department();
    department.setDepartmentId((long) 1);
    department.setName("Technology");
    department.setUbication("2A");

    departmentService.delete((long) 1);
  }
}