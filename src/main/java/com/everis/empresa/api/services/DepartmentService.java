package com.everis.empresa.api.services;

import com.everis.empresa.api.entities.Department;

import java.util.List;

public interface DepartmentService {

  List<Department> findAll();

  Department findById(Long id);

  void save(Department department);

  void update(Department department, Long id);

  void delete(Long id);

}
