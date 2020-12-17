package com.lsf.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 刘愿
 * @date 2020/11/26 11:50
 * @see [相关类/方法]
 * @since V1.00
 */
public class UploadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param=req.getParameter("CKEditorFuncNum");
        if(ServletFileUpload.isMultipartContent(req)){
            ServletFileUpload upload=new ServletFileUpload(
                    new DiskFileItemFactory()
            );
            try {
                List<FileItem> items=upload.parseRequest(req);
                for(int i=0;items!=null && i<items.size();i++){
                    FileItem item=items.get(i);
                    if(! item.isFormField()){
                        ServletContext ctx=getServletContext();
                        String path=ctx.getRealPath("/imgs");
                        File filePath=new File(path);
                        if(!filePath.exists()){
                            filePath.mkdirs();
                        }
                        File target=new File(filePath,item.getName());
                        item.write(target);
                        resp.setContentType("text/html;charset=UTF-8");
                        PrintWriter out=resp.getWriter();
                        out.print("<html><head>");
                        out.print("<script type='text/javascript'>window.parent.CKEDITOR.tools" +
                                ".callFunction("+ param + ", 'imgs/"+ item.getName()+ "' , " +
                                "'文件上传成功');</script>");
                        out.print("</head></html>");
                        out.close();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
