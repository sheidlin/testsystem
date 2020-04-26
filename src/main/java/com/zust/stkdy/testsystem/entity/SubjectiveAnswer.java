package com.zust.stkdy.testsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SubjectiveAnswer {
    private int id;
    private int examId;
    private int questionId;
    private int studentId;
    private double score;
    private String answer;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    private int isDeleted;
    private int secondLevelKnowledgePointId;
    private int firstLevelKnowledgePointId;
    private String  secondLevelKnowledgePoint;
    private String  firstLevelKnowledgePoint;

    @Override
    public String toString() {
        return "SubjectiveAnswer{" +
                "id=" + id +
                ", examId=" + examId +
                ", questionId=" + questionId +
                ", studentId=" + studentId +
                ", score=" + score +
                ", answer='" + answer + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                ", secondLevelKnowledgePointId=" + secondLevelKnowledgePointId +
                ", firstLevelKnowledgePointId=" + firstLevelKnowledgePointId +
                ", secondLevelKnowledgePoint='" + secondLevelKnowledgePoint + '\'' +
                ", firstLevelKnowledgePoint='" + firstLevelKnowledgePoint + '\'' +
                '}';
    }

    public SubjectiveAnswer() {
    }

    public SubjectiveAnswer(int examId, int questionId, int studentId) {
        this.examId = examId;
        this.questionId = questionId;
        this.studentId = studentId;
    }

    public int getSecondLevelKnowledgePointId() {
        return secondLevelKnowledgePointId;
    }

    public void setSecondLevelKnowledgePointId(int secondLevelKnowledgePointId) {
        this.secondLevelKnowledgePointId = secondLevelKnowledgePointId;
    }

    public int getFirstLevelKnowledgePointId() {
        return firstLevelKnowledgePointId;
    }

    public void setFirstLevelKnowledgePointId(int firstLevelKnowledgePointId) {
        this.firstLevelKnowledgePointId = firstLevelKnowledgePointId;
    }

    public String getSecondLevelKnowledgePoint() {
        return secondLevelKnowledgePoint;
    }

    public void setSecondLevelKnowledgePoint(String secondLevelKnowledgePoint) {
        this.secondLevelKnowledgePoint = secondLevelKnowledgePoint;
    }

    public String getFirstLevelKnowledgePoint() {
        return firstLevelKnowledgePoint;
    }

    public void setFirstLevelKnowledgePoint(String firstLevelKnowledgePoint) {
        this.firstLevelKnowledgePoint = firstLevelKnowledgePoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
