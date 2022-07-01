package com.ishitwa.FeedbackSystem.class_m;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepo extends JpaRepository<Class,Long> {
    public Class findClassByClassId(long id);
    public List<Class> findClassByBranch(String branch);
    public List<Class> findClassByDepartmentId(long id);
}
