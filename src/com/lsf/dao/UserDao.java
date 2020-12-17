package com.lsf.dao;

import com.lsf.entity.User;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/13 14:08
 * @see [相关类/方法]
 * @since V1.00
 */
public interface UserDao {
    /**
     *添加用户
     * @param user
     * @return
     */
    int insert(User user);

    /**
     *查询用户
     * @param user
     * @return
     */
    User selectByUser(User user);

    /**
     *按姓名查询用户
     * @param name
     * @param uid
     * @return
     */
    int selectByName(String name, Integer uid);

    /**
     *按Email查询用户
     * @param email
     * @param uid
     * @return
     */
    int selectByEmail(String email, Integer uid);

    /**
     *按手机号查询用户
     * @param phone
     * @param uid
     * @return
     */
    int selectByPhone(String phone, Integer uid);

    /**
     * 按用户id查询用户信息
     * @param uid
     * @return
     */
    User selectByUid(Integer uid);

    /**
     *修改用户密码
     * @param newPass
     * @param oldPass
     * @param uid
     * @return
     */
    int updatePassword(String newPass, String oldPass, Integer uid);

    /**
     *修改用户其他信息
     * @param user
     * @return
     */
    int updateInfo(User user);

    /**
     *修改用户积分
     * @param uid
     * @param integral
     * @return
     */
    int updateIntegral(Integer uid,Integer integral);

    /**
     * 修改用户状态
     * @param id
     * @param newState
     * @param oldState
     * @return
     */
    int updateState(Integer id,Integer newState,Integer oldState);

    /**
     * 查询所有用户
     * @param start
     * @param size
     * @return
     */
    List<User> selectUsers(Integer start,Integer size);

    /**
     * 获得用户数量
     * @return
     */
    int selectCount();

    /**
     * 查询所有用户
     * @return
     */
    List<User> selectUsers();
}
