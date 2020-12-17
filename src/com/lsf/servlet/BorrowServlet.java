package com.lsf.servlet;

import com.lsf.entity.User;
import com.lsf.service.BorrowService;
import com.lsf.service.UserState;
import com.lsf.service.impl.BorrowServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 刘愿
 * @date 2020/11/30 14:00
 * @see [相关类/方法]
 * @since V1.00
 */
@WebServlet("/borrow.do")
public class BorrowServlet extends HttpServlet {
    private BorrowService service = new BorrowServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if ("borrow".equals(op)) {
            doBorrow(req, resp, user);
            req.getRequestDispatcher("book?op=list").forward(req, resp);
            return;
        } else if ("return".equals(op)) {
            doReturn(req, resp, user);
        }
        String url = doList(req, resp, user);
        req.getRequestDispatcher(url).forward(req, resp);
    }

    private String doList(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String ref = "return.jsp";
        if (user.getState().intValue() > UserState.NORMAL.intValue()) {
            ref = "adminReturnBooks.jsp";
            req.setAttribute("bookvos", service.getAll());
        } else {
            req.setAttribute("bookvos", service.getByUser(user.getId()));
        }
        return "/WEB-INF/pages/" + ref;

    }

    private void doReturn(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {

        int bid = 0;
        int uid;
        try {
            bid = Integer.parseInt(req.getParameter("bid"));
            if (user.getState().intValue() > UserState.NORMAL.intValue()) {
                uid = Integer.parseInt(req.getParameter("uid"));
            } else {
                uid = user.getId();
            }
            int rt = service.returnBook(uid, bid);
            if (rt > 0) {
                req.setAttribute("msg", "图书归还成功！");
            } else {
                req.setAttribute("msg", "图书归还失败！");
            }
        } catch (NumberFormatException ex) {
            req.setAttribute("msg", "图书编号或用户编号不合法！");
        } catch (RuntimeException rex) {
            req.setAttribute("msg", rex.getMessage());
        }

    }

    private void doBorrow(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            int bid = Integer.parseInt(req.getParameter("bid"));
            BorrowService service = new BorrowServiceImpl();
            int ret = service.lend(user.getId(), bid);
            if (ret > 0) {
                req.setAttribute("msg", "借阅成功");
            } else {
                req.setAttribute("msg", "借阅失败");
            }
        } catch (NumberFormatException ex) {
            req.setAttribute("msg", "参数不合法");
        } catch (RuntimeException rex) {
            req.setAttribute("msg", rex.getMessage());
        }
    }
}
