package com.programme;

import com.programme.utils.DBUtils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Objects;

public class BookFind extends JFrame {

	private JPanel contentPane;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				BookFind frame = new BookFind();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookFind() {
		super("图书查询");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_2 = new JTextField();
		textField_2.setBounds(23, 7, 139, 25);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		//返回
		JButton btnNewButton_2 = new JButton("\u8FD4\u56DE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_2.setBounds(462, 319, 93, 23);
		contentPane.add(btnNewButton_2);

		//共有（本）
		JLabel lblNewLabel = new JLabel("\u5171\u6709\uFF08\u672C\uFF09");
		lblNewLabel.setBounds(23, 323, 70, 15);
		contentPane.add(lblNewLabel);
		
		textField_3 = new JTextField();
		textField_3.setBounds(82, 320, 66, 25);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 38, 532, 271);
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
		table.setPreferredScrollableViewportSize(new Dimension(500 ,300));    //设置滚动面板视口大小（超过该大小的行数据需要拖动滚动条）
		scrollPane.setViewportView(table);
		//把数据库表格内容显示到页面的表格
		Connection dbConn = null;
		try {

			Statement state = null;
			dbConn = DBUtils.getConnection();
			state = dbConn.createStatement();
			String sql0 = "select * from book";
			ResultSet rs = state.executeQuery(sql0);
			
			Statement stateCnt = dbConn.createStatement();
			String sql1 = "select count(*) from book";
			ResultSet rs_1 = stateCnt.executeQuery(sql1);
			while(rs_1.next()) {
				textField_3.setText(rs_1.getString(1));
			}//统计
			
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
			try {
				if(dbConn!=null)
					dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		String []item = new String[] {"书号","书名","作者","出版社","定价","内容简介","状态","分类"};
		JComboBox comboBox = new JComboBox(item);
		comboBox.setBounds(172, 6, 139, 25);
		contentPane.add(comboBox);
		
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		//查询
		btnNewButton.addActionListener(e -> {
			String str = textField_2.getText();					//列元素
			String selectName = "";//列名
			switch ((String) Objects.requireNonNull(comboBox.getSelectedItem())){
				case "书号":
					selectName = "book_id";
					break;
				case "书名":
					selectName = "book_name";
					break;
				case "作者":
					selectName = "auth";
					break;
				case "出版社":
					selectName = "press";
					break;
				case "定价":
					selectName = "price";
					break;
				case "内容简介":
					selectName = "content";
					break;
				case "状态":
					selectName = "status";
					break;
				case "分类":
					selectName = "category";
					break;
			}

			//从文本框下拉列表读取要查询的分类
			Connection dbConn1 = null;
			try {

				Statement state = null;
				dbConn1 = DBUtils.getConnection();
				state = dbConn1.createStatement();

				String sql0 = "select * from book where "+selectName+"='"+str+"'" ;
				ResultSet rs = state.executeQuery(sql0);

				Statement stateCnt = dbConn1.createStatement();
				String sql1 = "select count(*) from book where "+selectName+"='"+str+"'";
															//填入列名，	列元素
				ResultSet rs_1 = stateCnt.executeQuery(sql1);
				int n = 0;
				while(rs_1.next()) {
					textField_3.setText(rs_1.getString(1));
					n= Integer.parseInt(rs_1.getString(1));
				}//统计数量

				int i = 0;
				while(rs.next() && i<rowData.length) {	//显示到表格中
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
				while(i>=n && i<rowData.length) {
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
				try {
					if(dbConn1!=null)
						dbConn1.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}

		});
		btnNewButton.setBounds(363, 6, 93, 23);
		contentPane.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection dbConn = null;
				try {

					Statement state = null;
					dbConn = DBUtils.getConnection();
					state = dbConn.createStatement();
					String sql0 = "select * from book";
					ResultSet rs = state.executeQuery(sql0);
					
					Statement stateCnt = dbConn.createStatement();
					String sql1 = "select count(*) from book";
					ResultSet rs_1 = stateCnt.executeQuery(sql1);
					while(rs_1.next()) {
						textField_3.setText(rs_1.getString(1));
					}//统计
					
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
					TableModel tml = new DefaultTableModel(rowData,Name);
					table.setModel(tml);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					try {
						if(dbConn!=null)
							dbConn.close();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1.setBounds(462, 7, 93, 23);
		contentPane.add(btnNewButton_1);
		
	}
}
