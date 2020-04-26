package com.zust.stkdy.testsystem.service.impl;

import com.zust.stkdy.testsystem.dao.SubjectiveAnswerDao;
import com.zust.stkdy.testsystem.entity.SubjectiveAnswer;
import com.zust.stkdy.testsystem.service.SubjectiveAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectiveAnswerServiceImpl implements SubjectiveAnswerService {
    @Autowired
    private SubjectiveAnswerDao subjectiveAnswerDao;
    @Override
    public int updateAnswer(SubjectiveAnswer subjectiveAnswer) {
        return subjectiveAnswerDao.updateAnswer(subjectiveAnswer);
    }
}
