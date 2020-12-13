package com.zust.stkdy.testsystem.controller;

import com.mysql.cj.util.StringUtils;
import com.sun.org.apache.bcel.internal.generic.ReturnInstruction;
import com.zust.stkdy.testsystem.common.Constants;
import com.zust.stkdy.testsystem.common.Result;
import com.zust.stkdy.testsystem.common.ResultGenerator;
import com.zust.stkdy.testsystem.config.annotation.TokenToTeacher;
import com.zust.stkdy.testsystem.dao.TeacherDao;
import com.zust.stkdy.testsystem.entity.Exam;
import com.zust.stkdy.testsystem.entity.Teacher;
import com.zust.stkdy.testsystem.service.ExamService;
import com.zust.stkdy.testsystem.service.TeacherService;
import com.zust.stkdy.testsystem.utils.ExamUpdUtil;
import com.zust.stkdy.testsystem.utils.PageResult;
import com.zust.stkdy.testsystem.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/exams")
public class ExamController {
    @Autowired
    private ExamService examService;
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result saveExam(@RequestBody Exam exam, @TokenToTeacher Teacher teacher){
        if(teacher==null) {
            return ResultGenerator.genNotLoginResult();
        }
        if(exam.getStartTime().before(new Date())||exam.getStartTime().after(exam.getEndTime())){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_BAD_REQUEST,"TIME_ERROR");
        }
        if(StringUtils.isEmptyOrWhitespaceOnly(exam.getTitle())){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_BAD_REQUEST,"TITLE_ERROR");
        }
        if(examService.getExamByTitle(exam.getTitle())!=null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_BAD_REQUEST,"TITLE_EXIST");
        }
        exam.setTeacherId(teacher.getId());
        int id=examService.addExam(exam);
        if(id==1)return ResultGenerator.genSuccessResult();
        return ResultGenerator.genFailResult("FAIL");
    }
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result updateExam(@RequestBody Exam exam, @TokenToTeacher Teacher teacher){
        if(teacher==null) {
            return ResultGenerator.genNotLoginResult();
        }
        Exam exam1=examService.getExamByTitle(exam.getTitle());
        System.out.println(exam1.toString());
        if(exam1!=null&&exam1.getId()!=exam.getId()){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_BAD_REQUEST,"TITLE_EXIST");
        }
        if(exam1.getTeacherId()!=teacher.getId()){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_BAD_REQUEST,"NOT_YOUR_EXAM");
        }
        if(exam1==null){
            return ResultGenerator.genNullResult("NULL");
        }
        exam.setTeacherId(teacher.getId());
        if(examService.updateExam(exam)>0){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("FAIL");
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result getExamInPage(@RequestParam Map<String ,Object> param,@TokenToTeacher Teacher teacher){
        if(teacher==null) {
            return ResultGenerator.genNotLoginResult();
        }
        if (org.springframework.util.StringUtils.isEmpty(param.get("page")) || org.springframework.util.StringUtils.isEmpty(param.get("limit"))) {
//            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"");
            param.put("page","1");
            param.put("limit","10000");
        }
        PageUtil pageUtil=new PageUtil(param);
        PageResult pageResult=examService.getExamInPage(pageUtil,teacher.getId());
        return ResultGenerator.genSuccessResult(pageResult);
    }

    @RequestMapping(value = "/choice/delete",method = RequestMethod.POST)
    public Result deleteChoiceQuestion(@RequestBody ExamUpdUtil examUpdUtil,@TokenToTeacher Teacher teacher){
        if(teacher==null){
            return ResultGenerator.genNotLoginResult();
        }
        Exam exam=examService.getExamById(examUpdUtil.getExamId());
        if(exam==null){
            return ResultGenerator.genNullResult("NULL");
        }
        if(exam.getTeacherId()!=teacher.getId()){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_BAD_REQUEST,"NOT_YOUR_EXAM");
        }
        if(examService.deleteChoiceQuestion(examUpdUtil)>0){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("");
    }
    @RequestMapping(value = "/subjective/delete",method = RequestMethod.POST)
    public Result deleteSubjectiveQuestion(@RequestBody ExamUpdUtil examUpdUtil,@TokenToTeacher Teacher teacher){
        if(teacher==null){
            return ResultGenerator.genNotLoginResult();
        }
        Exam exam=examService.getExamById(examUpdUtil.getExamId());
        if(exam==null){
            return ResultGenerator.genNullResult("NULL");
        }
        if(exam.getTeacherId()!=teacher.getId()){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_BAD_REQUEST,"NOT_YOUR_EXAM");
        }
        if(examService.deleteSubjectiveQuestion(examUpdUtil)>0){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("");
    }
    @RequestMapping(value = "/choice/save",method = RequestMethod.POST)
    public Result addChoiceQuestion(@RequestBody ExamUpdUtil examUpdUtil,@TokenToTeacher Teacher teacher){
        if(teacher==null){
            return ResultGenerator.genNotLoginResult();
        }
        Exam exam=examService.getExamById(examUpdUtil.getExamId());
        if(exam==null){
            return ResultGenerator.genNullResult("NULL");
        }
        if(exam.getTeacherId()!=teacher.getId()){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_BAD_REQUEST,"NOT_YOUR_EXAM");
        }
        if(examService.addChoiceQuestion(examUpdUtil)>0){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("");
    }
    @RequestMapping(value = "/subjective/save",method = RequestMethod.POST)
    public Result addSubjectiveQuestion(@RequestBody ExamUpdUtil examUpdUtil,@TokenToTeacher Teacher teacher){
        if(teacher==null){
            return ResultGenerator.genNotLoginResult();
        }
        examUpdUtil.setTeacherId(teacher.getId());
        Exam exam=examService.getExamById(examUpdUtil.getExamId());
        if(exam==null){
            return ResultGenerator.genNullResult("NULL");
        }
        if(exam.getTeacherId()!=teacher.getId()){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_BAD_REQUEST,"NOT_YOUR_EXAM");
        }
        if(examService.addSubjectiveQuestion(examUpdUtil)>0){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("");
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result deleteExam(@RequestBody Exam exam1,@TokenToTeacher Teacher teacher){
        if(teacher==null){
            return ResultGenerator.genNotLoginResult();
        }
        System.out.println("1"+teacher.toString());
        Exam exam=examService.getExamById(exam1.getId());
        if(exam==null){
            return ResultGenerator.genNullResult("NULL");
        }
        if(exam.getTeacherId()!=teacher.getId()){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_BAD_REQUEST,"NOT_YOUR_EXAM");
        }
        if(examService.deleteExam(exam)>0){
            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult("FAIL");
    }
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    public Result getExam(@PathVariable int id,@TokenToTeacher Teacher teacher){
        if(teacher==null){
            return ResultGenerator.genNotLoginResult();
        }
        Exam exam=examService.getExamById(id);
        if(exam==null){
            return ResultGenerator.genNullResult("NOT_EXIST");
        }
        if(exam.getTeacherId()!=teacher.getId()){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_BAD_REQUEST,"NOT_YOUR_EXAM");
        }
        Exam exam1=examService.getExamInfo(exam);
        return ResultGenerator.genSuccessResult(exam1);
    }

}
