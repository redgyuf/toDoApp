package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

public class SQLConnector {

	ConnectionPool cPool = ConnectionPool.newInstance();

	public void runStartingScript() throws FileNotFoundException {
		Connection conn = cPool.getConnection();
		ScriptUtils.executeSqlScript(conn, new EncodedResource(new FileSystemResource("C:/Users/Gál Dániel/Desktop/CodeCool/web_tw_project/toDoApp/src/main/java/sql/createDatabase.sql")));
	}

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
