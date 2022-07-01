package com.ishitwa.FeedbackSystem.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUser {
    private String fname;
    private String lname;
    private String email;
    private String username;
    private String password;
    private long departmentId;
    private long classId;
}
