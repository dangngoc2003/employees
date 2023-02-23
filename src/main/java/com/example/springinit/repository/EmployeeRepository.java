package com.example.springinit.repository;

import com.example.springinit.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class EmployeeRepository {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private EntityManager entityManager;
    public List<Employee> findAll(){
        String query="SELECT e FROM Employee AS e";
        TypedQuery<Employee> strQuery=entityManager.createQuery(query,Employee.class);
        List<Employee> employees=strQuery.getResultList();
        return employees;
    }
    public Employee findById(int id){
        String query="SELECT e FROM Employee AS e where e.id= :id";
        TypedQuery<Employee> strQuery=entityManager.createQuery(query,Employee.class);
        strQuery.setParameter("id",id);
        return  strQuery.getSingleResult();
    }
    public void create(Employee employee){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.save(employee);
        transaction.commit();
        session.close();
    }
    public void update(Employee employee){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.update(employee);
        transaction.commit();
        session.close();
    }
}
