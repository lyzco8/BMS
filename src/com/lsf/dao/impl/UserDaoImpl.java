package com.lsf.dao.impl;

import com.lsf.dao.UserDao;
import com.lsf.entity.User;
import com.ly.util.jdbc.JdbcTemplate;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/13 14:09
 * @see [相关类/方法]
 * @since V1.00
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate();

    @Override
    public int insert(User user) {
        String sql = "insert into users values(0,?,?,?,?,?,?,1,?)";
        return template.update(sql, true, user.getName(),
                user.getPassword(), user.getEmail(), user.getGender(),
                user.getAge(), user.getPhone(), user.getIntegral());
    }

    @Override
    public User selectByUser(User user) {
        String sql = "select id, name, email, gender, age, phone, state from users where " +
                "(name=? or email=? or phone=?) " +
                "and (password=?) " +
                "and (state>0)";
        return template.queryOne(sql, User.class, user.getName()
                , user.getName(), user.getName(), user.getPassword());
    }

    @Override
    public int selectByName(String name, Integer uid) {
        String sql = "select count(1) from users where name=? and id!=?";
        return template.queryScale(sql, Integer.class, name, uid);
    }

    @Override
    public int selectByEmail(String email, Integer uid) {
        String sql = "select count(1) from users where email=? and id!=?";
        return template.queryScale(sql, Integer.class, email, uid);
    }

    @Override
    public int selectByPhone(String phone, Integer uid) {
        String sql = "select count(1) from users where phone=? and id!=?";
        return template.queryScale(sql, Integer.class, phone, uid);
    }

    @Override
    public User selectByUid(Integer uid) {
        String sql = "select * from users where id=?";
        return template.queryOne(sql, User.class, uid);
    }

    @Override
    public int updatePassword(String newPass, String oldPass, Integer uid) {
        String sql = "update users set password=? where id=? and password=?";
        return template.update(sql, false, newPass, uid, oldPass);
    }

    @Override
    public int updateInfo(User user) {
        String sql = "update users set name=?,email=?," +
                "gender=?,age=?,phone=? where id=?";
        return template.update(sql, false, user.getName(),
                user.getEmail(), user.getGender(), user.getAge(),
                user.getPhone(), user.getId());
    }

    @Override
    public int updateIntegral(Integer uid, Integer integral) {
        String sql = "update users set integral=integral-? where id=? and integral>=?";
        return template.update(sql, false, integral, uid, integral);
    }


    @Override
    public int updateState(Integer id, Integer newState, Integer oldState) {
        String sql = "update users set state=? where id=? and state=?";
        return template.update(sql, false, newState, id, oldState);
    }

    @Override
    public List<User> selectUsers(Integer start, Integer size) {
        String sql = "select * from users limit ?,?";
        return template.queryList(sql, User.class, start, size);
    }

    @Override
    public int selectCount() {
        String sql = "select count(1) from users";
        return template.queryScale(sql, Integer.class);
    }

    @Override
    public List<User> selectUsers() {
        String sql = "select * from users";
        return template.queryList(sql, User.class);
    }
}
