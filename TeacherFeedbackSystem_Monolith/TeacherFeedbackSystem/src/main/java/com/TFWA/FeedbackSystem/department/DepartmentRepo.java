package com.ishitwa.FeedbackSystem.department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department,Long> {
    public Department findDepartmentByDepartmentId(long id);
    public Department findDepartmentByDepartmentName(String name);
}
