package com.zust.stkdy.testsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StudentSecondKnowledgePoint {
    private int id;
    private int studentId;
    private int teacherId;
    private int firstKnowledgePointId;
    private int secondKnowledgePointId;
    private String knowledgePoint;
    private double beta;
    private double M;
    private double totalScore;
    private int totalVisit;
    private double aveScore;
    private String firstKnowledgePoint;
    private int isDeleted;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    public StudentSecondKnowledgePoint() {
    }

    public StudentSecondKnowledgePoint(int studentId, int teacherId, int firstKnowledgePointId, String firstKnowledgePoint, int secondKnowledgePointId, String knowledgePoint) {
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.firstKnowledgePointId = firstKnowledgePointId;
        this.firstKnowledgePoint=firstKnowledgePoint;
        this.secondKnowledgePointId = secondKnowledgePointId;
        this.knowledgePoint = knowledgePoint;
    }

    public double getAveScore() {
        return aveScore;
    }

    public void setAveScore(double aveScore) {
        this.aveScore = aveScore;
    }

    public int getSecondKnowledgePointId() {
        return secondKnowledgePointId;
    }

    public void setSecondKnowledgePointId(int secondKnowledgePointId) {
        this.secondKnowledgePointId = secondKnowledgePointId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getM() {
        return M;
    }

    public void setM(double m) {
        M = m;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public int getTotalVisit() {
        return totalVisit;
    }

    public void setTotalVisit(int totalVisit) {
        this.totalVisit = totalVisit;
    }

    public String getFirstKnowledgePoint() {
        return firstKnowledgePoint;
    }

    public void setFirstKnowledgePoint(String firstKnowledgePoint) {
        this.firstKnowledgePoint = firstKnowledgePoint;
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

    @Override
    public String toString() {
        return "StudentSecondKnowledgePoint{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", teacherId=" + teacherId +
                ", firstKnowledgePointId=" + firstKnowledgePointId +
                ", secondKnowledgePointId=" + secondKnowledgePointId +
                ", knowledgePoint='" + knowledgePoint + '\'' +
                ", beta=" + beta +
                ", M=" + M +
                ", totalScore=" + totalScore +
                ", totalVisit=" + totalVisit +
                ", aveScore=" + aveScore +
                ", firstKnowledgePoint='" + firstKnowledgePoint + '\'' +
                ", isDeleted=" + isDeleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
