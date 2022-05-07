package lib;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MYSQL_Connection {
	public static  java.sql.Connection getconnection() throws SQLException {
		java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
		return connection;
	}
}
