package com.oracle.jdbc.t3;

public class TestBankDAO {
    public static void main(String[] args) {
        BANKDAO dao=new BANKDAO();
        Account a=dao.login("6228480710021","1234567");
       boolean result= dao.trans("6228480710021","6228480710022",500f);
        System.out.println(result);
        System.out.println(a);
    }
}
