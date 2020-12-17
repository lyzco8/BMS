package com.lsf.servlet;

import com.lsf.entity.Book;
import com.lsf.service.BookService;
import com.lsf.service.impl.BookServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/23 18:38
 * @see [相关类/方法]
 * @since V1.00
 */

public class BookAdminServlet extends HttpServlet {
    private BookService service = new BookServiceImpl();
    private List<String> allowType = null;
    private Long maxFileSize = 0L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        allowType = new ArrayList<>();
        String value = config.getServletContext().getInitParameter("AllowType");
        String[] types = value.split(",");
        for (int i = 0; i < types.length; i++) {
            allowType.add(types[i]);
        }
        value = config.getServletContext().getInitParameter("MaxFileSize");
        try {
            maxFileSize = Long.parseLong(value);
        } catch (Exception ex) {
            maxFileSize = 2L * 1024 * 1024;
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        if ("add".equals(op)) {
            doAdd(req, resp);
        } else if ("cover".equals(op)) {
            doCover(req, resp);
        }
        doList(req, resp);
        req.getRequestDispatcher("/WEB-INF/pages/bookadmin.jsp").forward(req, resp);
    }

    private void doCover(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(req)) {
            ServletFileUpload upload = new ServletFileUpload(
                    new DiskFileItemFactory()
            );
            try {
                List<FileItem> items = upload.parseRequest(req);
                FileItem fileItem = null;
                String bid = null;
                for (int i = 0; items != null && i < items.size(); i++) {
                    FileItem item = items.get(i);
                    if (item.isFormField()) {
                        if (item.getFieldName().equals("bid")) {
                            bid = item.getString();
                        }
                    } else {
                        fileItem = item;
                    }
                }
                if (bid == null || fileItem == null) {
                    throw new RuntimeException("数据不全");
                }
                if (!allowType.contains(fileItem.getContentType())) {
                    throw new RuntimeException("图片格式不允许上传");
                }
                if (fileItem.getSize() > maxFileSize) {
                    throw new RuntimeException("图片大小超过限制");
                }
                ServletContext ctx = getServletContext();
                String path = ctx.getRealPath("/imgs/book");
                File filePath = new File(path);
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                File target = new File(filePath, bid + ".jpg");
                fileItem.write(target);
                req.setAttribute("msg", "ok");
            } catch (Exception e) {
                req.setAttribute("msg", e.getMessage());
            }
        } else {
            req.setAttribute("msg", "不是文件上传");
        }
    }

    private void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String publisher = req.getParameter("publisher");
        String strDate = req.getParameter("pubdate");
        String strCount = req.getParameter("count");
        String strPrice = req.getParameter("price");
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = fmt.parse(strDate);
            int count = Integer.parseInt(strCount);
            float price = Float.parseFloat(strPrice);
            Book book = new Book();
            book.setName(name);
            book.setAuthor(author);
            book.setPubComp(publisher);
            book.setCount(count);
            book.setPubDate(date);
            book.setPrice(price);
            int ret = service.add(book);
            if (ret > 0) {
                req.setAttribute("msg", "图书添加成功，编号是【" + ret + "】");
            } else {
                req.setAttribute("msg", "图书添加失败");
            }
        } catch (RuntimeException ex) {
            req.setAttribute("msg", ex.getMessage());
        } catch (ParseException e) {
            req.setAttribute("msg", e.getMessage());
        }
    }

    private void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = service.getAll();
        req.setAttribute("books", books);
    }

}
