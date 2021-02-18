package de.hftstuttgart.gruppe5.dbAccess;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/**
 * 
 * @author Lukas Ringle
 * @author Stefan Binninger
 * @author Sven Turowski
 * @author Paul Helstab
 * @author Lino Schmidt
 * @author Sebastian Mueller
 * 
 */
public class DBConnector {
	private static Connection conn = null;
	private static String url ="jdbc:mysql://193.196.143.168:3306/";
	private static String dbName = "oh9w_gruppe5";
	private static String driver ="com.mysql.jdbc.Driver";
	private static String username ="oh9w_gruppe5";
	
	/**
	 * This method creates the database connection
	 */
	public static void init(String password) {
		try {
			Class.forName(driver).newInstance();
			conn = (Connection) DriverManager
					.getConnection(url+dbName, username, password);
			System.out.println("Connected to Database");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * this method will disconnect the database connection
	 */
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getCon() {
		return DBConnector.conn;
	}
}
