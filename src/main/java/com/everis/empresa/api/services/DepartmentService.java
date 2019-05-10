package com.everis.empresa.api.services;

import com.everis.empresa.api.entities.Department;

import java.util.List;

public interface DepartmentService {

  List<Department> findAll();

  Department findById(Long id);

  Department save(Department department);

  Department update(Department department);

  void delete(Long id);

}
