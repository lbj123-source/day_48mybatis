package com.zhiyou.mapper;

import com.zhiyou.model.Student;
import com.zhiyou.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserMapper {

    User findUserById(int id);
    void addOne(User user);
    void update(Map<String,Object>map);
    //mybatis接口方法,只有一个参数
    //void update2(String name,int id);
    //但是可以使用注解来指定入参的值
    void update2(@Param("name")String name,@Param("id") int id);

    int count();
    List<User> findAll();
    List<User> findUserByKeyword(@Param("keyword") String keyword);

    List<User> findUserByMoreKeyword(HashMap<String, Object> map);

    List<User> findUserByKeyword2(@Param("keyword") String keyword);

    List<User> findUserByKeyword3(@Param("username") String username);

    List<User> findUserByListId(ArrayList<Integer> list);


}
