package com.ishitwa.FeedbackSystem.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    public Department saveDepartment(Department department){
        department.setDepartmentName(department.getDepartmentName().toUpperCase());
        return departmentRepo.save(department);
    }

    public Department getDepartmentById(long id){
        return departmentRepo.findDepartmentByDepartmentId(id);
    }

    public Department getDepartmentByName(String name){
        name = name.toUpperCase();
        return departmentRepo.findDepartmentByDepartmentName(name);
    }

    public List<Department> getAllDepartments(){
        return departmentRepo.findAll();
    }

}
