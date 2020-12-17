package com.lsf.dao;

import com.lsf.entity.Book;
import com.lsf.entity.Order;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/19 14:31
 * @see [相关类/方法]
 * @since V1.00
 */
public interface OrderDao {
    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    int insertOrder(Order order);

    /**
     * 修改订单
     * @param order
     * @return
     */
    int deleteOrder(Order order);

    /**
     * 根据订单编号和用户id查找订单
     * @param order
     * @return
     */
    Order selectOrder(Order order);

    /**
     * 根据用户查找订单
     * @param uid
     * @return
     */
    List<Order> selectByUser(Integer uid);

}
