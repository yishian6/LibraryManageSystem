package com.programme;

import com.programme.utils.DBUtils;

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
import java.awt.Font;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTextField;
import java.awt.Color;

public class UpdateUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UpdateUser frame = new UpdateUser();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateUser() {
		super("修改读者信息");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//修改读者信息
		JLabel lblNewLabel = new JLabel("\u4FEE\u6539\u8BFB\u8005\u4FE1\u606F");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(215, 10, 183, 63);
		contentPane.add(lblNewLabel);

		//
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.addActionListener(e -> {		//返回
			setVisible(false);
		});
		btnNewButton.setBounds(484, 328, 93, 23);
		contentPane.add(btnNewButton);

		//返回
		JLabel lblNewLabel_1 = new JLabel("\u501F\u4E66\u8BC1\u53F7");
		lblNewLabel_1.setBounds(10, 69, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(65, 66, 152, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 94, 567, 224);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 300, 150);
		panel.add(scrollPane);
		
		String []Name = {"借书证号", "姓名", "性别", "联系电话","职业","所在单位"};
		Object [][] rowData = new Object [100][6];           
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
			String sql0 = "select * from user";
			ResultSet rs = state.executeQuery(sql0);
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

		//查询
		JButton btnNewButton_1 = new JButton("\u67E5\u8BE2");
		//查询
		btnNewButton_1.addActionListener(e -> {
			String str0 = textField.getText();
			Connection dbConn1 = null;
			Statement state1 = null;
			try {
				dbConn1 = DBUtils.getConnection();
				state1 = dbConn1.createStatement();

				String sql0 = "select * from user where userid='"+str0+"'" ;
				ResultSet rs = state1.executeQuery(sql0);

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
				while(i>=1 && i<rowData.length) {
					rowData[i][0] = null;
					rowData[i][1] = null;
					rowData[i][2] = null;
					rowData[i][3] = null;
					rowData[i][4] = null;
					rowData[i][5] = null;
					i++;
				}
				//刷新
				TableModel tml = new DefaultTableModel(rowData,Name);
				table.setModel(tml);

				dbConn1.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		btnNewButton_1.setBounds(227, 65, 93, 23);
		contentPane.add(btnNewButton_1);
		
		
		JButton btnNewButton_2 = new JButton("\u4FEE\u6539/ \u6DFB\u52A0");//修改
		btnNewButton_2.addActionListener(e -> {
			M_Up_user Upuser = new M_Up_user();
			Upuser.setVisible(true);
		});
		btnNewButton_2.setBounds(10, 328, 101, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\u590D\u4F4D");
		//刷新
		btnNewButton_3.addActionListener(e -> {

			Connection dbConn2 = null;
			Statement state2 = null;
			try {
				dbConn2 = DBUtils.getConnection();
				state2 = dbConn2.createStatement();
				String sql0 = "select * from user";
				ResultSet rs = state2.executeQuery(sql0);		//最后一行
				//String s1 = new String();
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
				while(i<rowData.length) {

					rowData[i][0] = null;
					rowData[i][1] = null;
					rowData[i][2] = null;
					rowData[i][3] = null;
					rowData[i][4] = null;
					rowData[i][5] = null;

					i++;
				}
				//刷新
				TableModel tml = new DefaultTableModel(rowData,Name);
				table.setModel(tml);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				DBUtils.close(dbConn2,state2);
			}

		});
		btnNewButton_3.setBounds(484, 65, 93, 23);
		contentPane.add(btnNewButton_3);

		//要删除的借书者的账号是
		JLabel lblNewLabel_2 = new JLabel("\u8981\u5220\u9664\u7684\u501F\u4E66\u8005\u7684\u8D26\u53F7\u662F");
		lblNewLabel_2.setBounds(137, 332, 144, 15);
		contentPane.add(lblNewLabel_2);

		//操作成功
		JLabel lblNewLabel_3 = new JLabel("\u64CD\u4F5C\u6210\u529F");
		lblNewLabel_3.setBounds(371, 350, 54, 15);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);

		//操作失败
		JLabel lblNewLabel_4 = new JLabel("\u64CD\u4F5C\u5931\u8D25");
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setBounds(295, 350, 54, 15);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_4.setVisible(false);
		
		textField_1 = new JTextField();
		textField_1.setBounds(285, 329, 66, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		//删除
		JButton btnNewButton_4 = new JButton("\u5220\u9664");
		btnNewButton_4.addActionListener(e -> {
			String num = textField_1.getText();
			Connection dbConn3 = null;
			PreparedStatement pst = null;
			try {


				dbConn3 = DBUtils.getConnection();
				String sql0 = "delete from user where userid= ?";
				pst = dbConn3.prepareStatement(sql0);//删除
				pst.setString(1, num);//借书证号
				pst.addBatch();
				pst.executeBatch();
				lblNewLabel_3.setVisible(true);
				//刷新
				Statement state3 = dbConn3.createStatement();
				String sql1 = "select * from user";
				ResultSet rs = state3.executeQuery(sql1);		//最后一行
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
				while(i<rowData.length) {
					rowData[i][0] = null;
					rowData[i][1] = null;
					rowData[i][2] = null;
					rowData[i][3] = null;
					rowData[i][4] = null;
					rowData[i][5] = null;
					i++;
				}
				TableModel tml = new DefaultTableModel(rowData,Name);
				table.setModel(tml);

				dbConn3.close();
			} catch (Exception  e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				lblNewLabel_4.setVisible(true);
			} finally {
				DBUtils.close(dbConn3,pst);
			}
			if(lblNewLabel_3.isVisible()) {
				lblNewLabel_4.setVisible(false);
			}
			if(lblNewLabel_4.isVisible()) {
				lblNewLabel_3.setVisible(false);
			}

		});
		btnNewButton_4.setBounds(361, 328, 93, 23);
		contentPane.add(btnNewButton_4);
		
	}
}
