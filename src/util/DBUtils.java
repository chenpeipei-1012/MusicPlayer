package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * �����ࣺ��װ�˶����ݿ�����Ӻ͹ر����Ӳ���
 * @author ��ΪMateBook 13
 *
 */
public class DBUtils {
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			// ����1������MySQL��JDBC������
			Class.forName("com.mysql.jdbc.Driver");
			
			// ����2��ע��MySQL��JDBC������
			// MySQL����ʡ���ⲽ����ΪDriver���������static���������Ѿ�ע�����
			
			// ����3�����������ݿ�֮�������
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
