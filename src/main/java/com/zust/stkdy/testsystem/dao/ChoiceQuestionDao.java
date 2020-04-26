package com.zust.stkdy.testsystem.dao;

import com.zust.stkdy.testsystem.entity.ChoiceQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChoiceQuestionDao {
    int insertQuestion(ChoiceQuestion choiceQuestion);
    int updateQuestion(ChoiceQuestion choiceQuestion);
    int findNumOfQuestion(Map param);
    ChoiceQuestion findQuestionById(@Param("id") Integer id);
    ChoiceQuestion findQuestionByIdSpecialy(@Param("id") Integer id);
    int deleteQuestion(@Param("id") Integer id);
    List<ChoiceQuestion> findQuestion(Map param);
}
