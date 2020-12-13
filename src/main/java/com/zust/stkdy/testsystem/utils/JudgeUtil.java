package com.zust.stkdy.testsystem.utils;

import com.mysql.cj.util.StringUtils;
import com.zust.stkdy.testsystem.entity.KeyWord;
import com.zust.stkdy.testsystem.entity.StudentSecondKnowledgePoint;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class JudgeUtil {
    private String answer;
    private String standardAnswer;
    private String keyWords;
    private int questionType;
    private int modelType;
    private double score;
    private static final String MAIN_PATH="/usr/project/scd/";
    private static final String KEY_WORDS = MAIN_PATH+"data/in/KeyWords.txt";
    private static final String ANSWER = MAIN_PATH+"data/in/Answer.txt";
    private static final String STANDARD_ANSWER= MAIN_PATH+"data/in/StandardAnswer.txt";
    private static final String OUT= MAIN_PATH+"data/out/score.txt";
    private static final String[] QUESTION_TYPE={"题型一","题型二","题型三"};
    private static final String[] MODEL_TYPE={"/model1.py","/model2.py","/model3.py","/model4.py","/model5.py","/model6.py","/model7.py","/model8.py",};

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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public JudgeUtil() {
    }

    public JudgeUtil(String answer, String standardAnswer, String  keyWords, int questionType, int modelType) {
        this.answer=answer;
        this.modelType=modelType;
        this.questionType=questionType;
        this.standardAnswer=standardAnswer;
        if(keyWords!=" ") {
            String[] wordsArry = keyWords.split("，");
            StringBuffer stringBuffer = new StringBuffer();
            int cnt = 0;
            for (String s : wordsArry) {
                if (cnt == 0) {
                    stringBuffer.append(s);
                    cnt = 1;
                } else {
                    stringBuffer.append(" " + s);
                }
            }
            this.keyWords = stringBuffer.toString();
        }
        else keyWords=" ";
    }

    public static void writeImagePath(JudgeUtil judgeAnswerUtil) {
        System.out.println(1);
        PrintWriter pw1 = null;
        PrintWriter pw2 = null;
        PrintWriter pw3 = null;
        try {
            FileUtil.sloveFile(KEY_WORDS);
            FileUtil.sloveFile(ANSWER);
            FileUtil.sloveFile(STANDARD_ANSWER);
            pw1 = new PrintWriter(new FileWriter(new File(KEY_WORDS)));
            pw1.println(judgeAnswerUtil.getKeyWords());
            pw2 = new PrintWriter(new FileWriter(new File(ANSWER)));
            pw2.println(judgeAnswerUtil.getAnswer());
            pw3 = new PrintWriter(new FileWriter(new File(STANDARD_ANSWER)));
            pw3.println(judgeAnswerUtil.getStandardAnswer());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(2);
        pw1.close();
        pw2.close();
        pw3.close();
    }
    public static String readAnswer() {
        BufferedReader br;
        String answer = null;
        try {
            br = new BufferedReader(new FileReader(new File(OUT)));
            answer = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
    public void execPy() {
        Process proc = null;
        try {
            System.out.println("python " + MAIN_PATH+QUESTION_TYPE[questionType-1]+MODEL_TYPE[modelType-1]);
            proc = Runtime.getRuntime().exec("python " + MAIN_PATH+QUESTION_TYPE[questionType-1]+MODEL_TYPE[modelType-1]);
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public double judge(){
        writeImagePath(this);
        execPy();
        score=Double.parseDouble(readAnswer());
        return score;
    }

    public static StudentSecondKnowledgePoint rewriteLine(StudentSecondKnowledgePoint studentSecondKnowledgePoint,double score){
        //重绘
        int totVisit=studentSecondKnowledgePoint.getTotalVisit();
        Date now=new Date();
        Date lastTime =studentSecondKnowledgePoint.getUpdateTime();
        System.out.println(now.getTime()-lastTime.getTime());
        long minutes=(now.getTime()-lastTime.getTime())/(1000*60);

        double beta=studentSecondKnowledgePoint.getBeta();
        double totM=studentSecondKnowledgePoint.getM();
        double totScore=studentSecondKnowledgePoint.getTotalScore();
        beta=f(beta,score/10,totVisit,minutes);
        if(totVisit>0&&minutes>60)totM+=Math.pow(minutes,-beta);
        totScore+=score;
        studentSecondKnowledgePoint.setBeta(beta);
        studentSecondKnowledgePoint.setM(totM);
        studentSecondKnowledgePoint.setTotalVisit(totVisit+1);
        studentSecondKnowledgePoint.setTotalScore(totScore);
        return studentSecondKnowledgePoint;
    }
    public static double f(double beta,double d,int visit,long minutes){
        if(visit==0)return 0.3307-d*(0.3307-0.1056);
        if(minutes>60)return beta;
        return Math.max(0.08,beta/Math.sqrt(d+1));
    }
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStandardAnswer() {
        return standardAnswer;
    }

    public static String getOUT() {
        return OUT;
    }

    public void setStandardAnswer(String standardAnswer) {
        this.standardAnswer = standardAnswer;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public static String getANSWER() {
        return ANSWER;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    @Override
    public String toString() {
        return "JudgeUtil{" +
                "answer='" + answer + '\'' +
                ", standardAnswer='" + standardAnswer + '\'' +
                ", keyWords='" + keyWords + '\'' +
                ", questionType=" + questionType +
                ", modelType=" + modelType +
                '}';
    }
}
