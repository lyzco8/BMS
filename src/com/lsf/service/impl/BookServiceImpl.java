package com.lsf.service.impl;

import com.lsf.dao.BookDao;
import com.lsf.dao.impl.BookDaoImpl;
import com.lsf.entity.Book;
import com.lsf.service.BookService;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/13 14:21
 * @see [相关类/方法]
 * @since V1.00
 */
public class BookServiceImpl implements BookService {
    private BookDao dao = new BookDaoImpl();

    @Override
    public int add(Book book) {
        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd");
        List<Book> books = dao.selectAll();
        for (int i = 0; i < books.size(); i++) {
            Book bk = books.get(i);
            String dBook = smt.format(book.getPubDate());
            String dBk = smt.format(bk.getPubDate());
            if (book.getName().equals(bk.getName()) &&
                    book.getAuthor().equals(bk.getAuthor()) &&
                    book.getPubComp().equals(bk.getPubComp()) &&
                    dBook.equals(dBk) &&
                    book.getPrice().equals(bk.getPrice())) {
                return dao.updateCount(bk.getId(), -book.getCount());
            }
        }
        return dao.insert(book);
    }

    @Override
    public List<Book> getAll() {
        return dao.selectAll();
    }

    @Override
    public Book getById(Integer id) {
        return dao.selectById(id);
    }
}
