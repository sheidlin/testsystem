package com.zust.stkdy.testsystem.dao;

import com.zust.stkdy.testsystem.entity.FirstKnowledgePoint;
import org.apache.ibatis.annotations.Param;

public interface FirstKnowledgePointDao {
    int insertKnowledgePoint(FirstKnowledgePoint firstKnowledgePoint);
    int deleteKnowledgePointByKnowledgePoint(@Param("knowledgePoint") String knowledgePoint);
    FirstKnowledgePoint findKnowledgePointById(@Param("id") Integer id);
    FirstKnowledgePoint findKnowledgePointByKnowledgePoint(@Param("knowledgePoint") String knowledgePoint);
}
