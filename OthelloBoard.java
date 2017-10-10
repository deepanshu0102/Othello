package Game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class OthelloBoard {

	int[][] board = new int[8][8];
	int[] xDir = {-1,-1,-1,0,1,1,1,0};
	int yDir[] = {-1,0,1,1,1,0,-1,-1}; 
	public boolean isCoordinateValid(int x, int y){
		if(x<8 && x>-1 && y<8 && y>-1){
			return true;
		}
		return false;
	}
	
	public void move(char symbol, int x, int y) throws invalidCoordinatesException, invalidMoveException{
		// check
		System.out.println(x+" "+y);
		boolean smallAns = false;
		ArrayList<Integer> xCoordinate = new ArrayList<>();
		ArrayList<Integer> yCoordinate = new ArrayList<>();
		
		if(!isCoordinateValid(x, y)){
			throw new invalidCoordinatesException();
		}
		if(board[x][y] != 0  || !isCoordinateValid(x, y)){
			throw new invalidMoveException();
		}
		
		for(int i = 0;i < xDir.length; i++){
			int stepX = xDir[i];
			int stepY = yDir[i];
			int currentX = x + stepX;
			int currentY = y + stepY;
			
			xCoordinate.clear();
			yCoordinate.clear();
			xCoordinate.add(x);
			yCoordinate.add(y);
		int countOfOtherElements=0;
		if(currentX>7 || currentX <0 || currentY > 7 || currentY < 0)
			continue;
		if(board[currentX][currentY] !=0 && board[currentX][currentY] != symbol){
			countOfOtherElements++;
		} if(countOfOtherElements == 1){
			while((isCoordinateValid(currentX, currentY)) ){
				xCoordinate.add(currentX);
				yCoordinate.add(currentY);
				if(board[currentX][currentY] == 0){
					break;
//					throw new invalidMoveException();
					
				}else if(board[currentX][currentY] == symbol){
			    // conversion
					smallAns =true;
					for(int j=0; j<xCoordinate.size()-1; j++){
						board[xCoordinate.get(j)][yCoordinate.get(j)] = symbol;
						 countOfOtherElements++;
					}
					
					break;
				}
					 currentX = currentX + stepX;
					 currentY = currentY + stepY;
					
				
			}
		}
	
			
			}
		if(!smallAns){
			System.out.println("this one");
			throw new invalidMoveException();
			
		}
		
	}
	
	
	public boolean isMovePossilble(char symbol, int x, int y) {
//		System.out.println(x+" "+y);
		boolean smallAns = false;
		ArrayList<Integer> xCoordinate = new ArrayList<>();
		ArrayList<Integer> yCoordinate = new ArrayList<>();
		
		if(!isCoordinateValid(x, y)){
//			throw new invalidCoordinatesException();
			return false;
		}
		if(board[x][y] != 0  || !isCoordinateValid(x, y)){
//			throw new invalidMoveException();
			return false;
		}
		
		for(int i = 0;i < xDir.length; i++){
			int stepX = xDir[i];
			int stepY = yDir[i];
			int currentX = x + stepX;
			int currentY = y + stepY;
			
			xCoordinate.clear();
			yCoordinate.clear();
			xCoordinate.add(x);
			yCoordinate.add(y);
		int countOfOtherElements=0;
		if(currentX>7 || currentX <0 || currentY > 7 || currentY < 0)
			continue;
		if(board[currentX][currentY] !=0 && board[currentX][currentY] != symbol){
			countOfOtherElements++;
		} if(countOfOtherElements == 1){
			while((isCoordinateValid(currentX, currentY)) ){
				xCoordinate.add(currentX);
				yCoordinate.add(currentY);
				if(board[currentX][currentY] == 0){
					break;
//					throw new invalidMoveException();
					
				}else if(board[currentX][currentY] == symbol){
			    // conversion
					smallAns =true;
//					for(int j=0; j<xCoordinate.size()-1; j++){
//						board[xCoordinate.get(j)][yCoordinate.get(j)] = symbol;
//						
//					}
					
					break;
				}
					 currentX = currentX + stepX;
					 currentY = currentY + stepY;
					
				
			}
			if(smallAns){
				break;
			}
		}
		
			
			}
		if(!smallAns){
//			throw new invalidMoveException();
			return false;
			
		}
		return smallAns;
	}
	
	public int allPossibleMoves(Gui applet,char symbol){
		int count =0;
		for(int i=0; i < 8; i++){
			for(int j=0; j < 8; j++){
				int buttonNumber = i*8 + j;
				if(isMovePossilble(symbol, i, j)){
					applet.button[buttonNumber].setBackground(Color.orange);
					count++;
				}
			}
		}
		
		return count;
	}
	
	
	public void printBoard(Gui applet){
		for(int i=0; i < 8; i++){
			for(int j=0; j < 8; j++){
				int buttonNumber = i*8 + j;
				if(board[i][j] != 0){
				System.out.print(" "+(char)board[i][j]+"  ");
				
				if((char)board[i][j] == 'b'){
					applet.button[buttonNumber].setBackground(Color.black);
				}
				else if((char)board[i][j] == 'w'){
					applet.button[buttonNumber].setBackground(Color.white);
				}
			
				}
				else{
					System.out.print(i+","+j+" ");
					applet.button[buttonNumber].setBackground(Color.green);
//					applet.setVisible(true);
					
				}
			}
			System.out.println();
		}
	}
	
//	public static void main(String[] args) throws invalidCoordinatesException, invalidMoveException {
//		OthelloBoard board = new OthelloBoard();
//		board.board[3][3] = 119;
//		board.board[4][4] = 119;
//		board.board[3][4] = 98;
//		board.board[4][3] = 98;
//		System.out.println(board.isMovePossilble('w', 3,5 ));
//	}
}

