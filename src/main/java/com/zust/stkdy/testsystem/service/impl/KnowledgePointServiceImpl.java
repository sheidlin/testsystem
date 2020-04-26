package com.zust.stkdy.testsystem.service.impl;

import com.zust.stkdy.testsystem.dao.Student2SecondPointDao;
import com.zust.stkdy.testsystem.entity.FirstKnowledgePoint;
import com.zust.stkdy.testsystem.entity.Student;
import com.zust.stkdy.testsystem.entity.StudentSecondKnowledgePoint;
import com.zust.stkdy.testsystem.service.KnowledgePointService;
import com.zust.stkdy.testsystem.utils.KnowledgePointUtil;
import com.zust.stkdy.testsystem.utils.PageResult;
import com.zust.stkdy.testsystem.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgePointServiceImpl implements KnowledgePointService {
    @Autowired
    private Student2SecondPointDao student2SecondPointDao;
    @Override
    public PageResult getStudentFirstKnowledgePoint(PageUtil pageUtil, Student student) {
        List<StudentSecondKnowledgePoint>studentSecondKnowledgePoints=student2SecondPointDao.findKnowledgePointByStudentId(student.getId());
        if(studentSecondKnowledgePoints==null)return null;
        List<FirstKnowledgePoint>firstKnowledgePointList=KnowledgePointUtil.seconfToFirst(studentSecondKnowledgePoints);
        PageResult pageResult=new PageResult();
        pageResult.setTotalCount(firstKnowledgePointList.size());
        pageResult.setList(firstKnowledgePointList);
        return pageResult;
    }

    @Override
    public PageResult getSecondKnowledgePointByFirstPointId(Student student,int firstPointId) {
        List<StudentSecondKnowledgePoint>studentSecondKnowledgePointList=student2SecondPointDao.findKnowledgePointByStudentIdAndFirstPointId(student.getId(),firstPointId);
        KnowledgePointUtil.calSecondList(studentSecondKnowledgePointList);
        PageResult pageResult=new PageResult();
        pageResult.setTotalCount(studentSecondKnowledgePointList.size());
        pageResult.setList(studentSecondKnowledgePointList);
        return pageResult;
    }
}
