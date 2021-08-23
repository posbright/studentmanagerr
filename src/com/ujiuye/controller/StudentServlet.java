package com.ujiuye.controller;

import com.ujiuye.bean.Student;
import com.ujiuye.service.StudentService;
import com.ujiuye.util.DateUtils;
import com.ujiuye.util.PageTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

/**
 * 进行文件上传的 类上必须加上注解 @MultipartConfig
 * 如果不加获取不到请求提交的参数,获取的为null
 */
@MultipartConfig
@WebServlet("/stu")
public class StudentServlet extends BaseServlet{


    public void updateStudentDo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取修改的信息 等同于 添加

        // 注意: 在处理头像修改的时候,需要判断是否有上传的头像,有覆盖原头像地址,没有使用原头像地址

        // 跳转到主页面.
    }


    public void updateToJsp(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取要修改的学生编号

        // 根据学生编号从数据库获取学生信息

        // 将学生信息存放作用域
        //request.setAttribute("student",student);
        // 跳转到修改页面
        request.getRequestDispatcher("updateStu.jsp").forward(request,response);
    }


    public void getStudentPageInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 参数的获取
        String sname = request.getParameter("sname");
        String gender = request.getParameter("gender");
        String currentPage = request.getParameter("currentPage");
        // 数据的封装
        Student student = new Student(sname, gender);
        // 交给业务逻辑层处理

        StudentService ss = new StudentService();
        PageTools<Student> pageInfo = ss.getPageInfo(student, currentPage);
        // 将获取的页面对象信息交给页面
        request.setAttribute("student",student);
        request.setAttribute("page",pageInfo);
        request.getRequestDispatcher("queryStu.jsp").forward(request,response);
    }

    // 添加学生信息
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sname = request.getParameter("sname");
        String gender = request.getParameter("gender");
        String sbir = request.getParameter("sbir");
        String[] hobby = request.getParameterValues("hobby");

        // 文件上传
        /*
        1. 获取文件上传的组件 Part part = request.getPart(String name);
        2. 获取上传文件的名字
               1. 老版本tomcat服务器通过请求头来获取:  String filename = part.getHeader("Content-Disposition");
               2. tomcat 8.5 及以后的版本: part.getSubmittedFileName(); 就会返回上传文件的名字

         3. 将文件写入到指定的磁盘目录或者是服务上某个地址
         */
        Part file = request.getPart("file");
        // 获取上传文件的名字
        String filename = file.getSubmittedFileName();
        //String header = file.getHeader("Content-Disposition");
        System.out.println(filename);
        // 获取文件文件类型
        String suffix = filename.substring(filename.lastIndexOf("."));
        // 重新生成文件的名称
        String  name = UUID.randomUUID().toString() + suffix;
        String path = "E:\\pic";

        // 将上传的文件写入指定磁盘
        file.write(path + File.separator + name);

        // 封装Student 对象
        Student student = new Student(sname, gender,
                DateUtils.StringTransferDate(sbir, "yyyy-MM-dd"), Arrays.toString(hobby), name);
        StudentService ss = new StudentService();
        boolean b = ss.saveUser(student);
        if (b) {
            response.sendRedirect("stu?mark=getStudentPageInfo");
        }else {
            request.getRequestDispatcher("addStu.html").forward(request,response);
        }

    }
}
