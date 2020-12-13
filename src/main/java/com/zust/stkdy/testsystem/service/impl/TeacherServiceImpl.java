package com.zust.stkdy.testsystem.service.impl;

import com.zust.stkdy.testsystem.dao.TeacherDao;
import com.zust.stkdy.testsystem.entity.Teacher;
import com.zust.stkdy.testsystem.service.TeacherService;
import com.zust.stkdy.testsystem.utils.MD5Util;
import com.zust.stkdy.testsystem.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;
    @Override
    public Teacher findTeacherByToken(String token) {
        return teacherDao.findTeacherByToken(token);
    }

    @Override
    public Teacher findTeacherById(Teacher teacher) {
        return teacherDao.findTeacherById(teacher.getId());
    }

    @Override
    public int nameBeRegistered(String userName) {
        Teacher teacher=teacherDao.findTeacherByName(userName);
        if(teacher==null)return 0;
        return 1;
    }

    @Override
    public Teacher updateTokenAndLogin(Teacher teacher) {
        teacher.setPassword(MD5Util.encodeByMd5(teacher.getPassword()));
        Teacher loginteacher=teacherDao.findTeacherByNameAndPassword(teacher.getUserName(),teacher.getPassword());
        if(loginteacher!=null){
            String token = SystemUtil.getNewToken(System.currentTimeMillis() + "", loginteacher.getId());
            if(teacherDao.updateTeacherToken(loginteacher.getId(),token)>0){
                loginteacher.setToken(token);
                return loginteacher;
            }
        }
        return null;
    }

    @Override
    public int addTeacher(Teacher teacher) {
        teacher.setPassword(MD5Util.encodeByMd5(teacher.getPassword()));
        return teacherDao.insertTeacher(teacher);
    }
}
