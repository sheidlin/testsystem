package com.zust.stkdy.testsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ChoiceAnswer {
    private int examId;
    private int questionId;
    private int studentId;
    private int answer;
    private double score;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    private int isDeleted;

    public ChoiceAnswer() {
    }

    public ChoiceAnswer(int examId, int questionId, int studentId) {
        this.examId = examId;
        this.questionId = questionId;
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "ChoiceAnswer{" +
                ", examId=" + examId +
                ", questionId=" + questionId +
                ", studentId=" + studentId +
                ", answer=" + answer +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
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

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
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
