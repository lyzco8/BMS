package com.lsf.servlet;

import com.lsf.entity.Book;
import com.lsf.service.BookService;
import com.lsf.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘愿
 * @date 2020/11/23 16:56
 * @see [相关类/方法]
 * @since V1.00
 */
@WebServlet("/cart.do")
public class CartServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        HttpSession session = req.getSession();
        if ("clear".equals(op)) {
            session.removeAttribute("carts");
            req.setAttribute("msg", "已清空购物车");
            req.getRequestDispatcher("/WEB-INF/pages/carts.jsp").forward(req, resp);
            return;
        }
        BookService service = new BookServiceImpl();
        Map<String, Book> carts = (Map<String, Book>) session.getAttribute("carts");
        if (carts == null) {
            carts = new HashMap<>();
            session.setAttribute("carts", carts);
        }
        String bid = req.getParameter("bid");
        int id = 0;
        try {
            if ("add".equals(op)) {
                id = Integer.parseInt(bid);
                Book book = service.getById(id);
                if (book == null) {
                    throw new RuntimeException("无此图书");
                }
                if (book.getCount() == 0) {
                    throw new RuntimeException("图书无库存");
                }
                if (carts.containsKey(bid)) {
                    Book temp = carts.get(bid);
                    if (book.getCount() > temp.getCount()) {
                        temp.setCount(temp.getCount() + 1);
                    } else {
                        throw new RuntimeException("图书库存不足");
                    }
                } else {
                    Book temp = new Book();
                    temp.setId(id);
                    temp.setCount(1);
                    carts.put(bid, temp);
                }
            } else if ("sub".equals(op)) {
                id = Integer.parseInt(bid);
                Book book = service.getById(id);
                if (book == null) {
                    throw new RuntimeException("无此图书");
                }
                Book temp = carts.get(bid);
                if (book.getCount() < temp.getCount()) {
                    temp.setCount(book.getCount());
                    if (temp.getCount() == 0) {
                        carts.remove(bid);
                        throw new RuntimeException("图书无库存,已从购物车中删除");
                    } else {
                        req.setAttribute("msg", "图书数量减少成功");
                    }
                } else {
                    temp.setCount(temp.getCount() - 1);
                    if (temp.getCount() == 0) {
                        carts.remove(bid);
                        req.setAttribute("msg", "图书从购物车中删除成功");
                    } else {
                        req.setAttribute("msg", "图书数量减少成功");
                    }
                }
            } else if ("del".equals(op)) {
                if (carts.remove(bid) != null) {
                    req.setAttribute("msg", "图书" + bid + "从购物车中删除成功");
                } else {
                    req.setAttribute("msg", "购物车中无编号" + bid + "的图书");
                }
            } else if ("dels".equals(op)) {
                String[] ids = req.getParameterValues("bid");
                for (int i = 0; i < ids.length; i++) {
                    carts.remove(ids[i]);
                }
                req.setAttribute("msg", "批量删除成功");
            }
        } catch (NumberFormatException ex) {
            req.setAttribute("msg", "图书编号错误！");
        } catch (RuntimeException re) {
            req.setAttribute("msg", re.getMessage());
        }
        req.getRequestDispatcher("/WEB-INF/pages/carts.jsp").forward(req, resp);
    }
}
