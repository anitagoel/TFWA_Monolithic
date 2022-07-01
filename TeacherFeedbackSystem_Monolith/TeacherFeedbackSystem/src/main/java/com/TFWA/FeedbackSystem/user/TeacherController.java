package com.ishitwa.FeedbackSystem.user;

import com.ishitwa.FeedbackSystem.subject.Subject;
import com.ishitwa.FeedbackSystem.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private UserService userService;
    @Autowired
    private SubjectService subjectService;

//    @PostMapping("/signup")
//    public RegisterResponse signUp(@RequestBody RegisterUser user)throws Exception{
//        Teacher teacher= userService.saveTeacher(user);
//        return new RegisterResponse(
//                user,
//                teacher.getTeacherId()
//        );
//    }
//    moved to auth controller


//
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
//        User user;
//        Teacher teacher=userService.getTeacher(
//                loginRequest.getUsername()
//        );
//        if(teacher==null){
//            Student student=userService.getStudent(
//                    loginRequest.getUsername()
//            );
//            user=new User(student.getUsername(),student.getPassword());
//        }
//        else
//            user=new User(teacher.getUsername(),teacher.getPassword());
//        final UserDetails userDetails=userService.loadUserByUsername(
//                loginRequest.getUsername()
//        );
//        String token=jwtUtil.generateToken(userDetails);
//        return new ResponseEntity<LoginResponse>(
//                new LoginResponse(
//                        user.getUsername(),token
//                ),HttpStatus.OK
//        );
//    }
//    moved to auth controller

//    @GetMapping("/activate/{token}")
//    public ResponseEntity<?> activateTeacher(@PathVariable String token){
//        try{
//            Teacher teacher=userService.getTeacherFromToken(token);
//            teacher.setEnabled(true);
//            userService.updateTeacher(teacher);
//        }catch (Exception e){}
//        return new ResponseEntity<String>(
//                "Successfully enabled!",
//                HttpStatus.OK
//        );
//    }
//    moved to auth controller

//    @PostMapping("/addSubject")
//    public ResponseEntity<?> addSubject(@RequestBody Id2 id2){
//        try{
//            long subjectId= id2.getId1();
//            long teacherId= id2.getId2();
//            Subject s=userService.addSubject(teacherId,subjectId);
//            return new ResponseEntity<>(s,HttpStatus.OK);
//
//        }catch (Exception e){
//            return new ResponseEntity<>(
//                    e.getMessage(),
//                    HttpStatus.INTERNAL_SERVER_ERROR
//            );
//        }
//    }
//    should be in admin functionality
//    moved to admin controller

//    @GetMapping("/getSubjects/{id}")
//    public ResponseEntity<?> getSubjects(@PathVariable long id){
//        try{
//            Teacher teacher=userService.getTeacherFromId(id);
//            List<Subject> subjects=teacher.getSubjects();
//            return new ResponseEntity<>(
//                    subjects,
//                    HttpStatus.OK
//            );
//        }catch (Exception e){
//            return new ResponseEntity<>(
//                    e.getMessage(),
//                    HttpStatus.INTERNAL_SERVER_ERROR
//            );
//        }
//    }
//    should be in analytics functionality
//    moved to analytics controller

    @GetMapping("/getByDepartment/{id}")
    public ResponseEntity<?> getTeachersByDepartment(@PathVariable long id){
        try{
            List<Teacher> teacherList=userService.getTeachersByDepartment(id);
            return new ResponseEntity<>(teacherList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/getByDepartmentAndSubject/{departmentId}/{subjectId}")
    public ResponseEntity<?> getByDepartmentAndSubject(
            @PathVariable long departmentId,
            @PathVariable long subjectId
    ){
        try{
            List<Teacher> teachers=userService.getTeachersByDepartment(departmentId);
            Subject subject=subjectService.findSubjectById(subjectId);
            List<Teacher> result = new ArrayList<>();
            for(Teacher t:teachers){
                List<Subject> subjects=t.getSubjects();
                if(subjects!=null) {
                    for (Subject subject1 : subjects) {
                        if (subject1.getSubjectId() == subject.getSubjectId()) {
                            result.add(t);
                            break;
                        }
                    }
                }
            }
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

//    @GetMapping("/getTop5Teachers")
//    public ResponseEntity<?> getTopTeachers(){
//        try{
//            List<Teacher> teacherList=userService.findTopTeachers();
//            return new ResponseEntity<>(
//                    teacherList,
//                    HttpStatus.OK
//            );
//        }catch (Exception e){
//            return new ResponseEntity<>(
//                    e.getMessage(),
//                    HttpStatus.INTERNAL_SERVER_ERROR
//            );
//        }
//    }
//    should be in analytics functionality
//    moved to analytics functionality

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getTeacher(@PathVariable String username){
        try{
            Teacher teacher=userService.getTeacher(username);
            if(teacher==null){
                Student student=userService.getStudent(username);
                if(student==null) throw new Exception();
                return new ResponseEntity<>(student,HttpStatus.OK);
            }else
                return new ResponseEntity<>(teacher,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable long id){
        try{
            Teacher teacher=userService.getTeacherFromId(id);
            return new ResponseEntity<>(teacher,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Not Found!",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getTeachers/{classId}")
    public ResponseEntity<?> getTeachers(@PathVariable long classId){
        try{
            List<Student> students=userService.getAllStudents();
            List<Student> students1=new ArrayList<>();
            for(Student s:students){
                if(s.getClassId()==classId)students1.add(s);
            }
            return new ResponseEntity<>(students1.get(0).getTeachers(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByDepartmentAndTeacher/{departmentId}/{teacherId}")
    public ResponseEntity<?> getByDepartmentAndTeacher(
            @PathVariable long departmentId,
            @PathVariable long teacherId
    ){
        try{
            Teacher teacher=userService.getTeacherFromId(teacherId);
            List<Subject> subjects1=teacher.getSubjects();
            List<Subject> result=new ArrayList<>();
            if(subjects1!=null)
                for(Subject s:subjects1){
                    if(s.getDepartmentId()==departmentId){
                        result.add(s);
                    }
                }
            return new ResponseEntity<>(
                    result,
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