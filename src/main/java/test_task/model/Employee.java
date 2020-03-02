package test_task.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn
    private Department department;
    @ManyToOne
    @JoinColumn
    private Employee boss;
    private String name;
    private BigDecimal salary;


    public Employee(Department department, Employee boss, String name, BigDecimal salary) {
        this.department = department;
        this.boss = boss;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("%-5s%-20s%-15s", id, name, salary);
    }
}
