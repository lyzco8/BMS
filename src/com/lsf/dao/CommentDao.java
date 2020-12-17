package com.lsf.dao;

import com.lsf.entity.Comment;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/26 14:31
 * @see [相关类/方法]
 * @since V1.00
 */
public interface CommentDao {
    /**
     * 添加评论
     * @param comment
     * @return
     */
    int insertComment(Comment comment);

    /**
     * 按图书编号查找评论
     * @param bid
     * @return
     */
    List<Comment> selectByBid(Integer bid);
}
