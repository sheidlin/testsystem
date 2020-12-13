package com.zust.stkdy.testsystem.controller;

import com.mysql.cj.util.StringUtils;
import com.zust.stkdy.testsystem.common.Constants;
import com.zust.stkdy.testsystem.common.Result;
import com.zust.stkdy.testsystem.common.ResultGenerator;
import com.zust.stkdy.testsystem.config.annotation.TokenToTeacher;
import com.zust.stkdy.testsystem.entity.*;
import com.zust.stkdy.testsystem.service.ChoiceQuestionService;
import com.zust.stkdy.testsystem.utils.PageResult;
import com.zust.stkdy.testsystem.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Map;

@RestController
@RequestMapping("/questions/choice")
public class ChoiceQuestionController {
    @Autowired
    private ChoiceQuestionService choiceQuestionService;
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result saveChoiceQuestion(@RequestBody ChoiceQuestion choiceQuestion, @TokenToTeacher Teacher teacher){
        if(teacher==null) {
            return ResultGenerator.genNotLoginResult();
        }
        if(choiceQuestionService.addChoiceQuestion(choiceQuestion)>0)return ResultGenerator.genSuccessResult(ResultGenerator.genSuccessResult());
        else return ResultGenerator.genFailResult("");
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result updateChoiceQuestion(@RequestBody ChoiceQuestion choiceQuestion, @TokenToTeacher Teacher teacher){
        if(teacher==null) {
            return ResultGenerator.genNotLoginResult();
        }
        if(choiceQuestion.getId()<0){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"NotExist");
        }
        if(choiceQuestionService.updateChoiceQuestion(choiceQuestion)>0)return ResultGenerator.genSuccessResult("SUCCESS");
        else return ResultGenerator.genFailResult("");
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result deleteChoiceQuestion(@RequestBody ChoiceQuestion choiceQuestion, @TokenToTeacher Teacher teacher){
        if(teacher==null) {
            return ResultGenerator.genNotLoginResult();
        }
        if(choiceQuestion.getId()<0){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"NotExist");
        }
        if(choiceQuestionService.findChoiceQuestionById(choiceQuestion.getId())==null)return ResultGenerator.genNullResult("IsDeleted");
        if(choiceQuestionService.deleteChoiceQuestion(choiceQuestion.getId())>0)return ResultGenerator.genSuccessResult("SUCCESS");
        else return ResultGenerator.genFailResult("");
    }
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    public Result getChoiceQuestion(@PathVariable int id, @TokenToTeacher Teacher teacher){
        if(teacher==null) {
            return ResultGenerator.genNotLoginResult();
        }
        if(id<0){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"NotExist");
        }
        ChoiceQuestion choiceQuestion;
        choiceQuestion=choiceQuestionService.findChoiceQuestionById(id);
        if(choiceQuestion==null)return ResultGenerator.genNullResult("Null");
        else return ResultGenerator.genSuccessResult(choiceQuestion);
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result getChoiceQuestionList( @RequestParam Map<String ,Object> param, @TokenToTeacher Teacher teacher){
        if(teacher==null) {
            return ResultGenerator.genNotLoginResult();
        }
        if (org.springframework.util.StringUtils.isEmpty(param.get("page")) || org.springframework.util.StringUtils.isEmpty(param.get("limit"))) {
//            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"");
            param.put("page","1");
            param.put("limit","10000");
        }
        PageUtil pageUtil=new PageUtil(param);
        PageResult pageResult=choiceQuestionService.getChoiceQuestionInPage(pageUtil);
        return ResultGenerator.genSuccessResult(pageResult);
    }
}
