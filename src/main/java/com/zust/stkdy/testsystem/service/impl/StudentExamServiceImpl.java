package com.zust.stkdy.testsystem.service.impl;

import com.mysql.cj.util.StringUtils;
import com.zust.stkdy.testsystem.common.Result;
import com.zust.stkdy.testsystem.dao.*;
import com.zust.stkdy.testsystem.entity.*;
import com.zust.stkdy.testsystem.service.StudentExamService;
import com.zust.stkdy.testsystem.utils.ExamUtil;
import com.zust.stkdy.testsystem.utils.JudgeUtil;
import com.zust.stkdy.testsystem.utils.PageResult;
import com.zust.stkdy.testsystem.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("studentExamService")
public class StudentExamServiceImpl implements StudentExamService {
    @Autowired
    private StudentExamDao studentExamDao;
    @Autowired
    private ExamDao examDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private SubjectiveAnswerDao subjectiveAnswerDao;
    @Autowired
    private ChoiceAnswerDao choiceAnswerDao;
    @Autowired
    private SubjectiveQuestionDao subjectiveQuestionDao;
    @Autowired
    private ChoiceQuestionDao choiceQuestionDao;
    @Autowired
    private Student2SecondPointDao student2SecondPointDao;
    @Override
    public PageResult getStudentExamInPage(PageUtil pageUtil, int studentId) {
        pageUtil.put("studentId",studentId);
        List<StudentExam>studentExamList=studentExamDao.findStudentExamByStudentId(pageUtil);
        for(StudentExam studentExam:studentExamList){
            Exam exam=examDao.findExamById(studentExam.getExamId());
            studentExam.setTotalSubjective(exam.getTotalSubjective());
            studentExam.setTotalChoice(exam.getTotalChoice());
            studentExam.setTotalScore(studentExam.getTotalChoice()*4+studentExam.getTotalSubjective()*10);
        }
        int num=studentExamList.size();
        PageResult pageResult=new PageResult((num+pageUtil.getLimit()-1)/pageUtil.getLimit(),num,pageUtil.getPage(),pageUtil.getLimit());
        pageResult.setList(studentExamList);
        return pageResult;
    }

    @Override
    public PageResult getStudentExamInPageByTeacherId(PageUtil pageUtil, int teacherId) {
        pageUtil.put("teacherId",teacherId);
        List<StudentExam>studentExamList=studentExamDao.findStudentExamByTeacherId(pageUtil);
        for(StudentExam studentExam:studentExamList){
            Exam exam=examDao.findExamById(studentExam.getExamId());
            Student student=studentDao.findStudentById(studentExam.getStudentId());
            studentExam.setTotalSubjective(exam.getTotalSubjective());
            studentExam.setTotalChoice(exam.getTotalChoice());
            studentExam.setTotalScore(studentExam.getTotalChoice()*4+studentExam.getTotalSubjective()*10);
            if(student!=null){
                studentExam.addStudent(student);
            }
        }
        int num=studentExamDao.findNumOfExamByTeacherId(pageUtil);
        PageResult pageResult=new PageResult((num+pageUtil.getLimit()-1)/pageUtil.getLimit(),num,pageUtil.getPage(),pageUtil.getLimit());
        pageResult.setList(studentExamList);
        return pageResult;
    }

    @Override
    public StudentExam getExamInfo(int id, int studentId) {
        StudentExam studentExam=studentExamDao.findStudentExamById(id);
        List<SubjectiveAnswer> subjectiveAnswers = subjectiveAnswerDao.findAnswerByExamIdAndStudentId(studentExam.getExamId(),studentId);
        List<ChoiceAnswer> choiceAnswers = choiceAnswerDao.findAnswerByExamIdAndStudentId(studentExam.getExamId(),studentId);
        List<SubjectiveQuestion> subjectiveQuestions=new ArrayList<SubjectiveQuestion>();
        List<ChoiceQuestion> choiceQuestions=new ArrayList<ChoiceQuestion>();
        if(studentExam==null)return null;
        for(SubjectiveAnswer subjectiveAnswer : subjectiveAnswers){
            SubjectiveQuestion subjectiveQuestion=subjectiveQuestionDao.findQuestionByIdSpecialy(subjectiveAnswer.getQuestionId());
            if(subjectiveQuestion!=null)subjectiveQuestions.add(subjectiveQuestion);
            else{
                subjectiveQuestion=new SubjectiveQuestion();
                subjectiveQuestion.setDescription("试题不存在");
                subjectiveQuestion.setAnswer("试题不存在");
            }
        }
        for(ChoiceAnswer choiceAnswer : choiceAnswers){
            ChoiceQuestion choiceQuestion=choiceQuestionDao.findQuestionByIdSpecialy(choiceAnswer.getQuestionId());
            if(choiceQuestion!=null)choiceQuestions.add(choiceQuestion);
            else{
                choiceQuestion=new ChoiceQuestion();
                choiceQuestion.setDescription("试题不存在");
                choiceQuestion.setAnswer(-1);
            }
        }
        Student student=studentDao.findStudentById(studentId);
        if(student!=null){
            studentExam.setStudentSno(student.getSno());
            studentExam.setStudentName(student.getRealName());
            studentExam.setStudentMajor(student.getMajor());
        }
        studentExam.setChoiceAnswerList(choiceAnswers);
        studentExam.setChoiceQuestionList(choiceQuestions);
        studentExam.setSubjectiveAnswerList(subjectiveAnswers);
        studentExam.setSubjectiveQuestionList(subjectiveQuestions);
        studentExam.setTotalChoice(studentExam.getChoiceQuestionList().size());
        studentExam.setTotalSubjective(studentExam.getSubjectiveQuestionList().size());
        studentExam.setTotalScore(studentExam.getTotalChoice()*4+studentExam.getTotalSubjective()*10);
        return studentExam;
    }

    @Override
    public int judge(StudentExam studentExam) {
        int l=studentExam.getSubjectiveAnswerList().size();
        double totscore=0;
        for(int i=0;i<l;i++){
            SubjectiveAnswer subjectiveAnswer=studentExam.getSubjectiveAnswerList().get(i);
            SubjectiveQuestion subjectiveQuestion=subjectiveQuestionDao.findQuestionById(subjectiveAnswer.getQuestionId());
            JudgeUtil judgeUtil=new JudgeUtil(subjectiveAnswer.getAnswer(),subjectiveQuestion.getAnswer(),subjectiveQuestion.getWordsString(),1,1);
            double score;
            if(StringUtils.isEmptyOrWhitespaceOnly(judgeUtil.getAnswer()))score=0;
            else score=judgeUtil.judge();
            totscore+=score;
            subjectiveAnswer.setScore(score);
            subjectiveAnswerDao.updateAnswer(subjectiveAnswer);
            //知识点
            if(subjectiveQuestion.getSecondLevelKnowledgePointId()!=-1){
                StudentSecondKnowledgePoint studentSecondKnowledgePoint=student2SecondPointDao.findKnowledgePointByStudentIdAndPointId(studentExam.getStudentId(),subjectiveQuestion.getSecondLevelKnowledgePointId());
                if(studentSecondKnowledgePoint==null){
                    studentSecondKnowledgePoint=new StudentSecondKnowledgePoint(studentExam.getStudentId(),studentExam.getTeacherId(),subjectiveQuestion.getFirstLevelKnowledgePointId(),subjectiveQuestion.getFirstLevelKnowledgePoint(),subjectiveQuestion.getSecondLevelKnowledgePointId(),subjectiveQuestion.getSecondLevelKnowledgePoint());
                    student2SecondPointDao.insertKnowledgePoint(studentSecondKnowledgePoint);
                    studentSecondKnowledgePoint=student2SecondPointDao.findKnowledgePointByStudentIdAndPointId(studentExam.getStudentId(),subjectiveQuestion.getSecondLevelKnowledgePointId());
                }
                StudentSecondKnowledgePoint studentSecondKnowledgePoint1=JudgeUtil.rewriteLine(studentSecondKnowledgePoint,score);
                student2SecondPointDao.updateKnowledgePoint(studentSecondKnowledgePoint1);
            }
        }
        l=studentExam.getChoiceAnswerList().size();
        for(int i=0;i<l;i++){
            ChoiceAnswer choiceAnswer=studentExam.getChoiceAnswerList().get(i);
            ChoiceQuestion choiceQuestion=studentExam.getChoiceQuestionList().get(i);
            if(choiceAnswer.getAnswer()==choiceQuestion.getAnswer()){
                choiceAnswer.setScore(4);
                totscore+=4;
                choiceAnswerDao.updateAnswer(choiceAnswer);
            }
        }
        studentExam.setScore((float) totscore);
        studentExamDao.updateScore(studentExam);
        return 1;
    }

    @Override
    public int updateTeacher(Student student, Teacher teacher) {
        studentExamDao.deleteExamByStudentId(student.getId());
        subjectiveAnswerDao.deleteStudentAnswer(student.getId());
        choiceAnswerDao.deleteStudentAnswer(student.getId());
        studentExamDao.insertTeacherExam(student.getId(),teacher.getId());
        subjectiveAnswerDao.insertTeacherAnswer(student.getId(),teacher.getId());
        choiceAnswerDao.insertTeacherAnswer(student.getId(),teacher.getId());
        return 1;
    }

    @Override
    public int deleteAnswerByStudent(Student student) {
        subjectiveAnswerDao.deleteStudentAnswer(student.getId());
        choiceAnswerDao.deleteStudentAnswer(student.getId());
        return 1;
    }

    @Override
    public int deleteExam(Student student) {
        studentExamDao.deleteExamByStudentId(student.getId());
        subjectiveAnswerDao.deleteStudentAnswer(student.getId());
        choiceAnswerDao.deleteStudentAnswer(student.getId());
        return 1;
    }

    @Override
    public int finishExam(int id) {
        return studentExamDao.finishExam(id);
    }


}
