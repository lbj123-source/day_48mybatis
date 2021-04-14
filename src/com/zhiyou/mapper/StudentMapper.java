package com.zhiyou.mapper;

import com.zhiyou.model.Class;
import com.zhiyou.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface StudentMapper {


    //查询学生附带班级信息
    List<Student> findStudentAndClass();

    //查班级,附带查学生信息
    List<Class> findClassAndStudent();

    Class findClassById(int id);

    List<Student> findStudentAndClassByLazyLoding();

    Student findStudentById(int id);

    List<Class> findClassAndStudentLazyLoding();

    void updateStudent(@Param("sname")String sname,@Param("sid")int sid);

}
