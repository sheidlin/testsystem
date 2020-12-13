package com.zust.stkdy.testsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SecondKnowledgePoint {
    private int id;
    private int firstKnowledgePointId;
    private String knowledgePoint;
    private String firstKnowledgePoint;
    private int isDeleted;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    private double aveScore;
    public SecondKnowledgePoint() {
    }

    public SecondKnowledgePoint(int firstKnowledgePointId, String knowledgePoint, String firstKnowledgePoint) {
        this.firstKnowledgePointId = firstKnowledgePointId;
        this.knowledgePoint = knowledgePoint;
        this.firstKnowledgePoint = firstKnowledgePoint;
    }

    public double getAveScore() {
        return aveScore;
    }

    public void setAveScore(double aveScore) {
        this.aveScore = aveScore;
    }

    public String getFirstKnowledgePoint() {
        return firstKnowledgePoint;
    }

    public void setFirstKnowledgePoint(String firstKnowledgePoint) {
        this.firstKnowledgePoint = firstKnowledgePoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirstKnowledgePointId() {
        return firstKnowledgePointId;
    }

    public void setFirstKnowledgePointId(int firstKnowledgePointId) {
        this.firstKnowledgePointId = firstKnowledgePointId;
    }

    public String getKnowledgePoint() {
        return knowledgePoint;
    }

    public void setKnowledgePoint(String knowledgePoint) {
        this.knowledgePoint = knowledgePoint;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
