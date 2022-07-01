package com.ishitwa.FeedbackSystem.feedback;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeedbackPoint {
    long teacherId;
    long studentId;
    long subjectId;
    int q1,q2,q3,q4,q5,q6,q7,q8,q9,q10;
}
