package com.programme;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogInPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			//这段代码是为了让窗口更加美观
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(() -> {
			try {
				LogInPage frame = new LogInPage();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogInPage() {
		super("图书管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("图书管理系统");
		lblNewLabel.setBounds(130, 10, 174, 34);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 29));
		contentPane.add(lblNewLabel);

		//管理员登录
		JButton btnNewButton = new JButton("\u7BA1\u7406\u5458\u767B\u5F55");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager_Login MPage = new Manager_Login();
				MPage.setVisible(true);
			}
		});
		btnNewButton.setBounds(146, 92, 144, 39);
		contentPane.add(btnNewButton);

		//读者登录
		JButton btnNewButton_1 = new JButton("\u8BFB\u8005\u767B\u5F55");
		btnNewButton_1.addActionListener(e -> {
			User_Login UPage = new User_Login();
			UPage.setVisible(true);
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 17));
		btnNewButton_1.setBounds(146, 159, 144, 39);
		contentPane.add(btnNewButton_1);
	}
}
