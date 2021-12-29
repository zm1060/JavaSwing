package xingming;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;

public class DButil {
	private final static String username = "sa";
	private final static String password = "1575098153";
	private final static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=MyDB";
	
	public String titles[];
	public static Connection getConnect() {
		Connection connect = null;
		// 加载驱动程序
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("JDBCJTable.getConnect()：加载驱动失败");
			e.printStackTrace();
		}
		System.out.println("JDBCJTable.getConnect()：");
		// 得到与数据库的连接
		try {
			connect = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("JDBCJTable.getConnect()：连接sqlserver数据库失败");
			e.printStackTrace();
		}
		return connect;
	}

}




