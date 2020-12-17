package com.lsf.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 刘愿
 * @date 2020/11/24 11:48
 * @see [相关类/方法]
 * @since V1.00
 */
public class AuthFilter implements Filter {
    private String redirectURL = null;
    private String sessionAttrName = null;
    private String sendMethod = null;
    private String encoding = "UTF-8";
    private boolean enableGet = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String val = filterConfig.getInitParameter("Encoding");
        if (val != null && !val.trim().equals("")) {
            encoding = val;
        }
        val = filterConfig.getInitParameter("EnableGet");
        try {
            enableGet = Boolean.parseBoolean(val);
        } catch (Exception ex) {
        }
        redirectURL = filterConfig.getInitParameter("redirectURL");
        sessionAttrName = filterConfig.getInitParameter("sessionAttrName");
        sendMethod = filterConfig.getInitParameter("sendMethod");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        if ("post".equalsIgnoreCase(req.getMethod())) {
            servletRequest.setCharacterEncoding(encoding);
        } else if (enableGet && "get".equalsIgnoreCase(req.getMethod())) {
            servletRequest = new EncodingServletRequest(req, encoding);
        }
        if (session == null) {
            if (sendMethod.equalsIgnoreCase("redirect")) {
                resp.sendRedirect(redirectURL);
            } else {
                req.getRequestDispatcher(redirectURL).forward(req, resp);
            }
            return;
        }
        Object user = session.getAttribute(sessionAttrName);
        if (user == null) {
            if (sendMethod.equalsIgnoreCase("redirect")) {
                resp.sendRedirect(redirectURL);
            } else {
                req.getRequestDispatcher(redirectURL).forward(req, resp);
            }
            return;
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
