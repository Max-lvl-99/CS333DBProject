import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Player {
	// con must be used to access DB
	Connection con;
	// CallableStatement is used for stored procedures
	CallableStatement stmt;

	public Player(String username, String chName) throws SQLException {
		// Make the connection to SQL Server for queries.
		this.con = ConnectURL.makeConnection();
	}
}
