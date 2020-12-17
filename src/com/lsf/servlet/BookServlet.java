package com.lsf.servlet;

import com.alibaba.fastjson.JSON;
import com.lsf.entity.Book;
import com.lsf.entity.Comment;
import com.lsf.entity.User;
import com.lsf.service.BookService;
import com.lsf.service.BorrowService;
import com.lsf.service.CommentService;
import com.lsf.service.impl.BookServiceImpl;
import com.lsf.service.impl.BorrowServiceImpl;
import com.lsf.service.impl.CommentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/23 15:47
 * @see [相关类/方法]
 * @since V1.00
 */
@WebServlet("/books")
public class BookServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();
    private CommentService commentService = new CommentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        if ("list".equals(op)) {
            List<Book> books = bookService.getAll();
            req.setAttribute("books", books);
            req.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(req, resp);
        } else if ("one".equals(op)) {
            int bid = 0;
            try {
                bid = Integer.parseInt(req.getParameter("bid"));
                Book book = bookService.getById(bid);
                if (book != null) {
                    req.setAttribute("book", book);
                    List<Comment> comments = commentService.getByBid(bid);
                    req.setAttribute("comments", comments);
                } else {
                    req.setAttribute("msg", "查无此书");
                }
            } catch (Exception ex) {
                req.setAttribute("msg", ex.getMessage());
            }

            req.getRequestDispatcher("/WEB-INF/pages/bookComment.jsp").forward(req, resp);
        }
        else {
            req.getRequestDispatcher("books?op=list").forward(req, resp);
        }
    }
}
