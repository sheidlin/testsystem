package com.zust.stkdy.testsystem.controller;

import com.mysql.cj.util.StringUtils;
import com.zust.stkdy.testsystem.common.Constants;
import com.zust.stkdy.testsystem.common.Result;
import com.zust.stkdy.testsystem.common.ResultGenerator;
import com.zust.stkdy.testsystem.config.annotation.TokenToTeacher;
import com.zust.stkdy.testsystem.entity.SubjectiveQuestion;
import com.zust.stkdy.testsystem.entity.Teacher;
import com.zust.stkdy.testsystem.service.SubjectiveQuestionService;
import com.zust.stkdy.testsystem.utils.PageResult;
import com.zust.stkdy.testsystem.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/questions/subjective")
public class SubjectiveQuestionController {
    @Autowired
    private SubjectiveQuestionService subjectiveQuestionService;
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result saveChoiceQuestion(@RequestBody SubjectiveQuestion subjectiveQuestion, @TokenToTeacher Teacher teacher){
        if(teacher==null) {
            return ResultGenerator.genNotLoginResult();
        }
        subjectiveQuestion.wordsStringfy();
        if(subjectiveQuestion.getWordsString()==null){
            subjectiveQuestion.setWordsString(" ");
        }
        String firstPoint=subjectiveQuestion.getFirstLevelKnowledgePoint();
        String secondPoint=subjectiveQuestion.getSecondLevelKnowledgePoint();
        if(!StringUtils.isEmptyOrWhitespaceOnly(firstPoint)&&!StringUtils.isEmptyOrWhitespaceOnly(secondPoint)){
            subjectiveQuestionService.sloveKnowledgePoint(subjectiveQuestion); //更新知识点
        }
        if(subjectiveQuestionService.addSubjectiveQuestion(subjectiveQuestion)>0){
            return ResultGenerator.genSuccessResult();
        }
        else return ResultGenerator.genFailResult("");
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result updateChoiceQuestion(@RequestBody SubjectiveQuestion subjectiveQuestion, @TokenToTeacher Teacher teacher){
        if(teacher==null) {
            return ResultGenerator.genNotLoginResult();
        }
        if(subjectiveQuestion.getId()<0){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"NotExist");
        }
        subjectiveQuestion.wordsStringfy();
        if(subjectiveQuestion.getWordsString()==null){
            subjectiveQuestion.setWordsString(" ");
        }
        String firstPoint=subjectiveQuestion.getFirstLevelKnowledgePoint();
        String secondPoint=subjectiveQuestion.getSecondLevelKnowledgePoint();
        if(!StringUtils.isEmptyOrWhitespaceOnly(firstPoint)&&!StringUtils.isEmptyOrWhitespaceOnly(secondPoint)){
            subjectiveQuestionService.sloveKnowledgePoint(subjectiveQuestion); //更新知识点
        }
        if(subjectiveQuestionService.updateSubjectiveQuestion(subjectiveQuestion)>0)return ResultGenerator.genSuccessResult("SUCCESS");
        else return ResultGenerator.genFailResult("");
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result deleteChoiceQuestion(@RequestBody SubjectiveQuestion subjectiveQuestion, @TokenToTeacher Teacher teacher){
        if(teacher==null) {
            return ResultGenerator.genNotLoginResult();
        }
        if(subjectiveQuestion.getId()<0){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"NotExist");
        }
        if(subjectiveQuestionService.findSubjectiveQuestionById(subjectiveQuestion.getId())==null)return ResultGenerator.genNullResult("IsDeleted");
        if(subjectiveQuestionService.deleteSubjectiveQuestion(subjectiveQuestion.getId())>0)return ResultGenerator.genSuccessResult("SUCCESS");
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
        SubjectiveQuestion subjectiveQuestion;
        subjectiveQuestion=subjectiveQuestionService.findSubjectiveQuestionById(id);
        subjectiveQuestion.wordsListfy();
        if(subjectiveQuestion==null)return ResultGenerator.genNullResult("Null");
        else return ResultGenerator.genSuccessResult(subjectiveQuestion);
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result getChoiceQuestionList(@RequestParam Map<String ,Object> param, @TokenToTeacher Teacher teacher){
        if (org.springframework.util.StringUtils.isEmpty(param.get("page")) || org.springframework.util.StringUtils.isEmpty(param.get("limit"))) {
//            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"");
            param.put("page","1");
            param.put("limit","10000");
        }
        PageUtil pageUtil=new PageUtil(param);
        PageResult pageResult=subjectiveQuestionService.getSubjectiveQuestionInPage(pageUtil);
        return ResultGenerator.genSuccessResult(pageResult);
    }

    @RequestMapping(value = "/update/keywords",method = RequestMethod.PUT)
    public Result setKeyWords(@RequestBody SubjectiveQuestion subjectiveQuestion, @TokenToTeacher Teacher teacher){
        if(teacher==null) {
            return ResultGenerator.genNotLoginResult();
        }
        if(subjectiveQuestion.getWords()==null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"NO_KEY_WORDS");
        }
        if(subjectiveQuestionService.updateKeyWords(subjectiveQuestion)>0){
            return ResultGenerator.genSuccessResult();
        }
        else{
            return ResultGenerator.genFailResult("FAIL");
        }
    }
}
