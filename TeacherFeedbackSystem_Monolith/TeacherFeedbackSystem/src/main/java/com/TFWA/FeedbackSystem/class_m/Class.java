package com.ishitwa.FeedbackSystem.class_m;

import com.ishitwa.FeedbackSystem.user.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long classId;
    String branch;
    String section;
    int year;
    long departmentId;
    @OneToMany(cascade = CascadeType.PERSIST)
    @Nullable
    List<Student> students;
}
