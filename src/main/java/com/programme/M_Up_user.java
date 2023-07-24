package com.programme;

import com.programme.utils.DBUtils;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class M_Up_user extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				M_Up_user frame = new M_Up_user();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public M_Up_user() {
		super("用户信息修改界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//想要修改/添加的借书者的账号是
		JLabel lblNewLabel = new JLabel("\u60F3\u8981\u4FEE\u6539/\u6DFB\u52A0\u7684\u501F\u4E66\u8005\u7684\u8D26\u53F7\u662F");
		lblNewLabel.setBounds(10, 28, 187, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(195, 25, 104, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D");
		lblNewLabel_1.setBounds(10, 64, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(49, 61, 90, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u6027\u522B");
		lblNewLabel_2.setBounds(10, 104, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(49, 101, 66, 25);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u8054\u7CFB\u7535\u8BDD");
		lblNewLabel_3.setBounds(10, 135, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(71, 132, 126, 25);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\u804C\u4E1A");
		lblNewLabel_4.setBounds(10, 166, 54, 15);
		contentPane.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(49, 163, 148, 25);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		//所在单位
		JLabel lblNewLabel_5 = new JLabel("\u6240\u5728\u5355\u4F4D");
		lblNewLabel_5.setBounds(0, 197, 54, 15);
		contentPane.add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setBounds(59, 194, 195, 25);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		//操作成功
		JLabel lblNewLabel_6 = new JLabel("\u64CD\u4F5C\u6210\u529F");
		lblNewLabel_6.setBounds(113, 232, 54, 15);
		contentPane.add(lblNewLabel_6);
		lblNewLabel_6.setVisible(false);

		//请输入“男”或“女”
		JLabel lblNewLabel_8 = new JLabel("\u8BF7\u8F93\u5165\u201C\u7537\u201D\u6216\u201C\u5973\u201D");
		lblNewLabel_8.setForeground(Color.RED);
		lblNewLabel_8.setBounds(158, 104, 148, 15);
		contentPane.add(lblNewLabel_8);
		lblNewLabel_8.setVisible(false);
		
		JLabel lblNewLabel_7 = new JLabel("\u5FC5\u586B");
		lblNewLabel_7.setForeground(Color.RED);
		lblNewLabel_7.setBounds(309, 28, 66, 15);
		contentPane.add(lblNewLabel_7);
		lblNewLabel_7.setVisible(false);
		
		JLabel lblNewLabel_9 = new JLabel("\u6570\u636E\u5E93\u4E2D\u6CA1\u6709\u8BE5\u8D26\u6237");
		lblNewLabel_9.setForeground(Color.RED);
		lblNewLabel_9.setBounds(200, 52, 135, 15);
		contentPane.add(lblNewLabel_9);
		lblNewLabel_9.setVisible(false);

		//数据库中没有该账户
		JLabel lblNewLabel_10 = new JLabel("\u6BCF\u4E2A\u9879\u76EE\u90FD\u5FC5\u586B");
		lblNewLabel_10.setForeground(Color.RED);
		lblNewLabel_10.setBounds(273, 135, 116, 15);
		contentPane.add(lblNewLabel_10);
		lblNewLabel_10.setVisible(false);

		//返回
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.addActionListener(e -> setVisible(false));
		btnNewButton.setBounds(331, 228, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u4FEE\u6539");
		//修改
		btnNewButton_1.addActionListener(e -> {
			String name = textField_1.getText();
			String work = textField_4.getText();
			String sex = textField_2.getText();
			String phone = textField_3.getText();
			String site = textField_5.getText();
			String num = textField.getText();

			lblNewLabel_10.setVisible(false);
			if(num.equals("")) {			//借书证号必填
				lblNewLabel_7.setVisible(true);//“必填”标签
			}
			else {
				lblNewLabel_7.setVisible(false);
			}

			Connection dbConn = null;
			PreparedStatement pst = null;
			try {
				dbConn = DBUtils.getConnection();

				if (!name.equals("")) {
					String sql = "update user set user_name=? where userid=?";
					pst = dbConn.prepareStatement(sql);
					pst.setString(1, name);
					pst.setString(2, num);
					pst.executeUpdate();

				}
				if(!sex.equals("")) {
					try {
						String sql = "update user set sex=? where userid=?";
						pst = dbConn.prepareStatement(sql);
						pst.setString(1, sex);
						pst.setString(2, num);
						pst.executeUpdate();
						lblNewLabel_8.setVisible(false);
					}
					catch(Exception ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
						lblNewLabel_8.setVisible(true);
						lblNewLabel_6.setVisible(false);
					}
				}
				if(!work.equals("")) {
					String sql = "update user set job=? where userid=?";
					pst = dbConn.prepareStatement(sql);
					pst.setString(1, work);
					pst.setString(2, num);
					pst.executeUpdate();

				}
				if(!phone.equals("")) {
					String sql = "update user set phone=? where userid=?";
					pst = dbConn.prepareStatement(sql);
					pst.setString(1, phone);
					pst.setString(2, num);
					pst.executeUpdate();

				}
				if(!site.equals("")) {
					String sql = "update user set company=? where userid=?";
					pst = dbConn.prepareStatement(sql);
					pst.setString(1, site);
					pst.setString(2, num);
					pst.executeUpdate();

				}
				lblNewLabel_9.setVisible(false);
				lblNewLabel_6.setVisible(true);

			}  /*catch(Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				lblNewLabel_9.setVisible(true);
			}*/ catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				DBUtils.close(dbConn,pst);
			}

			if(lblNewLabel_8.isVisible() || lblNewLabel_9.isVisible()) {
				lblNewLabel_6.setVisible(false);
			}
			if(lblNewLabel_6.isVisible()) {
				lblNewLabel_7.setVisible(false);
				lblNewLabel_8.setVisible(false);
				lblNewLabel_9.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(10, 228, 93, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u6DFB\u52A0");
		//添加
		btnNewButton_2.addActionListener(e -> {

			String name = textField_1.getText();
			String work = textField_4.getText();
			String sex = textField_2.getText();
			String phone = textField_3.getText();
			String site = textField_5.getText();
			String num = textField.getText();

			if(num.equals("") || name.equals("") || sex.equals("") || phone.equals("") || work.equals("") || site.equals("")) {
				lblNewLabel_10.setVisible(true);//“必填”标签，每个项都必填
				lblNewLabel_6.setVisible(false);
			}
			else {
				lblNewLabel_10.setVisible(false);

				Connection dbConn = null;
				PreparedStatement pst = null;
				try {
					dbConn = DBUtils.getConnection();
					String sql = "insert into user values(?,?,?,?,?,?,?)";
					pst = dbConn.prepareStatement(sql);
					pst.setString(1, num);
					pst.setString(2, name);
					pst.setString(3, sex);
					pst.setString(4, phone);
					pst.setString(5, work);
					pst.setString(6, site);
					pst.setString(7, "123456");
					pst.addBatch();
					pst.executeBatch();

					lblNewLabel_8.setVisible(false);
					lblNewLabel_6.setVisible(true);

				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					lblNewLabel_8 .setVisible(true);
				} finally {
					DBUtils.close(dbConn,pst);
				}
			}
			if(lblNewLabel_8.isVisible() || lblNewLabel_9.isVisible()) {
				lblNewLabel_6.setVisible(false);
			}
			if(lblNewLabel_6.isVisible()) {
				lblNewLabel_7.setVisible(false);
				lblNewLabel_8.setVisible(false);
				lblNewLabel_9.setVisible(false);
				lblNewLabel_10.setVisible(false);

			}
		});
		btnNewButton_2.setBounds(177, 225, 93, 23);
		contentPane.add(btnNewButton_2);
			
	}
}
