package com.lsf.dao.impl;

import com.lsf.dao.OrderDao;
import com.lsf.entity.Order;
import com.ly.util.jdbc.JdbcTemplate;

import java.util.List;


/**
 * @author 刘愿
 * @date 2020/11/19 14:35
 * @see [相关类/方法]
 * @since V1.00
 */
public class OrderDaoImpl implements OrderDao {
    private JdbcTemplate template = new JdbcTemplate();

    @Override
    public int insertOrder(Order order) {
        String sql = "insert into orders values(0,?,?,?)";
        return template.update(sql, true, order.getUid(), order.getAddress(), order.getSummary());
    }

    @Override
    public int deleteOrder(Order order) {
        String sql = "delete from orders where oid = ?";
        return template.update(sql, false, order.getOid());
    }

    @Override
    public Order selectOrder(Order order) {
        String sql = "select * from orders where oid = ? and uid = ?";
        return template.queryOne(sql, Order.class, order.getOid(), order.getUid());
    }

    @Override
    public List<Order> selectByUser(Integer uid) {
        String sql = "select * from orders where uid = ?";
        return template.queryList(sql, Order.class, uid);
    }

}
