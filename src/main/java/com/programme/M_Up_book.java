package com.programme;

import com.programme.utils.DBUtils;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.Color;

public class M_Up_book extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				M_Up_book frame = new M_Up_book();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public M_Up_book() {
		super("图书信息修改界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//输入要修改的图书书号
		JLabel lblNewLabel = new JLabel("\u8F93\u5165\u8981\u4FEE\u6539\u7684\u56FE\u4E66\u4E66\u53F7 ");
		lblNewLabel.setBounds(10, 10, 133, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(142, 7, 106, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		//书名
		JLabel lblNewLabel_1 = new JLabel("\u4E66\u540D");
		lblNewLabel_1.setBounds(10, 35, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(51, 32, 227, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		//分类
		JLabel lblNewLabel_2 = new JLabel("\u5206\u7C7B");
		lblNewLabel_2.setBounds(10, 236, 54, 15);
		contentPane.add(lblNewLabel_2);

		//作者
		JLabel lblNewLabel_3 = new JLabel("\u4F5C\u8005");
		lblNewLabel_3.setBounds(10, 69, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(51, 66, 227, 25);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		//出版社
		JLabel lblNewLabel_4 = new JLabel("\u51FA\u7248\u793E");
		lblNewLabel_4.setBounds(10, 97, 54, 15);
		contentPane.add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(51, 94, 227, 25);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		//定价
		JLabel lblNewLabel_5 = new JLabel("\u5B9A\u4EF7");
		lblNewLabel_5.setBounds(10, 132, 54, 15);
		contentPane.add(lblNewLabel_5);
		
		textField_4 = new JTextField();
		textField_4.setBounds(51, 129, 92, 25);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		//内容简介
		JLabel lblNewLabel_6 = new JLabel("\u5185\u5BB9\u7B80\u4ECB");
		lblNewLabel_6.setBounds(10, 160, 54, 15);
		contentPane.add(lblNewLabel_6);
		
		textField_5 = new JTextField();
		textField_5.setBounds(74, 157, 339, 40);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("\u72B6\u6001");
		lblNewLabel_7.setBounds(10, 211, 54, 15);
		contentPane.add(lblNewLabel_7);
		
		textField_6 = new JTextField();
		textField_6.setBounds(51, 208, 81, 24);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(51, 233, 106, 24);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("\u4FEE\u6539\u6210\u529F");
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setBounds(198, 211, 54, 15);
		contentPane.add(lblNewLabel_8);
		lblNewLabel_8.setVisible(false);

		//必填
		JLabel lblNewLabel_9 = new JLabel("\u5FC5\u586B");
		lblNewLabel_9.setForeground(Color.RED);
		lblNewLabel_9.setBounds(255, 10, 54, 15);
		contentPane.add(lblNewLabel_9);
		lblNewLabel_9.setVisible(false);

		//数据库中没有该图书
		JLabel lblNewLabel_10 = new JLabel("\u6570\u636E\u5E93\u4E2D\u6CA1\u6709\u8BE5\u56FE\u4E66");
		lblNewLabel_10.setForeground(Color.RED);
		lblNewLabel_10.setBounds(297, 10, 116, 15);
		contentPane.add(lblNewLabel_10);
		lblNewLabel_10.setVisible(false);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4");
		//修改
		btnNewButton.addActionListener(e -> {
			String num = textField.getText();
			String name = textField_1.getText();
			String writer = textField_2.getText();
			String publish = textField_3.getText();
			String price = textField_4.getText();
			String intro = textField_5.getText();
			String status = textField_6.getText();
			String cla = textField_7.getText();

			lblNewLabel_9.setVisible(num.equals(""));//“必填”标签

			Connection dbConn = null;
			PreparedStatement pst = null;
			try {
				dbConn = DBUtils.getConnection();
				if (!name.equals("")) {
					String sql = "update book set book_name=? where book_id=?";
					pst = dbConn.prepareStatement(sql);
					pst.setString(1, name);
					pst.setString(2, num);
					pst.executeUpdate();

				}
				if(!writer.equals("")) {
					String sql = "update book set auth=? where book_id=?";
					pst = dbConn.prepareStatement(sql);
					pst.setString(1, writer);
					pst.setString(2, num);
					pst.executeUpdate();
				}
				if(!publish.equals("")) {
					String sql = "update book set press=? where book_id=?";
					pst = dbConn.prepareStatement(sql);
					pst.setString(1, publish);
					pst.setString(2, num);
					pst.executeUpdate();
				}
				if(!price.equals("")) {
					String sql = "update book set price=? where book_id=?";
					pst = dbConn.prepareStatement(sql);
					pst.setString(1, price);
					pst.setString(2, num);
					pst.executeUpdate();
				}
				if(!intro.equals("")) {
					String sql = "update book set content=? where book_id=?";
					pst = dbConn.prepareStatement(sql);
					pst.setString(1, intro);
					pst.setString(2, num);
					pst.executeUpdate();

				}
				if(!status.equals("")) {
					String sql = "update book set status=? where book_id=?";
					pst = dbConn.prepareStatement(sql);
					pst.setString(1, status);
					pst.setString(2, num);
					pst.executeUpdate();

				}
				if(!cla.equals("")) {
					String sql = "update book set category=? where book_id=?";
					pst = dbConn.prepareStatement(sql);
					pst.setString(1, cla);
					pst.setString(2, num);
					pst.executeUpdate();

				}
				lblNewLabel_10.setVisible(false);

			} catch(Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				lblNewLabel_10.setVisible(true);
			}finally {
				DBUtils.close(dbConn,pst);
			}

			lblNewLabel_8.setVisible(!lblNewLabel_9.isVisible() && !lblNewLabel_10.isVisible());

		});
		btnNewButton.setBounds(198, 232, 93, 23);
		contentPane.add(btnNewButton);

		//返回
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.addActionListener(e -> setVisible(false));
		btnNewButton_1.setBounds(331, 232, 93, 23);
		contentPane.add(btnNewButton_1);
		
		
	}

}
