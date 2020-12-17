package com.lsf.dao;

import com.lsf.entity.Orderdetail;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/19 14:52
 * @see [相关类/方法]
 * @since V1.00
 */
public interface OrderdetailDao {
    /**
     * 添加订单详情
     *
     * @param orderdetail
     * @return
     */
    int insertOrderdetail(Orderdetail orderdetail);

    /**
     * 删除订单详情
     *
     * @param oid
     * @return
     */
    int deleteOrderdetail(Integer oid);

    /**
     * 根据订单编号查找订单订单详情
     * @param oid
     * @return
     */
    List<Orderdetail> selectOrderdetail(Integer oid);
}
