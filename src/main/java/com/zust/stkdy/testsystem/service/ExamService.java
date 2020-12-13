package com.zust.stkdy.testsystem.service;

import com.zust.stkdy.testsystem.entity.ChoiceQuestion;
import com.zust.stkdy.testsystem.entity.Exam;
import com.zust.stkdy.testsystem.entity.SubjectiveQuestion;
import com.zust.stkdy.testsystem.utils.ExamUpdUtil;
import com.zust.stkdy.testsystem.utils.PageResult;
import com.zust.stkdy.testsystem.utils.PageUtil;

import java.util.List;

public interface ExamService {
    int addExam(Exam exam);
    Exam getExamById(int id);
    Exam getExamByTitle(String title);
    Exam getExamInfo(Exam exam);
    int updateExam(Exam exam);
    int deleteChoiceQuestion(ExamUpdUtil examUpdUtil);
    int deleteSubjectiveQuestion(ExamUpdUtil examUpdUtil);
    int addChoiceQuestion(ExamUpdUtil examUpdUtil);
    int addSubjectiveQuestion(ExamUpdUtil examUpdUtil);
    int deleteExam(Exam exam);
    PageResult getExamInPage(PageUtil pageUtil,int teacherId);
    List<Exam> getExamStatistics(PageUtil pageUtil,int teacherId);
}
