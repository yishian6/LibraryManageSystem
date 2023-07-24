package com.programme;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class U_mianPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				U_mianPage frame = new U_mianPage();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public U_mianPage() {
		super("用户主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("图书查询");
		btnNewButton.addActionListener(e -> {
			BookFind bf = new BookFind();
			bf.setVisible(true);
		});
		btnNewButton.setBounds(115, 53, 134, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("图书借阅");
		btnNewButton_1.addActionListener(e -> {
			Lend lendb = new Lend();
			lendb.setVisible(true);
		});
		btnNewButton_1.setBounds(115, 170, 134, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("图书归还");
		btnNewButton_2.addActionListener(e -> {
			ReturnBook rb = new ReturnBook();
			rb.setVisible(true);
		});
		btnNewButton_2.setBounds(115, 278, 134, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("修改个人信息");
		btnNewButton_3.addActionListener(e -> {
			U_upPerson up = new U_upPerson();
			up.setVisible(true);

		});
		btnNewButton_3.setBounds(496, 53, 141, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("修改密码");
		btnNewButton_4.addActionListener(e -> {
			UpdatePassword upw = new UpdatePassword("读者");
			upw.setVisible(true);
		});
		btnNewButton_4.setBounds(496, 170, 141, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("返回");
		btnNewButton_5.addActionListener(e -> setVisible(false));
		btnNewButton_5.setBounds(496, 278, 141, 23);
		contentPane.add(btnNewButton_5);

		//欢迎使用
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u4F7F\u7528");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel.setBounds(295, 108, 188, 36);
		contentPane.add(lblNewLabel);
	}
}
