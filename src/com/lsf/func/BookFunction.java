package com.lsf.func;

import com.lsf.entity.Book;
import com.lsf.service.BookService;
import com.lsf.service.impl.BookServiceImpl;

/**
 * @author 刘愿
 * @date 2020/11/20 16:49
 * @see [相关类/方法]
 * @since V1.00
 */
public class BookFunction {
    private static BookService service = new BookServiceImpl();

    public static Book getBook(Integer id) {
        return service.getById(id);
    }
}
