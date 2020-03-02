package test_task.service;

import test_task.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllBySalaryGreaterThatBoss();

    List<Employee> findAllByMaxSalary();

    List<Employee> findAllWithoutBoss();

    Long fireEmployee(String name);

    Long changeSalary(String name);

    Long hireEmployee(Employee employee);
}
