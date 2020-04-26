package com.zust.stkdy.testsystem.service;

import com.zust.stkdy.testsystem.entity.SubjectiveQuestion;
import com.zust.stkdy.testsystem.utils.PageResult;
import com.zust.stkdy.testsystem.utils.PageUtil;
import org.springframework.stereotype.Service;

public interface SubjectiveQuestionService {
    int addSubjectiveQuestion(SubjectiveQuestion subjectiveQuestion);
    int updateSubjectiveQuestion(SubjectiveQuestion subjectiveQuestion);
    int deleteSubjectiveQuestion(int id);
    int updateKeyWords(SubjectiveQuestion subjectiveQuestion);
    SubjectiveQuestion sloveKnowledgePoint(SubjectiveQuestion subjectiveQuestion);
    SubjectiveQuestion findSubjectiveQuestionById(int id);
    PageResult getSubjectiveQuestionInPage(PageUtil pageUtil);
}
