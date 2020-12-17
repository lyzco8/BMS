package com.lsf.service;

import com.lsf.entity.Paginate;
import com.lsf.entity.User;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/13 14:19
 * @see [相关类/方法]
 * @since V1.00
 */
public interface UserService {
    /**
     *用户注册
     * @param user
     * @return
     */
    int reg(User user);

    /**
     *用户登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     *修改用户密码
     * @param newPass
     * @param oldPass
     * @param uid
     * @return
     */
    int modifyPass(String newPass, String oldPass, Integer uid);

    /**
     *修改用户信息
     * @param user
     * @return
     */
    int modifyInfo(User user);

    /**
     * 查询所有用户
     * @param page
     * @return
     */
    List<User> getUsers(Paginate page);

    /**
     * 查询所有用户
     * @return
     */
    List<User> getUsers();

    int delete(Integer id);
    int recover(Integer id);
    int degrade(Integer id);
    int upgrade(Integer id);

}
