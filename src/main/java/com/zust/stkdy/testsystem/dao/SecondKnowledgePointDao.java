package com.zust.stkdy.testsystem.dao;

import com.zust.stkdy.testsystem.entity.SecondKnowledgePoint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecondKnowledgePointDao {
    int insertKnowledgePoint(SecondKnowledgePoint secondKnowledgePoint);
    int deleteKnowledgePointByKnowledgePoint(@Param("knowledgePoint") String knowledgePoint);
    SecondKnowledgePoint findKnowledgePointById(@Param("id") Integer id);
    SecondKnowledgePoint findKnowledgePointByKnowledgePoint(@Param("knowledgePoint") String knowledgePoint);
    SecondKnowledgePoint findKnowledgePointByKnowledgePointAndFirstPointId
            (@Param("firstKnowledgePointId") Integer firstPointId,@Param("knowledgePoint") String knowledgePoint);
    List<SecondKnowledgePoint> findKnowledgePointsByExamId(@Param("examId") Integer examId);
}
