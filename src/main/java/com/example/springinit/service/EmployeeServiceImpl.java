package com.example.springinit.service;

import com.example.springinit.ICrudService;
import com.example.springinit.model.Employee;
import com.example.springinit.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeServiceImpl implements ICrudService<Employee> {
    @Autowired
            private EmployeeRepository employeeRepository;
    List<Employee> employees;
    public EmployeeServiceImpl(){
        employees=new ArrayList<>();
        employees.add(new Employee("Jinx",23,20D,"HaNoi"));
    }
    @Override
    public List findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void save(Employee employee) {
       employeeRepository.create(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeRepository.update(employee);
    }

    @Override
    public void deleteById(Integer id) {
Employee employee=findById(id);
employees.remove(employee);
    }
}
