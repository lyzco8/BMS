package com.lsf.dao.impl;

import com.lsf.dao.OrderdetailDao;
import com.lsf.entity.Orderdetail;
import com.ly.util.jdbc.JdbcTemplate;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/19 14:52
 * @see [相关类/方法]
 * @since V1.00
 */
public class OrderdetailDaoImpl implements OrderdetailDao {
    private JdbcTemplate template = new JdbcTemplate();

    @Override
    public int insertOrderdetail(Orderdetail orderdetail) {
        String sql = "insert into orderdetail values(0,?,?,?,?)";
        return template.update(sql, true, orderdetail.getOid(),
                orderdetail.getBid(), orderdetail.getQuantity(), orderdetail.getPrice());
    }

    @Override
    public int deleteOrderdetail(Integer oid) {
        String sql = "delete from orderdetail where oid = ?";
        return template.update(sql, false, oid);
    }

    @Override
    public List<Orderdetail> selectOrderdetail(Integer oid) {
        String sql = "select * from orderdetail where oid = ?";
        return template.queryList(sql, Orderdetail.class, oid);
    }
}
