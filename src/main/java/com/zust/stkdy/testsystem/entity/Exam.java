package com.zust.stkdy.testsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Exam {
    private int id;
    private int teacherId;
    private int isDeleted;
    private int totalSubjective;
    private int totalChoice;
    private int totalScore;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;
    private List<ChoiceQuestion>choiceQuestions;
    private List<SubjectiveQuestion>subjectiveQuestions;

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", isDeleted=" + isDeleted +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public int getTotalSubjective() {
        return totalSubjective;
    }

    public void setTotalSubjective(int totalSubjective) {
        this.totalSubjective = totalSubjective;
    }

    public int getTotalChoice() {
        return totalChoice;
    }

    public void setTotalChoice(int totalChoice) {
        this.totalChoice = totalChoice;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public List<ChoiceQuestion> getChoiceQuestions() {
        return choiceQuestions;
    }

    public void setChoiceQuestions(List<ChoiceQuestion> choiceQuestions) {
        this.choiceQuestions = choiceQuestions;
    }

    public List<SubjectiveQuestion> getSubjectiveQuestions() {
        return subjectiveQuestions;
    }

    public void setSubjectiveQuestions(List<SubjectiveQuestion> subjectiveQuestions) {
        this.subjectiveQuestions = subjectiveQuestions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
