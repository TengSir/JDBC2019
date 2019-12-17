package com.oracle.jdbc.t1;

import javax.swing.*;
import javax.swing.table.*;
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
    private JScrollPane scrollPane;
    private Object[] columnNames={"员工编号","员工姓名","员工工资","员工邮箱"};;
    public FriendSearch(){
        setSize(400,400);
        setResizable(false);
        setTitle("搜索好友");
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        initComponent();
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
                   //3.建立会话
                   Statement sta=con.createStatement();
                   //4.使用会话对象发起sql语句操作数据库
                   ResultSet countResult=sta.executeQuery("select count(*) from hr.employees");

                   countResult.next();
                   int allCount=countResult.getInt(1);
                   model=new DefaultTableModel(columnNames,allCount);
                   ResultSet rs=sta.executeQuery("select *  from hr.employees");
                   int rowno=0;
                   //5.提取结果
                   while(rs.next()){
                       model.setValueAt(rs.getString("employee_id"),rowno,0);
                       model.setValueAt(rs.getString("first_name")+" "+rs.getString("last_name"),rowno,1);
                       model.setValueAt(rs.getString("salary"),rowno,2);
                       model.setValueAt(rs.getString("email"),rowno,3);
//                       model.setValueAt(rs.getString("phone_number"),rowno,4);
                       rowno++;
                   }
                   table.setModel(model);

                   table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
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

        model=new DefaultTableModel(columnNames,0);
        table=new JTable(model);
        table.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        scrollPane=new JScrollPane(table);
        scrollPane.setBounds(10,40,380,320);
        this.add(scrollPane);

    }

    public static void main(String[] args) {
        new FriendSearch();
    }
}
