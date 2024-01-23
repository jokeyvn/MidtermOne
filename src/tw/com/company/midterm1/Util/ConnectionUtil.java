package tw.com.company.midterm1.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static Connection conn;
	public static Connection createSQLConn() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String urlstr = "jdbc:sqlserver://localhost:1433;databaseName=Midterm;"+"user=jokeyvn857;password=slqy3b277x;encrypt=true;trustServerCertificate=true";
		conn = DriverManager.getConnection(urlstr);
		System.out.print("建立SQL連線");
		return conn;
	}
	
	public static void closeSQLConn() throws SQLException {
		if(conn != null) {
			conn.close();
		}
		System.out.println("關閉SQL連線");
	}
}
