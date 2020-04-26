package com.zust.stkdy.testsystem.service;

import com.zust.stkdy.testsystem.entity.ChoiceQuestion;
import com.zust.stkdy.testsystem.utils.PageResult;
import com.zust.stkdy.testsystem.utils.PageUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface ChoiceQuestionService {
    int addChoiceQuestion(ChoiceQuestion choiceQuestion);
    int updateChoiceQuestion(ChoiceQuestion choiceQuestion);
    int deleteChoiceQuestion(int id);
    ChoiceQuestion findChoiceQuestionById(int id);
    PageResult getChoiceQuestionInPage(PageUtil pageUtil);
}
