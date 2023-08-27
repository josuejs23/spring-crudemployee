package com.josuejs23.springboot.crudemployee.dao;

import com.josuejs23.springboot.crudemployee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
