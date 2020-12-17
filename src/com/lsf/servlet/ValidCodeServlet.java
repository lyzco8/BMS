package com.lsf.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 用于产生验证码的Servlet
 * 随机产生4位大小写字母和数字组成的验证码
 * @author  刘愿
 * @version  [V1.00, 2018-12-12]
 * @since V1.00
 */

public class ValidCodeServlet extends HttpServlet
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("image/jpeg");
        OutputStream out=response.getOutputStream();

        Random rnd=new Random();
        String code="";
        for (int i = 0; i < 4; i++)
        {
            //0-61
            int n=rnd.nextInt(62);
            if(n<10) {
                code += n;
            }
            //Upper
            else if(n<36) {
                code += (char) (n - 10 + 'A');
            }
            else {
                code += (char) (n - 36 + 'a');
            }
        }
        HttpSession se=request.getSession();
        se.setAttribute("code", code);

        BufferedImage image = new BufferedImage(
                100, 25, BufferedImage.TYPE_INT_RGB);
        //画笔
        Graphics gra = image.getGraphics();
        // 设置背景色
        gra.setColor(Color.white);
        // 绘制边框
        gra.fillRect(2, 2, 96, 21);
        // 设置字体
        gra.setFont(new Font("Times New Roman", Font.BOLD, 20));
        for (int i = 0; i < 4; i++)
        {
            Color clr=new Color(rnd.nextInt(256),
                    rnd.nextInt(256),rnd.nextInt(256));
            //设置字体颜色
            gra.setColor(clr);
            //绘制文字
            gra.drawString(code.substring(i,i+1),15+20*i,18);
        }
        //绘制干扰线
        for (int i = 0; i < 5; i++)
        {
            Color clr=new Color(rnd.nextInt(256),
                    rnd.nextInt(256),rnd.nextInt(256));
            //设置线条颜色
            gra.setColor(clr);
            //设置线条粗细
            Graphics2D g2d=(Graphics2D)gra;
            g2d.setStroke(new BasicStroke(1+rnd.nextFloat()+rnd.nextInt(2)));
            gra.drawLine(rnd.nextInt(100), rnd.nextInt(25),
                    rnd.nextInt(100), rnd.nextInt(25));
        }
        ImageIO.write(image, "jpeg", out);
        out.close();
    }
}

