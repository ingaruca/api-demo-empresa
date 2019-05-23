package com.everis.empresa.api.services;

import com.everis.empresa.api.entities.Department;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface DepartmentService {

  Flowable<Department> findAll();

  Maybe<Department> findById(Long id);

  Single<Department> save(Department department);

  Single<Department> update(Department department);

  void delete(Long id);

}
