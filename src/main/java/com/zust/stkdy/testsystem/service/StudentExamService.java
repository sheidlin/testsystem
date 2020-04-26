package com.zust.stkdy.testsystem.service;

import com.zust.stkdy.testsystem.entity.Student;
import com.zust.stkdy.testsystem.entity.StudentExam;
import com.zust.stkdy.testsystem.entity.Teacher;
import com.zust.stkdy.testsystem.utils.PageResult;
import com.zust.stkdy.testsystem.utils.PageUtil;

public interface StudentExamService {
    public PageResult getStudentExamInPage(PageUtil pageUtil,int studentId);
    public PageResult getStudentExamInPageByTeacherId(PageUtil pageUtil,int teacherId);
    public StudentExam getExamInfo(int examId,int studentId);
    public int judge(StudentExam studentExam);
    public int finishExam(int id);
    public int updateTeacher(Student student, Teacher teacher);
    public int deleteExam(Student student);
    public int deleteAnswerByStudent(Student student);
}
