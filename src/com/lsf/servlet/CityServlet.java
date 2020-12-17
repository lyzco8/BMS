package com.lsf.servlet;

import com.alibaba.fastjson.JSON;
import com.lsf.entity.City;
import com.lsf.entity.JsonData;
import com.lsf.service.CityService;
import com.lsf.service.impl.CityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/2 13:51
 * @see [相关类/方法]
 * @since V1.00
 */
@WebServlet("/city.do")
public class CityServlet extends HttpServlet {
    private CityService service = new CityServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String str = "";
        String op = req.getParameter("op");
        JsonData<List<City>> data = new JsonData<>();
        if ("cas".equals(op)) {
            int up = 0;
            try {
                up = Integer.parseInt(req.getParameter("up"));
                List<City> cts = service.getByUp(up);
                if (cts.size() == 0) {
                    data.setErrorCode(2);
                    data.setMessage("查无数据");
                } else {
                    data.setData(cts);
                    data.setMessage("OK");
                }
            } catch (Exception ex) {
                data.setErrorCode(1);
                data.setMessage(ex.getMessage());
            }
            str = JSON.toJSONString(data);
        }
        out.write(str);
        out.close();
    }
}
