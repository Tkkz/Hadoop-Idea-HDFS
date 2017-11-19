package JDBCtest;


import org.junit.Test;

import java.sql.*;

/**
 * Created by sssss on 2017/3/19.
 */

public class TestCRUD {
    @Test
    public void testStatement() throws Exception {
        long start = System.currentTimeMillis();
        //创建连接
        String driverClass = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/big4";
        String username = "root";
        String password = "111111";
        Class.forName(driverClass);
        Connection conn = DriverManager.getConnection(url, username, password);
        //关闭自动提交
        conn.setAutoCommit(false);

        //创建语句
        Statement st = conn.createStatement();
        for (int i = 0; i < 10000; i++) {
            String sql = "insert into user(name,age) values('tomas" + i + "'," + (i % 100) + ")";
            st.execute(sql);
        }
        conn.commit();
        st.close();
        conn.close();
        System.out.println(System.currentTimeMillis() - start);
    }


    @Test
    public void testCallableStatement() throws Exception{
        long start = System.currentTimeMillis();
        //创建连接
        String driverClass = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/big4";

        String username = "root";
        String password = "111111";
        Class.forName(driverClass);
        Connection conn = DriverManager.getConnection(url, username, password);
        //关闭自动提交
        conn.setAutoCommit(false);

        //创建可调用语句，调用存储过程
        CallableStatement cst = conn.prepareCall("{call sp_add(?,?,?)}");
        cst.setInt(1,2);        //绑定参数
        cst.setInt(2,3);
        //注册输出参数类型
        cst.registerOutParameter(3, Types.INTEGER);
        cst.execute();
        int sum = cst.getInt(3);
        System.out.println(sum);
        conn.commit();
        conn.close();
        System.out.println(System.currentTimeMillis()-start);
    }
 }


