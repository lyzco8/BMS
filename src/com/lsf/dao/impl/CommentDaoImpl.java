package com.lsf.dao.impl;

import com.lsf.dao.CommentDao;
import com.lsf.entity.Comment;
import com.ly.util.jdbc.JdbcTemplate;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/26 14:33
 * @see [相关类/方法]
 * @since V1.00
 */
public class CommentDaoImpl implements CommentDao {
    private JdbcTemplate template=new JdbcTemplate();
    @Override
    public int insertComment(Comment comment) {
        String sql = "insert into comment values(0,?,?,?,?)";
        return template.update(sql,true,comment.getUid(),comment.getBid(),
                comment.getTitle(),comment.getContent());
    }

    @Override
    public List<Comment> selectByBid(Integer bid) {
        String sql = "select * from comment where bid = ?";
        return template.queryList(sql,Comment.class,bid);
    }
}
