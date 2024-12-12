package sample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/**
	 * 得到数据库连接
	 */
	public Connection getConn() {

		// 注册驱动
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 获得数据库连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/woolen_factory_db?&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC","root","123456");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		// 返回连接
		return conn;
		
	}




}


