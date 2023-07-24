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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.Objects;

public class ReturnBook extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ReturnBook frame = new ReturnBook();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReturnBook() {
		super("还书界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//书号
		JLabel lblNewLabel = new JLabel("\u4E66\u53F7");
		lblNewLabel.setBounds(10, 306, 54, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(54, 303, 107, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		//操作成功
		JLabel lblNewLabel_1 = new JLabel("\u64CD\u4F5C\u6210\u529F");
		lblNewLabel_1.setBounds(107, 360, 54, 15);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		//返回
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		//返回
		btnNewButton_1.addActionListener(e -> setVisible(false));
		btnNewButton_1.setBounds(408, 356, 93, 23);
		contentPane.add(btnNewButton_1);

		//操作失败
		JLabel lblNewLabel_2 = new JLabel("\u64CD\u4F5C\u5931\u8D25");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(34, 331, 54, 15);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 507, 286);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 300, 150);
		panel.add(scrollPane);
		
		String []Name = {"书号", "书名", "借出时间"};
		Object [][] rowData = new Object [100][3];           
		JTable table = new JTable(rowData, Name);
		table.setBounds(297, 179, -279, -124);
		table.setRowHeight(30);                          //设置行高
		table.getColumnModel().getColumn(0).setPreferredWidth(110); //第一列列宽
		table.setPreferredScrollableViewportSize(new Dimension(480 ,300));    //设置滚动面板视口大小（超过该大小的行数据需要拖动滚动条）
		scrollPane.setViewportView(table);
		
		String user = User_Login.getAccount();
		Connection dbConn = null;
		Statement state = null;
		try {

			dbConn = DBUtils.getConnection();

			state = dbConn.createStatement();  //根据id查询所有的借阅的图书，并且根据时间进行排序
			String sql1 = "select borrow_book_id,borrow_book_name,borrow_time from borrow_book where borrow_id = '"+user+"' order by borrow_time asc";
			ResultSet rs_1 = state.executeQuery(sql1);
			
			int i = 0;
			while(rs_1.next() && i<rowData.length) {
				rowData[i][0] = rs_1.getString("borrow_book_id");  //获取借阅的书号，书名和借阅时间
				rowData[i][1] = rs_1.getString("borrow_book_name");
				rowData[i][2] = rs_1.getString("borrow_time");
				i++;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			DBUtils.close(dbConn,state);
		}

		//已超时还书，请到前台缴滞纳金
		JLabel lblNewLabel_3 = new JLabel("\u5DF2\u8D85\u65F6\u8FD8\u4E66\uFF0C\u8BF7\u5230\u524D\u53F0\u7F34\u6EDE\u7EB3\u91D1");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(182, 331, 184, 15);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);
		
		JButton btnNewButton = new JButton("\u8FD8\u4E66");
		//确认还书
		btnNewButton.addActionListener(e -> {
			String Bnum = textField.getText(); //获取要还书的书的id
			Calendar c = Calendar.getInstance();
			String tm = null;
			long result = 0;

			Connection dbConn1 = null;
			Statement state1 = null;
			try {//获取时间，判断是否超时
				dbConn1 = DBUtils.getConnection();
				state1 = dbConn1.createStatement();
				String sql1 = "select borrow_time from borrow_book where borrow_book_id = '"+Bnum+"'"; //根据书的id查找书籍的时间
				ResultSet rs_1 = state1.executeQuery(sql1);
				while(rs_1.next()) {
					tm = rs_1.getString(1);		//时间戳
				}
				Calendar ct = Calendar.getInstance();
				Timestamp ts = Timestamp.valueOf(Objects.requireNonNull(tm));
				ct.setTime(ts);
				result = (c.getTimeInMillis() - ct.getTimeInMillis())/(1000*60*60*24);
				//时间差
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				lblNewLabel_2.setVisible(true);
			} finally {
				DBUtils.close(dbConn1,state1);
			}

			if(result>30) {//超过30天
				lblNewLabel_3.setVisible(true);
				lblNewLabel_2.setVisible(true);  //将操作失败设置为可见
				lblNewLabel_1.setVisible(false); //操作成功设置为不可见

			}
			else {
				lblNewLabel_3.setVisible(false);
				lblNewLabel_2.setVisible(false);
				Connection dbConn2 = null;
				try {

					dbConn2 = DBUtils.getConnection();
					PreparedStatement pst1 = null;
					pst1 = dbConn2.prepareStatement("update book set status=? where book_id=?");//修改
					pst1.setString(1, "空闲在册");
					pst1.setString(2, Bnum);
					pst1.addBatch();
					pst1.executeBatch();

					String Unum = null;
					String name = null;
					String Bname = null;
					Statement state_1 = dbConn2.createStatement();
					String sql1 = "select * from borrow_book where borrow_book_id='"+Bnum+"'";
					ResultSet rs1 = state_1.executeQuery(sql1);
					while(rs1.next()) {
						Unum = rs1.getString("borrow_id");
						name = rs1.getString("user_name");
						Bname = rs1.getString("borrow_book_name");
					}

					PreparedStatement pst2 = null;		//插入还书表
					pst2 = dbConn2.prepareStatement("insert into return_book (borrow_id,borrow_name,borrow_book_id,borrow_book_name,borrow_time,return_time) values(?,?,?,?,?,?)");//借书证号，姓名，书号，书名，借书时间，还书时间
					pst2.setString(1, Unum);
					pst2.setString(2, name);
					pst2.setString(3, Bnum);
					pst2.setString(4, Bname);
					pst2.setString(5, tm);
					pst2.setTimestamp(6, new Timestamp(c.getTimeInMillis()));
					pst2.addBatch();
					pst2. executeBatch();

					String sql0 = "delete from borrow_book where borrow_book_id= ?";  //删除借书记录
					PreparedStatement pst = dbConn2.prepareStatement(sql0);//删除
					pst.setString(1, Bnum);
					pst.addBatch();
					pst.executeBatch();

					Statement state_1_1 = dbConn2.createStatement();
					String sql1_1 = "select * from borrow_book where borrow_id = '"+Unum+"' order by borrow_time asc";
					ResultSet rs_1 = state_1_1.executeQuery(sql1_1);

					int i = 0;
					while(rs_1.next() && i<rowData.length) {
						rowData[i][0] = rs_1.getString("borrow_book_id");
						rowData[i][1] = rs_1.getString("borrow_book_name");
						rowData[i][2] = rs_1.getString("borrow_time");
						i++;
					}
					while(i<rowData.length) {
						rowData[i][0] = null;
						rowData[i][1] = null;
						rowData[i][2] = null;
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
				} finally {
					if(dbConn2!=null){
						try {
							dbConn2.close();
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
					}
				}
				if(lblNewLabel_2.isVisible() || lblNewLabel_3.isVisible()) {
					lblNewLabel_1.setVisible(false);
				}
				if(lblNewLabel_1.isVisible()) {
					lblNewLabel_2.setVisible(false);
					lblNewLabel_3.setVisible(false);
				}
			}
		});
		btnNewButton.setBounds(10, 356, 93, 23);
		contentPane.add(btnNewButton);
		
	}

}

