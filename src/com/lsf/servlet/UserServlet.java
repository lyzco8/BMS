package com.lsf.servlet;

import com.lsf.entity.User;
import com.lsf.service.UserService;
import com.lsf.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/30 14:14
 * @see [相关类/方法]
 * @since V1.00
 */
public class UserServlet extends HttpServlet {
    private UserService service = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {
            req.getRequestDispatcher("books?op=index").forward(req, resp);
            return;
        }
        String op = req.getParameter("op");
        if ("showreg".equals(op)) {
            req.getRequestDispatcher("/WEB-INF/pages/reg.jsp").forward(req, resp);
        } else if ("login".equals(op)) {
            doLogin(req, resp);
        } else if ("reg".equals(op)) {
            doReg(req, resp);
        } else if ("checkname".equals(op)) {
            doCheckName(req, resp);
        } else if ("checkemail".equals(op)) {
            doCheckEmail(req, resp);
        } else if ("checkphone".equals(op)) {
            doCheckPhone(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String seCode = (String) session.getAttribute("code");
        String code = req.getParameter("code");
        if (seCode == null || !seCode.equalsIgnoreCase(code)) {
            req.setAttribute("msg", "验证码不正确");
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
            return;
        }
        User user = new User();
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        if (name == null || name.trim().equals("") || pass == null || pass.trim().equals("")) {
            req.setAttribute("msg", "登陆信息不完整");
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
            return;
        }
        user.setName(name.trim());
        user.setPassword(pass.trim());
        UserService service = new UserServiceImpl();
        try {
            User temp = service.login(user);
            if (temp == null) {
                req.setAttribute("msg", "登陆失败！");
                req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
            } else {
                session.setAttribute("user", temp);
                //还有存储到cookie中
                int st = 0;
                try {
                    st = Integer.parseInt(req.getParameter("savetime"));
                    if (st == -1) {
                        st = Integer.MAX_VALUE;
                    } else {
                        st = st * 24 * 60 * 60;
                    }
                } catch (Exception ex) {
                }
                Cookie ck = new Cookie("name", temp.getName());
                ck.setMaxAge(st);
                resp.addCookie(ck);
                ck = new Cookie("pass", pass);
                ck.setMaxAge(st);
                resp.addCookie(ck);
                resp.sendRedirect("books?op=index");
            }
        } catch (Exception ex) {
            req.setAttribute("msg", "登陆异常：" + ex.getMessage());
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
    }

    private void doReg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String pass = req.getParameter("pass");
        String repass = req.getParameter("repass");
        User temp = new User();
        try {
            if (!pass.equals(repass)) {
                throw new RuntimeException("两次密码不一致");
            }
            temp.setAge(Integer.parseInt(req.getParameter("age")));
            temp.setName(name);
            temp.setEmail(email);
            temp.setGender(gender);
            temp.setPhone(phone);
            temp.setPassword(pass);
            try {
                int ret = service.reg(temp);
                if (ret > 0) {
                    req.setAttribute("msg", "用户注册成功");
                    req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
                    return;
                } else {
                    req.setAttribute("msg", "用户注册失败");
                }
            } catch (NumberFormatException e) {
                req.setAttribute("msg", e.getMessage());
            }
        } catch (Exception ex) {
            req.setAttribute("msg", ex.getMessage());
        }
        req.getRequestDispatcher("/WEB-INF/pages/reg.jsp").forward(req, resp);
    }

    private void doCheckName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        List<User> users = service.getUsers();
        try {
            for (User user : users) {
                if (name.equals(user.getName())) {
                    out.print("用户名已存在");
                    return;
                }
            }
            if (name == null || name.trim().equals("")) {
                out.print("用户名不能为空");
            } else if (!name.matches(".+")) {
                out.print("姓名不合要求");
            } else {
                out.print("用户名可以使用");
            }

        } catch (RuntimeException rex) {
            out.print(rex.getMessage());
        }
        out.close();
    }

    private void doCheckEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String email = req.getParameter("email");
        List<User> users = service.getUsers();
        try {
            for (User user : users) {
                if (email.equals(user.getEmail())) {
                    out.print("Email已存在");
                    return;
                }
            }
            if (email == null || email.trim().equals("")) {
                out.print("Email不能为空");
            } else if (!email.matches("\\w+@\\w+\\.\\w+")) {
                out.print("Email不合要求");
            } else {
                out.print("Email可以使用");
            }

        } catch (RuntimeException rex) {
            out.print(rex.getMessage());
        }
        out.close();
    }

    private void doCheckPhone(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String phone = req.getParameter("phone");
        List<User> users = service.getUsers();
        try {
            for (User user : users) {
                if (phone.equals(user.getPhone())) {
                    out.print("手机号已存在");
                    return;
                }
            }
            if (phone == null || phone.trim().equals("")) {
                out.print("手机号不能为空");
            } else if (!phone.matches("1\\d{10}")) {
                out.print("手机号不合要求");
            } else {
                out.print("手机号可以使用");
            }

        } catch (RuntimeException rex) {
            out.print(rex.getMessage());
        }
        out.close();
    }
}

