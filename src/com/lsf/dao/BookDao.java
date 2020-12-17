package com.lsf.dao;

import com.lsf.entity.Book;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/13 14:08
 * @see [相关类/方法]
 * @since V1.00
 */
public interface BookDao {
    /**
     * 添加图书
     * @param book
     * @return
     */
    int insert(Book book);

    /**
     * 根据图书Id查找
     * @param id
     * @return
     */
    Book selectById(Integer id);

    /**
     * 根据图书名模糊查找
     * @param name
     * @return
     */
    List<Book> selectByName(String name);

    /**
     * 查找所有图书
     * @return
     */
    List<Book> selectAll();

    /**
     * 根据图书id及库存查找
     * @param bid
     * @return
     */
    int selectCountById(Integer bid);

    /**
     * 修改图书库存
     * @param bid
     * @param count
     * @return
     */
    int updateCount(Integer bid,int count);
}
