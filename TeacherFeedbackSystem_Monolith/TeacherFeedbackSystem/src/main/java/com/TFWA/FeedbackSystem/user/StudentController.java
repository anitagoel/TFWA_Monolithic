package com.ishitwa.FeedbackSystem.user;

import com.ishitwa.FeedbackSystem.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private UserService userService;

//    @PostMapping("/signup")
//    public RegisterResponse signUp(@RequestBody RegisterUser user)throws Exception{
//        Student student=userService.saveStudent(user);
//        return new RegisterResponse(user,student.getStudentId());
//    }
//    moved to auth controller

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
//        try{
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequest.getUsername(),
//                            loginRequest.getPassword()
//                    )
//            );
//        }catch (Exception e){
//            return new ResponseEntity<String>(
//                    e.getMessage(),
//                    HttpStatus.NON_AUTHORITATIVE_INFORMATION
//            );
//        }
//        final UserDetails userDetails=userService.loadUserByUsername(
//                loginRequest.getUsername()
//        );
//        Student student=userService.getStudent(loginRequest.getUsername());
//        String token=jwtUtil.generateToken(userDetails);
//        return new ResponseEntity<LoginRequest>(
//                new LoginRequest(
//                        student.getUsername(), token
//                ),HttpStatus.OK);
//    }
    // moved to auth controller

//    @GetMapping("/activate/{token}")
//    public ResponseEntity<?> activateUser(@PathVariable String token){
//        try{
//            Student student=userService.getStudentFromToken(token);
//            student.setEnabled(true);
//            userService.updateStudent(student);
//        }catch (Exception e){}
//        return new ResponseEntity<String>("Successfully Enabled!",HttpStatus.OK);
//    }
//    moved to auth controller

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        List<Student> students=userService.getAllStudents();
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

//    @PostMapping("/changeClassId")
//    public ResponseEntity<?> changeClass(@RequestBody Id2 classId){
//        long studentId = classId.getId1();
//        long cid = classId.getId2();
//        try{
//            Student student=userService.getStudentFromId(studentId);
//            student.setClassId(cid);
//            userService.updateStudent(student);
//            return new ResponseEntity<>(
//                    student,
//                    HttpStatus.OK
//            );
//        }catch (Exception e){
//            return new ResponseEntity<>(
//                    "Error in updating classId",
//                    HttpStatus.INTERNAL_SERVER_ERROR
//            );
//        }
//    }
//    redundant

    @GetMapping("/details/{id}")
    public ResponseEntity<?> getDetails(@PathVariable long id){
        try{
            Student student=userService.getStudentFromId(id);
            return new ResponseEntity<>(student,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

//    @PostMapping("/addTeacher")
//    public ResponseEntity<?> addTeacher(@RequestBody Id2 id2){
//        long studentId=id2.getId1();
//        long teacherId=id2.getId2();
//        try{
//            Teacher t=userService.addTeacherToStudent(
//                    studentId,teacherId
//            );
//            return new ResponseEntity<>(t,HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(
//                    e.getMessage(),
//                    HttpStatus.INTERNAL_SERVER_ERROR
//            );
//        }
//    }
//    should be an admin function
//    moved to admin controller

//    @PostMapping("/addSubject")
//    public ResponseEntity<?> addSubject(@RequestBody Id2 id2){
//        long studentId= id2.getId1();
//        long subjectId= id2.getId2();
//        try{
//            Subject s=userService.addSubjectToStudent(studentId,subjectId);
//            return new ResponseEntity<>(s,HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(
//                    e.getMessage(),
//                    HttpStatus.INTERNAL_SERVER_ERROR
//            );
//        }
//    }
//    should be an admin function
//    moved to admin controller

    @GetMapping("/getStudentsByClass/{cId}")
    public ResponseEntity<?> getAllStudentsByClass(@PathVariable long cId){
        try{
            List<Student> students=userService.findStudentByClass(cId);
            return new ResponseEntity<>(students,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getSubject/{studentId}/{teacherId}")
    public ResponseEntity<?> getSubject(
            @PathVariable long studentId,
            @PathVariable long teacherId
    ){
        try{
            Student student=userService.getStudentFromId(studentId);
            List<Subject> subjects=student.getSubjects();
            Teacher teacher=userService.getTeacherFromId(teacherId);
            List<Subject> subjects1=teacher.getSubjects();
            if(subjects!=null && subjects1!=null) {
                Subject subject=null;
                for (Subject s : subjects) {
                    for (Subject s2 : subjects1) {
                        if(s.getSubjectId()==s2.getSubjectId()){
                            subject=s;
                            break;
                        }
                    }
                }
                return new ResponseEntity<>(
                        subject,
                        HttpStatus.OK
                );
            }
            return new ResponseEntity<>(
                    "Not Found!",
                    HttpStatus.NOT_FOUND
            );

        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
