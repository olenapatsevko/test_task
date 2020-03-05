package test_task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test_task.dao.EmployeeDao;
import test_task.model.Employee;
import test_task.service.EmployeeService;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public static final BigDecimal SALARY_INCREMENT = BigDecimal.valueOf(100);

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public List<Employee> findAllBySalaryGreaterThatBoss() {
        return employeeDao.findAllWhereSalaryGreaterThatBoss();
    }

    @Override
    public List<Employee> findAllByMaxSalary() {
        return employeeDao.findAllByMaxSalary();
    }

    @Override
    public List<Employee> findAllWithoutBoss() {
        return employeeDao.findAllWithoutBoss();
    }

    @Override
    public Long fireEmployee(String name) {
        Iterable<Employee> employees = employeeDao.findAll();
        
        //TODO Implement method using Collection
        // ---write your code here

        Iterator<Employee> employeeIterator = employees.iterator();
        Employee employee = null;
        while (employeeIterator.hasNext()) {
            employee = employeeIterator.next();
            if (employee.getName().equals(name)) {
                employeeDao.deleteById(employee.getId());
            }
        }
        return employee == null? 0L : employee.getId();
    }

    @Override
    public Long changeSalary(String name) {
        Iterable<Employee> employees = employeeDao.findAll();
        //TODO Implement method using Collection
        // ---write your code here

        Iterator<Employee> employeeIterator = employees.iterator();
        Employee employee;

        while (employeeIterator.hasNext()) {
            employee = employeeIterator.next();
            if (employee.getName().equals(name)) {
                employee.setSalary(employee.getSalary().add(SALARY_INCREMENT));
                return employeeDao.save(employee).getId();
            }
        }
        return 0L;
    }

    @Override
    public Long hireEmployee(Employee employee) {
        //TODO Implement method using Collection and DAO
        // ---write your code here
        return employeeDao.save(employee).getId();
    }
}
