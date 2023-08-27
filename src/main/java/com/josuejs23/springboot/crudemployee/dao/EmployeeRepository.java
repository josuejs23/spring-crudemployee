package com.josuejs23.springboot.crudemployee.dao;

import com.josuejs23.springboot.crudemployee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// Sorting http://localhost:8080/api/members?sort=lastName,desc
// Sorting http://localhost:8080/api/members?sort=lastName,firstName,desc
@RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
