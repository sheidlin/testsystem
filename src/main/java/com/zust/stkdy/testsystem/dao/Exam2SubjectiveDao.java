package com.zust.stkdy.testsystem.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Exam2SubjectiveDao {
    List<Integer> findQuestionIdByExamId(@Param("examId") Integer examId);
    int deleteExam(@Param("examId") Integer examId);
    int insertExam2Question(@Param("examId") Integer examId,@Param("questionId") Integer questionId,@Param("teacherId") Integer teacherId);
    int deleteExam2Question(@Param("examId") Integer examId,@Param("questionId") Integer questionId);
}
