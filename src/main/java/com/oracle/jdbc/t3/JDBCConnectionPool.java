package com.oracle.jdbc.t3;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCConnectionPool {
    public static void main(String[] args) {
        //使用apache的dbcp连接池创建一个jdbc的链接池
        Properties  properties=new Properties();//创建一个键值对集合，用来加载properties文件中的配置参数
        try {
            properties.load(new FileInputStream("/Users/tengsir/workspace/java/idea/JDBC2019/src/main/resources/datasource.properties"));//使用集合的load方法从一个文件流中读取配置文件的参数
        } catch (IOException e) {
            e.printStackTrace();
        }

        //使用dbcp的api直接创建一个连接池
        try {
            DataSource dataSource= BasicDataSourceFactory.createDataSource(properties);//使用dbcp的类直接创建了一个连接池

            Connection c= dataSource.getConnection();//连接池里面就是存储的Connection，所以直接从连接池get一个链接即可

            System.out.println(c.getMetaData().getDatabaseProductName());
            System.out.println(c.getMetaData().getDatabaseProductVersion());
            Statement  sta=c.createStatement();
            ResultSet  rs=sta.executeQuery("select * from accounts");
            System.out.println(rs.getMetaData().getColumnCount());
            while(rs.next()){
                System.out.println(rs.getString("accrealname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
