package com.zust.stkdy.testsystem.controller;

import com.zust.stkdy.testsystem.common.Constants;
import com.zust.stkdy.testsystem.common.Result;
import com.zust.stkdy.testsystem.common.ResultGenerator;
import com.zust.stkdy.testsystem.config.annotation.TokenToStudent;
import com.zust.stkdy.testsystem.config.annotation.TokenToTeacher;
import com.zust.stkdy.testsystem.dao.Student2SecondPointDao;
import com.zust.stkdy.testsystem.dao.StudentDao;
import com.zust.stkdy.testsystem.entity.FirstKnowledgePoint;
import com.zust.stkdy.testsystem.entity.Student;
import com.zust.stkdy.testsystem.entity.SubjectiveQuestion;
import com.zust.stkdy.testsystem.entity.Teacher;
import com.zust.stkdy.testsystem.service.*;
import com.zust.stkdy.testsystem.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectiveQuestionService subjectiveQuestionService;
    @Autowired
    private StudentExamService studentExamService;
    @Autowired
    private KnowledgePointService knowledgePointService;
    @RequestMapping(value = "/student/register",method = RequestMethod.POST)
    public Result register(@RequestBody Student student) {
        if(studentService.nameBeRegistered(student.getUserName())>0){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_BAD_REQUEST,"NAME_EXIST");
        }
        if(studentService.findStudentByEmail(student.getEmail())!=null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_BAD_REQUEST,"EMAIL_EXIST");
        }
        if(!student.legal()){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"");
        }
        if(studentService.addStudent(student)>0){
            return ResultGenerator.genSuccessResult();
        }
        else{
            return ResultGenerator.genFailResult("FAIL");
        }
    }
    @RequestMapping(value = "/student/login", method = RequestMethod.POST)
    public Result login(@RequestBody Student student) {
        Result result = ResultGenerator.genFailResult("FAIL");
        if (StringUtils.isEmpty(student.getUserName()) || StringUtils.isEmpty(student.getPassword())) {
            ;return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"");
        }
        Student loginStudent=studentService.updateTokenAndLogin(student);
        if(loginStudent==null){
            return result;
        }
        return ResultGenerator.genSuccessResult(loginStudent);
    }
    @RequestMapping(value = "/students/list",method = RequestMethod.GET)
    public Result list(@RequestParam Map<String, Object> params, @TokenToTeacher Teacher teacher) {
        if(teacher==null) {
            return ResultGenerator.genNotLoginResult();
        }
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
//            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"");
              params.put("page","1");
              params.put("limit","10000");
        }
        PageUtil pageUtil = new PageUtil(params);
        return ResultGenerator.genSuccessResult(studentService.getStudentInPage(pageUtil,teacher.getId()));
    }
    @RequestMapping(value = "/students/delete",method = RequestMethod.POST)
    public Result deleteStudent(@RequestBody Student delStudent, @TokenToTeacher Teacher teacher) {
        if(teacher==null) {
            return ResultGenerator.genNotLoginResult();
        }
        Student student=studentService.findStudentById(delStudent.getId());
        studentExamService.deleteExam(student);
        studentExamService.deleteAnswerByStudent(student);
        if(student==null||student.getTeacherId()!=teacher.getId()){
            return ResultGenerator.genNullResult("NULL");
        }
        if(studentService.deleteStudentTeacher(student)>0)return ResultGenerator.genSuccessResult();
        return ResultGenerator.genSuccessResult();
    }
    @RequestMapping(value = "/student/updateteacher",method = RequestMethod.POST)
    public Result updateTeacher(@RequestBody Teacher teacher, @TokenToStudent Student student) {
        if(student==null) {
            return ResultGenerator.genNotLoginResult();
        }
        if(student.getTeacherId()==teacher.getId()){
            return ResultGenerator.genFailResult("EXIST");
        }
        if(student.getTeacherId()!=-1){
            return ResultGenerator.genFailResult("TEACHER_EXIST");
        }
        teacher=teacherService.findTeacherById(teacher);
        if(teacher==null){
            return ResultGenerator.genNullResult("NULL");
        }
        student.setTeacherId(teacher.getId());
        studentService.updateStudent(student);
        if(studentExamService.updateTeacher(student,teacher)>0)return ResultGenerator.genSuccessResult();
        return ResultGenerator.genSuccessResult();
    }
    @RequestMapping(value = "/student/update",method = RequestMethod.PUT)
    public Result updateStudent(@RequestBody Student updstudent, @TokenToStudent Student student) {
        if(student==null) {
            return ResultGenerator.genNotLoginResult();
        }
        updstudent.setUserName(student.getUserName());
        updstudent.setId(student.getId());
        updstudent.setTeacherId(student.getTeacherId());
        updstudent.setEmail(student.getEmail());
        updstudent.setSno(student.getSno());
        if(studentService.updateStudent(updstudent)>0)return ResultGenerator.genSuccessResult();
        return ResultGenerator.genSuccessResult();
    }
    @RequestMapping(value = "/student/updatePassword",method = RequestMethod.PUT)
    public Result updatePwd(@RequestBody PwdUtil pwdUtil, @TokenToStudent Student student) {
        if(student==null) {
            return ResultGenerator.genNotLoginResult();
        }
        student.setPassword(pwdUtil.getPassword());
        if(studentService.StudentConfirm(student)==null){
            return ResultGenerator.genFailResult("FAIL");
        }
        student.setPassword(pwdUtil.getNewPassword());
        if(studentService.updatePassword(student)>0){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("FAIL");
    }
    @RequestMapping(value = "/student/info",method = RequestMethod.GET)
    public Result updatePwd(@TokenToStudent Student student) {
        if(student==null) {
            return ResultGenerator.genNotLoginResult();
        }
        student.setToken("");
        return ResultGenerator.genSuccessResult(student);
    }
    @RequestMapping(value = "/students/info/{id}",method = RequestMethod.GET)
    public Result getStudent(@PathVariable int id, @TokenToTeacher Teacher teacher){
        if(teacher==null) {
            return ResultGenerator.genNotLoginResult();
        }
        if(id<0){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"NotExist");
        }
        Student student=studentService.findStudentById(id);
        if(student==null)return ResultGenerator.genNullResult("Null");
        else return ResultGenerator.genSuccessResult(student);
    }
    @RequestMapping(value = "/students/save",method = RequestMethod.POST)
    public Result save(@RequestBody Student upStudent, @TokenToTeacher Teacher teacher) {
        if(teacher==null) {
            return ResultGenerator.genNotLoginResult();
        }
        Student student1;
        student1=studentService.findStudentBySno(upStudent.getUserNumber());
        if(student1==null){
            return ResultGenerator.genNullResult("NULL");
        }
        student1.setTeacherId(teacher.getId());
        if(studentService.updateStudent(student1)>0){
            studentExamService.updateTeacher(student1,teacher);
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genSuccessResult();
    }
    @RequestMapping(value = "/common/judgetest",method = RequestMethod.POST)
    public Result judge(@RequestBody TestJudgeUtil testJudgeUtil) {
        SubjectiveQuestion subjectiveQuestion=subjectiveQuestionService.findSubjectiveQuestionById(testJudgeUtil.getQuestionId());
        JudgeUtil judgeUtil=new JudgeUtil(testJudgeUtil.getAnswer(),subjectiveQuestion.getAnswer(),subjectiveQuestion.getWordsString(),testJudgeUtil.getQuestionType(),testJudgeUtil.getModelType());
        System.out.println(judgeUtil.toString());
        Double score=judgeUtil.judge();
        testJudgeUtil.setScore(score);
        return ResultGenerator.genSuccessResult(testJudgeUtil);
    }
    @RequestMapping(value = "/student/knowledge/first/profile")
    public Result getFirstKnowledgePointList(@RequestParam Map<String ,Object> param,@TokenToStudent Student student){
        if(student==null) {
            return ResultGenerator.genNotLoginResult();
        }
        if (org.springframework.util.StringUtils.isEmpty(param.get("page")) || org.springframework.util.StringUtils.isEmpty(param.get("limit"))) {
//            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"");
            param.put("page","1");
            param.put("limit","10000");
        }
        PageUtil pageUtil=new PageUtil(param);
        PageResult pageResult=knowledgePointService.getStudentFirstKnowledgePoint(pageUtil,student);
        return ResultGenerator.genSuccessResult(pageResult);
    }
    @RequestMapping(value = "/student/knowledge/first/profile/{id}")
    public Result getFirstKnowledgePointList(@RequestParam Map<String ,Object> param, @PathVariable int id,@TokenToStudent Student student){
        if(student==null) {
            return ResultGenerator.genNotLoginResult();
        }
        if (org.springframework.util.StringUtils.isEmpty(param.get("page")) || org.springframework.util.StringUtils.isEmpty(param.get("limit"))) {
//            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"");
            param.put("page","1");
            param.put("limit","10000");
        }
        PageResult pageResult=knowledgePointService.getSecondKnowledgePointByFirstPointId(student,id);
        return ResultGenerator.genSuccessResult(pageResult);
    }
}
