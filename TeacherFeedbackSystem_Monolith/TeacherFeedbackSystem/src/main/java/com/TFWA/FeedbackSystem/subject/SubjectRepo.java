package com.ishitwa.FeedbackSystem.subject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepo extends JpaRepository<Subject,Long> {

    public Subject findSubjectBySubjectId(long id);
    public Subject findSubjectByName(String name);
    public List<Subject> findSubjectByDepartmentId(long id);
}
