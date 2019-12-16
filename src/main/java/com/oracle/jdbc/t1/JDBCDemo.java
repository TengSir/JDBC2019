package com.oracle.jdbc.t1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo {
    public static void main(String[] args) throws Exception {
        //1.装载数据库底层驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");

        String url="jdbc:oracle:thin:@172.19.22.174:1521:XE";
        String username="test";
        String password="test";
        //2.建立链接
        Connection  con=DriverManager.getConnection(url,username,password);
        System.out.println(con);
        //3.建立会话
        Statement  sta=con.createStatement();
        //4.使用会话对象发起sql语句操作数据库
        ResultSet rs=sta.executeQuery("select *  from hr.employees");
        //5.提取结果
        while(rs.next()){
            System.out.print(rs.getString("first_name")+",");
            System.out.print(rs.getString("last_name")+",");
            System.out.print(rs.getString("salary")+",");
            System.out.print(rs.getString("email"));
            System.out.println();
        }
        //6.业务执行完毕后，关闭jdbc各个对象
        rs.close();
        sta.close();
        con.close();
    }
}
