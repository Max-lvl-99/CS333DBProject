import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to MushvsGrump");
		System.out.println("Would you like to start a new game? (y/n)");
		String next = scan.next();
		if (next.equals("y")) {
			Game g = new Game();
		} else
			System.out.println("Thank you for playing");
		scan.close();
	}
}
