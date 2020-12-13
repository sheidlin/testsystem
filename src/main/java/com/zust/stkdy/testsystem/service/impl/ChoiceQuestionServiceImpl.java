package com.zust.stkdy.testsystem.service.impl;

import com.zust.stkdy.testsystem.dao.ChoiceQuestionDao;
import com.zust.stkdy.testsystem.entity.ChoiceQuestion;
import com.zust.stkdy.testsystem.service.ChoiceQuestionService;
import com.zust.stkdy.testsystem.utils.PageResult;
import com.zust.stkdy.testsystem.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChoiceQuestionServiceImpl implements ChoiceQuestionService {
    @Autowired
    private ChoiceQuestionDao choiceQuestionDao;

    @Override
    public int addChoiceQuestion(ChoiceQuestion choiceQuestion) {
        return choiceQuestionDao.insertQuestion(choiceQuestion);
    }

    @Override
    public int updateChoiceQuestion(ChoiceQuestion choiceQuestion) {
        return choiceQuestionDao.updateQuestion(choiceQuestion);
    }

    @Override
    public int deleteChoiceQuestion(int id) {
        return choiceQuestionDao.deleteQuestion(id);
    }

    @Override
    public ChoiceQuestion findChoiceQuestionById(int id) {
        return choiceQuestionDao.findQuestionById(id);
    }

    @Override
    public PageResult getChoiceQuestionInPage(PageUtil pageUtil) {
        List<ChoiceQuestion>choiceQuestionList;
        choiceQuestionList=choiceQuestionDao.findQuestion(pageUtil);
        int num=choiceQuestionDao.findNumOfQuestion(pageUtil);
        PageResult pageResult=new PageResult();
        pageResult.setTotalCount(num);
        pageResult.setCurrentPage(pageUtil.getPage());
        pageResult.setList(choiceQuestionList);
        pageResult.setPageSize(pageUtil.getLimit());
        pageResult.setTotalPage((num+pageUtil.getLimit()-1)/pageUtil.getLimit());
        return pageResult;
    }
}
