package test_task.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test_task.model.Department;

import java.util.List;

@Repository
public interface

DepartmentDao extends CrudRepository<Department, Long> {
    //TODO Get a list of department IDS where the number of employees doesn't exceed 3 people
    @Query(
            value = "select d.id from department as d inner join employee as e on d.id = e.department_id " +
                    " group by d.id " +
                    " having count(e.id) <= 3",
            nativeQuery = true)
    List<Long> findAllWhereDepartmentDoesntExceedThreePeople();

    //TODO Get a list of departments IDs with the maximum total salary of employees
    @Query(
            value = "select id\n" +
                    "from (\n" +
                    "         select d.id as id, sum(e.salary) as salary_sum\n" +
                    "         from department as d\n" +
                    "                  join employee as e\n" +
                    "                       on d.id = e.department_id\n" +
                    "         group by d.id)\n" +
                    "         as temp\n" +
                    "where salary_sum = (Select max(salary_sum)\n" +
                    "                    from (\n" +
                    "                             select d.id as id, sum(e.salary) as salary_sum\n" +
                    "                             from department as d\n" +
                    "                                      join employee as e\n" +
                    "                                           on d.id = e.department_id\n" +
                    "                             group by d.id)\n" +
                    "                             as temp)",
            nativeQuery = true)
    List<Long> findAllByMaxTotalSalary();
}
