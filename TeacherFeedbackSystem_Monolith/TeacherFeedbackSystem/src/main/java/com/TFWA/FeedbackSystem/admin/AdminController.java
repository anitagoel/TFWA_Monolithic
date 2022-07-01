package com.ishitwa.FeedbackSystem.admin;

import com.ishitwa.FeedbackSystem.subject.Subject;
import com.ishitwa.FeedbackSystem.user.Teacher;
import com.ishitwa.FeedbackSystem.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/student/addTeacher/{teacherId}/{studentId}")
    public ResponseEntity<?> addTeacherToStudent(
            @PathVariable long teacherId,
            @PathVariable long studentId
    ){
        try{
            Teacher t=userService.addTeacherToStudent(
                    studentId,teacherId
            );
            return new ResponseEntity<>(t, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/student/addSubject/{subjectId}/{studentId}")
    public ResponseEntity<?> addSubjectToStudent(
            @PathVariable long subjectId,
            @PathVariable long studentId
    ){
        try{
            Subject s=userService.addSubjectToStudent(studentId,subjectId);
            return new ResponseEntity<>(s,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/teacher/addSubject/{subjectId}/{teacherId}")
    public ResponseEntity<?> addSubjectToTeacher(
            @PathVariable long subjectId,
            @PathVariable long teacherId
    ){
        try{
            Subject s=userService.addSubject(teacherId,subjectId);
            return new ResponseEntity<>(s,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
