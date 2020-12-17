package com.lsf.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author 刘愿
 * @date 2020/11/24 15:50
 * @see [相关类/方法]
 * @since V1.00
 */
public class EncodingServletRequest extends HttpServletRequestWrapper {
    private String encoding;

    public EncodingServletRequest(HttpServletRequest request, String encoding) {
        super(request);
        this.encoding = encoding;
    }

    @Override
    public String getParameter(String name) {
        return doEncoding(super.getParameter(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] temp = super.getParameterValues(name);
        for (int i = 0; temp != null && i < temp.length; i++) {
            temp[i] = doEncoding(temp[i]);
        }
        return temp;
    }

    private String doEncoding(String src) {
        try {
            return new String(
                    src.getBytes("ISO8859-1"), encoding);
        } catch (Exception ex) {
            return src;
        }
    }
}
