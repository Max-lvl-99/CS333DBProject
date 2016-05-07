
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	Player character;
	Scanner scan;
	char input;
	Player[] enemies;
	Scenario checkpoint;
	Scenario current;
	ArrayList<Character> act;
	// Needs to be referenced to access CD
	Connection con;
	// CallableStatement is used for stored procedures
	CallableStatement stmt;
	boolean showExplanations = true;
	StringBuilder explanations = new StringBuilder();

	public Game(Player c) throws SQLException {
		// Make the connection to SQL Server for queries.
		character = c;
		con = ConnectURL.makeConnection();
		scan = new Scanner(System.in);

		// String name = scan.next();
		// character = new Player(c);
		Scenario checkpoint = new Scenario(0, 1);
		current = checkpoint;

		main();
	}

	public void main() throws SQLException {
		while (true) {
			act = getActions();
			System.out.println("Health: " + this.character.getHP() + "/" + this.character.getMaxHP() + " Floor: "
					+ character.getFloor() + " Room: " + character.getRoom());
			// System.out.println("l: go left; r: go right; f: go forward; "
			// + "i: view inventory; d: delete from inventory; h: help; e:
			// exit");
			String choices = stringActions();
			if (showExplanations)
				System.out.println(explanations.toString());
			System.out.println("Your choices are: " + choices);
			String c = scan.next();
			handleActions(c);
		}
	}

	public ArrayList<Character> getActions() {
		ArrayList<Character> actions = new ArrayList<Character>();
		actions.addAll(current.getActions());
		actions.add('h');
		return actions;
	}

	public String stringActions() {
		StringBuilder sb = new StringBuilder();
		explanations = new StringBuilder();
		for (Character c : act) {
			sb.append(c);
			explanations = addExplanation(explanations, c);
			sb.append(' ');
		} // Make the option to show or hide explanations last
		sb.append('s');
		explanations = addExplanation(explanations, 's');
		sb.append(' ');
		return sb.toString();
	}

	/**
	 * Adds the appropriate explanation for the character the user can hit for
	 * this round.
	 * 
	 * @param explanations2
	 * @param c
	 */
	private StringBuilder addExplanation(StringBuilder ex, Character c) {
		// System.out.println("in addExplanations c: " + c + " ex: " + ex);
		if (c.equals('f'))
			ex.append("Press f then hit enter to move forward to the next room.  ");
		else if (c.equals('s'))
			ex.append("Press s then hit enter to hide these explanations.  ");
		else if (c.equals('l'))
			ex.append("Press l then hit enter to move to the room that's left of this room.  ");
		else if (c.equals('r'))
			ex.append("Press r then hit enter to move to the room that's right of this room.  ");
		else if (c.equals('b'))
			ex.append("Press b then hit enter to go back to the room you came from. ");
		else if (c.equals('h'))
			ex.append("Press h then hit enter to see the help screen.  ");
		else if (c.equals('i'))
			ex.append("Press i then hit enter to see your inventory of items and weapons.  ");
		else if (c.equals('d'))
			ex.append("Press d then hit enter to delete items from your inventory.  ");
		else if (c.equals('0'))
			ex.append("Press 0 then hit enter to put an item you found into your inventory.  ");
		else if (c.equals('1'))
			ex.append("Press 1 then hit enter to put an item you found into your inventory.  ");
		else if (c.equals('2'))
			ex.append("Press 2 then hit enter to put an item you found into your inventory.  ");
		else if (c.equals('3'))
			ex.append("Press 3 then hit enter to battle an enemy!  ");
		else if (c.equals('4'))
			ex.append("Press 4 then hit enter to battle an enemy!  ");
		return ex;
	}

	/**
	 * Handles when the user enters a character and presses the enter key.
	 * 
	 * @param c
	 * @throws SQLException
	 */
	public void handleActions(String c) throws SQLException {
		ResultSet res;
		StringBuilder str;
		String sql;
		switch (c) {
		case "f":
			if (current.forwardScen != null)
				current = current.forwardScen;
			else
				current = new Scenario(0, 1, 'f', current);
			break;
		case "b":
			if (current.backScen != null)
				current = current.backScen;
			else
				current = new Scenario(0, 1, 'b', current);
			break;
		case "l":
			if (current.leftScen != null)
				current = current.leftScen;
			else
				current = new Scenario(0, 1, 'l', current);
			break;
		case "r":
			if (current.rightScen != null)
				current = current.rightScen;
			else
				current = new Scenario(0, 1, 'r', current);
			break;
		case "h":
			helpString();
			break;
		case "s":
			showExplanations = !showExplanations;
			break;
		case "i":
			sql = "{call [Display Inventory] (?)}";
			stmt = con.prepareCall(sql);
			stmt.setInt(1, Player.inID);
			res = stmt.executeQuery();
			str = new StringBuilder();
			while (res.next()) {
				str.append(res.getString(1));
				str.append(" (");
				str.append(res.getString(2));
				str.append("); ");
			}
			System.out.println(str.toString());
			sql = "{call displayWeapons (?)}";
			stmt = con.prepareCall(sql);
			stmt.setInt(1, this.character.getChID());
			res = stmt.executeQuery();
			str = new StringBuilder();
			while (res.next()) {
				str.append(res.getString(1));
				str.append(" (");
				str.append(res.getString(2));
				str.append("); ");
			}
			System.out.println(str.toString());
			break;
		// DONE: Ryan make the inventory show up here
		case "d":
			sql = "{call [UpdateItemInInventory] (?,?,?)}";
			stmt = con.prepareCall(sql);
			stmt.setInt(1, Player.inID);

			System.out.println("Insert item ID to update");
			int i = scan.nextInt();
			stmt.setInt(2, i);

			System.out.println("Insert amount to update (negative for removal)");
			i = scan.nextInt();
			stmt.setInt(3, i);

			stmt.executeUpdate();

			System.out.println(i + " items added!");
			break;
		// DONE: Ryan allow the user to delete item(s) here
		case "u":
			// TODO: Next Milestone -- Use items
		case "0":
			if (current.interactibles.get(0).type == 0) {
				character.insertIntoInventory(current.interactibles.get(0).id, "I");
			} else {
				character.insertIntoInventory(current.interactibles.get(0).id, "W");
			}
			current.interactibles.remove(0);
			break;
		case "1":
			if (current.interactibles.get(1).type == 0) {
				character.insertIntoInventory(current.interactibles.get(1).id, "I");
			} else {
				character.insertIntoInventory(current.interactibles.get(1).id, "W");
			}
			current.interactibles.remove(1);
			break;
		case "2":
			if (current.interactibles.get(2).type == 0) {
				character.insertIntoInventory(current.interactibles.get(2).id, "I");
			} else {
				character.insertIntoInventory(current.interactibles.get(2).id, "W");
			}
			current.interactibles.remove(2);
			break;
		case "3":
			new Battle(character, current.enemies.get(0));
			if (character.getHP() < 0) {
				System.out.println("Starting from checkpoint.");
				current = checkpoint;
			}
			break;
		case "4":
			new Battle(character, current.enemies.get(1));
			if (character.getHP() < 0) {
				System.out.println("Starting from checkpoint.");
				current = checkpoint;
			}
			break;
		case "e":
			scan.close();
			System.exit(0);
		}
	}

	/**
	 * Used to show help for specific commands.
	 * 
	 * @throws SQLException
	 */
	public void helpString() throws SQLException {
		System.out.println("Which command do you need information on?");
		String c = scan.next();
		CallableStatement cs;
		String b;
		ResultSet res;
		StringBuilder str;
		String sql;
		switch (c) {
		case "f":
			System.out.println("Go forward");
			break;
		case "b":
			System.out.println("Go back");
			break;
		case "l":
			System.out.println("Go left");
			break;
		case "r":
			System.out.println("Go right");
			break;
		case "h":
			System.out.println("This is the help function");
			break;
		case "0":
			if (current.interactibles.get(0).type == 0) {
				cs = con.prepareCall("{call get_Item_Name(?,?)}");
			} else {
				cs = con.prepareCall("{call get_Weapon_Name(?,?)}");
			}
			cs.setInt(1, current.interactibles.get(0).id);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.execute();
			b = cs.getString(2);
			System.out.println(b);
			break;
		case "1":
			if (current.interactibles.get(1).type == 0) {
				cs = con.prepareCall("{call get_Item_Name(?,?)}");
			} else {
				cs = con.prepareCall("{call get_Weapon_Name(?,?)}");
			}
			cs.setInt(1, current.interactibles.get(1).id);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.execute();
			b = cs.getString(2);
			System.out.println(b);
			break;
		case "2":
			if (current.interactibles.get(2).type == 0) {
				cs = con.prepareCall("{call get_Item_Name(?,?)}");
			} else {
				cs = con.prepareCall("{call get_Weapon_Name(?,?)}");
			}
			cs.setInt(1, current.interactibles.get(2).id);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.execute();
			b = cs.getString(2);
			System.out.println(b);
			break;

		case "3":
			System.out.println(current.enemies.get(0).name);
			break;
		case "4":
			System.out.println(current.enemies.get(1).name);
			break;
		}
	}

}
