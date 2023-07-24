package com.programme;

import com.programme.utils.DBUtils;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Return_table extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Return_table frame = new Return_table();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Return_table() {
		super("还书记录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 684, 290);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 300, 150);
		panel.add(scrollPane);
		
		String []Name = {"借书证号", "姓名", "书号", "书名","借书时间","还书时间"};
		Object [][] rowData = new Object [100][6];           
		JTable table = new JTable(rowData, Name);
		table.setBounds(297, 179, -279, -124);
		table.setRowHeight(30);                          //设置行高
		table.getColumnModel().getColumn(0).setPreferredWidth(50); //第一列列宽
		table.setPreferredScrollableViewportSize(new Dimension(650 ,300));    //设置滚动面板视口大小（超过该大小的行数据需要拖动滚动条）
		scrollPane.setViewportView(table);
		Connection dbConn = null;
		Statement state = null;
		try {

			dbConn = DBUtils.getConnection();
			state = dbConn.createStatement();
			String sql = "select borrow_id,borrow_name,borrow_book_id,borrow_book_name,borrow_time,return_time from return_book order by return_time asc";
			ResultSet rs = state.executeQuery(sql);
			
			int i = 0;
			while(rs.next() && i<rowData.length) {
					
				rowData[i][0] = rs.getString(1);
				rowData[i][1] = rs.getString(2);   
				rowData[i][2] = rs.getString(3);   
				rowData[i][3] = rs.getString(4);   
				rowData[i][4] = rs.getString(5);   
				rowData[i][5] = rs.getString(6);
				i++;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			DBUtils.close(dbConn,state);
		}

		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.addActionListener(e -> setVisible(false));
		btnNewButton.setBounds(431, 305, 93, 23);
		contentPane.add(btnNewButton);
	}

}
