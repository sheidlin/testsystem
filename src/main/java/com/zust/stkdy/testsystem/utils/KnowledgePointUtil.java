package com.zust.stkdy.testsystem.utils;

import com.zust.stkdy.testsystem.entity.FirstKnowledgePoint;
import com.zust.stkdy.testsystem.entity.StudentSecondKnowledgePoint;

import java.util.*;

public class KnowledgePointUtil {
    public static List<FirstKnowledgePoint> seconfToFirst(List<StudentSecondKnowledgePoint> studentSecondKnowledgePointList){
        Map<Integer,FirstKnowledgePoint> mp=new HashMap<Integer,FirstKnowledgePoint>();
        List<FirstKnowledgePoint>firstKnowledgePointList=new ArrayList<>();
        for(StudentSecondKnowledgePoint studentSecondKnowledgePoint:studentSecondKnowledgePointList){
            int firstId=studentSecondKnowledgePoint.getFirstKnowledgePointId();
            FirstKnowledgePoint firstKnowledgePoint=mp.get(firstId);
            if(firstKnowledgePoint==null){
                firstKnowledgePoint=new FirstKnowledgePoint(studentSecondKnowledgePoint.getFirstKnowledgePointId(),studentSecondKnowledgePoint.getFirstKnowledgePoint(),0,0,0);
            }
            int totVisit=studentSecondKnowledgePoint.getTotalVisit();
            Date now=new Date();
            Date lastTime =studentSecondKnowledgePoint.getUpdateTime();
            long minutes=(now.getTime()-lastTime.getTime())/(1000*60);
            double totM=studentSecondKnowledgePoint.getM();
            totM+=Math.pow(minutes,-studentSecondKnowledgePoint.getBeta());
            double totScore=studentSecondKnowledgePoint.getTotalScore();
            ////
            firstKnowledgePoint.setAveScore(firstKnowledgePoint.getAveScore()+totScore);
            firstKnowledgePoint.setAveRetention(firstKnowledgePoint.getAveRetention()+totM);
            firstKnowledgePoint.setTotalVisit(firstKnowledgePoint.getTotalVisit()+totVisit);
            mp.put(firstKnowledgePoint.getId(),firstKnowledgePoint);
        }
        for(Map.Entry<Integer,FirstKnowledgePoint> entry : mp.entrySet()){
            FirstKnowledgePoint firstKnowledgePoint=entry.getValue();
            firstKnowledgePoint.toAve();
            firstKnowledgePointList.add(firstKnowledgePoint);
        }
        return firstKnowledgePointList;
    }
    public static List<StudentSecondKnowledgePoint> calSecondList(List<StudentSecondKnowledgePoint>studentSecondKnowledgePointList){
        for(StudentSecondKnowledgePoint studentSecondKnowledgePoint:studentSecondKnowledgePointList){
            int totalVisit=studentSecondKnowledgePoint.getTotalVisit();
            double totalScore=studentSecondKnowledgePoint.getTotalScore();
            studentSecondKnowledgePoint.setAveScore(totalScore/totalVisit);
        }
        return studentSecondKnowledgePointList;
    }
}
