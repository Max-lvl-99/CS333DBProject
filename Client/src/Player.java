import ConnectDB.ConnectURL;

public class Player {

	public Player(String name) {
		// Make the connection to SQL Server for queries.
		ConnectURL.makeConnection();
	}
}
