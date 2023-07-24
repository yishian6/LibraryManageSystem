package com.programme;


import com.programme.utils.DBUtils;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import java.awt.Dimension;


public class M_upPerson extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				M_upPerson frame = new M_upPerson();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public M_upPerson() {
		super("管理员信息修改界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u59D3\u540D\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel.setBounds(43, 91, 54, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u6027\u522B\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(43, 125, 54, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(37, 193, 88, 31);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u804C\u79F0\uFF1A");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(43, 159, 54, 24);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(107, 93, 104, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(107, 127, 66, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(107, 162, 104, 25);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(117, 198, 148, 25);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.addActionListener(e -> setVisible(false));
		btnNewButton.setBounds(369, 259, 93, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("\u8BF7\u4FEE\u6539\u4E3A\u7537\u6216\u5973");
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setBounds(218, 130, 122, 15);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_4.setVisible(false);
		
		JLabel lblNewLabel_5 = new JLabel("\u4FEE\u6539\u6210\u529F");
		lblNewLabel_5.setBounds(71, 234, 54, 15);
		contentPane.add(lblNewLabel_5);
		lblNewLabel_5.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 452, 75);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 300, 150);
		panel.add(scrollPane);
		
		String []Name = {"工号", "姓名", "性别", "职称","联系电话"};
		Object [][] rowData = new Object [1][5];           
		JTable table = new JTable(rowData, Name);
		table.setBounds(297, 179, -279, -124);
		table.setRowHeight(30);                          //设置行高
		table.getColumnModel().getColumn(0).setPreferredWidth(110); //第一列列宽
		table.setPreferredScrollableViewportSize(new Dimension(440 ,300));    //设置滚动面板视口大小（超过该大小的行数据需要拖动滚动条）
		scrollPane.setViewportView(table);
		
		String num0 = Manager_Login.getAccount();
		Connection dbConn = null;
		Statement state = null;
		try {
			dbConn = DBUtils.getConnection();
			
			state = dbConn.createStatement();
			String sql = "select * from book_manager where id='"+num0+"'";
			ResultSet rs_1 = state.executeQuery(sql);
			
			int i = 0;
			while(rs_1.next() && i<rowData.length) {
				rowData[i][0] = rs_1.getString(1);
				rowData[i][1] = rs_1.getString(2);   
				rowData[i][2] = rs_1.getString(3);   
				rowData[i][3] = rs_1.getString(4);   
				rowData[i][4] = rs_1.getString(5);
				i++;
			}
			dbConn.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			DBUtils.close(dbConn,state);
		}


		JButton btnNewButton_1 = new JButton("\u4FDD\u5B58");		//保存修改信息
		btnNewButton_1.addActionListener(e -> {
			String name = textField.getText();
			String work = textField_2.getText();
			String sex = textField_1.getText();
			String phone = textField_3.getText();
			String num = Manager_Login.getAccount();
			Connection dbConn1 = null;
			PreparedStatement pst = null;
			try {

				dbConn1 = DBUtils.getConnection();

				if (!name.equals("")) {
					String sql = "update book_manager set name=? where id=?";
					pst = dbConn1.prepareStatement(sql);
					pst.setString(1, name);
					pst.setString(2, num);
					pst.executeUpdate();

				}
				if(!sex.equals("")) {
					try {
						String sql = "update book_manager set sex=? where id=?";
						pst = dbConn1.prepareStatement(sql);
						pst.setString(1, sex);
						pst.setString(2, num);
						pst.executeUpdate();
						lblNewLabel_4.setVisible(false);
					}
					catch(Exception ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
						lblNewLabel_4.setVisible(true);
						lblNewLabel_5.setVisible(false);
					}
				}
				if(!work.equals("")) {
					String sql = "update book_manager set title=? where id=?";
					pst = dbConn1.prepareStatement(sql);
					pst.setString(1, work);
					pst.setString(2, num);
					pst.executeUpdate();

				}
				if(!phone.equals("")) {
					String sql = "update book_manager set telephone=? where id=?";
					pst = dbConn1.prepareStatement(sql);
					pst.setString(1, phone);
					pst.setString(2, num);
					pst.executeUpdate();

				}

				Statement stateCnt = dbConn1.createStatement();
				String sql1 = "select * from book_manager where id='"+num+"'";
				ResultSet rs_1 = stateCnt.executeQuery(sql1);

				int i = 0;
				while(rs_1.next() && i<rowData.length) {

					rowData[i][0] = rs_1.getString(1);
					rowData[i][1] = rs_1.getString(2);
					rowData[i][2] = rs_1.getString(3);
					rowData[i][3] = rs_1.getString(4);
					rowData[i][4] = rs_1.getString(5);
					i++;
				}
				//刷新
				TableModel tml = new DefaultTableModel(rowData,Name);
				table.setModel(tml);

				lblNewLabel_5.setVisible(!lblNewLabel_4.isVisible());

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				DBUtils.close(dbConn1,pst);
			}

		});
		btnNewButton_1.setBounds(43, 259, 93, 23);
		contentPane.add(btnNewButton_1);
		
	}

}
