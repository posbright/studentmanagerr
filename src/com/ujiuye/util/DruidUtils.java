package com.ujiuye.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Druid 数据库连接池工具类
 */
public class DruidUtils {
    /**
     ( 创建druid数据库连接池对象
     * @return DataSource
     */
    public static DataSource getDataSource() {
        InputStream in = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("druid.properties");

        Properties props = new Properties();
        DataSource dataSource = null;
        try {
            props.load(in);
            dataSource = DruidDataSourceFactory.createDataSource(props);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     * 创建QueryRunner 对象并传递数据库连接池对象
     * @return QueryRunner
     */
    public static QueryRunner getQueryRunner() {
        return new QueryRunner(getDataSource());
    }

    @Test
    public void getQ(){
        System.out.println("getQueryRunner() = " + getQueryRunner());
    }
}
