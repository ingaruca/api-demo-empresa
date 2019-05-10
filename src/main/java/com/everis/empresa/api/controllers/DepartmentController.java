package com.everis.empresa.api.controllers;

import com.everis.empresa.api.entities.Department;
import com.everis.empresa.api.services.DepartmentService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

  @Autowired
  private DepartmentService departmentService;

  @GetMapping("/departments")
  List<Department> getAll() {
    return departmentService.findAll();
  }

  @PostMapping("/departments")
  @ResponseStatus(HttpStatus.CREATED)
  void create(@Valid @RequestBody Department department) {
    departmentService.save(department);
  }

  @GetMapping("/departments/{id}")
  Department findById(@PathVariable Long id) {
    return departmentService.findById(id);
  }

  @PutMapping("/departments")
  void update(@Valid @RequestBody Department department) {
    departmentService.update(department);
  }

  @DeleteMapping("/departments/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void delete(@PathVariable Long id) {
    departmentService.delete(id);
  }

}
