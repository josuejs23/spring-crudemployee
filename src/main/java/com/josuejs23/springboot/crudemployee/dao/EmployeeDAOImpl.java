package com.josuejs23.springboot.crudemployee.dao;

import com.josuejs23.springboot.crudemployee.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee findById(int id) {
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee WHERE id = :id", Employee.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public Employee save(Employee employee) {
        System.out.println(employee);
        return entityManager.merge(employee);
    }

    @Override
    public void deleteById(int id) {
        Employee employee = this.findById(id);
        entityManager.remove(employee);
    }
}
