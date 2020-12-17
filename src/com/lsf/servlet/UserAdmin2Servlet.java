package com.lsf.servlet;

import com.alibaba.fastjson.JSON;
import com.lsf.entity.JsonData;
import com.lsf.entity.Paginate;
import com.lsf.entity.User;
import com.lsf.service.UserService;
import com.lsf.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘愿
 * @date 2020/12/2 18:17
 * @see [相关类/方法]
 * @since V1.00
 */
public class UserAdmin2Servlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonData<Map<String, Object>> data = new JsonData<>();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Paginate pg = new Paginate();
        pg.setPageSize(10);
        pg.setPageNo(1);
        //        try{
//            pg.setPageNo(Integer.parseInt(req.getParameter("id")));
//            pg.setPageSize(10);
//        }
//        catch(Exception e){
//            pg.setPageSize(10);
//            pg.setPageNo(1);
//        }
        List<User> users = userService.getUsers(pg);
        Map<String, Object> map = new HashMap<>();
        map.put("users", users);
        map.put("page", pg);
        data.setData(map);
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(JSON.toJSONString(data));
        out.close();
    }
}
