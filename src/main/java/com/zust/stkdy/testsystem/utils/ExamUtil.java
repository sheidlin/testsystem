package com.zust.stkdy.testsystem.utils;

import com.zust.stkdy.testsystem.common.UserPair;
import com.zust.stkdy.testsystem.entity.Exam;
import com.zust.stkdy.testsystem.entity.Student;
import com.zust.stkdy.testsystem.entity.StudentExam;
import org.apache.catalina.User;
import org.hibernate.validator.constraints.Range;
import org.python.core.AstList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamUtil {

    public static List<StudentExam> resortExam(List<StudentExam>studentExams){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<StudentExam>studentExams1=new ArrayList<StudentExam>();
        List<StudentExam>studentExams2=new ArrayList<StudentExam>();
        for(StudentExam studentExam : studentExams){
            if(studentExam.getStartTime().before(new Date())&&studentExam.getEndTime().after(new Date())){
                studentExams1.add(studentExam);
            }
            else studentExams2.add(studentExam);
        }
        for(StudentExam studentExam : studentExams2){
            studentExams1.add(studentExam);
        }
        return studentExams1;
    }
    public static List<UserPair>getScorePairList(float score){
        double scorePartition[]={0.6,0.7,0.8,0.9,1};
        List<UserPair>scorePairList=new ArrayList<>();
        float upper,lower=0;
        for(double partition:scorePartition){
            upper=(float)(score*partition);
            if(partition!=1){
                UserPair scorePair=new UserPair(lower,(float)(upper-0.1));
                scorePairList.add(scorePair);
            }
            else{
                UserPair scorePair=new UserPair(lower,(float)(upper));
                scorePairList.add(scorePair);
            }
            lower=upper;
        }
        return scorePairList;
    }
}
