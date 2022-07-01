package com.ishitwa.FeedbackSystem.feedback;

import com.ishitwa.FeedbackSystem.feedback.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepo extends JpaRepository<Feedback,Long> {

    public Feedback findFeedbackByFeedbackId(Long id);
    public Feedback findFeedbackByTeacherIdAndStudentId(Long teacherId,Long studentId);
    public List<Feedback> findFeedbackByTeacherId(Long id);
    public List<Feedback> findFeedbackByStudentId(Long id);
    public List<Feedback> findFeedbackBySubjectId(Long id);
    public List<Feedback> findFeedbackByTeacherIdAndSubjectId(Long teacherId,Long subjectId);
    public List<Feedback> findFeedbackByClassId(Long id);
}
