package com.zust.stkdy.testsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mysql.cj.util.StringUtils;

import java.util.Date;

public class Student {
    private int id;
    private String userName;
    private String password;
    private String realName;
    private String school;
    private String major;
    private String sno;
    private String classs;
    private String email;
    private String token;
    private String userNumber;

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    private int teacherId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    private int isDeleted;
    public boolean legal(){
        return !(StringUtils.isEmptyOrWhitespaceOnly(userName)||StringUtils.isEmptyOrWhitespaceOnly(password)||StringUtils.isEmptyOrWhitespaceOnly(realName)
                ||StringUtils.isEmptyOrWhitespaceOnly(school)||StringUtils.isEmptyOrWhitespaceOnly(major)||StringUtils.isEmptyOrWhitespaceOnly(classs)||
                StringUtils.isEmptyOrWhitespaceOnly(email));
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", sno='" + sno + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", createTime=" + createTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
