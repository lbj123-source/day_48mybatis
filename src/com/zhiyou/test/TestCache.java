package com.zhiyou.test;

import com.zhiyou.mapper.StudentMapper;
import com.zhiyou.model.Class;
import com.zhiyou.model.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestCache {

    //不命中缓存
    @Test
    public void test01() throws IOException {
        String path = "sqlmapconfig.xml";
        InputStream stream = Resources.getResourceAsStream(path);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = build.openSession();
        SqlSession sqlSession2 = build.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        StudentMapper mapper2 = sqlSession2.getMapper(StudentMapper.class);
        List<Student> list = mapper.findStudentAndClass();
        System.out.println(list);
        System.out.println("-------------");
        List<Student> list1 = mapper2.findStudentAndClass();
        System.out.println(list1);

    }

    //命中缓存
    @Test
    public void test02() throws IOException {
        String path = "sqlmapconfig.xml";
        InputStream stream = Resources.getResourceAsStream(path);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = build.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> list = mapper.findStudentAndClass();
        System.out.println(list);
        System.out.println("-------------");
        List<Student> list1 = mapper.findStudentAndClass();
        System.out.println(list);

    }

   //清空缓存
    @Test
    public void selectOne2() throws IOException {
        String path = "sqlmapconfig.xml";
        //1.加载配置文件
        InputStream stream = Resources.getResourceAsStream(path);
        //2.通过配置文件,创建sqlsession工厂
        SqlSessionFactory sqlSessionfactory = new SqlSessionFactoryBuilder().build(stream);
        //3.创建sqlsession
        SqlSession sqlSession1 = sqlSessionfactory.openSession();
        //4.1后的代理对象
        StudentMapper mapper = sqlSession1.getMapper(StudentMapper.class);
        //4.2执行
        List<Student> list = mapper.findStudentAndClass();
        System.out.println(list);
        System.out.println("-------------");
//        mapper.updateStudent("王举", 1);

        List<Student> list1 = mapper.findStudentAndClass();
        sqlSession1.commit();
        System.out.println(list1);
        sqlSession1.close();
    }


}
