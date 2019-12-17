package com.oracle.jdbc.t2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CallableStatementDemo {
    public static void main(String[] args)  throws Exception{
        //CallableStatement 会话对象用来专门调用数据库存储过程的会话
        Class.forName("oracle.jdbc.driver.OracleDriver");//手动装载类
        Connection con= DriverManager.getConnection("jdbc:oracle:thin:@172.19.22.174:1521:XE","test","test");
        CallableStatement c=con.prepareCall("{call TRANS(?,?,?)}");
        c.setLong(1,6228480710003L);
        c.setLong(2,6228480710001L);
        c.setFloat(3,20000);
        boolean result=c.execute();
        System.out.println(result);
    }
}
