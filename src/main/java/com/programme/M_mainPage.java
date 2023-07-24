package com.programme;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;


public class M_mainPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				M_mainPage frame = new M_mainPage();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public M_mainPage() {
		super("管理员主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("\u56FE\u4E66\u67E5\u8BE2");//图书查询
		btnNewButton.addActionListener(e -> {
			BookFind bf = new BookFind();
			bf.setVisible(true);
		});
		btnNewButton.setBounds(0, 10, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u56FE\u4E66\u6DFB\u52A0");//图书添加
		btnNewButton_1.addActionListener(e -> {
			AddBook Ad = new AddBook();
			Ad.setVisible(true);
		});
		btnNewButton_1.setBounds(0, 71, 93, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u56FE\u4E66\u7F16\u8F91");//图书编辑
		btnNewButton_2.addActionListener(e -> {
			UpdateBook Ub = new UpdateBook();
			Ub.setVisible(true);
		});
		btnNewButton_2.setBounds(0, 129, 93, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("\u8FD4\u56DE");//返回
		btnNewButton_4.addActionListener(e -> setVisible(false));
		btnNewButton_4.setBounds(320, 227, 93, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("\u8BFB\u8005\u67E5\u8BE2\u4E0E\u7F16\u8F91");//编辑读者信息
		btnNewButton_5.addActionListener(e -> {
			UpdateUser Uuser = new UpdateUser();
			Uuser.setVisible(true);
		});
		btnNewButton_5.setBounds(0, 227, 127, 23);
		contentPane.add(btnNewButton_5);
		
		
		JButton btnNewButton_6 = new JButton("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");//修改个人信息
		btnNewButton_6.addActionListener(e -> {
			M_upPerson Mpage = new M_upPerson();
			Mpage.setVisible(true);
		});
		btnNewButton_6.setBounds(316, 47, 111, 23);
		contentPane.add(btnNewButton_6);
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u4F7F\u7528");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel.setBounds(167, 71, 127, 51);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_7 = new JButton("\u4FEE\u6539\u5BC6\u7801");//修改密码
		btnNewButton_7.addActionListener(e -> {
			UpdatePassword Upw = new UpdatePassword("管理员");
			Upw.setVisible(true);
		});
		btnNewButton_7.setBounds(320, 118, 93, 23);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_3 = new JButton("\u501F\u4E66\u7BA1\u7406");
		btnNewButton_3.addActionListener(e -> {
			lend_manage lm = new lend_manage();
			lm.setVisible(true);
		});
		btnNewButton_3.setBounds(0, 178, 93, 23);
		contentPane.add(btnNewButton_3);
	}

}
