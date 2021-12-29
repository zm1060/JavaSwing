package xingming;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import org.apache.commons.lang3.StringUtils;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SwingApplication {

	private JFrame frame;
	private JTextField number;
	private JTextField mid;
	public String titles[];
	public String total_titles[];

	public List<String> record;
	public List<String> total_record;
	public ButtonGroup buttonGroup = new ButtonGroup();
	public JComboBox<String> brand = new JComboBox<String>();
	public JComboBox<String> color = new JComboBox<String>();
	public JList list;
	public JList list_1;
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	DefaultListModel<String> total = new DefaultListModel<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingApplication window = new SwingApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwingApplication() {
		list = new JList(dlm);
		list_1 = new JList(total);
		record = new ArrayList<String>();
		total_record = new ArrayList<String>();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u7B14\u8BB0\u672C\u7535\u8111\u91C7\u8D2D\u7BA1\u7406");
		frame.setBounds(100, 100, 967, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.EAST, color, -72, SpringLayout.WEST, list);
		springLayout.putConstraint(SpringLayout.EAST, brand, -72, SpringLayout.WEST, list);
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblNewLabel_1 = new JLabel("\u54C1\u724C");
		springLayout.putConstraint(SpringLayout.WEST, brand, 20, SpringLayout.EAST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, brand, -2, SpringLayout.NORTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -363, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton button_save_db = new JButton("\u63D0\u4EA4\u5230\u6570\u636E\u5E93");
		springLayout.putConstraint(SpringLayout.SOUTH, list, -6, SpringLayout.NORTH, button_save_db);
		button_save_db.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection con = DButil.getConnect();

				Statement stmt = null;
				try {
					stmt = con.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String sql,sql_check;

				
				
				for(int i = 0;i<record.size();i++) {
					sql = "insert into laptop(mID,brand,color,screen,num) values(";
					String[] data = record.get(i).split(",");
					sql_check = "select * from laptop where mID=" + record.get(0) +";";
					
					for(int j = 0;j<data.length;j++) {
						if(j == data.length - 1) {
							sql = sql + "";
						}else{
							sql = sql + "'";
						}
						sql+=data[j];
						if(j == data.length - 1) {
							sql = sql + "";
						}else{
							sql = sql + "'";
						}
						if(j == data.length - 1) {
							sql = sql + ");";
						}else{
							sql = sql + ",";
						}
						System.out.println("This data" + data[j]);
					}
					
					System.out.println(sql);
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					System.out.println("插入成功！");
					
				}
			
				dlm.removeAllElements();
			}
		});
		button_save_db.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		frame.getContentPane().add(button_save_db);
		//
		JButton button_add = new JButton("\u6DFB\u52A0\u8D2D\u4E70\u8BB0\u5F55");
		button_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String smid =  mid.getText();
				String snumber = number.getText();
				String sbrand = brand.getSelectedItem().toString();
				String sscreen = buttonGroup.getSelection().getActionCommand();
				String scolor = color.getSelectedItem().toString();
				String data = smid + "," + sbrand + "," + scolor + ","+ sscreen + "," + snumber + "\n";
				record.add(data);
				dlm.addElement(data);
			}
		});
		frame.getContentPane().add(button_add);
		
		JLabel lblNewLabel_2 = new JLabel("\u6570\u91CF");
		springLayout.putConstraint(SpringLayout.NORTH, button_save_db, -4, SpringLayout.NORTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.WEST, button_add, 0, SpringLayout.WEST, lblNewLabel_2);
		frame.getContentPane().add(lblNewLabel_2);
		
		number = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, number, -180, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, button_add, 33, SpringLayout.SOUTH, number);
		springLayout.putConstraint(SpringLayout.WEST, number, 14, SpringLayout.EAST, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 3, SpringLayout.NORTH, number);
		frame.getContentPane().add(number);
		number.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u578B\u53F7");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 30, SpringLayout.SOUTH, lblNewLabel_3);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_3, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel_3);
		
		mid = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, mid, 20, SpringLayout.EAST, lblNewLabel_3);
		springLayout.putConstraint(SpringLayout.NORTH, mid, -3, SpringLayout.NORTH, lblNewLabel_3);
		frame.getContentPane().add(mid);
		mid.setColumns(10);
		
		JRadioButton wuscreen = new JRadioButton("\u96FE\u9762\u5C4F");
		springLayout.putConstraint(SpringLayout.WEST, wuscreen, 0, SpringLayout.WEST, lblNewLabel_1);
		wuscreen.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		buttonGroup.add(wuscreen);
		wuscreen.setSelected(true);
		wuscreen.setActionCommand("雾面屏");
		frame.getContentPane().add(wuscreen);
		
		JRadioButton jingscreen = new JRadioButton("\u955C\u9762\u5C4F");
		springLayout.putConstraint(SpringLayout.WEST, list, 68, SpringLayout.EAST, jingscreen);
		springLayout.putConstraint(SpringLayout.WEST, jingscreen, 70, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, jingscreen, 0, SpringLayout.NORTH, wuscreen);
		jingscreen.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		buttonGroup.add(jingscreen);
		jingscreen.setActionCommand("镜面屏");
		frame.getContentPane().add(jingscreen);
		
		JButton button_query = new JButton("\u6570\u91CF\u67E5\u8BE2");
		springLayout.putConstraint(SpringLayout.EAST, button_query, -337, SpringLayout.EAST, frame.getContentPane());
		button_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_query.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					getTotal();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		frame.getContentPane().add(button_query);
		try {
			this.getData();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		brand.addItem("华为");
		brand.addItem("联想");
		brand.addItem("苹果");
		color.addItem("黑色");
		color.addItem("白色");
		color.addItem("灰色");
		frame.getContentPane().add(brand);
		frame.getContentPane().add(list);
		
		JButton button_save_file = new JButton("\u4FDD\u5B58\u6587\u4EF6");
		springLayout.putConstraint(SpringLayout.NORTH, button_save_file, 6, SpringLayout.SOUTH, list);
		springLayout.putConstraint(SpringLayout.EAST, button_save_file, -664, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, button_save_db, 52, SpringLayout.EAST, button_save_file);
		button_save_file.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FileWriter writer = null;
				try {
					writer = new FileWriter("src/xingming/laptop.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				for(String str: record) {
					try {
						writer.write(str + System.lineSeparator());
						System.out.println("成功写入!");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				try {
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(button_save_file);
		
		JButton button_start_time = new JButton("\u65B0\u5E74\u5012\u8BA1\u65F6");
		button_start_time.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame count = new Count();
				count.setVisible(true);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, button_start_time, 0, SpringLayout.NORTH, button_query);
		springLayout.putConstraint(SpringLayout.WEST, button_start_time, 96, SpringLayout.EAST, button_query);
		frame.getContentPane().add(button_start_time);
		
		JLabel lblNewLabel_4 = new JLabel("Laptop");
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2, 0, SpringLayout.EAST, lblNewLabel_4);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 17, SpringLayout.SOUTH, lblNewLabel_4);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_4, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel_4);
		
		
		springLayout.putConstraint(SpringLayout.NORTH, color, 23, SpringLayout.SOUTH, brand);
		frame.getContentPane().add(color);
		
		JLabel lblNewLabel_5 = new JLabel("\u989C\u8272");
		springLayout.putConstraint(SpringLayout.WEST, color, 20, SpringLayout.EAST, lblNewLabel_5);
		springLayout.putConstraint(SpringLayout.NORTH, wuscreen, 46, SpringLayout.SOUTH, lblNewLabel_5);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_5, 29, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_5, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel_5);
		
		
		springLayout.putConstraint(SpringLayout.WEST, list_1, 520, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, list_1, -135, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, list, -52, SpringLayout.WEST, list_1);
		springLayout.putConstraint(SpringLayout.NORTH, button_query, 29, SpringLayout.SOUTH, list_1);
		springLayout.putConstraint(SpringLayout.NORTH, list_1, 39, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, list_1, 254, SpringLayout.NORTH, frame.getContentPane());
		frame.getContentPane().add(list_1);
		
		JLabel lblNewLabel = new JLabel("\u91C7\u8D2D\u8BB0\u5F55");
		springLayout.putConstraint(SpringLayout.NORTH, list, 8, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, lblNewLabel_4);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 157, SpringLayout.EAST, lblNewLabel_4);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_6 = new JLabel("\u7EDF\u8BA1\u91C7\u8D2D\u4FE1\u606F");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_6, 0, SpringLayout.WEST, list_1);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_6, 0, SpringLayout.SOUTH, lblNewLabel_4);
		frame.getContentPane().add(lblNewLabel_6);
	}
	
	
	

	public void getData() throws SQLException {
		Connection con = DButil.getConnect();
		// 执行查询
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from laptop");
		// 得到元数据
		ResultSetMetaData meta = rs.getMetaData();
		// 返回列的数量
		int colCnt = meta.getColumnCount();
		System.out.println(colCnt);
		titles = new String[colCnt];
		
		while (rs.next()) {
//			每一行
			String temp = "";
//			 从结果集中取数据放入向量rec_vector中
			for (int i = 0; i < titles.length; i++) {
				String obj = rs.getString(i+1);
				//System.out.print(obj + ";");
				temp+=obj;
				//rec_vector.addElement(obj == null ? null : StringUtils.strip(obj.toString(),"[]"));
				if(i == titles.length - 1) {
					temp+="\n";
				}else {
					temp+=",";
				}
			}
			System.out.print(temp);
			dlm.addElement(temp);
			record.add(temp);
			//System.out.println();
			
		}
		
	}
	
	public void getTotal() throws SQLException{
		Connection con = DButil.getConnect();
		// 执行查询
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT brand,sum(num) from laptop group by brand;");
		// 得到元数据
		ResultSetMetaData meta = rs.getMetaData();
		// 返回列的数量
		int colCnt = meta.getColumnCount();
		System.out.println(colCnt);
		total_titles = new String[colCnt];
		
		while (rs.next()) {
//			每一行
			String temp = "";
//			 从结果集中取数据放入向量rec_vector中
			for (int i = 0; i < total_titles.length; i++) {
				String obj = rs.getString(i+1);
				//System.out.print(obj + ";");
				temp+=obj;
				//rec_vector.addElement(obj == null ? null : StringUtils.strip(obj.toString(),"[]"));
				if(i == total_titles.length - 1) {
					temp+="\n";
				}else {
					temp+=",";
				}
			}
			System.out.print(temp);
			total.addElement(temp);
			//total_record.add(temp);
			//System.out.println();
			
		}
	}
}
