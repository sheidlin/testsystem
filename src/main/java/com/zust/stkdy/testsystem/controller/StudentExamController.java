package com.zust.stkdy.testsystem.controller;

import com.mysql.cj.util.StringUtils;
import com.zust.stkdy.testsystem.common.Constants;
import com.zust.stkdy.testsystem.common.Result;
import com.zust.stkdy.testsystem.common.ResultGenerator;
import com.zust.stkdy.testsystem.config.annotation.TokenToStudent;
import com.zust.stkdy.testsystem.config.annotation.TokenToTeacher;
import com.zust.stkdy.testsystem.entity.*;
import com.zust.stkdy.testsystem.service.StudentExamService;
import com.zust.stkdy.testsystem.service.SubjectiveAnswerService;
import com.zust.stkdy.testsystem.utils.ExamUpdUtil;
import com.zust.stkdy.testsystem.utils.PageResult;
import com.zust.stkdy.testsystem.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/student/exams")
public class StudentExamController {
    @Autowired
    private StudentExamService studentExamService;
    @Autowired
    private SubjectiveAnswerService subjectiveAnswerService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result getExamInPage(@RequestParam Map<String ,Object> param, @TokenToStudent Student student){
        if(student==null) {
            return ResultGenerator.genNotLoginResult();
        }
        if (org.springframework.util.StringUtils.isEmpty(param.get("page")) || org.springframework.util.StringUtils.isEmpty(param.get("limit"))) {
//            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"");
            param.put("page","1");
            param.put("limit","10000");
        }
        PageUtil pageUtil=new PageUtil(param);
        PageResult pageResult=studentExamService.getStudentExamInPage(pageUtil,student.getId());
        return ResultGenerator.genSuccessResult(pageResult);
    }
    @RequestMapping(value = "/info/{studentId}/{id}",method = RequestMethod.GET)
    public Result examInfo(@PathVariable int id,@PathVariable int studentId,@TokenToTeacher Teacher teacher){
        if(teacher==null) {
            return ResultGenerator.genNotLoginResult();
        }
        StudentExam studentExam=new StudentExam();
        studentExam=studentExamService.getExamInfo(id,studentId);
        System.out.println(studentExam.toString());
        studentExam.turn();
        if(studentExam==null)return ResultGenerator.genFailResult("");
        else return ResultGenerator.genSuccessResult(studentExam);
    }
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    public Result enterExam(@PathVariable int id, @TokenToStudent Student student){
        if(student==null) {
            return ResultGenerator.genNotLoginResult();
        }
        StudentExam studentExam=new StudentExam();
        studentExam=studentExamService.getExamInfo(id,student.getId());
        if(studentExam==null)return ResultGenerator.genFailResult("");
        studentExam.turn();
        return ResultGenerator.genSuccessResult(studentExam);
    }
    @RequestMapping(value = "/correct",method = RequestMethod.POST)
    public Result judgeExam(@RequestBody StudentExam studentExam1, @TokenToStudent Student student){
        if(student==null){
            return ResultGenerator.genNotLoginResult();
        }
        StudentExam studentExam=studentExamService.getExamInfo(studentExam1.getId(),student.getId());
        if(studentExam==null)return ResultGenerator.genNullResult("NULL");
        if(studentExamService.judge(studentExam)>0){
            studentExamService.finishExam(studentExam.getId());
            return ResultGenerator.genSuccessResult();
        }
        else{
            return ResultGenerator.genFailResult("FAIL");
        }
    }
}
