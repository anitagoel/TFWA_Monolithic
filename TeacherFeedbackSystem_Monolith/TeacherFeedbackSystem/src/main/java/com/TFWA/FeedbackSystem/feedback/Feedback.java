package com.ishitwa.FeedbackSystem.feedback;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Feedback {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long feedbackId;
    private long studentId;
    private long teacherId;
    private long subjectId;
    private long departmentId;
    private long classId;
    private int ques1,ques2,ques3,ques4,ques5,ques6,ques7,ques8,ques9,ques10;
}
