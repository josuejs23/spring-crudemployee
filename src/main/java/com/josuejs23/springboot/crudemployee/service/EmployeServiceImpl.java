package com.josuejs23.springboot.crudemployee.service;

import com.josuejs23.springboot.crudemployee.dao.EmployeeDAO;
import com.josuejs23.springboot.crudemployee.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeDAO.findById(id);
    }

    @Override
    @Transactional
    public Employee saveEmployee(Employee employee) {
        return this.employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public void deleteEmployeeByid(int id) {
        employeeDAO.deleteById(id);
    }
}
