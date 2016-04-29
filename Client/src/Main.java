import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Main {
	// con must be used to access DB
	static Connection con;
	// CallableStatement is used for stored procedures
	static CallableStatement stmt;

	public static void main(String[] args) throws SQLException {
		// con must be used to access the DB
		con = ConnectURL.makeConnection();
		// next will represent what was last read off the scanner
		String next = null;
		String pass = null;
		Scanner scan = new Scanner(System.in);
		Console console = System.console();

		// pass = new String(console.readPassword("Please enter your password:
		// "));
		System.out.println("Welcome to MushvsGrump");
		System.out.println("If you are a previous user, please press p; if you want to register "
				+ "as a new user, please press r");
		next = scan.next();
		if (next.equals("r")) {
			// If this returns false, it means the registration failed.
			if (!registerNewUser(scan, pass, next)) {
				main(args);
				// return;
			}
		} else if (next.equals("p")) {
			if (!checkUNameAndPass(scan, next, pass)) {
				main(args);
				// return;
			}
		} else {
			main(args);
		}
		System.out.println("Would you like to start a new game? (y/n)");
		next = scan.next();
		if (next.equals("y")) {
			Game g = new Game();
		} else
			System.out.println("Thank you for playing");
		scan.close();
	}

	private static boolean checkUNameAndPass(Scanner scan, String next, String pass) throws SQLException {
		System.out.println("Please enter your username");
		next = scan.next();
		// char[] password = r.readPassword(
		// "Please enter your password", username);
		System.out.println("Please enter your password");
		pass = scan.next();
		// Sanitize DB args
		if (!CheckArg.checkArgValid(next)) {
			System.out.println("Invalid character in username.  ' ; --  not allowed.");
			return false;
		}
		if (!CheckArg.checkArgValid(pass)) {
			System.out.println("Invalid character in password.  ' ; -- not allowed");
			return false;
		} // Now we we can call the SP
		String sql = "{? = call checkUNameAndPass (?, ?)}";
		stmt = con.prepareCall(sql);
		stmt.setString(2, next);
		stmt.setString(3, pass);
		stmt.registerOutParameter(1, Types.INTEGER);
		boolean hadResults = stmt.execute();
		int result = stmt.getInt(1);
		if (result == 1) {
			System.out.println("This username and password combination does not exist");
			return false;
		}
		return true;
		// Process all returned result sets
		// while (hadResults) {
		// ResultSet rs = stmt.getResultSet();
		// System.out.println("rs: " + rs);
		// }
		// process result set

		// hadResults = stmt.getMoreResults();
	}

	private static boolean registerNewUser(Scanner scan, String pass, String next) throws SQLException {
		if (!CheckArg.checkArgValid(next)) {
			System.out.println("Invalid character in username.  ' ; --  not allowed.");
			return false;
		}
		System.out.println("now check pass");
		if (!CheckArg.checkArgValid(pass)) {
			System.out.println("Invalid character in password.  ' ; -- not allowed");
			return false;
		}
		System.out.println("Please enter a username");
		next = scan.next();
		if (!CheckArg.checkArgValid(next)) {
			System.out.println("Invalid character in username.  ' ; --  not allowed.");
			return false;
		}
		String sql = "{? = call checkUName (?)}";
		// con = ConnectURL.makeConnection();
		stmt = con.prepareCall(sql);
		stmt.setString(2, next);
		stmt.registerOutParameter(1, Types.INTEGER);
		boolean hadResults = stmt.execute();
		int result = stmt.getInt(1);
		if (result == 1) {
			System.out.println("The username " + next + " already exists.");
			return false;
		}
		// // Process all returned result sets
		// while (hadResults) {
		// ResultSet rs = stmt.getResultSet();
		// System.out.println("rs: " + rs);
		// }
		// char[] password = r.readPassrword(
		// "Please enter your password", username);
		System.out.println("Please enter your password");
		pass = scan.next();
		sql = "{? = call registerNewUser (?, ?)}";
		stmt = con.prepareCall(sql);
		stmt.setString(2, next);
		stmt.setString(3, pass);
		stmt.registerOutParameter(1, Types.INTEGER);
		if (!CheckArg.checkArgValid(pass)) {
			System.out.println("Invalid character in password.  ' ; -- not allowed");
			return false;
		}
		hadResults = stmt.execute();
		int results = stmt.getInt(1);
		if (results == 1) {
			System.out.println("The username " + next + " already exists.");
			return false;
		}
		System.out.println("Registration successful!");
		return true;
		// // Process all returned result sets
		// while (hadResults) {
		// ResultSet rs = stmt.getResultSet();
		// System.out.println("rs2: " + rs);
		// }
		// Sanitize DB args
		// Now we we can call the SP

		// process result set

		// hadResults = stmt.getMoreResults();
	}

	class ReadMyPassword {
		public char[] readPassword(String format, Object... args) throws IOException {
			if (System.console() != null)
				return System.console().readPassword(format, args);
			return this.readLine(format, args).toCharArray();
		}

		private String readLine(String format, Object... args) throws IOException {
			if (System.console() != null) {
				return System.console().readLine(format, args);
			}
			System.out.print(String.format(format, args));
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
	}

}
