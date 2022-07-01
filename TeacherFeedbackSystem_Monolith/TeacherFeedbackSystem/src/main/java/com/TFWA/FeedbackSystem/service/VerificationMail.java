package com.ishitwa.FeedbackSystem.service;

import com.ishitwa.FeedbackSystem.config.SecurityConstants;
import com.ishitwa.FeedbackSystem.user.Student;
import com.ishitwa.FeedbackSystem.user.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationMail {

    @Autowired
    MailService mailService;

    public void sendVerificationMailToTeacher(Teacher user){
        String subject = "Please verify your registration.";
        String senderName = "Teacher Feedback System";
        String userMail = user.getEmail();
        String mailContent = "<p>Dear, "+user.getFname()+" "+user.getLname()+"</p>";
        String site = SecurityConstants.hostUrl;
        String verifyUrl = "/teacher/activate/"+user.getToken();
        mailContent += "<p>Please click the link below to verify the registration</p>";
        mailContent += "<a href=\""+site+verifyUrl+"\">VERIFY</a><br>";
        mailService.sendMail(userMail,subject,senderName,mailContent);
    }

    public void sendVerificationMailToStudent(Student student){
        String subject = "Please verify your registration.";
        String senderName = "Teacher Feedback System";
        String userMail = student.getEmail();
        String mailContent = "<p>Dear, "+student.getFname()+" "+student.getLname()+"</p>";
        String site = SecurityConstants.hostUrl;
        String verifyUrl = "/student/activate/"+student.getToken();
        mailContent += "<p>Please click the link below to verify the registration</p>";
        mailContent += "<a href=\""+site+verifyUrl+"\">VERIFY</a><br>";
        mailService.sendMail(userMail,subject,senderName,mailContent);
    }
}
