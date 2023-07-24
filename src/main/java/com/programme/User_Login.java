package com.programme;


import com.programme.utils.DBUtils;

import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;


public class User_Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private static String account;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User_Login frame = new User_Login();
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
	
	public User_Login() {
		super("用户登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//读者登录
		JLabel lblNewLabel = new JLabel("\u8BFB\u8005\u767B\u5F55");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 22));
		lblNewLabel.setBounds(170, 10, 115, 36);
		contentPane.add(lblNewLabel);

		//借书证号：
		JLabel lblNewLabel_1 = new JLabel("\u501F\u4E66\u8BC1\u53F7\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(44, 64, 84, 36);
		contentPane.add(lblNewLabel_1);

		//密码：
		JLabel lblNewLabel_2 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(59, 126, 42, 15);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(138, 72, 184, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		//返回
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(309, 197, 93, 23);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(138, 123, 184, 25);
		contentPane.add(passwordField);

		//借书证号或密码错误，请重新输入
		JLabel lblNewLabel_3 = new JLabel("\u501F\u4E66\u8BC1\u53F7\u6216\u5BC6\u7801\u9519\u8BEF\uFF0C\u8BF7\u91CD\u65B0\u8F93\u5165");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(138, 163, 198, 24);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);

		//登录
		JButton btnNewButton_1 = new JButton("\u767B\u5F55");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strP = new String(passwordField.getPassword());//密码
				String strT = textField.getText();//借书证号

				Connection dbConn = null;
				PreparedStatement state = null;
				ResultSet rs = null;
				String sql = "select userid,passwd from user";
				
				try {

					dbConn = DBUtils.getConnection();
					state = dbConn.prepareStatement(sql);
					rs = state.executeQuery();
					while (rs.next()) {
						if(strT.equals(rs.getString(1)) && strP.equals(rs.getString(2))) {
							lblNewLabel_3.setVisible(false);
							U_mianPage ump = new U_mianPage();
							ump.setVisible(true);
							account = strT;

						}
						else {
							lblNewLabel_3.setVisible(true);
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					DBUtils.close(dbConn,state,rs);
				}

			}
		});
		btnNewButton_1.setBounds(8, 197, 93, 23);
		contentPane.add(btnNewButton_1);
				
	}

	public static String getAccount(){
		return account;
	}

}