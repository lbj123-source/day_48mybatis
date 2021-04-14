package com.zhiyou.test;



import com.zhiyou.mapper.StudentMapper;
import com.zhiyou.mapper.UserMapper;
import com.zhiyou.model.Class;
import com.zhiyou.model.Student;
import com.zhiyou.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MybatisTest {
   private SqlSession sqlSession = null;
    @Before
    public void beforeTest() throws IOException {

        String path = "sqlmapconfig.xml";
        //1.加载配置文件
        InputStream stream = Resources.getResourceAsStream(path);
        // 2 通过配置文件,创建sqlsession工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder( ).build(stream);
        //3.创建sqlsession
        sqlSession = sqlSessionFactory.openSession();
    }
    @Test
    public void selectone()  {


        //4..1获得代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.2执行
        User user = mapper.findUserById(5);
        System.out.println(user);

    }

    //插入一条数据
    @Test
    public void addOne(){
        //获得代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
//        user.setId(39);
        user.setUsername("王新山");
        user.setPassword("1");
        user.setBirthday(new Date());
        user.setAddress("奥利给星球");
        user.setPhone("110");
        //执行
        mapper.addOne(user);
        //增删改需要提交
        sqlSession.commit();
    }

    //查条数
    @Test
    public void count(){
        //获得代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //使用
        int count = mapper.count();
        System.out.println("= "+count);

    }

    //查全部
    @Test
    public void findAll(){

        //获得代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.findAll();
        System.out.println("users = "+users);
    }


    //模糊查询查全部
    @Test
    public void keywordAll(){

        //获得代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //使用
        List<User> users = mapper.findUserByKeyword("翠");
        System.out.println("users = "+users);
    }


    //多字段 模糊查询查全部  用<thoose> <when></when> </thoose>
    @Test
    public void keywordAll2(){

        //获得代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("username","翠");
        map.put("address","周口");
        map.put("phone", "110");
        //使用
        List<User> users = mapper.findUserByMoreKeyword(map);
        System.out.println("users = "+users);
    }


    //多字段 模糊查询查全部  用 where if
    @Test
    public void keywordAll3(){

        //获得代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //使用
        List<User> users = mapper.findUserByKeyword2("翠");
        System.out.println("users = "+users);
    }


    //模糊查询查全部  使用<where>标签
    @Test
    public void keywordAll4(){

        //获得代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //使用
        List<User> users = mapper.findUserByKeyword3("翠");
        System.out.println("users = "+users);
    }


    //更新
    @Test
    public void update(){
        //获得代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 4.2 执行
        HashMap<String, Object> map = new HashMap<>( );
        map.put("id",3);
        map.put("username","zoo");
        map.put("password","456");
        map.put("birthday",new Date());
        map.put("address","派出所");
        map.put("phone","110");
        mapper.update(map);

        sqlSession.commit();
    }



    @Test
    public void findUserById(){
        //获得代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(20);
        List<User> userByListId = mapper.findUserByListId(list);
        System.out.println(userByListId);
    }
    //更新
    @Test
    public void update2(){
        //获得代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.update2("老王",3);

        sqlSession.commit();
    }


     //查询全部的学生信息附带班级信息
      @Test
      public void findStudentAndClass(){
        //获得代理
          StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
          List<Student> studentAndClass = mapper.findStudentAndClass();
          System.out.println(studentAndClass);
      }

    //查询全部的班级信息附带学生信息
    @Test
    public void findClassAndStudent(){
        //获得代理
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentAndClass = mapper.findStudentAndClass();
        System.out.println(studentAndClass);
    }

    //懒加载 一对 一
    @Test
    public void lazyLoading(){
        //获得代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //执行
        List<Student> loding = mapper.findStudentAndClassByLazyLoding();
        for (Student student : loding) {
            System.out.println(student.getSname()+" - "+student.getaClass().getCname());
        }
    }



    //懒加载  一对多
    @Test
    public void lazyLoading2(){
        //获得代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        //执行
        List<Class> loding = mapper.findClassAndStudentLazyLoding();
        System.out.println(loding);
        for (Class aClass : loding) {
            System.out.println(aClass.getStudent().toString());
            System.out.println("-----------");
        }
    }


    //更新
    @Test
    public void updateStudent(){
        //获得代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        //执行
        mapper.updateStudent("121",1);
        sqlSession.commit();

    }


    @After
    public void after(){
        //4.2关闭会话
        sqlSession.close();
    }
}
