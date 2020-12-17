package com.lsf.service;

import com.lsf.entity.Book;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/13 14:19
 * @see [相关类/方法]
 * @since V1.00
 */
public interface BookService {
    /**
     * 添加图书
     * @param book
     * @return
     */
    int add(Book book);

    /**
     *查找所有书
     * @return
     */
    List<Book> getAll();

    /**
     * 根据图书Id查找
     * @param id
     * @return
     */
    Book getById(Integer id);

}
