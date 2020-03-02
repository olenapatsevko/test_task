package test_task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test_task.dao.DepartmentDao;
import test_task.model.Department;
import test_task.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentDao repository;

    @Override
    public void save(Department department) {
        repository.save(department);
    }

    @Override
    public List<Long> findAllByDepartmentDoesntExceedThreePeople() {
        return repository.findAllWhereDepartmentDoesntExceedThreePeople();
    }

    @Override
    public List<Long> findAllByMaxTotalSalary() {
        return repository.findAllByMaxTotalSalary();
    }
}
