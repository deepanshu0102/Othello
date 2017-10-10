package Game;

import java.util.Scanner;

public class Player {
	int playerNumber;
	String name;
	char symbol;
	public int score;
	public Player(int playerNumber) {
		Scanner in = new Scanner(System.in);
		this.playerNumber = playerNumber;
//		System.out.println("Enter name of the player "+playerNumber);
//		name = in.nextLine();
//		System.out.println("Enter symbol of player "+playerNumber);
		if(playerNumber ==1)
		symbol = 'w';
		else symbol = 'b';
		score =2;
	}
}
