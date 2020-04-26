package com.zust.stkdy.testsystem.service.impl;

import com.zust.stkdy.testsystem.dao.StudentDao;
import com.zust.stkdy.testsystem.dao.StudentExamDao;
import com.zust.stkdy.testsystem.entity.Student;
import com.zust.stkdy.testsystem.entity.Teacher;
import com.zust.stkdy.testsystem.service.StudentService;
import com.zust.stkdy.testsystem.utils.MD5Util;
import com.zust.stkdy.testsystem.utils.PageResult;
import com.zust.stkdy.testsystem.utils.PageUtil;
import com.zust.stkdy.testsystem.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Override
    public Student findStudentByToken(String token) {
        return studentDao.findStudentByToken(token);
    }

    @Override
    public PageResult getStudentInPage(PageUtil pageUtil, int teacherId) {
        pageUtil.put("teacherId",teacherId);
        List<Student>studentList=studentDao.findStudentByTeacherId(pageUtil);
        int num=studentDao.findNumOfStudentByTeacherId(teacherId);
        PageResult pageResult=new PageResult((num+pageUtil.getLimit()-1)/pageUtil.getLimit(),num,pageUtil.getPage(),pageUtil.getLimit());
        pageResult.setList(studentList);
        return pageResult;
    }

    @Override
    public int nameBeRegistered(String userName) {
        Student student=studentDao.findStudentByName(userName);
        if(student==null)return 0;
        return 1;
    }

    @Override
    public int addStudent(Student student) {
        student.setPassword(MD5Util.encodeByMd5(student.getPassword()));
        return studentDao.insertStudent(student);
    }

    @Override
    public Student updateTokenAndLogin(Student student) {
        student.setPassword(MD5Util.encodeByMd5(student.getPassword()));
        Student loginStudent=studentDao.findStudentByNameAndPassword(student.getUserName(),student.getPassword());
        if(loginStudent!=null){
            String token = SystemUtil.getNewToken(System.currentTimeMillis() + "", loginStudent.getId());
            if(studentDao.updateStudentToken(loginStudent.getId(),token)>0){
                loginStudent.setToken(token);
                return loginStudent;
            }
        }
        return null;
    }

    @Override
    public Student StudentConfirm(Student student) {
        student.setPassword(MD5Util.encodeByMd5(student.getPassword()));
        Student loginStudent=studentDao.findStudentByNameAndPassword(student.getUserName(),student.getPassword());
        return loginStudent;
    }

    @Override
    public int deleteStudent(int id) {
        return studentDao.deleteStudent(id);
    }

    @Override
    public Student findStudentById(int id) {
        return studentDao.findStudentById(id);
    }

    @Override
    public int deleteStudentTeacher(Student student) {
        student.setTeacherId(-1);
        return studentDao.updateStudent(student);
    }

    @Override
    public int updateStudent(Student student) {
        return studentDao.updateStudent(student);
    }

    @Override
    public Student findStudentBySno(String  sno) {
        System.out.println(sno);
        return studentDao.findStudentBySno(sno);
    }

    @Override
    public int updatePassword(Student student) {
        student.setPassword(MD5Util.encodeByMd5(student.getPassword()));
        return studentDao.updateStudentPassword(student.getId(),student.getPassword());
    }


    @Override
    public Student findStudentByEmail(String email) {
        return studentDao.findStudentByEmail(email);
    }
}
