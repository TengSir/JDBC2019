package com.oracle.jdbc.project.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.oracle.jdbc.project.dao.AccountDAO;
import com.oracle.jdbc.project.dao.AccountDAOImp;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class AtmTrans extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private AccountDAO accDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtmTrans frame = new AtmTrans(0l);
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
	public AtmTrans(final long from) {
		accDAO=new AccountDAOImp();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
			textField = new JTextField();
		textField.setBounds(190, 38, 203, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
	 	JLabel label = new JLabel("\u5BF9\u65B9\u5E10\u6237");
		label.setBounds(82, 41, 54, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u8F6C\u8D26\u91D1\u989D");
		label_1.setBounds(82, 96, 54, 15);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(192, 93, 201, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.setBounds(190, 185, 93, 23);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean  result=accDAO.trans(from, Long.parseLong(textField.getText().trim()), Integer.parseInt(textField_1.getText().trim()));
			   JOptionPane.showMessageDialog(null, result?"ת�˳ɹ�!":"ת��ʧ��!");
			}
		});
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.setBounds(314, 185, 93, 23);
		contentPane.add(button_1);
	}

}
