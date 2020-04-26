package com.zust.stkdy.testsystem.service;

import com.zust.stkdy.testsystem.entity.FirstKnowledgePoint;
import com.zust.stkdy.testsystem.entity.Student;
import com.zust.stkdy.testsystem.entity.StudentSecondKnowledgePoint;
import com.zust.stkdy.testsystem.utils.PageResult;
import com.zust.stkdy.testsystem.utils.PageUtil;

import java.util.List;

public interface KnowledgePointService {
    public PageResult getStudentFirstKnowledgePoint(PageUtil pageUtil, Student student);
    public PageResult getSecondKnowledgePointByFirstPointId(Student student,int firstPointId);
}
