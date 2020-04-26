package com.zust.stkdy.testsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FirstKnowledgePoint {
    private int id;
    private String knowledgePoint;
    private int isDeleted;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    private double aveScore;
    private double aveRetention;
    private int totalVisit;

    public FirstKnowledgePoint(int id, String knowledgePoint, double aveScore, double aveRetention, int totalVisit) {
        this.id = id;
        this.knowledgePoint = knowledgePoint;
        this.aveScore = aveScore;
        this.aveRetention = aveRetention;
        this.totalVisit = totalVisit;
    }

    public FirstKnowledgePoint() {
    }

    public FirstKnowledgePoint(String knowledgePoint) {
        this.knowledgePoint = knowledgePoint;
    }

    public void toAve(){
        if(totalVisit!=0){
            this.aveRetention/=totalVisit;
            this.aveScore/=totalVisit;
        }
    }

    public double getAveScore() {
        return aveScore;
    }

    public void setAveScore(double aveScore) {
        this.aveScore = aveScore;
    }

    public double getAveRetention() {
        return aveRetention;
    }

    public void setAveRetention(double aveRetention) {
        this.aveRetention = aveRetention;
    }

    public int getTotalVisit() {
        return totalVisit;
    }

    public void setTotalVisit(int totalVisit) {
        this.totalVisit = totalVisit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
