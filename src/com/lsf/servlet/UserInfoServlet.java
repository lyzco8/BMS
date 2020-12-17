package com.lsf.servlet;

import com.lsf.entity.User;
import com.lsf.service.UserService;
import com.lsf.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author 刘愿
 * @date 2020/11/30 14:13
 * @see [相关类/方法]
 * @since V1.00
 */
public class UserInfoServlet extends HttpServlet {
    private UserService service = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        String ref = "/WEB-INF/pages/modifyInfo.jsp";
        if ("showpass".equals(op)) {
            req.getRequestDispatcher("/WEB-INF/pages/modifyPass.jsp").forward(req, resp);
            return;
        }
        if ("showinfo".equals(op)) {
            req.getRequestDispatcher("/WEB-INF/pages/modifyInfo.jsp").forward(req, resp);
            return;
        }
        if ("logoff".equals(op)) {
            HttpSession session = req.getSession();
            session.removeAttribute("user");
            Cookie ck = new Cookie("name", "");
            resp.addCookie(ck);
            session.invalidate();
            req.setAttribute("msg", "已安全退出");
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
            return;
        }
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if ("pass".equals(op)) {
            ref = "/WEB-INF/pages/modifyPass.jsp";
            String oldpass = req.getParameter("oldpass");
            String newpass1 = req.getParameter("newpass1");
            String newpass2 = req.getParameter("newpass2");
            if (newpass1 == null || !newpass1.equals(newpass2)) {
                req.setAttribute("msg", "二次密码不同");
            } else {
                try {
                    int ret = service.modifyPass(newpass1, oldpass, user.getId());
                    if (ret > 0) {
                        req.setAttribute("msg", "密码修改成功");
                    } else {
                        req.setAttribute("msg", "密码修改失败");
                    }
                } catch (Exception ex) {
                    req.setAttribute("msg", ex.getMessage());
                }
            }
        } else if ("info".equals(op)) {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String gender = req.getParameter("gender");
            String phone = req.getParameter("phone");

            int age = 0;
            try {
                age = Integer.parseInt(req.getParameter("age"));
                User temp = new User();
                temp.setId(user.getId());
                temp.setName((name == null || name.trim().equals("")) ? user.getName() : name);
                temp.setEmail((email == null || email.trim().equals("")) ? user.getEmail() : email);
                temp.setGender((gender == null || gender.trim().equals("")) ? user.getGender() : gender);
                temp.setPhone((phone == null || phone.trim().equals("")) ? user.getPhone() : phone);
                temp.setAge(age);
                try {
                    int ret = service.modifyInfo(temp);
                    if (ret > 0) {
                        user.setAge(temp.getAge());
                        user.setName(temp.getName());
                        user.setPhone(temp.getPhone());
                        user.setGender(temp.getGender());
                        user.setEmail(temp.getEmail());
                        req.setAttribute("msg", "用户信息修改成功");
                    } else {
                        req.setAttribute("msg", "用户信息修改失败");
                    }
                } catch (Exception ex) {
                    req.setAttribute("msg", ex.getMessage());
                }
            } catch (Exception ex) {
                req.setAttribute("msg", "年龄不符合要求");
            }
        } else {
            req.setAttribute("msg", "无此操作");
        }
        req.getRequestDispatcher(ref).forward(req, resp);
    }
}
