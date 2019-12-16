package com.oracle.jdbc.t1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FriendSearch extends JFrame {
    private JButton  list,delete,add;
    private JTable  table;
    private TableModel  model;
    public FriendSearch(){
        setSize(400,400);
        setResizable(false);
        setTitle("搜索好友");
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);
        setLayout(null);
        initComponent();
        paintComponents(getGraphics());
        paintAll(getGraphics());
    }
    public void initComponent(){
        list=new JButton("列出全部");
        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try{
                   //1.装载数据库底层驱动
                   Class.forName("oracle.jdbc.driver.OracleDriver");

                   String url="jdbc:oracle:thin:@172.19.22.174:1521:XE";
                   String username="test";
                   String password="test";
                   //2.建立链接
                   Connection con= DriverManager.getConnection(url,username,password);
                   System.out.println(con);
                   //3.建立会话
                   Statement sta=con.createStatement();
                   //4.使用会话对象发起sql语句操作数据库
                   ResultSet rs=sta.executeQuery("select *  from hr.employees");
                   int rowno=1;
                   //5.提取结果
                   while(rs.next()){
                       model.setValueAt(rs.getString("first_name"),rowno,0);
                       model.setValueAt(rs.getString("last_name"),rowno,1);
                       model.setValueAt(rs.getString("salary"),rowno,2);
                       model.setValueAt(rs.getString("email"),rowno,3);
                       rowno++;
//                       System.out.print(rs.getString("first_name")+",");
//                       System.out.print(rs.getString("last_name")+",");
//                       System.out.print(rs.getString("salary")+",");
//                       System.out.print(rs.getString("email"));
//                       System.out.println();
                   }
                   //6.业务执行完毕后，关闭jdbc各个对象
                   rs.close();
                   sta.close();
                   con.close();
               }catch (Exception ee){
                   ee.printStackTrace();;
               }
            }
        });
        list.setBounds(10,10,100,20);
        delete=new JButton("删除");
        delete.setBounds(120,10,100,20);
        add=new JButton("添加");
        add.setBounds(230,10,100,20);
        this.add(list);
        this.add(delete);
        this.add(add);
        Object[] columnNames={"first_name","last_name","salary","emil","phone_number"};
        model=new DefaultTableModel(columnNames,100);
        table=new JTable(model);
        table.setModel(model);
        table.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        table.setBounds(10,40,380,320);
        this.add(table);

    }

    public static void main(String[] args) {
        new FriendSearch();
    }
}
