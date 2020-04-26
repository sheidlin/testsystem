package com.zust.stkdy.testsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ChoiceQuestion {
    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    private String description;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private int answer;
    private int studentAnswer;
    private int isDeleted;

    public int getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(int studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "ChoiceQuestion{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", answer='" + answer + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
