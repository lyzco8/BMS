package com.lsf.service;

import com.lsf.entity.Comment;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/26 14:36
 * @see [相关类/方法]
 * @since V1.00
 */
public interface CommentService {
    /**
     * 添加评论
     * @param comment
     * @return
     */
    int addComment(Comment comment);

    /**
     * 按图书编号查找评论
     * @param bid
     * @return
     */
    List<Comment> getByBid(Integer bid);
}
