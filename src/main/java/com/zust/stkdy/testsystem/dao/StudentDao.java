package com.zust.stkdy.testsystem.dao;

import com.zust.stkdy.testsystem.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    int insertStudent(Student student);
    int deleteStudent(@Param("id") Integer id);
    Student findStudentById(@Param("id") Integer id);
    Student findStudentBySno(@Param("sno") String sno);
    Student findStudentByToken(@Param("token") String token);
    Student findStudentByName(@Param("userName") String userName);
    Student findStudentByEmail(@Param("email") String email);
    Student findStudentByNameAndPassword(@Param("userName") String userName,@Param("password") String password);
    List<Student> findStudentByTeacherId(Map param);
    List<Student> findStudent(Map param);
    int findNumOfStudentByTeacherId(@Param("teacherId") Integer teacherId);
    int findNumOfStudent();
    List<Student> findMajorGroup(Map param);
    List<Student> findClassGroup(Map param);
    List<Student> findStudentGroup(Map param);
    int updateStudent(Student student);
    int updateStudentToken(@Param("id") Integer id,@Param("token") String token);
    int updateStudentPassword(@Param("id") Integer id,@Param("password") String password);
}
