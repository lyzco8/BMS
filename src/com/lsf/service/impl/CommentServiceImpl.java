package com.lsf.service.impl;

import com.lsf.dao.CommentDao;
import com.lsf.dao.impl.CommentDaoImpl;
import com.lsf.entity.Comment;
import com.lsf.service.CommentService;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/26 14:37
 * @see [相关类/方法]
 * @since V1.00
 */
public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao = new CommentDaoImpl();

    @Override
    public int addComment(Comment comment) {
        return commentDao.insertComment(comment);
    }

    @Override
    public List<Comment> getByBid(Integer bid) {
        return commentDao.selectByBid(bid);
    }
}
