package com.zust.stkdy.testsystem.dao;

import com.zust.stkdy.testsystem.entity.ChoiceQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Exam2ChoiceDao {
    List<Integer> findQuestionIdByExamId(@Param("examId") Integer examId);
    int deleteExam(@Param("examId") Integer examId);
    int insertExam2Question(@Param("examId") Integer examId,@Param("questionId") Integer questionId,@Param("teacherId") Integer teacherId);
    int deleteExam2Question(@Param("examId") Integer examId,@Param("questionId") Integer questionId);
}
