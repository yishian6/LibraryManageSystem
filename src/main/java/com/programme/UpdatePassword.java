package com.programme;

import com.programme.utils.DBUtils;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.sql.*;
public class UpdatePassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UpdatePassword frame = new UpdatePassword();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdatePassword(){
		this("读者");
	}
	public UpdatePassword(String judgeIdentity) {
		super("密码修改界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u4FEE\u6539\u4E2A\u4EBA\u5BC6\u7801");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(144, 10, 140, 31);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u8FD4\u56DE");//返回
		btnNewButton.addActionListener(e -> setVisible(false));
		btnNewButton.setBounds(331, 228, 93, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("\u539F\u5BC6\u7801");
		lblNewLabel_1.setBounds(31, 57, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u65B0\u5BC6\u7801");
		lblNewLabel_2.setBounds(31, 108, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		lblNewLabel_3.setBounds(10, 151, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u8F93\u5165\u9519\u8BEF\uFF0C\u8BF7\u6821\u68C0");
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setBounds(90, 192, 225, 15);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_4.setVisible(false);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(78, 54, 237, 25);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(78, 105, 237, 25);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(78, 151, 237, 25);
		contentPane.add(passwordField_2);
		

		JLabel lblNewLabel_5 = new JLabel("\u4FEE\u6539\u6210\u529F");
		lblNewLabel_5.setBounds(31, 206, 54, 15);
		contentPane.add(lblNewLabel_5);
		lblNewLabel_5.setVisible(false);
		
		JButton btnNewButton_1 = new JButton("\u786E\u8BA4");
		//修改密码
		btnNewButton_1.addActionListener(e -> {
			String s1 = String.valueOf(passwordField.getPassword()); //原密码
			String s2 = String.valueOf(passwordField_1.getPassword());  //新密码
			String s3 = String.valueOf(passwordField_2.getPassword());  //确认新密码

			Connection dbConn = null;
			PreparedStatement pst = null;

			String num ="" ;
			if(judgeIdentity.equals("读者"))
				num = User_Login.getAccount();
			else if(judgeIdentity.equals("管理员"))
				num = Manager_Login.getAccount();

			try {
				Statement stateManager = null;
				Statement stateUser = null;
				String sqlManager = "select id,passwd from book_manager";
				String sqlUser = "select userid,passwd from user";
				dbConn = DBUtils.getConnection();
				stateManager = dbConn.createStatement();
				stateUser = dbConn.createStatement();
				ResultSet rsManager = stateManager.executeQuery(sqlManager);
				ResultSet rsUser = stateUser.executeQuery(sqlUser);


				while (rsManager.next()) {			//如果是图书管理员
					//System.out.println(rs.getString(1)+" "+rs.getString(2));
					String managerName = rsManager.getString(1);			//账号
					String managerPasswd = rsManager.getString(2);			//原密码
					if(managerName.equals(num) && managerPasswd.equals(s1)) {	//工号是当前操作账号
						if(s2.equals(s3)) {
							String sql = "update book_manager set passwd=? where id=?";
							pst = dbConn.prepareStatement(sql);
							pst.setString(1, s3);
							pst.setString(2, num);
							pst.executeUpdate();
							lblNewLabel_5.setVisible(true);
							if(lblNewLabel_5.isVisible()) {
								lblNewLabel_4.setVisible(false);
							}
						}else {
							lblNewLabel_4.setVisible(true);
						}
					}
					else {
						lblNewLabel_4.setVisible(true);
					}
				}

				while (rsUser.next()) {			//如果是借书者
					//System.out.println(rs.getString(1)+" "+rs.getString(2));
					String userName = rsUser.getString(1);
					String userPasswd = rsUser.getString(2);			//原密码
					if(userName.equals(num) && userPasswd.equals(s1)) {
						if(s2.equals(s3)) {
							String sql_1 = "update user set passwd=? where userid=?";
							pst = dbConn.prepareStatement(sql_1);
							pst.setString(1, s3);
							pst.setString(2, num);
							pst.executeUpdate();
							lblNewLabel_5.setVisible(true);
							lblNewLabel_4.setVisible(false);
						}else {
							lblNewLabel_4.setVisible(true);
						}
					}
					else {
						lblNewLabel_4.setVisible(true);
					}
				}
				if(lblNewLabel_5.isVisible()) {
					lblNewLabel_4.setVisible(false);
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				DBUtils.close(dbConn,pst);
			}

		});
		btnNewButton_1.setBounds(10, 228, 93, 23);
		contentPane.add(btnNewButton_1);
		
	}
}
