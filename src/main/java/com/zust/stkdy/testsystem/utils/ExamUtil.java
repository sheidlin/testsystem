package com.zust.stkdy.testsystem.utils;

import com.zust.stkdy.testsystem.entity.Exam;
import com.zust.stkdy.testsystem.entity.Student;
import com.zust.stkdy.testsystem.entity.StudentExam;
import org.hibernate.validator.constraints.Range;

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
}
