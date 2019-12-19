package dao;

import java.sql.*;

public class BaseDAOImp implements  BaseDAO {
    private Connection con;
    private Statement sta;
    private PreparedStatement preSta;
    public Connection getCon() {
        if(con==null){
            try {
                Class.forName(driverName);
                con= DriverManager.getConnection(url,username,password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return con;
    }
    public Statement getSta() {
        if(con==null)
        {
            getCon();
        }
        try {
            sta=con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sta;
    }
    public PreparedStatement getPreSta(String sql) {
        if(con==null)
        {
            getCon();
        }
        try {
            preSta=con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preSta;
    }

    /**
     * 由于jdbc的具体实现类每个方法都要关闭资源，这种代码高度重复，所以父类封装一个关闭资源的方法，让子类调用
     * @param pre
     * @param rs
     */
    public void closeSource(PreparedStatement pre,ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(pre!=null){
            try {
                pre.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
