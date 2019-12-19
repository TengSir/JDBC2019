package com.oracle.jdbc.t3;


public class Transferrecords {

  private int tid;
  private String accid;
  private String ttype;

  @Override
  public String toString() {
    return "Transferrecords{" +
            "tid=" + tid +
            ", accid='" + accid + '\'' +
            ", ttype='" + ttype + '\'' +
            ", money=" + money +
            ", times=" + times +
            '}';
  }

  private double money;
  private java.sql.Timestamp times;


  public int getTid() {
    return tid;
  }

  public void setTid(int tid) {
    this.tid = tid;
  }


  public String getAccid() {
    return accid;
  }

  public void setAccid(String accid) {
    this.accid = accid;
  }


  public String getTtype() {
    return ttype;
  }

  public void setTtype(String ttype) {
    this.ttype = ttype;
  }


  public double getMoney() {
    return money;
  }

  public void setMoney(double money) {
    this.money = money;
  }


  public java.sql.Timestamp getTimes() {
    return times;
  }

  public void setTimes(java.sql.Timestamp times) {
    this.times = times;
  }

}
