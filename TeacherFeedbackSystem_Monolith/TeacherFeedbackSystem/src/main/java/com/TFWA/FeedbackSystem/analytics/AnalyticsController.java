package com.ishitwa.FeedbackSystem.analytics;

import com.ishitwa.FeedbackSystem.feedback.Feedback;
import com.ishitwa.FeedbackSystem.subject.Subject;
import com.ishitwa.FeedbackSystem.user.Teacher;
import com.ishitwa.FeedbackSystem.feedback.FeedbackService;
import com.ishitwa.FeedbackSystem.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserService userService;

    @PostMapping("/feedbacks/teacher/{id}")
    public ResponseEntity<?> getFeedbacksByTeacher(@PathVariable long id){
        try {
            List<Feedback> feedbacks = feedbackService.getFeedbackOfTeacher(id);
            return new ResponseEntity<>(feedbacks, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    "Feedback not found!",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("/feedbacks/subject/{id}")
    public ResponseEntity<?> getFeedbacksBySubject(@PathVariable long id){
        try{
            List<Feedback> feedbacks=feedbackService.getFeedbackBySubjects(id);
            return new ResponseEntity<>(feedbacks,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    "Feedbacks not found!",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("/feedbacks/teacher/subject/{teacherId}/{subjectId}")
    public ResponseEntity<?> getFeedbackByTeacherAndSubject(
            @PathVariable long teacherId,
            @PathVariable long subjectId
    ){
        try{
            List<Feedback> feedbacks=feedbackService.getFeedbackByTeacherAndSubject(teacherId,subjectId);
            return new ResponseEntity<>(feedbacks,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    "Feedbacks not found!",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("/feedbacks/class/{id}")
    public ResponseEntity<?> getFeedbackByClass(@PathVariable long id){
        try{
            List<Feedback> feedbacks=feedbackService.getFeedbackByClass(id);
            return new ResponseEntity<>(
                    feedbacks,
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    "Feedbacks not found!",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("/teacher/getSubjects/{id}")
    public ResponseEntity<?> getSubjects(@PathVariable long id){
        try{
            Teacher teacher=userService.getTeacherFromId(id);
            List<Subject> subjects=teacher.getSubjects();
            return new ResponseEntity<>(
                    subjects,
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/getTop5Teachers")
    public ResponseEntity<?> getTopTeachers(){
        try{
            List<Teacher> teacherList=userService.findTopTeachers();
            return new ResponseEntity<>(
                    teacherList,
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
