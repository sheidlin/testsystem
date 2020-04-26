package com.zust.stkdy.testsystem.dao;

import com.zust.stkdy.testsystem.entity.SubjectiveAnswer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SubjectiveAnswerDao {
    int insertAnswer(SubjectiveAnswer subjectiveAnswer);
    int insertTeacherAnswer(@Param("studentId") Integer studentId,@Param("teacherId") Integer teacherId);
    int deleteStudentAnswer(@Param("studentId") Integer studentId);
    int deleteExam(@Param("examId") Integer examId);
    int updateAnswer(SubjectiveAnswer subjectiveAnswer);
    List<SubjectiveAnswer> findAnswerByExamIdAndStudentId(@Param("examId") Integer examId,@Param("studentId") Integer studentId);
}
