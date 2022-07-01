package com.ishitwa.FeedbackSystem.user;

import com.ishitwa.FeedbackSystem.user.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepo extends JpaRepository<Teacher,Long> {

    public Teacher findTeacherByUsername(String username);

    public Teacher findTeacherByTeacherId(Long id);

    public Teacher findTeacherByToken(String token);

    public List<Teacher> findTeacherByDepartmentId(long id);

    public List<Teacher> findTop5ByOrderByAveragePointsDesc();


}
