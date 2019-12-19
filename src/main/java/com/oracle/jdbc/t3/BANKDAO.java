package com.oracle.jdbc.t3;

import com.oracle.jdbc.t3.Transferrecords;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 写一个简单的银行业务数据库操作类，里面提供若干个银行数据库业务方法
 * 要求至少包含如下四个方法：
 * 1.登陆
 * 2.存钱
 * 3.取钱
 * 4.转账
 * 5.查看账户明细（可选）
 *
 * 方法名自定义，方法参数自己设计，返回值自己设计
 */
public class BANKDAO {

    /**
     * 登陆方法
     * @param accid
     * @param accpass
     * @return
     */
    public Account login(String accid,String accpass){
        Account account=null;
        Connection con=null;
        PreparedStatement sta=null;
        ResultSet rs=null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");//手动装载类
             con= DriverManager.getConnection("jdbc:oracle:thin:@172.19.22.174:1521:XE","test","test");
             sta=con.prepareCall("select  * from ACCOUNT where ACCID=? and ACCPASS=?");
             sta.setString(1,accid);
             sta.setString(2,accpass);
             rs=sta.executeQuery();
             if(rs.next()){
                 account=new Account();
                 account.setAccid(rs.getString("accid"));
                 account.setAccrealname(rs.getString("accrealname"));
             }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(sta!=null) {
                try {
                    sta.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(con!=null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return account;

    }

    /**
     * 转账方法
     * @param from
     * @param to
     * @param money
     * @return
     */
    public boolean trans(String from,String to,float money){
        boolean result=false;
        Connection con=null;
        Statement sta=null;
        ResultSet rs=null;
        try {
        Class.forName("oracle.jdbc.driver.OracleDriver");//手动装载类
         con= DriverManager.getConnection("jdbc:oracle:thin:@172.19.22.174:1521:XE","test","test");

        con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);//手动设置当前事务的隔离策略，


         con.setAutoCommit(false);//设置当前的链接事务策略是手动事务
         sta=con.createStatement();//基于当前的链接对象创建了一个会话对象

            int update1 = sta.executeUpdate("update ACCOUNT set ACCMONEY=ACCMONEY-"+money+" where accid='"+from+"'");
            int record1=sta.executeUpdate("insert into TRANSFERRECORDS values (SEQ_TR.nextval,'"+from+"','转出',"+money+",sysdate)");
            int update2 = sta.executeUpdate("update ACCOUNT set ACCMONEY=ACCMONEY+"+money+" where accid='"+to+"'");
            int record2=sta.executeUpdate("insert into TRANSFERRECORDS values (SEQ_TR.nextval,'"+to+"','转入',"+money+",sysdate)");


            con.commit();;//手动事务必须在业务执行完毕后调用connection的commit方法，才能将之前的语句彻底修改到数据库
            result=true;
            System.out.println("转账成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("转账失败");
            result=false;
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if(sta!=null) {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con!=null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 存款方法
     * @param accid
     * @param money
     * @return
     */
    public boolean  cunqian(String accid,float money){
        boolean result=false;
        Connection con=null;
        Statement sta=null;
        ResultSet rs=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//手动装载类
            con= DriverManager.getConnection("jdbc:oracle:thin:@172.19.22.174:1521:XE","test","test");
            con.setAutoCommit(false);//设置当前的链接事务策略是手动事务
            sta=con.createStatement();//基于当前的链接对象创建了一个会话对象
            int update1 = sta.executeUpdate("update ACCOUNTS set ACCMONEY=ACCMONEY+"+money+" where accid='"+accid+"'");
            int record1=sta.executeUpdate("insert into TRANSFERRECORDS values (SEQ_TR.nextval,'"+accid+"','存款',"+money+",sysdate)");
            result=true;
            System.out.println("存款成功");
            con.commit();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("存款失败");
            result=false;
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if(sta!=null) {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con!=null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 取款方法
     * @param accid
     * @param money
     * @return
     */
    public boolean quqian(String accid,float money){
        boolean result=false;
        Connection con=null;
        Statement sta=null;
        ResultSet rs=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//手动装载类
            con= DriverManager.getConnection("jdbc:oracle:thin:@172.19.22.174:1521:XE","test","test");
            con.setAutoCommit(false);//设置当前的链接事务策略是手动事务
            sta=con.createStatement();//基于当前的链接对象创建了一个会话对象
            int update1 = sta.executeUpdate("update ACCOUNTS set ACCMONEY=ACCMONEY-"+money+" where accid='"+accid+"'");
            int record1=sta.executeUpdate("insert into TRANSFERRECORDS values (SEQ_TR.nextval,'"+accid+"','取款',"+money+",sysdate)");
            result=true;
            System.out.println("取款成功");
            con.commit();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("取款失败");
            result=false;
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if(sta!=null) {
            try {
                sta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con!=null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 查看转账记录方法
     * @param accid
     * @return
     */
    public Set<Transferrecords> watchRecord(String accid){
        Set<Transferrecords>  ts=new HashSet<>();
        Connection con=null;
        PreparedStatement sta=null;
        ResultSet rs=null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");//手动装载类
            con= DriverManager.getConnection("jdbc:oracle:thin:@172.19.22.174:1521:XE","test","test");
            sta=con.prepareCall("select  * from TRANSFERRECORDS where ACCID=?");
            sta.setString(1,accid);
            rs=sta.executeQuery();
            while(rs.next()){
                Transferrecords tr=new Transferrecords();
                tr.setTid(rs.getInt("tid"));
                tr.setAccid(rs.getString("accid"));
                tr.setMoney(rs.getFloat("money"));
                tr.setTimes(rs.getTimestamp("times"));
                tr.setTtype(rs.getString("type"));
                ts.add(tr);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(sta!=null) {
                try {
                    sta.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(con!=null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ts;
    }
}
