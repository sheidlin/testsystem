package com.zust.stkdy.testsystem.entity;

import java.util.List;

public class StudentMajor {
    private String studentMajor;
    List<StudentClass>classList;

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public List<StudentClass> getClassList() {
        return classList;
    }

    public void setClassList(List<StudentClass> classList) {
        this.classList = classList;
    }
}
