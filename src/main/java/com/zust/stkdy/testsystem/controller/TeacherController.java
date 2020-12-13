package com.zust.stkdy.testsystem.controller;
import com.mysql.cj.util.StringUtils;
import com.zust.stkdy.testsystem.common.Constants;
import com.zust.stkdy.testsystem.common.Result;
import com.zust.stkdy.testsystem.common.ResultGenerator;
import com.zust.stkdy.testsystem.config.annotation.TokenToStudent;
import com.zust.stkdy.testsystem.config.annotation.TokenToTeacher;
import com.zust.stkdy.testsystem.entity.Student;
import com.zust.stkdy.testsystem.entity.Teacher;
import com.zust.stkdy.testsystem.service.StudentExamService;
import com.zust.stkdy.testsystem.service.TeacherService;
import com.zust.stkdy.testsystem.utils.PageResult;
import com.zust.stkdy.testsystem.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController{
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentExamService studentExamService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestBody Teacher teacher) {
        if(teacherService.addTeacher(teacher)>0){
            return ResultGenerator.genSuccessResult();
        }
        else{
            return ResultGenerator.genFailResult("FAIL");
        }
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result list(@RequestParam Map<String, Object> params, @TokenToStudent Student student) {
//        if(student==null) {
//            return ResultGenerator.genNotLoginResult();
//        }
        if (StringUtils.isEmptyOrWhitespaceOnly((String) params.get("page")) || StringUtils.isEmptyOrWhitespaceOnly((String) params.get("limit"))) {
//            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"");
            params.put("page","1");
            params.put("limit","10000");
        }
        PageUtil pageUtil = new PageUtil(params);
        String wd=(String )params.get("wd");
        pageUtil.put("wd",wd);
        System.out.println(1);
        return ResultGenerator.genSuccessResult(teacherService.getTeacherInPage(pageUtil,student.getId()));
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Teacher teacher) {
        Result result = ResultGenerator.genFailResult("FAIL");
        if (StringUtils.isEmptyOrWhitespaceOnly(teacher.getUserName()) || StringUtils.isEmptyOrWhitespaceOnly(teacher.getPassword())) {
            ;return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"");
        }
        Teacher loginTeacher=teacherService.updateTokenAndLogin(teacher);
        if(loginTeacher==null){
            return result;
        }
        return ResultGenerator.genSuccessResult(loginTeacher);
    }

    @RequestMapping(value = "/exams/list/{id}", method = RequestMethod.GET)
    public Result getStudentExamInPage(@RequestParam Map<String ,Object> param, @PathVariable int id, @TokenToTeacher Teacher teacher){
        if (org.springframework.util.StringUtils.isEmpty(param.get("page")) || org.springframework.util.StringUtils.isEmpty(param.get("limit"))) {
//            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"");
            param.put("page","1");
            param.put("limit","10000");
        }
        PageUtil pageUtil=new PageUtil(param);
        pageUtil.put("examId",id);
        String major=(String)param.get("major");
        String classs=(String)param.get("class");
        String realName=(String)param.get("realName");
        String sno=(String)param.get("sno");
        pageUtil.put("major",null);
        pageUtil.put("class",null);
        pageUtil.put("realName",null);
        pageUtil.put("sno",null);
        System.out.println(major);
        System.out.println(classs);
        System.out.println(sno);
        System.out.println(realName);
        if(major!=null&&!StringUtils.isEmptyOrWhitespaceOnly(major)&&major!=""){
            pageUtil.put("major",major);
        }
        if(classs!=""){
            pageUtil.put("class",classs);
        }
        if(realName!=""){
            pageUtil.put("realName",realName);
        }
        if(sno!=""){
            pageUtil.put("sno",sno);
        }
        PageResult pageResult=studentExamService.getStudentExamInPageByTeacherId(pageUtil,teacher.getId());
        return ResultGenerator.genSuccessResult(pageResult);
    }
}
