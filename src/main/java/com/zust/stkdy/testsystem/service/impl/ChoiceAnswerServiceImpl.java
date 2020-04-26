package com.zust.stkdy.testsystem.service.impl;

import com.zust.stkdy.testsystem.dao.ChoiceAnswerDao;
import com.zust.stkdy.testsystem.dao.SubjectiveAnswerDao;
import com.zust.stkdy.testsystem.entity.ChoiceAnswer;
import com.zust.stkdy.testsystem.service.ChoiceAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChoiceAnswerServiceImpl implements ChoiceAnswerService {
    @Autowired
    private ChoiceAnswerDao choiceAnswerDao;
    @Override
    public int updateAnswer(ChoiceAnswer choiceAnswer) {
        return choiceAnswerDao.updateAnswer(choiceAnswer);
    }
}
