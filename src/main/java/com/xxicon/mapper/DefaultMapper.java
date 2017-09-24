package com.xxicon.mapper;

import com.xxicon.model.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DefaultMapper {

    @Select("SELECT * FROM user")
    List<User> getUsers();

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUser(int id);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User getUserByUsername(String username);

    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteUser(int id);

    @Insert("INSERT INTO user (username, password, nick_name, type, status) VALUES (#{username}, #{password}, #{nickName}, #{type}, #{status})")
    int insertUser(User user);

    @Update("UPDATE user SET password = #{password}, nick_name = #{nickName}, type = #{type}, status = #{status} WHERE id = #{id}")
    int updateUser(User user);

}
