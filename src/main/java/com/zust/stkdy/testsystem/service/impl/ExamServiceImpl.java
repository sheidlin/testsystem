package com.zust.stkdy.testsystem.service.impl;

import com.zust.stkdy.testsystem.common.UserPair;
import com.zust.stkdy.testsystem.dao.*;
import com.zust.stkdy.testsystem.entity.*;
import com.zust.stkdy.testsystem.service.ExamService;
import com.zust.stkdy.testsystem.utils.ExamUpdUtil;
import com.zust.stkdy.testsystem.utils.ExamUtil;
import com.zust.stkdy.testsystem.utils.PageResult;
import com.zust.stkdy.testsystem.utils.PageUtil;
import org.apache.catalina.User;
import org.python.core.AstList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamDao examDao;
    @Autowired
    private ChoiceQuestionDao choiceQuestionDao;
    @Autowired
    private SubjectiveQuestionDao subjectiveQuestionDao;
    @Autowired
    private Exam2ChoiceDao exam2ChoiceDao;
    @Autowired
    private Exam2SubjectiveDao exam2SubjectiveDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentExamDao studentExamDao;
    @Autowired
    private ChoiceAnswerDao choiceAnswerDao;
    @Autowired
    private SubjectiveAnswerDao subjectiveAnswerDao;
    @Autowired
    private SecondKnowledgePointDao secondKnowledgePointDao;

    @Override
    public int addExam(Exam exam) {
        List<Student>studentList=new ArrayList<>();
        Map  map=new HashMap<String ,Integer>();
        map.put("teacherId",exam.getTeacherId());
        studentList=studentDao.findStudentByTeacherId(map);
        if(exam.getChoiceQuestions()==null)exam.setTotalChoice(0);
        else exam.setTotalChoice(exam.getChoiceQuestions().size());
        if(exam.getSubjectiveQuestions()==null)exam.setTotalSubjective(0);
        else exam.setTotalSubjective(exam.getSubjectiveQuestions().size());
        if(examDao.insertExam(exam)<1)return 0;
        Exam exam1=examDao.findExamByTitle(exam.getTitle());
        exam.setId(exam1.getId());
        for(ChoiceQuestion choiceQuestion:exam.getChoiceQuestions()){
            if(exam2ChoiceDao.insertExam2Question(exam.getId(),choiceQuestion.getId(),exam.getTeacherId())<1){
                examDao.deleteExam(exam.getId());
                return 0;
            }
        }
        for(SubjectiveQuestion subjectiveQuestion:exam.getSubjectiveQuestions()){
            if(exam2SubjectiveDao.insertExam2Question(exam.getId(),subjectiveQuestion.getId(),exam.getTeacherId())<1){
                examDao.deleteExam(exam.getId());
                return 0;
            }
        }
        for (Student student : studentList){
            StudentExam studentExam=new StudentExam(exam.getTeacherId(),exam.getId(),student.getId(),0,exam.getTitle(),exam.getStartTime(),exam.getEndTime());
            studentExamDao.insertStudentExam(studentExam);
            StudentExam studentExam1=studentExamDao.findeStudentExamByExamIdAndStudentId(student.getId(),exam.getId());
            for(ChoiceQuestion choiceQuestion:exam.getChoiceQuestions()){
                ChoiceAnswer choiceAnswer=new ChoiceAnswer(exam.getId(),choiceQuestion.getId(),student.getId());
                choiceAnswerDao.insertAnswer(choiceAnswer);
            }
            for(SubjectiveQuestion subjectiveQuestion:exam.getSubjectiveQuestions()){
                SubjectiveAnswer subjectiveAnswer=new SubjectiveAnswer(exam.getId(),subjectiveQuestion.getId(),student.getId());
                subjectiveAnswerDao.insertAnswer(subjectiveAnswer);
            }
        }
        return 1;
    }

    @Override
    public Exam getExamByTitle(String title) {
        return examDao.findExamByTitle(title);
    }

    @Override
    public Exam getExamById(int id) {
        return examDao.findExamById(id);
    }

    @Override
    public int updateExam(Exam exam) {
        examDao.updateExam(exam);
        studentExamDao.updateStudentExam(exam);
        return 1;
    }

    @Override
    public PageResult getExamInPage(PageUtil pageUtil, int teacherId) {
        pageUtil.put("teacherId",teacherId);
        List<Exam>examList=examDao.findExamByTeacherId(pageUtil);
        for(Exam exam:examList){
            exam.setTotalScore(exam.getTotalChoice()*4+exam.getTotalSubjective()*10);
        }
        int num=examDao.findNumOfExamByTeacherId(pageUtil);
        PageResult pageResult=new PageResult((num+pageUtil.getLimit()-1)/pageUtil.getLimit(),num,pageUtil.getPage(),pageUtil.getLimit());
        pageResult.setList(examList);
        return pageResult;
    }

    @Override
    public List<Exam> getExamStatistics(PageUtil pageUtil, int teacherId) {
        pageUtil.put("teacherId",teacherId);
        List<Exam>examList=examDao.findFinishedExamByTeacherId(pageUtil);
        for(Exam exam:examList){
            exam.setTotalScore(exam.getTotalChoice()*4+exam.getTotalSubjective()*10);
            List<UserPair>scorePairList=ExamUtil.getScorePairList(exam.getTotalScore());
            List<Integer>numOfScoreDis=new ArrayList<>();
            for(UserPair scorePair:scorePairList){
                System.out.println(scorePair.getKey()+" "+scorePair.getValue());
                numOfScoreDis.add(studentExamDao.findNumOfParticipantsByUpperAndLower(scorePair.getKey(),scorePair.getValue(),exam.getId()));
            }
            exam.setScoredis(numOfScoreDis);
            exam.setNumberOfParticipants(studentExamDao.findNumOfParticipantsByExamId(exam.getId()));
            exam.setNumberOfFailures(numOfScoreDis.get(0));
            exam.setNumberOfPass(exam.getNumberOfParticipants()-exam.getNumberOfFailures());
            //KnowledgeList
            System.out.println(2);
            List<SecondKnowledgePoint>knowledgePointList=secondKnowledgePointDao.findKnowledgePointsByExamId(exam.getId());
            for(SecondKnowledgePoint secondKnowledgePoint:knowledgePointList){
                System.out.println(3);
                SecondKnowledgePoint knowledgePoint=secondKnowledgePointDao.findKnowledgePointByKnowledgePoint(secondKnowledgePoint.getKnowledgePoint());
                secondKnowledgePoint.setId(knowledgePoint.getId());
                secondKnowledgePoint.setFirstKnowledgePoint(knowledgePoint.getFirstKnowledgePoint());
                double aveScore=secondKnowledgePoint.getAveScore();
                DecimalFormat df = new DecimalFormat("#0.00");
                secondKnowledgePoint.setAveScore(Double.valueOf(df.format(aveScore)));
            }
            exam.setKnowledgePointList(knowledgePointList);
        }
        System.out.println(4);
        int num=examDao.findNumOfExamByTeacherId(pageUtil);
        return examList;
    }

    @Override
    public int deleteExam(Exam exam) {
        if(examDao.deleteExam(exam.getId())<1)return 0;
        exam2ChoiceDao.deleteExam(exam.getId());
        exam2SubjectiveDao.deleteExam(exam.getId());
        choiceAnswerDao.deleteExam(exam.getId());
        subjectiveAnswerDao.deleteExam(exam.getId());
        studentExamDao.deleteStudentExamByExamId(exam.getId());
        return 1;
    }

    @Override
    public Exam getExamInfo(Exam exam) {
        exam=examDao.findExamById(exam.getId());
        List<Integer> choiceQuestionsId;
        List<ChoiceQuestion>choiceQuestionList=new ArrayList<ChoiceQuestion>();
        List<Integer> subjectiveQuestionsId;
        List<SubjectiveQuestion>subjectiveQuestionList=new ArrayList<SubjectiveQuestion>();
        choiceQuestionsId=exam2ChoiceDao.findQuestionIdByExamId(exam.getId());
        subjectiveQuestionsId=exam2SubjectiveDao.findQuestionIdByExamId(exam.getId());
        for(int id : choiceQuestionsId){
            ChoiceQuestion choiceQuestion=choiceQuestionDao.findQuestionById(id);
            if(choiceQuestion!=null){
                choiceQuestionList.add(choiceQuestion);
            }
        }
        for(int id : subjectiveQuestionsId){
            SubjectiveQuestion subjectiveQuestion=subjectiveQuestionDao.findQuestionById(id);
            if(subjectiveQuestion!=null){
                subjectiveQuestionList.add(subjectiveQuestion);
            }
        }
        exam.setChoiceQuestions(choiceQuestionList);
        exam.setSubjectiveQuestions(subjectiveQuestionList);
        exam.setTotalScore(exam.getTotalChoice()*4+exam.getTotalSubjective()*10);
        return exam;
    }

    @Override
    public int deleteChoiceQuestion(ExamUpdUtil examUpdUtil) {
        examDao.deleteChoiceQuestion(examUpdUtil.getExamId());
        return exam2ChoiceDao.deleteExam2Question(examUpdUtil.getExamId(),examUpdUtil.getQuestionId());
    }

    @Override
    public int deleteSubjectiveQuestion(ExamUpdUtil examUpdUtil) {
        examDao.deleteSubjectiveQuestion(examUpdUtil.getExamId());
        return exam2SubjectiveDao.deleteExam2Question(examUpdUtil.getExamId(),examUpdUtil.getQuestionId());
    }

    @Override
    public int addChoiceQuestion(ExamUpdUtil examUpdUtil) {
        examDao.addChoiceQuestion(examUpdUtil.getExamId());
        return exam2ChoiceDao.insertExam2Question(examUpdUtil.getExamId(),examUpdUtil.getQuestionId(),examUpdUtil.getTeacherId());
    }

    @Override
    public int addSubjectiveQuestion(ExamUpdUtil examUpdUtil) {
        examDao.addSubjectiveQuestion(examUpdUtil.getExamId());
        return exam2SubjectiveDao.insertExam2Question(examUpdUtil.getExamId(),examUpdUtil.getQuestionId(),examUpdUtil.getTeacherId());
    }
}
