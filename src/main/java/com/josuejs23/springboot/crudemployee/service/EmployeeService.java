package com.josuejs23.springboot.crudemployee.service;

import com.josuejs23.springboot.crudemployee.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
    Employee getEmployeeById(int id);
    Employee saveEmployee(Employee employee);
    void deleteEmployeeByid(int id);
}
