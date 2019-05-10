package com.everis.empresa.api.services.impl;

import com.everis.empresa.api.entities.Department;
import com.everis.empresa.api.exceptions.NotFoundException;
import com.everis.empresa.api.repositories.DepartmentRepository;
import com.everis.empresa.api.services.DepartmentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentRepository departmentRepository;

  @Autowired
  public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public List<Department> findAll() {
    return departmentRepository.findAll();
  }

  @Override
  public Department findById(Long id) throws NotFoundException {
    return departmentRepository.findById(id)
            .orElseThrow(() ->
                    new NotFoundException("0001", "Could not found department with id " + id));
  }

  @Override
  public Department save(Department department) {
    return departmentRepository.save(department);
  }

  @Override
  public Department update(Department department) {
    Long departmentId = department.getDepartmentId();
    Department currentDepartment = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new NotFoundException("0001",
                    "Could not found department with id " + departmentId));
    currentDepartment.setName(department.getName());
    currentDepartment.setUbication(department.getUbication());
    return departmentRepository.save(currentDepartment);
  }

  @Override
  public void delete(Long id) {
    departmentRepository.findById(id)
            .orElseThrow(() ->
                    new NotFoundException("0001", "Could not found department with id " + id));
    departmentRepository.deleteById(id);
  }
}
