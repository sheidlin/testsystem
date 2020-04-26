package com.zust.stkdy.testsystem.dao;

import com.zust.stkdy.testsystem.entity.ChoiceAnswer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChoiceAnswerDao {
    int insertAnswer(ChoiceAnswer choiceAnswer);
    int insertTeacherAnswer(@Param("studentId") Integer studentId,@Param("teacherId") Integer teacherId);
    int deleteStudentAnswer(@Param("studentId") Integer studentId);
    int updateAnswer(ChoiceAnswer choiceAnswer);
    int deleteExam(@Param("examId") Integer examId);
    List<ChoiceAnswer> findAnswerByExamIdAndStudentId(@Param("examId") Integer examId,@Param("studentId") Integer studentId);
}
