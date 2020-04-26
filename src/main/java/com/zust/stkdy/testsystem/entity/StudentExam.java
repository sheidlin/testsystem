package com.zust.stkdy.testsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class StudentExam {
    private int id;
    private int isFinished;
    private int teacherId;
    private int examId;
    private int studentId;
    private String  studentName;
    private String  studentMajor;
    private String  studentSno;
    private String  studentClass;
    private int isDeleted;
    private float score;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    private List<ChoiceQuestion>choiceQuestionList;
    private List<SubjectiveQuestion>subjectiveQuestionList;
    private List<ChoiceAnswer>choiceAnswerList;
    private List<SubjectiveAnswer>subjectiveAnswerList;
    public StudentExam() {
    }

    public StudentExam(int teacherId, int examId, int studentId, float score, String title, Date startTime, Date endTime) {
        this.teacherId = teacherId;
        this.examId = examId;
        this.studentId = studentId;
        this.score = score;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public void addStudent(Student student){
        this.setStudentClass(student.getClasss());
        this.setStudentMajor(student.getMajor());
        this.setStudentName(student.getRealName());
        this.setStudentSno(student.getSno());
    }
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public String getStudentSno() {
        return studentSno;
    }

    public void setStudentSno(String studentSno) {
        this.studentSno = studentSno;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public List<ChoiceQuestion> getChoiceQuestionList() {
        return choiceQuestionList;
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

    public void setChoiceQuestionList(List<ChoiceQuestion> choiceQuestionList) {
        this.choiceQuestionList = choiceQuestionList;
    }

    public List<SubjectiveQuestion> getSubjectiveQuestionList() {
        return subjectiveQuestionList;
    }

    public void setSubjectiveQuestionList(List<SubjectiveQuestion> subjectiveQuestionList) {
        this.subjectiveQuestionList = subjectiveQuestionList;
    }

    public List<ChoiceAnswer> getChoiceAnswerList() {
        return choiceAnswerList;
    }

    public void setChoiceAnswerList(List<ChoiceAnswer> choiceAnswerList) {
        this.choiceAnswerList = choiceAnswerList;
    }

    public List<SubjectiveAnswer> getSubjectiveAnswerList() {
        return subjectiveAnswerList;
    }

    public void setSubjectiveAnswerList(List<SubjectiveAnswer> subjectiveAnswerList) {
        this.subjectiveAnswerList = subjectiveAnswerList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "StudentExam{" +
                "id=" + id +
                ", isFinished=" + isFinished +
                ", teacherId=" + teacherId +
                ", examId=" + examId +
                ", studentId=" + studentId +
                ", isDeleted=" + isDeleted +
                ", score=" + score +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", updateTime=" + updateTime +
                ", choiceAnswerList=" + choiceAnswerList +
                ", subjectiveAnswerList=" + subjectiveAnswerList +
                '}';
    }
    public void turn(){
        if(subjectiveAnswerList!=null){
            for(int i=0;i<subjectiveAnswerList.size();i++){
                SubjectiveQuestion subjectiveQuestion=subjectiveQuestionList.get(i);
                SubjectiveAnswer subjectiveAnswer=subjectiveAnswerList.get(i);
                if(subjectiveAnswer!=null&&subjectiveQuestion!=null) {
                    String answer = subjectiveAnswer.getAnswer();
                    subjectiveAnswer.setAnswer(subjectiveQuestion.getAnswer());
                    subjectiveQuestion.setAnswer(answer);
                }
            }
        }
        if(choiceAnswerList!=null){
            for(int i=0;i<choiceAnswerList.size();i++){
                ChoiceAnswer choiceAnswer=choiceAnswerList.get(i);
                ChoiceQuestion choiceQuestion=choiceQuestionList.get(i);
                if(choiceQuestion!=null&&choiceAnswer!=null){
                    int answer=choiceAnswer.getAnswer();
                    choiceAnswer.setAnswer(choiceQuestion.getAnswer());
                    choiceQuestion.setAnswer(answer);
                }

            }
        }

    }
}
