package com.ishitwa.FeedbackSystem.department;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ishitwa.FeedbackSystem.user.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long departmentId;
    @Column(unique = true)
    String departmentName;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    List<Teacher> teacherList;
}
