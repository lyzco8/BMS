package com.lsf.service.impl;

import com.lsf.dao.BookDao;
import com.lsf.dao.OrderDao;
import com.lsf.dao.OrderdetailDao;
import com.lsf.dao.UserDao;
import com.lsf.dao.impl.BookDaoImpl;
import com.lsf.dao.impl.OrderDaoImpl;
import com.lsf.dao.impl.OrderdetailDaoImpl;
import com.lsf.dao.impl.UserDaoImpl;
import com.lsf.entity.Book;
import com.lsf.entity.Order;
import com.lsf.entity.Orderdetail;
import com.lsf.entity.User;
import com.lsf.service.OrderService;
import com.ly.util.jdbc.TransactionManager;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/19 14:39
 * @see [相关类/方法]
 * @since V1.00
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao dao = new OrderDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private OrderdetailDao orderdetailDao = new OrderdetailDaoImpl();
    private TransactionManager tm = new TransactionManager();

    @Override
    public int addOrder(Integer uid, List<Book> books) {
        int orderNo = 0;
        try {
            tm.start();
            float summary = 0.0f;
            for (int i = 0; i < books.size(); i++) {
                Book bk = books.get(i);
                Book book = bookDao.selectById(bk.getId());
                if (book == null) {
                    throw new RuntimeException("图书【" + bk.getId() + "】不存在");
                }
                bk.setPrice(book.getPrice());
                summary += book.getPrice() * bk.getCount();
            }
            Order order = new Order();
            order.setUid(uid);
            order.setAddress("");
            order.setSummary(summary);
            int rt = dao.insertOrder(order);
            orderNo = rt;
            if (rt == 0) {
                throw new RuntimeException("订单添加失败");
            }
            Orderdetail orderdetail = new Orderdetail();
            orderdetail.setOid(rt);
            for (Book bk : books) {
                orderdetail.setBid(bk.getId());
                orderdetail.setQuantity(bk.getCount());
                orderdetail.setPrice(bk.getPrice());
                rt = orderdetailDao.insertOrderdetail(orderdetail);
                if (rt == 0) {
                    throw new RuntimeException("订单详情添加失败");
                }
                rt = bookDao.updateCount(bk.getId(), bk.getCount());
                if (rt == 0) {
                    throw new RuntimeException("修改图书库存失败");
                }
            }
            rt = userDao.updateIntegral(uid, -(int) Math.floor(summary));
            if (rt == 0) {
                throw new RuntimeException("修改用户积分失败");
            }
            tm.commit();
            return orderNo;
        } catch (RuntimeException e) {
            tm.rollback();
            throw e;
        }
    }

    @Override
    public int deleteOrder(Order order) {
        try {
            tm.start();
            Order od = dao.selectOrder(order);
            if (od == null) {
                throw new RuntimeException("订单不存在或者订单与用户不符");
            }
            User user = userDao.selectByUid(order.getUid());
            if (user.getIntegral() < (int) Math.floor(od.getSummary())) {
                throw new RuntimeException("用户积分不足，不能退货");
            }
            int rt = userDao.updateIntegral(order.getUid(), (int) Math.floor(od.getSummary()));
            if (rt == 0) {
                throw new RuntimeException("修改用户积分失败");
            }
            List<Orderdetail> orderdetails = orderdetailDao.selectOrderdetail(order.getOid());
            for (Orderdetail orderdetail : orderdetails) {
                rt = bookDao.updateCount(orderdetail.getBid(), -orderdetail.getQuantity());
                if (rt == 0) {
                    throw new RuntimeException("修改图书库存失败");
                }
            }
            rt = orderdetailDao.deleteOrderdetail(order.getOid());
            if (rt == 0) {
                throw new RuntimeException("删除订单详情失败");
            }
            rt = dao.deleteOrder(order);
            if (rt == 0) {
                throw new RuntimeException("删除订单失败");
            }
            tm.commit();
            return order.getOid();
        } catch (RuntimeException e) {
            tm.rollback();
            throw e;
        }
    }

    @Override
    public List<Order> getByUser(Integer uid) {
        return dao.selectByUser(uid);
    }
}
