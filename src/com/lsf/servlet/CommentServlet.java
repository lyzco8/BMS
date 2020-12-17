package com.lsf.servlet;

import com.lsf.entity.Comment;
import com.lsf.entity.User;
import com.lsf.service.CommentService;
import com.lsf.service.impl.CommentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 刘愿
 * @date 2020/11/26 14:42
 * @see [相关类/方法]
 * @since V1.00
 */
@WebServlet("/cke")
public class CommentServlet extends HttpServlet {
    private CommentService service = new CommentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String sid = req.getParameter("bid");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (title == null || title.trim().equals("")) {
            req.setAttribute("msg", "评论标题不能为空");
        } else if (content == null || content.trim().equals("")) {
            req.setAttribute("msg", "评论内容不能为空");
        } else {
            try {
                int bid = Integer.parseInt(sid);
                Comment comment = new Comment();
                comment.setContent(content);
                comment.setTitle(title);
                comment.setBid(bid);
                comment.setUid(user.getId());
                int ret = service.addComment(comment);
                if (ret > 0) {
                    req.setAttribute("msg", "评论成功");
                } else {
                    req.setAttribute("msg", "评论失败");
                }
            } catch (NumberFormatException ex) {
                req.setAttribute("msg", "参数有误");
            }
        }
        req.getRequestDispatcher("books?op=one&bid=" + sid).forward(req, resp);
    }
}
