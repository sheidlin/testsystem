package com.zust.stkdy.testsystem.entity;

import java.util.List;

public class StudentClass {
    private String studentClass;
    List<Student> studentList;

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}