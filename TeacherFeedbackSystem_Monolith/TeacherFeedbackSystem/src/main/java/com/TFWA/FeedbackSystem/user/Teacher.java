package com.ishitwa.FeedbackSystem.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ishitwa.FeedbackSystem.auth.RegisterUser;
import com.ishitwa.FeedbackSystem.feedback.Feedback;
import com.ishitwa.FeedbackSystem.subject.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.utility.RandomString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long teacherId;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String fname;
    private String lname;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String roles;
    @JsonIgnore
    private String token;
    @JsonIgnore
    private boolean enabled;
    private double averagePoints;
    private int totalPoints;
    private long feedbacks;
    private long departmentId;
    @OneToMany(cascade = CascadeType.PERSIST)
    @Nullable
    private List<Feedback> feedbackList;
    @Nullable
    @ManyToMany(cascade = CascadeType.PERSIST)
    List<Subject> subjects;

    public Teacher(RegisterUser user){
        this.fname=user.getFname();
        this.lname=user.getLname();
        this.username=user.getUsername();
        this.email=user.getEmail();
        this.password=user.getPassword();
        this.departmentId= user.getDepartmentId();
        this.token= RandomString.make(14);
        this.roles="ROLE_TEACHER";
        this.enabled=true;
        this.subjects=new ArrayList<>();
        this.feedbackList=new ArrayList<>();
    }

}
