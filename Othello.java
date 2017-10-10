package Game;

import java.util.Scanner;

public class Othello {
	int player1WON =1;
	int player2WON =2;
	int DRAW =3;
	int INCOMPLETE = 4;
	OthelloBoard board = new OthelloBoard();
	Player player1 = new Player(1);
	Player player2 = new Player(2);
//	Gui graphics = new Gui();
	boolean player1Turn = true; 
	boolean player2Turn = false;
	boolean done;
	Scanner in = new Scanner(System.in);
	public void setNewGame() throws invalidCoordinatesException, invalidMoveException{
		board.board[3][3] = player1.symbol;
		board.board[4][4] = player1.symbol;
		board.board[3][4] = player2.symbol;
		board.board[4][3] = player2.symbol;
		board.printBoard();
		while(gameStatus() == INCOMPLETE){
			if(player1Turn == true){
				System.out.println("player1's turn");
				System.out.println("enter coordinates");
				int x = in.nextInt();
				int y = in.nextInt();
				try{
			int scoreChange = 	board.move(player1.symbol, x, y);
			player1.score += scoreChange;
			player2.score -= scoreChange;
					player1Turn = false;
					player2Turn = true;
				}catch (invalidCoordinatesException e){
					System.out.println("Invalid Coordinates ");
				}
				catch (invalidMoveException m){
					System.out.println("Invalid Move!! Try Again ");
				}
				board.printBoard();
				scorePrint();
//				System.out.println("SCORE ");
//				System.out.println("palyer1 = "+player1.score);
//				System.out.println("palyer2 = "+player2.score);
				}
				
			
			
			if(player2Turn == true){
				System.out.println("player2's turn");
				System.out.println("enter coordinates");
				int x = in.nextInt();
				int y = in.nextInt();
				try{
				int scoreChange =		board.move(player2.symbol, x, y);
				player2.score += scoreChange;
				player1.score -= scoreChange;
					player2Turn = false;
					player1Turn = true;
				}catch (invalidCoordinatesException e){
					System.out.println("Invalid Coordinates ");
				}
				catch (invalidMoveException m){
					System.out.println("Invalid Move!! Try Again ");
				}
					board.printBoard();
					scorePrint();
//					System.out.println("SCORE ");
//					System.out.println("palyer1 = "+player1.score);
//					System.out.println("palyer2 = "+player2.score);
				}
			
		}
		if(gameStatus() == 1)
			System.out.println("Player 1 Wins !!!!!");
		else if(gameStatus() == 2)
			System.out.println("Player 2 Wins !!!!!");
		else if(gameStatus() == 3)
			System.out.println("Match Draw !!");
	}
	
	
	
public void scorePrint(){
	int score1=0,score2=0;
	for(int i=0; i<board.board.length; i++){
		for(int j=0; j<board.board.length; j++){
			if(board.board[i][j] == player1.symbol){
				score1++;
			}		
			else if(board.board[i][j] == player2.symbol){
				score2++;
			}
		}}
	player1.score = score1;
	player2.score = score2;
	System.out.println();
	System.out.println("SCORE");
	System.out.println("PLAYER1 = "+player1.score);
	System.out.println("PLAYER2 = "+player2.score);
	System.out.println();
	return ;
}




	public int gameStatus(){
		for(int i=0; i<board.board.length; i++){
			for(int j=0; j<board.board.length; j++){
				if(board.board[i][j] == 0){
					return 4;
				}		
			}}
			if(player1.score > player2.score)
				return 1;
			else if(player1.score < player2.score)
				return 2;
			else if(player1.score == player2.score)
				return 3;
		
		return 4;
	}


}
