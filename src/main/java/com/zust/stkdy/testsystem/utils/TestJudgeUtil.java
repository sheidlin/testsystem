package com.zust.stkdy.testsystem.utils;

public class TestJudgeUtil {
    private int questionType;
    private int modelType;
    private int questionId;
    private String answer;
    private double score;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public int getModelType() {
        return modelType;
    }

    public void setModelType(int modelType) {
        this.modelType = modelType;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
