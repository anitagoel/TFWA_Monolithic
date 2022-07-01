package com.ishitwa.FeedbackSystem.auth;

import com.ishitwa.FeedbackSystem.user.Student;
import com.ishitwa.FeedbackSystem.user.Teacher;
import com.ishitwa.FeedbackSystem.user.UserDetails;
import com.ishitwa.FeedbackSystem.user.UserService;
import com.ishitwa.FeedbackSystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/student/signup")
    public RegisterResponse signUp(@RequestBody RegisterUser user)throws Exception{
        Student student=userService.saveStudent(user);
        return new RegisterResponse(user,student.getStudentId());
    }

//    @PostMapping("/student/login")
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

    @PostMapping("/teacher/signup")
    public RegisterResponse teacherSignUp(@RequestBody RegisterUser user)throws Exception{
        Teacher teacher= userService.saveTeacher(user);
        return new RegisterResponse(
                user,
                teacher.getTeacherId()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        }catch (Exception e){
            return new ResponseEntity<String>(
                    e.getMessage(),
                    HttpStatus.NON_AUTHORITATIVE_INFORMATION
            );
        }
        User user;
        Teacher teacher=userService.getTeacher(
                loginRequest.getUsername()
        );
        if(teacher==null){
            Student student=userService.getStudent(
                    loginRequest.getUsername()
            );
            user=new User(student.getUsername(),student.getPassword());
        }
        else
            user=new User(teacher.getUsername(),teacher.getPassword());
        final UserDetails userDetails=userService.loadUserByUsername(
                loginRequest.getUsername()
        );
        String token=jwtUtil.generateToken(userDetails);
        return new ResponseEntity<LoginResponse>(
                new LoginResponse(
                        user.getUsername(),token
                ),HttpStatus.OK
        );
    }

}
