package com.ujiuye.dao;

import com.ujiuye.bean.Student;
import com.ujiuye.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 数据交互层
 */
public class StudentDao {
    // 创建QueryRunner 核心对象
    private QueryRunner queryRunner = DruidUtils.getQueryRunner();

    public int addStudent(Student student){
        String sql = "insert into stu(sname,gender,hobby,photo,birthday) values(?,?,?,?,?)";
        int row = 0;
        try {
            row = queryRunner.update(sql, student.getSname(),
                    student.getGender(), student.getHobby(), student.getPhoto(), student.getBirthday());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return row;
    }


    /**
     * 统计符合条件的学生总条数
     * @param student 学生条件信息
     * @return 总条数
     */
    public long getCountNum(Student student) {
        StringBuilder sb = new StringBuilder("select count(id) from stu where 1 = 1 ");
        // sql 条件的拼接
        sqlCondtion(student, sb);

        String sql = sb.toString();
        System.out.println("sql = " + sql);
        Long lo = null;
        try {
            lo = queryRunner.query(sql, new ScalarHandler<Long>());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lo;
    }

    /**
     * 根据查询条件拼接SQL语句
     * @param student 条件
     * @param sb 拼接对象
     *           ctrl + alt + M
     */
    private void sqlCondtion(Student student, StringBuilder sb) {
        String sname = student.getSname();
        if (sname != null && !"".equals(sname.trim())){
            sb.append("  and sname like  '%" + sname + "%' ");
        }

        String gender = student.getGender();
        if (gender != null && !"-1".equals(gender) && !"".equals(gender.trim())) {
            sb.append(" and gender = '" + gender + "' ");
        }
    }

    /**
     * 获取页面的分页数据
     * @param student 分页数据的查询条件
     * @param start 获取数据的开始位置
     * @param num 获取的条数
     * @return 分页数据
     */
    public List<Student> getPageList(Student student,int start, int num) {
        StringBuilder sb = new StringBuilder("select * from stu where 1=1 ");
        // 条件的拼接
        sqlCondtion(student,sb);

        // 分页参数的拼接
        sb.append("  limit ?,? ");
        String sql = sb.toString();
        System.out.println("sql = " + sql);
        List<Student> pageList = null;
        try {
            pageList = queryRunner.query(sql, new BeanListHandler<>(Student.class), start, num);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pageList;

    }
}
