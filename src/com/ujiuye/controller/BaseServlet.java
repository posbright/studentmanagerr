package com.ujiuye.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet  extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理请求乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        String mark = req.getParameter("mark");
        System.out.println("====================== " + mark + "==================================");
        // 注册页面 mark=register
        // 登陆页面 mark=login
        if (null != mark && mark.trim().length() > 0) {
            // 获取当前类的类对象
            Class<? extends BaseServlet> aClass = this.getClass();

            try {
                // 根据方法名获取方法对象
                Method meth = aClass.getMethod(mark, HttpServletRequest.class, HttpServletResponse.class);
                meth.invoke(this,req,resp);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            System.out.println("mark 的值为空 = " + mark);
        }
    }
}
