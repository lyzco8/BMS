package com.lsf.service;

import com.lsf.entity.Book;
import com.lsf.entity.Order;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/19 14:38
 * @see [相关类/方法]
 * @since V1.00
 */
public interface OrderService {
    /**
     * 添加订单
     * @param uid
     * @param books
     * @return
     */
    int addOrder(Integer uid, List<Book> books);

    /**
     * 删除订单
     * @param order
     * @return
     */
    int deleteOrder(Order order);

    /**
     * 根据用户查找订单
     * @param uid
     * @return
     */
    List<Order> getByUser(Integer uid);
}
