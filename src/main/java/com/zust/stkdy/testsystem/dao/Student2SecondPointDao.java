package com.zust.stkdy.testsystem.dao;

import com.zust.stkdy.testsystem.entity.StudentSecondKnowledgePoint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Student2SecondPointDao {
    int insertKnowledgePoint(StudentSecondKnowledgePoint studentSecondKnowledgePoint);
    int updateKnowledgePoint(StudentSecondKnowledgePoint studentSecondKnowledgePoint);
    List<StudentSecondKnowledgePoint> findKnowledgePointByStudentId(@Param("studentId") Integer studentId);
    StudentSecondKnowledgePoint findKnowledgePointByStudentIdAndPointId(@Param("studentId") Integer studentId,@Param("secondKnowledgePointId") Integer secondKnowledgePointId);
    List<StudentSecondKnowledgePoint> findKnowledgePointByStudentIdAndFirstPointId(@Param("studentId") Integer studentId,@Param("firstKnowledgePointId") Integer firstKnowledgePointId);
}
