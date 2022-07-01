package com.ishitwa.FeedbackSystem.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse extends RegisterUser{
    private long userId;
    private String username;
    private String fname;
    private String lname;
    private String email;
    private long classId;
    private long departmentId;
    public RegisterResponse(RegisterUser user,Long id){
        this.userId=id;
        this.username=user.getUsername();
        this.fname=user.getFname();
        this.lname=user.getLname();
        this.email=user.getEmail();
        this.classId=user.getClassId();
        this.departmentId=user.getDepartmentId();
    }
}
