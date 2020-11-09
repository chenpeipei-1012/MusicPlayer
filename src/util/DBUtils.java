package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 工具类：封装了对数据库的连接和关闭连接操作
 * @author 华为MateBook 13
 *
 */
public class DBUtils {
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			// 步骤1：加载MySQL的JDBC驱动器
			Class.forName("com.mysql.jdbc.Driver");
			
			// 步骤2：注册MySQL的JDBC驱动器
			// MySQL可以省略这步，因为Driver驱动器类的static方法块中已经注册过了
			
			// 步骤3：建立与数据库之间的连接
			String url = "jdbc:mysql://localhost:3306/music_player";
			conn = DriverManager.getConnection(url, "root", "123456");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void closeConnection(Connection conn){
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
