package com.programme;

import com.programme.utils.DBUtils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class UpdateBook extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UpdateBook frame = new UpdateBook();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7F16\u8F91\u56FE\u4E66");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(255, 5, 142, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u8F93\u5165\u4E66\u53F7");
		lblNewLabel_1.setBounds(20, 54, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(84, 51, 131, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(10, 128, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.addActionListener(e -> setVisible(false));
		btnNewButton_1.setBounds(493, 386, 93, 23);
		contentPane.add(btnNewButton_1);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 86, 576, 262);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 300, 150);
		panel.add(scrollPane);
		
		String []Name = {"书号", "书名", "作者", "出版社","定价","内容简介","状态","分类"};
		Object [][] rowData = new Object [100][8];           
		JTable table = new JTable(rowData, Name);
		table.setBounds(297, 179, -279, -124);
		table.setRowHeight(30);                          //设置行高
		table.getColumnModel().getColumn(0).setPreferredWidth(110); //第一列列宽
		table.setPreferredScrollableViewportSize(new Dimension(550 ,300));    //设置滚动面板视口大小（超过该大小的行数据需要拖动滚动条）
		scrollPane.setViewportView(table);

		Connection dbConn = null;
		Statement state = null;
		try {
			dbConn = DBUtils.getConnection();
			state = dbConn.createStatement();
			String sql0 = "select * from book";
			ResultSet rs = state.executeQuery(sql0);
			int i = 0;
			while(rs.next() && i<rowData.length) {
				rowData[i][0] = rs.getString(1);
				rowData[i][1] = rs.getString(2);   
				rowData[i][2] = rs.getString(3);   
				rowData[i][3] = rs.getString(4);   
				rowData[i][4] = rs.getString(5);
				rowData[i][5] = rs.getString(6);
				rowData[i][6] = rs.getString(7);
				rowData[i][7] = rs.getString(8);
				i++;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			DBUtils.close(dbConn,state);
		}


		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		//查询
		btnNewButton.addActionListener(e -> {
			String str0 = textField.getText();
			Connection dbConn1 = null;
			Statement state1 = null;
			try {
				dbConn1 = DBUtils.getConnection();
				state1 = dbConn1.createStatement();

				String sql0 = "select * from book where book_id='"+str0+"'" ;
				ResultSet rs = state1.executeQuery(sql0);

				int i = 0;
				while(rs.next() && i<rowData.length) {

					rowData[i][0] = rs.getString(1);
					rowData[i][1] = rs.getString(2);
					rowData[i][2] = rs.getString(3);
					rowData[i][3] = rs.getString(4);
					rowData[i][4] = rs.getString(5);
					rowData[i][5] = rs.getString(6);
					rowData[i][6] = rs.getString(7);
					rowData[i][7] = rs.getString(8);
					i++;
				}
				while(i<rowData.length) {
					rowData[i][0] = null;
					rowData[i][1] = null;
					rowData[i][2] = null;
					rowData[i][3] = null;
					rowData[i][4] = null;
					rowData[i][5] = null;
					rowData[i][6] = null;
					rowData[i][7] = null;
					i++;
				}
			//刷新
				TableModel tml = new DefaultTableModel(rowData,Name);
				table.setModel(tml);

				dbConn1.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				DBUtils.close(dbConn1,state1);
			}
		});
		btnNewButton.setBounds(248, 50, 93, 23);
		contentPane.add(btnNewButton);
		
		
		JButton btnNewButton_2 = new JButton("\u4FEE\u6539");
		//确认修改
		btnNewButton_2.addActionListener(e -> {
			M_Up_book Upbook = new M_Up_book();
			Upbook.setVisible(true);
		});
		btnNewButton_2.setBounds(20, 358, 93, 23);
		contentPane.add(btnNewButton_2);
		
		
		JButton btnNewButton_3 = new JButton("\u590D\u4F4D");
		//复位
		btnNewButton_3.addActionListener(e -> {
			Connection dbConn12 = null;
			Statement state12 = null;
			try {
				dbConn12 = DBUtils.getConnection();
				state12 = dbConn12.createStatement();
				String sql0 = "select * from book";
				ResultSet rs = state12.executeQuery(sql0);
				int i = 0;
				while(rs.next() && i<rowData.length) {

					rowData[i][0] = rs.getString(1);
					rowData[i][1] = rs.getString(2);
					rowData[i][2] = rs.getString(3);
					rowData[i][3] = rs.getString(4);
					rowData[i][4] = rs.getString(5);
					rowData[i][5] = rs.getString(6);
					rowData[i][6] = rs.getString(7);
					rowData[i][7] = rs.getString(8);
					i++;
				}
				while(i<rowData.length) {
					rowData[i][0] = null;
					rowData[i][1] = null;
					rowData[i][2] = null;
					rowData[i][3] = null;
					rowData[i][4] = null;
					rowData[i][5] = null;
					rowData[i][6] = null;
					rowData[i][7] = null;
					i++;
				}
				//刷新
				TableModel tml = new DefaultTableModel(rowData,Name);
				table.setModel(tml);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				DBUtils.close(dbConn12,state12);
			}
		});
		btnNewButton_3.setBounds(449, 50, 93, 23);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_3 = new JLabel("\u8981\u5220\u9664\u7684\u56FE\u4E66\u4E66\u53F7");
		lblNewLabel_3.setBounds(255, 362, 100, 15);
		contentPane.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(356, 359, 80, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\u64CD\u4F5C\u6210\u529F");
		lblNewLabel_4.setBounds(248, 390, 54, 15);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_4.setVisible(false);
		
		JLabel lblNewLabel_5 = new JLabel("\u64CD\u4F5C\u5931\u8D25");
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setBounds(161, 390, 54, 15);
		contentPane.add(lblNewLabel_5);
		lblNewLabel_5.setVisible(false);
		
		JButton btnNewButton_4 = new JButton("\u5220\u9664");
		btnNewButton_4.addActionListener(new ActionListener() {//删除
			public void actionPerformed(ActionEvent e) {
				String num = textField_1.getText();//获得要删除的书号
				Connection dbConn = null;
				PreparedStatement pst = null;
				try {

					dbConn = DBUtils.getConnection();
					String sql0 = "delete from book where book_id= ?";
					pst = dbConn.prepareStatement(sql0);//删除
					pst.setString(1, num);//书号
					pst.addBatch();
					pst.executeBatch();
					
					Statement state = dbConn.createStatement();
					String sql = "select * from book";
					ResultSet rs = state.executeQuery(sql);
					int i = 0;
					while(rs.next() && i<rowData.length) {							
						rowData[i][0] = rs.getString(1);
						rowData[i][1] = rs.getString(2);   
						rowData[i][2] = rs.getString(3);   
						rowData[i][3] = rs.getString(4);   
						rowData[i][4] = rs.getString(5);
						rowData[i][5] = rs.getString(6);
						rowData[i][6] = rs.getString(7);
						rowData[i][7] = rs.getString(8);
						i++;
					}
					while(i<rowData.length) {
						rowData[i][0] = null;
						rowData[i][1] = null;
						rowData[i][2] = null;
						rowData[i][3] = null;
						rowData[i][4] = null;
						rowData[i][5] = null;
						rowData[i][6] = null;
						rowData[i][7] = null;
						i++;
					}
					//刷新
					TableModel tml = new DefaultTableModel(rowData,Name);
					table.setModel(tml);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					lblNewLabel_5.setVisible(true);
				} finally {
					DBUtils.close(dbConn,pst);
				}

				if(lblNewLabel_4.isVisible()) {
					lblNewLabel_5.setVisible(false);
				}
				if(lblNewLabel_5.isVisible()) {
					lblNewLabel_4.setVisible(false);
				}
			}
		});
		btnNewButton_4.setBounds(150, 358, 93, 23);
		contentPane.add(btnNewButton_4);
			
	}
}