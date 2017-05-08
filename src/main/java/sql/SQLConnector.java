package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLConnector {
	
	ConnectionPool cPool = ConnectionPool.newInstance();
	
	public void sendQuery(String query) {	
		try {
			Connection conn = cPool.getConnection();
			Statement stmt = conn.createStatement();			
			System.out.println("Sending Query: " + query);
			stmt.executeUpdate(query);
			
		} catch (Exception e) {
			System.err.println("sendQuery - Got an exception: ");
			System.err.println(e.getMessage());
		}
	}

	public ResultSet getData(String query) {
		ResultSet rs = null;
		try {
			Connection conn = cPool.getConnection();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
		} catch (Exception e) {
			System.err.println("getData - Got an exception: ");
			System.err.println(e.getMessage());
		}
		
		return rs;
	}	
}
