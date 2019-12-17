package com.oracle.jdbc.t2;

import java.sql.*;

public class PreparedStatementDemo {
    public static void main(String[] args)  throws Exception{
        //预处理会话适合那些带有参数的sql语句执行，他可以提前对sql语句预编译处理，后期只需要注入参数，所以执行效率高很多，而且可以有效防范sql注入风险
        Class.forName("oracle.jdbc.driver.OracleDriver");//手动装载类
        Connection con= DriverManager.getConnection("jdbc:oracle:thin:@172.19.22.174:1521:XE","test","test");


        //普通的会话对象，每次执行数据库操作都需要发送一条语句给数据库，每条语句都需要让数据库引擎翻译，效率地下
       /* Statement  statement=con.createStatement();
        ResultSet rs= statement.executeQuery("select * from accounts where ACCID=123132 and ACCPASS='234234' ");
        ResultSet rs1= statement.executeQuery("select * from accounts where ACCID=123132 and ACCPASS='234234' ");
        ResultSet rs2= statement.executeQuery("select * from accounts where ACCID=123132 and ACCPASS='234234' ");*/


        //预处理会话提前预编译一条sql语句，后期可以反复调用执行，不用每次都发送语句，只需要每次执行前注入参数即可
        PreparedStatement pre= con.prepareStatement("select * from accounts where ACCID=? and ACCPASS=? ");

        //预处理会话在创建时已经对要执行过对sql语句预编译了，如果真的要执行查询，就需要注入参数（必须注入参数）
        pre.setLong(1,6228480710001L);
        pre.setString(2,"1234567");

        ResultSet rs=pre.executeQuery();
        if(rs.next()){
            System.out.println(rs.getString("accrealname")+","+rs.getFloat("accmoney"));
        }
        System.out.println("other code...");
        pre.setLong(1,6228480710002L);
        pre.setString(2,"1234567");

         rs=pre.executeQuery();
        if(rs.next()){
            System.out.println(rs.getString("accrealname")+","+rs.getFloat("accmoney"));
        }

        System.out.println("other code...");
        pre.setLong(1,6228480710003L);
        pre.setString(2,"1234567");

        rs=pre.executeQuery();
        if(rs.next()){
            System.out.println(rs.getString("accrealname")+","+rs.getFloat("accmoney"));
        }

    }

}
