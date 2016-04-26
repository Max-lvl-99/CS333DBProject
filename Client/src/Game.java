
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
	
	public Game() {
		// Make the connection to SQL Server for queries.
		ConnectURL.makeConnection();
		scan = new Scanner(System.in);
		System.out.println("Please enter the name of your character.");

		String name = scan.next();
		character = new Player(name);
		Scenario checkpoint = new Scenario(0, 1);
		current = checkpoint;

		main();

	}

	public void main() {
		while (true) {
			act = getActions();
			System.out.println("Your choices are: " + stringActions());
			String c = scan.next();
			handleActions(c);
		}
	}

	public ArrayList<Character> getActions() {
		ArrayList<Character> actions = new ArrayList<Character>();
		actions.addAll(current.getActions());
		return actions;
	}

	public String stringActions() {
		StringBuilder sb = new StringBuilder();
		for (Character c : act) {
			sb.append(c);
			sb.append(' ');
		}
		return sb.toString();
	}

	public void handleActions(String c) {
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
		}
	}

}
