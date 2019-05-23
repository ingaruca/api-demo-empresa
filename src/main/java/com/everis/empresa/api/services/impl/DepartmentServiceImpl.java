package com.everis.empresa.api.services.impl;

import com.everis.empresa.api.entities.Department;
import com.everis.empresa.api.exceptions.NotFoundException;
import com.everis.empresa.api.repositories.DepartmentRepository;
import com.everis.empresa.api.services.DepartmentService;

import java.util.List;
import java.util.Optional;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

  private DepartmentRepository departmentRepository;

  @Autowired
  public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public Flowable<Department> findAll() {
    return Flowable.fromIterable(departmentRepository.findAll());
  }

  @Override
  public Maybe<Department> findById(Long id) throws NotFoundException {
    return Maybe.just(departmentRepository.findById(id))
            .map(department -> department.get());
  }

  @Override
  public Single<Department> save(Department department) {
    return Single.just(departmentRepository.save(department));
  }

  @Override
  public Single<Department> update(Department department) {
    Long departmentId = department.getDepartmentId();
    Optional<Department> currentDepartment = departmentRepository.findById(departmentId);
    currentDepartment.get().setName(department.getName());
    currentDepartment.get().setUbication(department.getUbication());
    return Single.just(departmentRepository.save(currentDepartment.get()));
  }

  @Override
  public void delete(Long id) {
    departmentRepository.deleteById(id);
  }
}
