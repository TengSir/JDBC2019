package com.oracle.jdbc.project.dao;


import com.oracle.jdbc.project.bean.Account;

public interface AccountDAO extends BaseDAO {

		public Account login(String username, String password);
		public boolean  updateAccountStatus(long accId,int status);
		public  boolean trans(long from,long to,float money);
}
