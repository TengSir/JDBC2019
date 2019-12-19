package com.oracle.jdbc.t3;


public class Account {
  @Override
  public String toString() {
    return "Account{" +
            "accid='" + accid + '\'' +
            ", accpass='" + accpass + '\'' +
            ", accrealname='" + accrealname + '\'' +
            ", accmoney=" + accmoney +
            ", acclevel='" + acclevel + '\'' +
            ", accstatus='" + accstatus + '\'' +
            '}';
  }

  private String accid;
  private String accpass;
  private String accrealname;
  private double accmoney;
  private String acclevel;
  private String accstatus;


  public String getAccid() {
    return accid;
  }

  public void setAccid(String accid) {
    this.accid = accid;
  }


  public String getAccpass() {
    return accpass;
  }

  public void setAccpass(String accpass) {
    this.accpass = accpass;
  }


  public String getAccrealname() {
    return accrealname;
  }

  public void setAccrealname(String accrealname) {
    this.accrealname = accrealname;
  }


  public double getAccmoney() {
    return accmoney;
  }

  public void setAccmoney(double accmoney) {
    this.accmoney = accmoney;
  }


  public String getAcclevel() {
    return acclevel;
  }

  public void setAcclevel(String acclevel) {
    this.acclevel = acclevel;
  }


  public String getAccstatus() {
    return accstatus;
  }

  public void setAccstatus(String accstatus) {
    this.accstatus = accstatus;
  }

}
