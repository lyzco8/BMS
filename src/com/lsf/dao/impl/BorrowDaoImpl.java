package com.lsf.dao.impl;

import com.lsf.dao.BorrowDao;
import com.lsf.entity.Borrow;
import com.lsf.entity.vo.BorrowVo;
import com.ly.util.jdbc.JdbcTemplate;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/13 14:09
 * @see [相关类/方法]
 * @since V1.00
 */
public class BorrowDaoImpl implements BorrowDao {
    private JdbcTemplate template=new JdbcTemplate();
    @Override
    public int insert(Borrow borrow) {
        String sql="insert into borrow values(?,?,now(),adddate(now(),30),null)";
        return template.update(sql,false,borrow.getRid(),borrow.getBid());
    }

    @Override
    public List<BorrowVo> selectByUser(Integer uid) {
        String sql="select b.*,bk.bname bookName  from borrow b inner join book bk " +
                "on b.bid=bk.bid where b.rid=? and returndate is null";
        return template.queryList(sql,BorrowVo.class,uid);
    }

    @Override
    public List<BorrowVo> selectAll() {
        String sql="select b.*,bk.bname bookName ,u.name userName " +
                "from borrow b inner join book bk " +
                "on b.bid=bk.bid inner join users u " +
                "on b.rid=u.id where returndate is null";
        return template.queryList(sql,BorrowVo.class);
    }

    @Override
    public Borrow selectByUserBook(Integer uid, Integer bid) {
        String sql="select * from borrow  " +
                "where rid=? and bid=? and returndate is null";
        return template.queryOne(sql,Borrow.class,uid,bid);
    }

    @Override
    public int updateReturnDate(Integer uid, Integer bid) {
        String sql="update borrow set returndate=now() " +
                "where rid=? and bid=? and returnDate is null";
        return template.update(sql,false,uid,bid);
    }
}
