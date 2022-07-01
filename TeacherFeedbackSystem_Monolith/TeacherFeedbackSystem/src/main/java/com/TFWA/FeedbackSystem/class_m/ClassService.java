package com.ishitwa.FeedbackSystem.class_m;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    @Autowired
    private ClassRepo classRepo;

    public void saveClass(Class c){
        c.setBranch(c.getBranch().toUpperCase());
        classRepo.save(
                c
        );
    }

    public Class getClassFromId(long id){
        return classRepo.findClassByClassId(id);
    }

    public List<Class> getClassesFromBranch(String branch){
        branch=branch.toUpperCase();
        return classRepo.findClassByBranch(branch);
    }

//    public Class updateDepartment(long classId,long departmentId){
//        Class c=getClassFromId(classId);
//        c.setDepartmentId(departmentId);
//        return classRepo.save(c);
//    }
//    redundant

    public List<Class> getClassesByDepartment(long departmentId){
        return classRepo.findClassByDepartmentId(departmentId);
    }

    public List<Class> getAllClasses(){
        return classRepo.findAll();
    }

}
