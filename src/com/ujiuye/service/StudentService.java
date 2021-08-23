package com.ujiuye.service;

import com.ujiuye.bean.Student;
import com.ujiuye.dao.StudentDao;
import com.ujiuye.util.PageTools;

import java.util.List;

/**
 * 学生业务逻辑层
 */
public class StudentService {
    // 创建dao层的对象
    private StudentDao sd = new StudentDao();

    /**
     ( 保存学生信息到数据交互层
     * @param student 学生信息
     * @return 返回是否保存成功
     */
    public boolean saveUser(Student student){
        return sd.addStudent(student) > 0;
    }

    /**
     * 获取页面的分页数据
     * @param student 查询条件
     * @param currentPage 当前页
     * @return 页面对象
     */
    public PageTools<Student> getPageInfo(Student student,String currentPage){
        // 获取符合条件页面总条数
        long countNum = sd.getCountNum(student);
        int initSize  = 3;
        // 对于当前页码的处理
        int current = 1;
        if (null != currentPage && !"".equals(currentPage.trim())) {
            current = Integer.parseInt(currentPage);
        }

        PageTools<Student> pt = new PageTools<>(initSize, current, (int) countNum);
        // 获取分页数据
        /*
        1 页: 0 ,3
        2. 页: 3, 3
        3页 : 6,3
        n页: n-1 * 3, 3
         */
        int start  = (current -1) * initSize;
        List<Student> pageList = sd.getPageList(student, start, initSize);
        // 交给分页对象
        pt.setPageList(pageList);

        return pt;
    }

}
