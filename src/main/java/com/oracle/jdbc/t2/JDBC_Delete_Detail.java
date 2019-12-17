package com.oracle.jdbc.t2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class JDBC_Delete_Detail {
    public static void main(String[] args)  throws Exception{
        //JDBC编程六步骤
        //1.装载驱动（jdk6之后不必须，但是最好提供），可以装载多个驱动（驱动的作用是负责链接数据库）
        Class.forName("oracle.jdbc.driver.OracleDriver");//手动装载类

        //2.使用jdbc提供的Drivermagger对象来创建链接
       Connection  con=DriverManager.getConnection("jdbc:oracle:thin:@172.19.22.174:1521:XE","test","test");

        //3.创建会话（会话就是能够和数据库发送SQL语句的对象）
        Statement sta=con.createStatement();//基于当前的链接对象创建了一个会话对象

        //4.使用刚刚创建好的会话对象发送数据库操作语句（SQL语句）
        int deleteCount=sta.executeUpdate("delete from accounts where accid=123");

        //5.处理结果（一般来说只针对查询，增删改基本没有数据提取意义）
        if(deleteCount>0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
        //6.关闭jdbc对象

        if(sta!=null)
        sta.close();
        if(con!=null)
        con.close();

    }
}
