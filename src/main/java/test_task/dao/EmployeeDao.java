package test_task.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test_task.model.Employee;

import java.util.List;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {

    //TODO Get a list of employees receiving a salary greater than that of the boss
    @Query(
            value = "SELECT * FROM employee AS a " +
                    "WHERE a.salary > (" +
                    "SELECT salary FROM employee AS b " +
                    "WHERE b.id = a.boss_id)",
            nativeQuery = true)
    List<Employee> findAllWhereSalaryGreaterThatBoss();

    //TODO Get a list of employees receiving the maximum salary in their department
    @Query(
            value = "SELECT a.id as id , a.name as name , a.salary as salary , a.boss_id as boss_id, a.department_id " +
                    "FROM employee AS a " +
                    "WHERE a.salary IN (" +
                    "SELECT max(b.salary) FROM employee AS b " +
                    "INNER JOIN department d ON b.department_id = d.id " +
                    "GROUP BY d.id) ",
            nativeQuery = true)
    List<Employee> findAllByMaxSalary();

    //TODO Get a list of employees who do not have boss in the same department
    @Query(
            value = "SELECT a.id as id , a.name as name , a.salary as salary , a.boss_id as boss_id, a.department_id as department_id " +
                    "FROM employee AS a" +
                    "WHERE a.department_id not in (" +
                    "SELECT b.department_id FROM employee AS b" +
                    "WHERE b.id = a.boss_id)",
            nativeQuery = true)
    List<Employee> findAllWithoutBoss();

}
