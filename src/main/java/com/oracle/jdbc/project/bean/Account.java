package com.oracle.jdbc.project.bean;

public class Account {
	private long  accId;
	private String accPass;
	private String accRealname;
	private float  accMoney;
	private String  accLevel;
	private String accStatus;
	public long getAccId() {
		return accId;
	}
	public void setAccId(long accId) {
		this.accId = accId;
	}
	public String getAccPass() {
		return accPass;
	}
	public void setAccPass(String accPass) {
		this.accPass = accPass;
	}
	public String getAccRealname() {
		return accRealname;
	}
	public void setAccRealname(String accRealname) {
		this.accRealname = accRealname;
	}
	public float getAccMoney() {
		return accMoney;
	}
	public void setAccMoney(float accMoney) {
		this.accMoney = accMoney;
	}
	public String getAccLevel() {
		return accLevel;
	}
	public void setAccLevel(String accLevel) {
		this.accLevel = accLevel;
	}
	public String getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
	public Account(long accId, String accPass, String accRealname, float accMoney, String accLevel, String accStatus) {
		super();
		this.accId = accId;
		this.accPass = accPass;
		this.accRealname = accRealname;
		this.accMoney = accMoney;
		this.accLevel = accLevel;
		this.accStatus = accStatus;
	}
	public Account() {
		super();
	}
	@Override
	public String toString() {
		return "Account [accId=" + accId + ", accPass=" + accPass + ", accRealname=" + accRealname + ", accMoney="
				+ accMoney + ", accLevel=" + accLevel + ", accStatus=" + accStatus + "]";
	}
	
	

}
