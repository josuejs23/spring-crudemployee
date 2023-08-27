package com.josuejs23.springboot.crudemployee.dao;

import com.josuejs23.springboot.crudemployee.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    public List<Employee> findAll();
    public Employee findById(int id);
    public Employee save(Employee employee);
    public void deleteById(int id);
}
