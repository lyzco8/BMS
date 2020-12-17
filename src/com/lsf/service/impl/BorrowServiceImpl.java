package com.lsf.service.impl;

import com.lsf.dao.BookDao;
import com.lsf.dao.BorrowDao;
import com.lsf.dao.PenaltyDao;
import com.lsf.dao.UserDao;
import com.lsf.dao.impl.BookDaoImpl;
import com.lsf.dao.impl.BorrowDaoImpl;
import com.lsf.dao.impl.PenaltyDaoImpl;
import com.lsf.dao.impl.UserDaoImpl;
import com.lsf.entity.Book;
import com.lsf.entity.Borrow;
import com.lsf.entity.Penalty;
import com.lsf.entity.vo.BorrowVo;
import com.lsf.service.BorrowService;
import com.lsf.service.PenaltyType;
import com.lsf.utils.DateUtil;
import com.ly.util.jdbc.TransactionManager;

import java.util.Date;
import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/13 14:20
 * @see [相关类/方法]
 * @since V1.00
 */
public class BorrowServiceImpl implements BorrowService {
    private BookDao bookDao = new BookDaoImpl();
    private BorrowDao borrowDao = new BorrowDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private PenaltyDao penaltyDao = new PenaltyDaoImpl();
    private TransactionManager tm = new TransactionManager();

    @Override
    public int lend(Integer uid, Integer bid) {
        try {
            tm.start();
            int ret = bookDao.selectCountById(bid);
            if (ret == 0) {
                throw new RuntimeException("图书不存在或库存不足");
            }
            Borrow borrow = new Borrow();
            borrow.setBid(bid);
            borrow.setRid(uid);
            ret = borrowDao.insert(borrow);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (ret == 0) {
                throw new RuntimeException("借阅记录添加失败");
            }
            ret = bookDao.updateCount(bid, 1);
            if (ret == 0) {
                throw new RuntimeException("修改图书库存失败");
            }
            tm.commit();
            return ret;
        } catch (RuntimeException ex) {
            tm.rollback();
            throw ex;
        }
    }

    @Override
    public List<BorrowVo> getByUser(Integer uid) {
        return borrowDao.selectByUser(uid);
    }

    @Override
    public List<BorrowVo> getAll() {
        return borrowDao.selectAll();
    }

    @Override
    public int returnBook(Integer uid, Integer bid) {
        try {
            tm.start();
            Borrow borrow = borrowDao.selectByUserBook(uid, bid);
            if (borrow == null) {
                throw new RuntimeException("无此借阅信息");
            }
            int rt = borrowDao.updateReturnDate(uid, bid);
            if (rt == 0) {
                throw new RuntimeException("修改借阅信息失败");
            }
            rt = bookDao.updateCount(bid, -1);
            if (rt == 0) {
                throw new RuntimeException("修改图书库存失败");
            }
            rt = userDao.updateIntegral(uid, -10);
            if (rt == 0) {
                throw new RuntimeException("修改用户积分失败");
            }
            Date now = new Date();
            if (now.after(borrow.getWillDate())) {
                int days = DateUtil.getDaysBetweenTwoDate(now, borrow.getWillDate());
                Book book = bookDao.selectById(bid);
                float amount = days * 0.1f;
                amount = (amount > book.getPrice() * 1.5f) ? (book.getPrice().floatValue() * 1.5f) : amount;
                Penalty penalty = new Penalty();
                penalty.setUserId(uid);
                penalty.setBookId(bid);
                penalty.setType(PenaltyType.DELAY);
                penalty.setAmount(amount);
                rt = penaltyDao.insert(penalty);
                if (rt == 0) {
                    throw new RuntimeException("添加罚金记录失败");
                }
            }
            tm.commit();
            return rt;
        } catch (RuntimeException ex) {
            tm.rollback();
            throw ex;
        }

    }
}
