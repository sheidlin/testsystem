package com.zust.stkdy.testsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubjectiveQuestion {
    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    private String description;
    private String answer;
    private String studentAnswer;
    private int isDeleted;
    private String wordsString=null;
    private ArrayList<KeyWord> words;
    private int secondLevelKnowledgePointId;
    private int firstLevelKnowledgePointId;
    private String  secondLevelKnowledgePoint;
    private String  firstLevelKnowledgePoint;

    public void wordsListfy(){
        if(wordsString==null)return;
        int cnt=0;
        if(words==null)words=new ArrayList<KeyWord>();
        words.clear();
        StringBuffer stringBuffer=new StringBuffer();
        String[] wordsArry =wordsString.split("，");
        for(String s:wordsArry){
            words.add(new KeyWord(s));
        }
    }
    public void wordsStringfy(){
        if(words==null)return;
        int cnt=0;
        StringBuffer stringBuffer=new StringBuffer();
        wordsString=null;
        for(KeyWord keyWord : words){
            if(cnt==0){
                stringBuffer.append(keyWord.getWord());
                cnt=1;
            }
            else{
                stringBuffer.append("，"+keyWord.getWord());
            }
            wordsString=stringBuffer.toString();
        }
    }

    @Override
    public String toString() {
        return "SubjectiveQuestion{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
                ", answer='" + answer + '\'' +
                ", studentAnswer='" + studentAnswer + '\'' +
                ", isDeleted=" + isDeleted +
                ", wordsString='" + wordsString + '\'' +
                ", words=" + words +
                ", secondLevelKnowledgePointId=" + secondLevelKnowledgePointId +
                ", firstLevelKnowledgePointId=" + firstLevelKnowledgePointId +
                ", secondLevelKnowledgePoint='" + secondLevelKnowledgePoint + '\'' +
                ", firstLevelKnowledgePoint='" + firstLevelKnowledgePoint + '\'' +
                '}';
    }

    public String getWordsString() {
        return wordsString;
    }

    public void setWordsString(String wordsString) {
        this.wordsString = wordsString;
    }

    public ArrayList<KeyWord> getWords() {
        return words;
    }

    public void setWords(ArrayList<KeyWord> words) {
        this.words = words;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
