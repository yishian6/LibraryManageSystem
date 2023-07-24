package com.programme;

import com.programme.utils.DBUtils;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.util.Calendar;
public class Manager_Login extends JFrame {

	private JPanel contentPane;
	public JTextField textField;
	private JPasswordField passwordField;
	private static String account;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager_Login frame = new Manager_Login();
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
	public Manager_Login() {
		super("管理员登录界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//管理员登录
		JLabel lblNewLabel = new JLabel("\u7BA1\u7406\u5458\u767B\u5F55");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 22));
		lblNewLabel.setBounds(156, 10, 129, 36);
		contentPane.add(lblNewLabel);

		//工号：
		JLabel lblNewLabel_1 = new JLabel("\u5DE5\u53F7\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(60, 64, 68, 36);
		contentPane.add(lblNewLabel_1);

		//密码：
		JLabel lblNewLabel_2 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(59, 126, 42, 15);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(127, 72, 195, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		//返回
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		//返回
		btnNewButton.addActionListener(e -> setVisible(false));
		btnNewButton.setBounds(309, 197, 93, 23);
		contentPane.add(btnNewButton);
		
		//密码错误标签
		JLabel lblNewLabel_3 = new JLabel("\u5DE5\u53F7\u6216\u5BC6\u7801\u9519\u8BEF\uFF0C\u8BF7\u91CD\u65B0\u8F93\u5165");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(137, 162, 185, 15);
		contentPane.add(lblNewLabel_3);
				
		passwordField = new JPasswordField();
		passwordField.setBounds(127, 123, 195, 25);
		contentPane.add(passwordField);
		lblNewLabel_3.setVisible(false);

		//登录
		JButton btnNewButton_1 = new JButton("\u767B\u5F55");
		btnNewButton_1.addActionListener(e -> {
			String strP = new String(passwordField.getPassword());//密码
			String strT = textField.getText();//工号

			Connection dbConn = null;
			PreparedStatement state = null;
			String sql = "select id,passwd from book_manager";

			try {
				dbConn = DBUtils.getConnection();
				state = dbConn.prepareStatement(sql);
				ResultSet rs = state.executeQuery();
				while (rs.next()) {
					//System.out.println(rs.getString(1)+" "+rs.getString(2));
					if(strT.equals(rs.getString(1)) && strP.equals(rs.getString(2))) {

						Calendar c = Calendar.getInstance();
						M_mainPage mPage = new M_mainPage();
						mPage.setVisible(true);
						lblNewLabel_3.setVisible(false);
						account = strT;  //每次登录成功之后，将账号保存给account
						lblNewLabel_3.setVisible(false);
					}
					else {
						lblNewLabel_3.setVisible(true);
					}
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				DBUtils.close(dbConn,state);
			}

		});
		btnNewButton_1.setBounds(61, 197, 93, 23);
		contentPane.add(btnNewButton_1);

	}

	public static String getAccount(){
		return account;   //来获取account属性
	}
}
