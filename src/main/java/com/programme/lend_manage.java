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
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class lend_manage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				lend_manage frame = new lend_manage();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public lend_manage() {
		super("借书管理界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 524, 286);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 300, 150);
		panel.add(scrollPane);
		
		String []Name = {"借书证号", "姓名", "书号", "书名","借书时间"};
		Object [][] rowData = new Object [100][5];           
		JTable table = new JTable(rowData, Name);
		table.setBounds(297, 179, -279, -124);
		table.setRowHeight(30);                          //设置行高
		table.getColumnModel().getColumn(0).setPreferredWidth(110); //第一列列宽
		table.setPreferredScrollableViewportSize(new Dimension(500 ,300));    //设置滚动面板视口大小（超过该大小的行数据需要拖动滚动条）
		scrollPane.setViewportView(table);

		Connection dbConn = null;
		Statement state = null;
		try {
			dbConn = DBUtils.getConnection();
			state = dbConn.createStatement();
			String sql0 = "select borrow_id,user_name,borrow_book_id,borrow_book_name,borrow_time from borrow_book order by borrow_time asc";
			ResultSet rs = state.executeQuery(sql0);
			
			int i = 0;
			while(rs.next() && i<rowData.length) {
				rowData[i][0] = rs.getString(1);
				rowData[i][1] = rs.getString(2);   
				rowData[i][2] = rs.getString(3);   
				rowData[i][3] = rs.getString(4);   
				rowData[i][4] = rs.getString(5);
				i++;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			DBUtils.close(dbConn,state);
		}
		textField = new JTextField();
		textField.setBounds(74, 306, 73, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		//输入书号
		JLabel lblNewLabel = new JLabel("\u8F93\u5165\u4E66\u53F7");
		lblNewLabel.setBounds(10, 309, 54, 15);
		contentPane.add(lblNewLabel);

		//操作成功
		JLabel lblNewLabel_1 = new JLabel("\u64CD\u4F5C\u6210\u529F");
		lblNewLabel_1.setBounds(113, 338, 54, 15);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);

		//操作失败
		JLabel lblNewLabel_2 = new JLabel("\u64CD\u4F5C\u5931\u8D25");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(166, 338, 54, 15);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);

		//返回
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.addActionListener(e -> setVisible(false));
		btnNewButton.setBounds(441, 334, 93, 23);
		contentPane.add(btnNewButton);

		//更改归还
		JButton btnNewButton_1 = new JButton("\u66F4\u6539\u5F52\u8FD8");
		btnNewButton_1.addActionListener(e -> {
			String Bnum = textField.getText();
			Calendar c = Calendar.getInstance();
			String tm = null;

			Connection dbConn1 = null;
			try {
				dbConn1 = DBUtils.getConnection();
				String Unum = null;
				String name = null;
				String Bname = null;
				Statement state_1 = dbConn1.createStatement();
				String sql1 = "select * from borrow_book where borrow_book_id='"+Bnum+"'";
				ResultSet rs1 = state_1.executeQuery(sql1);
				String s1 = "";
				String s2 = "";
				while(rs1.next()) {
					s1 = rs1.getString("borrow_id");
					s2 = rs1.getString("user_name");
					Bname = rs1.getString("borrow_book_name");
					tm = rs1.getString("borrow_time");
				}
				Unum = s1;
				name = s2;

				dbConn1.setAutoCommit(false);  //开启事物
				PreparedStatement pst2 = null;		//插入还书表
				pst2 = dbConn1.prepareStatement("insert into return_book (borrow_id,borrow_name,borrow_book_id,borrow_book_name,borrow_time,return_time) values(?,?,?,?,?,?)");//借书证号，姓名，书号，书名，借书时间，还书时间
				pst2.setString(1, Unum);
				pst2.setString(2, name);
				pst2.setString(3, Bnum);
				pst2.setString(4, Bname);
				pst2.setString(5, tm);
				pst2.setTimestamp(6, new Timestamp(c.getTimeInMillis()));
				pst2.addBatch();
				pst2.executeBatch();

				PreparedStatement pst1 = null;
				pst1 = dbConn1.prepareStatement("update book set status=? where book_id=?");
				pst1.setString(1, "空闲在册");
				pst1.setString(2, Bnum);
				pst1.addBatch();
				pst1.executeBatch();

				String sql0 = "delete from borrow_book where borrow_book_id= ?";
				PreparedStatement pst = dbConn1.prepareStatement(sql0);//删除
				pst.setString(1, Bnum);
				pst.addBatch();
				pst.executeBatch();
				dbConn1.commit(); //提交事务

				Statement state_1_1 = dbConn1.createStatement();
				String sql1_1 = "select borrow_id,user_name,borrow_book_id,borrow_book_name,borrow_time from borrow_book order by borrow_time asc";
				ResultSet rs_1 = state_1_1.executeQuery(sql1_1);

				int i = 0;
				while(rs_1.next() && i<rowData.length) {
					rowData[i][0] = rs_1.getString(1);
					rowData[i][1] = rs_1.getString(2);
					rowData[i][2] = rs_1.getString(3);
					rowData[i][3] = rs_1.getString(4);
					rowData[i][4] = rs_1.getString(5);

					i++;
				}
				while(i<rowData.length) {
					rowData[i][0] = null;
					rowData[i][1] = null;
					rowData[i][2] = null;
					rowData[i][3] = null;
					rowData[i][4] = null;
					i++;
				}
				//刷新
				TableModel tml = new DefaultTableModel(rowData,Name);
				table.setModel(tml);

				lblNewLabel_1.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				lblNewLabel_2.setVisible(true);
				lblNewLabel_1.setVisible(false);
				DBUtils.rollback(dbConn1);
			}finally {
				if(dbConn1!=null){
					try{
						dbConn1.close();
					}catch (SQLException e2){
						e2.printStackTrace();
					}
				}
			}
			if(lblNewLabel_1.isVisible()) {
				lblNewLabel_2.setVisible(false);
			}
			if(lblNewLabel_2.isVisible()) {
				lblNewLabel_1.setVisible(false);
			}

		});
		btnNewButton_1.setBounds(10, 334, 93, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u8FD8\u4E66\u8BB0\u5F55");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Return_table rb = new Return_table();
				rb.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(441, 306, 93, 23);
		contentPane.add(btnNewButton_2);
		
	}

}