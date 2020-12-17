package com.lsf.listener;

import com.lsf.entity.Paginate;
import com.ly.util.jdbc.DataBaseUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author 刘愿
 * @date 2020/11/25 8:46
 * @see [相关类/方法]
 * @since V1.00
 */
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String value=servletContextEvent.getServletContext().getInitParameter("DBPOOL_TYPE");
        if(value!=null && !value.trim().equals("")) {
            DataBaseUtil.DBPOOL_TYPE = value;
        }
        value=servletContextEvent.getServletContext().getInitParameter("GROUP_SIZE");
        try{
            Paginate.GROUP_SIZE=Integer.parseInt(value);
        }
        catch (Exception ex){}
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
