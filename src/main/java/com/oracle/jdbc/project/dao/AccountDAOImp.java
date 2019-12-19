package com.oracle.jdbc.project.dao;

import com.oracle.jdbc.project.bean.Account;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AccountDAOImp extends BaseDAOImp implements AccountDAO {


	@Override
	public Account login(String username, String password) {
		Account  a=null;
		try {
			String sql="select *  from  accounts where accid=? and  accpass=?";
			PreparedStatement sta=getPreSta(sql);
			sta.setLong(1, Long.parseLong(username));
			sta.setString(2, password);
			ResultSet  rs=sta.executeQuery();
			if(rs.next())
			{
				a=new Account(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getString(5),rs.getString(6));
				return a;
			}else
			{
				return null;
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateAccountStatus(long accId,int status) {
		try {
			String sql="update accouts set accStatus=? where accid=?";
			PreparedStatement sta=getPreSta(sql);
			sta.setLong(1, status);
			sta.setLong(2, accId);
			int  rs=sta.executeUpdate();
			if(rs>0)
			{
				return true;
			}else
			{
				return false;
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean trans(long from, long to, float money) {
		String sql1="update  accounts set accmoney=accmoney-? where accid=?";
		String sql2="update  accounts set accmoney=accmoney+? where accid=?";
		String sql3="insert  into transferrecords values(seq_tr.nextVal,?,?,?,?)";
		Connection con=null;
		try {
			  con=getCon();
			con.setAutoCommit(false);
			PreparedStatement sta=con.prepareStatement(sql1);
			sta.setFloat(1, money);
			sta.setLong(2, from);
			int  result1=sta.executeUpdate();
			
			 sta=con.prepareStatement(sql2);
				sta.setFloat(1, money);
				sta.setLong(2, to);
				int  result2=sta.executeUpdate();
				
				sta=con.prepareStatement(sql3);
				sta.setLong(1, from);
				sta.setString(2, "����");
				sta.setFloat(3, money);
				sta.setDate(4, new Date(new java.util.Date().getTime()));
				int  result3=sta.executeUpdate();
				
				
				sta.setLong(1, to);
				sta.setString(2, "����");
				sta.setFloat(3, money);
				sta.setDate(4, new Date(new java.util.Date().getTime()));
				int  result4=sta.executeUpdate();
				con.commit();
				
			return ( result1+result2+result3+result4)>=4?true:false;
		} catch (Exception e2) {
			e2.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
	}
	

}
