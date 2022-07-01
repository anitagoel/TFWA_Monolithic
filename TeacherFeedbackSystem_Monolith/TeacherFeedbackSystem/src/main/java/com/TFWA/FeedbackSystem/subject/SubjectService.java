package com.ishitwa.FeedbackSystem.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepo subjectRepo;

    public Subject saveSubject(Subject subject){
        subject.setName(subject.getName().toUpperCase());
        return subjectRepo.save(subject);
    }

    public Subject findSubjectById(long id){
        return subjectRepo.findSubjectBySubjectId(id);
    }

    public Subject findSubjectByName(String name){
        try{
            return subjectRepo.findSubjectByName(name);
        }catch (Exception e){
            return null;
        }
    }

    public List<Subject> getSubjectsByDepartment(long departmentId){
        return subjectRepo.findSubjectByDepartmentId(departmentId);
    }
}
