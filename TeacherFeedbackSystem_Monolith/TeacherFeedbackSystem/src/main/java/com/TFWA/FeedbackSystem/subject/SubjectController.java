package com.ishitwa.FeedbackSystem.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/create")
    public ResponseEntity<?> addSubject(@RequestBody Subject subject){
        String name=subject.getName();
        Subject s=subjectService.findSubjectByName(name);
        if(s==null) {
            try {
                subjectService.saveSubject(subject);
                return new ResponseEntity<>(
                        subject,
                        HttpStatus.OK
                );
            } catch (Exception e) {
                return new ResponseEntity<>(
                        e.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }
        }else{
            return new ResponseEntity<>(
                    "Subject already exists!",
                    HttpStatus.FOUND
            );
        }
    }

    @PostMapping("/find/{subjectId}")
    public ResponseEntity<?> findSubjectById(@PathVariable long subjectId){
        try{
            Subject subject= subjectService.findSubjectById(subjectId);
            return new ResponseEntity<>(subject,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/getSubjectsByDepartment/{id}")
    public ResponseEntity<?> getSubjects(@PathVariable long id){
        try{
            List<Subject> subjects=subjectService.getSubjectsByDepartment(id);
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

//    @GetMapping("/getByDepartmentAndTeacher/{departmentId}/{teacherId}")
//    public ResponseEntity<?> getByDepartmentAndTeacher(@PathVariable long departmentId,@PathVariable long teacherId){
//        try{
//            Teacher teacher=userService.getTeacherFromId(teacherId);
//            List<Subject> subjects1=teacher.getSubjects();
//            List<Subject> result=new ArrayList<>();
//            for(Subject s:subjects1){
//                if(s.getDepartmentId()==departmentId){
//                    result.add(s);
//                }
//            }
//            return new ResponseEntity<>(
//                    result,
//                    HttpStatus.OK
//            );
//        }catch (Exception e){
//            return new ResponseEntity<>(
//                    e.getMessage(),
//                    HttpStatus.INTERNAL_SERVER_ERROR
//            );
//        }
//    }
    // moved to teacher controller

//    @PostMapping("/getSubject")
//    public ResponseEntity<?> getSubject(@RequestBody Id2 id2){
//        long studentId=id2.getId1();
//        long teacherId=id2.getId2();
//        try{
//            Student student=userService.getStudentFromId(studentId);
//            List<Subject> subjects=student.getSubjects();
//            Teacher teacher=userService.getTeacherFromId(teacherId);
//            List<Subject> subjects1=teacher.getSubjects();
//            if(subjects!=null && subjects1!=null) {
//                Subject subject=null;
//                for (Subject s : subjects) {
//                    for (Subject s2 : subjects1) {
//                        if(s.getSubjectId()==s2.getSubjectId()){
//                            subject=s;
//                            break;
//                        }
//                    }
//                }
//                return new ResponseEntity<>(
//                        subject,
//                        HttpStatus.OK
//                );
//            }
//            return new ResponseEntity<>(
//                    "Not Found!",
//                    HttpStatus.NOT_FOUND
//            );
//
//        }catch (Exception e){
//            return new ResponseEntity<>(
//                    e.getMessage(),
//                    HttpStatus.INTERNAL_SERVER_ERROR
//            );
//        }
//    }
    // moved to student controller
}
