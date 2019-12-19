package com.oracle.jdbc.project.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.oracle.jdbc.project.bean.Account;

public class AtmMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Account a=new Account(62288899888888l, "123", "����", 298990f,	 "�׽�", "����");
					AtmMain frame = new AtmMain(a);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AtmMain(final Account  loginedAccount) {
	
		setTitle("\u4E2D\u56FD\u519C\u4E1A\u5F88\u884CATM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\TengSir\\Desktop\\bank.jpg"));
		lblNewLabel.setBounds(0, 0, 575, 102);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u60A8\u7684\u4F59\u989D:"+loginedAccount.getAccMoney());
		lblNewLabel_1.setBounds(112, 136, 260, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u6B22\u8FCE\u60A8:"+loginedAccount.getAccRealname());
		lblNewLabel_2.setBounds(112, 112, 247, 15);
		contentPane.add(lblNewLabel_2);
		
		JButton button = new JButton("\u8F6C\u8D26");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AtmTrans  a=new AtmTrans(loginedAccount.getAccId());
				a.setVisible(true);
				AtmMain.this.dispose();
			}
		});
		button.setBounds(116, 240, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u53D6\u73B0");
		button_1.setBounds(326, 240, 93, 23);
		contentPane.add(button_1);
	}

}
