package com.oracle.jdbc.project.view;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.oracle.jdbc.project.bean.Account;
import com.oracle.jdbc.project.dao.AccountDAO;
import com.oracle.jdbc.project.dao.AccountDAOImp;

public class AtmLogin extends JFrame { ///view-->model(DAO)
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private AccountDAO accDAO;//has-a
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtmLogin frame = new AtmLogin();
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
	public AtmLogin() {
		accDAO=new AccountDAOImp();
		setResizable(false);
		setTitle("\u4E2D\u56FD\u519C\u4E1A\u5F88\u884CATM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("logo.jpg"));
		lblNewLabel.setBounds(0, 0, 533, 86);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField("6228480710001");
		textField.setBounds(129, 137, 209, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField("1234567");
		passwordField.setBounds(129, 183, 209, 21);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String  username=textField.getText().trim().toString();
				String  password=passwordField.getText().trim().toString();
				
				Account result=accDAO.login(username, password);
				if(result!=null)
				{
					AtmMain m=new AtmMain(result);
					m.setVisible(true);
					AtmLogin.this.dispose();
				}else
				{
					JOptionPane.showMessageDialog(null, "����ʧ��!");
				}
			}
		});
		btnNewButton.setBounds(128, 255, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnExit = new JButton("exit");
		btnExit.setBounds(267, 255, 93, 23);
		contentPane.add(btnExit);
	}
}
