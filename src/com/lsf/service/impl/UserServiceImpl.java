package com.lsf.service.impl;

import com.lsf.dao.UserDao;
import com.lsf.dao.impl.UserDaoImpl;
import com.lsf.entity.Paginate;
import com.lsf.entity.User;
import com.lsf.service.UserService;
import com.lsf.service.UserState;
import com.lsf.utils.Md5Util;
import com.ly.util.jdbc.TransactionManager;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/13 14:20
 * @see [相关类/方法]
 * @since V1.00
 */
public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    private TransactionManager tm = new TransactionManager();

    @Override
    public int reg(User user) {
        try {
            tm.start();
            int ret = dao.selectByName(user.getName(), 0);
            if (ret > 0) {
                throw new RuntimeException("用户名[" + user.getName() + "]已存在！");
            }
            ret = dao.selectByEmail(user.getEmail(), 0);
            if (ret > 0) {
                throw new RuntimeException("Email[" + user.getEmail() + "]已存在！");
            }
            ret = dao.selectByPhone(user.getPhone(), 0);
            if (ret > 0) {
                throw new RuntimeException("手机号[" + user.getPhone() + "]已存在！");
            }
            user.setPassword(Md5Util.getMD5(user.getPassword()));
            ret = dao.insert(user);
            tm.commit();
            return ret;
        } catch (RuntimeException ex) {
            tm.rollback();
            throw ex;
        }
    }

    @Override
    public User login(User user) {
        user.setPassword(Md5Util.getMD5(user.getPassword()));
        return dao.selectByUser(user);
    }

    @Override
    public int modifyPass(String newPass, String oldPass, Integer uid) {
        return dao.updatePassword(Md5Util.getMD5(newPass),
                Md5Util.getMD5(oldPass), uid);
    }

    @Override
    public int modifyInfo(User user) {
        try {
            tm.start();
            int ret = dao.selectByName(user.getName(), user.getId());
            if (ret > 0) {
                throw new RuntimeException("用户名[" + user.getName() + "]已存在！");
            }
            ret = dao.selectByEmail(user.getEmail(), user.getId());
            if (ret > 0) {
                throw new RuntimeException("Email[" + user.getEmail() + "]已存在！");
            }
            ret = dao.selectByPhone(user.getPhone(), user.getId());
            if (ret > 0) {
                throw new RuntimeException("手机号[" + user.getPhone() + "]已存在！");
            }
            ret = dao.updateInfo(user);
            tm.commit();
            return ret;
        } catch (RuntimeException ex) {
            tm.rollback();
            throw ex;
        }

    }

    @Override
    public List<User> getUsers(Paginate page) {
        try {
            tm.start();
            int records = dao.selectCount();
            page.setRecords(records);
            List<User> users = dao.selectUsers((page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());
            tm.commit();
            return users;
        } catch (RuntimeException e) {
            tm.rollback();
            throw e;
        }
    }

    @Override
    public List<User> getUsers() {
        return dao.selectUsers();
    }

    @Override
    public int delete(Integer id) {
        return dao.updateState(id, UserState.DELETED, UserState.NORMAL);
    }

    @Override
    public int recover(Integer id) {
        return dao.updateState(id, UserState.NORMAL, UserState.DELETED);
    }

    @Override
    public int degrade(Integer id) {
        return dao.updateState(id, UserState.NORMAL, UserState.ADMIN);
    }

    @Override
    public int upgrade(Integer id) {
        return dao.updateState(id, UserState.ADMIN, UserState.NORMAL);
    }
}
