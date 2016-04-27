import java.sql.Connection;
import java.sql.SQLException;

public class Player {
	// con must be used to access DB
	Connection con;

	public Player(String name) throws SQLException {
		// Make the connection to SQL Server for queries.
		con = ConnectURL.makeConnection();
	}
}
