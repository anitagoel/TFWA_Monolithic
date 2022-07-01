package com.ishitwa.FeedbackSystem.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepo feedbackRepo;

    public Feedback saveFeedback(Feedback feedback){
        return feedbackRepo.save(
                feedback
        );
    }

    public Feedback getFeedbackFromStudentAndTeacher(Long teacherId,Long studentId){
        try {
            return feedbackRepo.findFeedbackByTeacherIdAndStudentId(
                    teacherId,
                    studentId
            );
        }catch (Exception e){
            return null;
        }
    }
//    this function is used to fetch the feedbacks by the student and teacher IDs.

    public List<Feedback> getFeedbackOfTeacher(Long teacherId){
        return feedbackRepo.findFeedbackByTeacherId(teacherId);
    }
//    this function is used to fetch the feedbacks by the teacher ID.

    public List<Feedback> getFeedbackByStudents(Long studentId){
        return feedbackRepo.findFeedbackByStudentId(studentId);
    }

    public List<Feedback> getFeedbackBySubjects(Long subjectId){
        return feedbackRepo.findFeedbackBySubjectId(subjectId);
    }
//    this function is used to fetch the feedbacks by the subject ID.

    public List<Feedback> getFeedbackByClass(long id){
        return feedbackRepo.findFeedbackByClassId(id);
    }
//    this function is used to fetch the feedbacks by the Class ID.

    public Feedback getFeedbackById(Long id){
        return feedbackRepo.findFeedbackByFeedbackId(id);
    }

    public List<Feedback> getFeedbackByTeacherAndSubject(Long teacherId,Long subjectId){
        return feedbackRepo.findFeedbackByTeacherIdAndSubjectId(teacherId,subjectId);
    }
//    this function is used to fetch the feedback by Teacher and Student IDs.
}
