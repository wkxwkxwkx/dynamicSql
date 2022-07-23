package com.qcby.dao;

import com.qcby.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author wangkx
 */
public interface UserDao {
    List<User> selectUsernameAndSex(User user);
    int updateBySet(User user);
    List<User> selectByChoose(User user);
    int deleteByArray(@Param("ids") Integer[] ids);
    int insertByList(@Param("users") List<User> users);

}
