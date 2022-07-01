package com.ishitwa.FeedbackSystem.user;

import com.ishitwa.FeedbackSystem.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student,Long> {

    public Student findStudentByUsername(String username);
    public Student findStudentByStudentId(Long id);
    public Student findStudentByToken(String token);
    public List<Student> findStudentByClassId(long classId);

}
