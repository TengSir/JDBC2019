package com.oracle.jdbc.t2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class JDBC_Select_Detail {
    public static void main(String[] args)  throws Exception{
        Set<Stu>  stus=new HashSet<Stu>();
        //JDBC编程六步骤
        //1.装载驱动（jdk6之后不必须，但是最好提供），可以装载多个驱动（驱动的作用是负责链接数据库）
        Class.forName("oracle.jdbc.driver.OracleDriver");//手动装载类
        Class.forName("com.mysql.jdbc.Driver");//手动装载类

        //2.使用jdbc提供的Drivermagger对象来创建链接
       Connection  con=DriverManager.getConnection("jdbc:oracle:thin:@172.19.22.174:1521:XE","test","test");

        //3.创建会话（会话就是能够和数据库发送SQL语句的对象）
        Statement sta=con.createStatement();//基于当前的链接对象创建了一个会话对象

        //4.使用刚刚创建好的会话对象发送数据库操作语句（SQL语句）
        ResultSet  rs=sta.executeQuery("select STUNAME,stusex,STUAGE from stu");

        int updateCount=sta.executeUpdate("update hr.employees set first_name='teng' where employee_id='1001'");
        System.out.println(updateCount);

        //5.处理结果（一般来说只针对查询，增删改基本没有数据提取意义）
        while(rs.next()){
            /**
             * 提取结果集的循环中一般是将数据库提取的一行数据存储到一个java对象中，然后将每个存储数据库的java对象放入一个集合中
             */
            Stu s=new Stu();
            s.setStuname(rs.getString("stuname"));
            s.setStusex(rs.getString("stusex"));
            s.setStuage(rs.getInt("stuage"));
            stus.add(s);

        }
        //6.关闭jdbc对象
        if(rs!=null)
        rs.close();
        if(sta!=null)
        sta.close();
        if(con!=null)
        con.close();


        for(Stu ss:stus){
            System.out.println(ss);
        }
    }
}
