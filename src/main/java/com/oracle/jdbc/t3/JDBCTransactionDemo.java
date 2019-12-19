package com.oracle.jdbc.t3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCTransactionDemo {
    public static void main(String[] args)  throws Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver");//手动装载类
        Connection con= DriverManager.getConnection("jdbc:oracle:thin:@172.19.22.174:1521:XE","test","test");

        con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);//手动设置当前事务的隔离策略，


        con.setAutoCommit(false);//设置当前的链接事务策略是手动事务
        Statement sta=con.createStatement();//基于当前的链接对象创建了一个会话对象
        try {
            int update1 = sta.executeUpdate("update ACCOUNTS set ACCMONEY=ACCMONEY-200 where accid=6228480710004");


            //隐式事务，每一条语句都说i一个独立事务，而且jdbc会自动提交执行成功语句，撤销执行失败的语句

            int update2 = sta.executeUpdate("update ACCOUNTS set ACCMONEY=ACCMONEY+200 where accid=6228480710005");

            con.commit();;//手动事务必须在业务执行完毕后调用connection的commit方法，才能将之前的语句彻底修改到数据库
            System.out.println("转账成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("转账失败");
            con.rollback();;//如果业务执行过程中有异常那么整体撤销
        }

        if(sta!=null)
            sta.close();
        if(con!=null)
            con.close();
    }
}
