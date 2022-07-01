package com.ishitwa.FeedbackSystem.feedback;

import com.ishitwa.FeedbackSystem.user.UserService;
import com.ishitwa.FeedbackSystem.user.Student;
import com.ishitwa.FeedbackSystem.user.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private UserService userService;
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/score")
    public ResponseEntity<?> giveFeedback(@RequestBody FeedbackPoint feedbackPoint){

        long studentId= feedbackPoint.getStudentId();
        long teacherId= feedbackPoint.getTeacherId();
        long subjectId= feedbackPoint.getSubjectId();
        int[] points = new int[10];
        int total=0;
        points[0]=feedbackPoint.getQ1();
        points[1]=feedbackPoint.getQ2();
        points[2]=feedbackPoint.getQ3();
        points[3]=feedbackPoint.getQ4();
        points[4]=feedbackPoint.getQ5();
        points[5]=feedbackPoint.getQ6();
        points[6]=feedbackPoint.getQ7();
        points[7]=feedbackPoint.getQ8();
        points[8]=feedbackPoint.getQ9();
        points[9]=feedbackPoint.getQ10();

        for(int i=0;i<10;i++)total+=points[i];

        try {
            Student student= userService.getStudentFromId(studentId);
            Teacher teacher= userService.getTeacherFromId(teacherId);

            teacher.setTotalPoints(total);
            teacher.setFeedbacks(teacher.getFeedbacks()+1);
            teacher.setAveragePoints((double)teacher.getTotalPoints()/teacher.getFeedbacks());

            Feedback feedback = new Feedback();

            feedback.setSubjectId(subjectId);
            feedback.setStudentId(studentId);
            feedback.setTeacherId(teacherId);
            feedback.setDepartmentId(teacher.getDepartmentId());
            feedback.setClassId(student.getClassId());
            feedback.setQues1(points[0]);
            feedback.setQues2(points[1]);
            feedback.setQues3(points[2]);
            feedback.setQues4(points[3]);
            feedback.setQues5(points[4]);
            feedback.setQues6(points[5]);
            feedback.setQues7(points[6]);
            feedback.setQues8(points[7]);
            feedback.setQues9(points[8]);
            feedback.setQues10(points[9]);

            List<Feedback> feedbackList=teacher.getFeedbackList();
            feedbackList.add(feedback);
            teacher.setFeedbackList(feedbackList);

            List<Feedback> feedbackList1=student.getFeedbacks();
            feedbackList1.add(feedback);
            student.setFeedbacks(feedbackList1);

            feedbackService.saveFeedback(feedback);

            return new ResponseEntity<>(
                    "Successfully Submited!",
                    HttpStatus.OK
            );


        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

//    @PostMapping("/teacher")
//    public ResponseEntity<?> getFeedbacksByTeacher(@RequestBody Id teacherId){
//        long id = teacherId.getId();
//        try {
//            List<Feedback> feedbacks = feedbackService.getFeedbackOfTeacher(id);
//            return new ResponseEntity<>(feedbacks, HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(
//                    "Feedback not found!",
//                    HttpStatus.NOT_FOUND
//            );
//        }
//    }
//    should be in analytics function
//    moved to analytics controller

//    @PostMapping("/subject")
//    public ResponseEntity<?> getFeedbacksBySubject(@RequestBody Id subjectId){
//        long id=subjectId.getId();
//        try{
//            List<Feedback> feedbacks=feedbackService.getFeedbackBySubjects(id);
//            return new ResponseEntity<>(feedbacks,HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(
//                    "Feedbacks not found!",
//                    HttpStatus.NOT_FOUND
//            );
//        }
//    }
//    should be in analytics function
//    moved to analytics controller

//    @PostMapping("/teacher/subject")
//    public ResponseEntity<?> getFeedbackByTeacherAndSubject(@RequestBody TeacherSubject teacherSubject){
//        long teacherId= teacherSubject.getTeacherId();
//        long subjectId=teacherSubject.getSubjectId();
//        try{
//            List<Feedback> feedbacks=feedbackService.getFeedbackByTeacherAndSubject(teacherId,subjectId);
//            return new ResponseEntity<>(feedbacks,HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(
//                    "Feedbacks not found!",
//                    HttpStatus.NOT_FOUND
//            );
//        }
//    }
//    should be in analytics functionality
//    moved to analytics controller

//    @PostMapping("/class")
//    public ResponseEntity<?> getFeedbackByClass(@RequestBody Id classId){
//        long id= classId.getId();
//        try{
//            List<Feedback> feedbacks=feedbackService.getFeedbackByClass(id);
//            return new ResponseEntity<>(
//                    feedbacks,
//                    HttpStatus.OK
//            );
//        }catch (Exception e){
//            return new ResponseEntity<>(
//                    "Feedbacks not found!",
//                    HttpStatus.NOT_FOUND
//            );
//        }
//    }
//    should be in analytics functionality
//    moved to analytics controller


//    @GetMapping("/all/teachers")
//    public ResponseEntity<?> getAllTeachersFeedback(){
//        try{
//            List<Department> departments=departmentService.getAllDepartments();
//            return new ResponseEntity<>(
//                    departments,
//                    HttpStatus.OK
//            );
//        }catch (Exception e){
//            return new ResponseEntity<>(
//                    "Some error occured!",
//                    HttpStatus.NOT_FOUND
//            );
//        }
//    }
    // moved to department controller

//    @PostMapping("/check")
//    public ResponseEntity<?> checkFeedback(@RequestBody Id2 id2){
//        long studentId=id2.getId1();
//        long teacherId=id2.getId2();
//        try{
//            Feedback feedback=feedbackService.getFeedbackFromStudentAndTeacher(teacherId,studentId);
//            System.out.println(feedback==null?"False":"True");
//            if(feedback==null){
//                return new ResponseEntity<>(false,HttpStatus.OK);
//            }
//            return new ResponseEntity<>(true,HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(false,HttpStatus.OK);
//        }
//    }

    @GetMapping("/check/{studentId}/{teacherId}")
    public boolean checkFeedback(@PathVariable long studentId,@PathVariable long teacherId){
        Feedback feedback=feedbackService.getFeedbackFromStudentAndTeacher(teacherId,studentId);
        if(feedback==null)return false;
        return true;
    }

}
