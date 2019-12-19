package com.oracle.jdbc.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseDAOImp implements BaseDAO {
	private Connection  con;
	private Statement  sta;
	private PreparedStatement  preSta;
	private CallableStatement  callSta;
	public Connection getCon() {
		if(con==null){
			try {
				con=DriverManager.getConnection(url,username,password);
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
	public CallableStatement getCallSta(String sql) {
		if(con==null)
		{
			getCon();
		}
			try {
				callSta=con.prepareCall(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return callSta;
	}


}
