package test_task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import test_task.model.Department;
import test_task.model.Employee;
import test_task.service.DepartmentService;
import test_task.service.EmployeeService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        fillDatabase();

        log.info("1) Get a list of employees receiving a salary greater than that of the boss...");
        outputResult(employeeService.findAllBySalaryGreaterThatBoss());

        log.info("2) Get a list of departments IDs where the number of employees doesn't exceed 3 people...");
        List<Long> allByDepartmentDoesntExceedThreePeople = departmentService.findAllByDepartmentDoesntExceedThreePeople();
        log.info(allByDepartmentDoesntExceedThreePeople.toString());

        log.info("3) Get a list of departments IDs with the maximum total salary of employees...");
        List<Long> allByMaxTotalSalary = departmentService.findAllByMaxTotalSalary();
        log.info(allByMaxTotalSalary.toString());

        log.info("4) Get a list of employees receiving the maximum salary in their department...");
        outputResult(employeeService.findAllByMaxSalary());

        log.info("5) Get a list of employees who do not have boss in the same department...");
        outputResult(employeeService.findAllWithoutBoss());

        log.info("6) Fire Dayna Whitworth and get her ID...");
        log.info(employeeService.fireEmployee("Dayna Whitworth").toString());

        log.info("7) Change salary for Kelis Andrews and get his ID...");
        log.info(employeeService.changeSalary("Kelis Andrews").toString());

        log.info("8) Hire new employee in IT department and get his new ID...");
        log.info(employeeService.hireEmployee(new Employee()).toString());

    }

    private void outputResult(List<Employee> employeesList) {
        employeesList.forEach(employee -> log.info(employee.toString()));
    }

    private void fillDatabase() {
        log.info("database initialization started...");

        Department department1 = new Department("IT");
        Department department2 = new Department("Accounting");
        Department department3 = new Department("Management");

        Employee employee1 = new Employee(department1, null, "Ralphy Mcneill", new BigDecimal(10000));
        Employee employee2 = new Employee(department2, null, "Kieran Morton", new BigDecimal(20000));
        Employee employee3 = new Employee(department3, null, "Jayden Cruz", new BigDecimal(30000));

        Employee employee1_1 = new Employee(department1, employee1, "Libby Garner", new BigDecimal(1000));
        Employee employee1_2 = new Employee(department1, employee1, "Valentino Brady", new BigDecimal(20000));
        Employee employee1_3 = new Employee(department1, employee1, "Nour Wagstaff", new BigDecimal(3000));

        Employee employee2_1 = new Employee(department2, employee2, "Zishan Nash", new BigDecimal(1200));
        Employee employee2_2 = new Employee(department2, employee2, "Dayna Whitworth", new BigDecimal(2300));
        Employee employee2_3 = new Employee(department2, employee2, "Kelis Andrews", new BigDecimal(3400));

//        Employee employee3_1 = new Employee(department3, employee3, "Keanu Romero", new BigDecimal(1250));
        Employee employee3_2 = new Employee(department3, employee3, "Macauley Wells", new BigDecimal(2360));
        Employee employee3_3 = new Employee(department3, employee3, "Hollie Hawkins", new BigDecimal(34700));

        department1.setEmployees(new HashSet<>());
        department1.getEmployees().add(employee1);
        department1.getEmployees().add(employee1_1);
        department1.getEmployees().add(employee1_2);
        department1.getEmployees().add(employee1_3);

        department2.setEmployees(new HashSet<>());
        department2.getEmployees().add(employee2);
        department2.getEmployees().add(employee2_1);
        department2.getEmployees().add(employee2_2);
        department2.getEmployees().add(employee2_3);

        department3.setEmployees(new HashSet<>());
        department3.getEmployees().add(employee3);
//        department3.getEmployees().add(employee3_1);
        department3.getEmployees().add(employee3_2);
        department3.getEmployees().add(employee3_3);

        departmentService.save(department1);
        departmentService.save(department2);
        departmentService.save(department3);

        log.info("database initialization finished...");
    }
}
