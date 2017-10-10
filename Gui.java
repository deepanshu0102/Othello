package Game;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;


public class Gui extends JFrame implements ActionListener{
	Othello game = new Othello();

	private static final long serialVersionUID = 1L;
	JPanel[] row = new JPanel[9];
 public   JButton[] button = new JButton[64];
    int[] dimW = {300,45,100,90};
    int[] dimH = {50, 40};
    Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
    Dimension regularDimension = new Dimension(dimW[1], dimH[1]);

    boolean[] function = new boolean[20];
    double[] temporary = {0, 0};
    JTextArea display = new JTextArea(5,20);
    Font font = new Font("Times new Roman", Font.BOLD, 14);
    
    Gui() throws invalidCoordinatesException, invalidMoveException {
        super("Othello");
        setDesign();
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(9,9);
        setLayout(grid);
        
//        for(int i = 0; i < 4; i++)
//            function[i] = false;
        
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
        FlowLayout f2 = new FlowLayout(FlowLayout.CENTER,1,1);
        for(int i = 0; i < 9; i++)
            row[i] = new JPanel();
        row[0].setLayout(f1);
        for(int i = 1; i < 9; i++)
            row[i].setLayout(f2);
        
        for(int i = 0; i < 64; i++) {
            button[i] = new JButton();
//            button[i].setText("");
            button[i].setBackground(Color.green);
            button[i].setFont(font);
            button[i].addActionListener(this);
        }
        button[27].setBackground(Color.white);
        button[28].setBackground(Color.black);
        button[35].setBackground(Color.black);
        button[36].setBackground(Color.white);
        display.setFont(font);
        display.setText("    Player 1 - White            Player 2 - Black");
        display.setEditable(true);
        display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        display.setPreferredSize(displayDimension);
        
        for(int i = 0; i < 64; i++)
            button[i].setPreferredSize(regularDimension);
//        for(int i = 14; i < 18; i++)
//            button[i].setPreferredSize(rColumnDimension);
//        button[18].setPreferredSize(zeroButDimension);
        
        row[0].add(display);
        add(row[0]);
        
        for(int i = 0; i < 8; i++)
            row[1].add(button[i]);
//        row[1].add(button[14]);
        add(row[1]);
        
        for(int i = 8; i < 16; i++)
            row[2].add(button[i]);
//        row[2].add(button[15]);
        add(row[2]);
        
        for(int i = 16; i < 24; i++)
            row[3].add(button[i]);
//        row[3].add(button[16]);
        add(row[3]);
        
//        row[4].add(button[18]);
        for(int i = 24; i < 32; i++)
            row[4].add(button[i]);
//        row[4].add(button[17]);
        add(row[4]);
        
        for(int i = 32; i < 40; i++)
            row[5].add(button[i]);
        add(row[5]);
        
        for(int i = 40; i < 48; i++)
            row[6].add(button[i]);
        add(row[6]);
        
        for(int i = 48; i < 56; i++)
            row[7].add(button[i]);
        add(row[7]);
        
        for(int i = 56; i < 64; i++)
            row[8].add(button[i]);
        add(row[8]);
        board.allPossibleMoves(this, player1.symbol);
        setVisible(true);
    }
    
    public void clear() {
        try {
            display.setText("");
            for(int i = 0; i < 4; i++)
                function[i] = false;
            for(int i = 0; i < 2; i++)
                temporary[i] = 0;
        } catch(NullPointerException e) {  
        }
    }
    
    
    
    public final void setDesign() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception e) {   
        }
    }
    
   
    Coordinates point = new Coordinates();
   
    
    
    public void actionPerformed(ActionEvent ae) {
    	for(int i=0; i<64; i++){
    		if(ae.getSource() == button[i]){
    			point.y = i%8;
    			point.x = i/8;	
    		}	
    	}
    	
    	
//******************************************************************************************
    	
    	Scanner in = new Scanner(System.in);
//    	public void setNewGame() throws invalidCoordinatesException, invalidMoveException{
    
//    		board.printBoard(this);
    		System.out.println();
    		while(gameStatus() == INCOMPLETE){
    			if(player1Turn == true){
    				clear();
    				display.setText("player2's turn");
    				System.out.println("player1's turn");
//    				System.out.println("enter coordinates");
    				int x = point.x;
    				int y = point.y;
    				try{
//    					board.allPossibleMoves(this, player1.symbol);
    			board.move(player1.symbol, x, y);
    		
    					player1Turn = false;
    					player2Turn = true;
    				}catch (invalidCoordinatesException e){
    					System.out.println("Invalid Coordinates ");
    					clear();
    					display.setText("Invalid");
    				}
    				catch (invalidMoveException m){
    					clear();
    					display.setText("Invalid Move !! Try Again ");
//    					board.allPossibleMoves(this, player1.symbol);
    					
    				}
    				board.printBoard(this);
    				
    					if(player2Turn){
						int possibleMoves = board.allPossibleMoves(this, player2.symbol);
//						if(possibleMoves == 0)
//						display.setText("No player2 moves possible so player1's turn");
//						player1Turn = true;
//    					player2Turn = false;
//    					board.allPossibleMoves(this, player1.symbol);
						}
    					else{
    						board.allPossibleMoves(this, player1.symbol);
    					}
    				System.out.println();
    				
    				scorePrint();
//    				System.out.println("SCORE ");
//    				System.out.println("palyer1 = "+player1.score);
//    				System.out.println("palyer2 = "+player2.score);
    				if(gameStatus() == INCOMPLETE)
        				return;
    				}
    				
    			
    			
    			if(player2Turn == true){
    				clear();
    				display.setText("Player 1's Turn");
    				System.out.println("player2's turn");
//    				System.out.println("enter coordinates");
    				
    				int x = point.x;
    				int y = point.y;
    				try{
//    					board.allPossibleMoves(this, player2.symbol);
    				board.move(player2.symbol, x, y);
    				
    					player2Turn = false;
    					player1Turn = true;
    				}catch (invalidCoordinatesException e){
    					System.out.println("Invalid Coordinates ");
    				}
    				catch (invalidMoveException m){
    					clear();
    					display.setText("Invalid Move !! Try Again");
    					board.allPossibleMoves(this, player2.symbol);
    				
    				}
    					board.printBoard(this);
    					
    						if(player1Turn){
    							int possibleMoves = board.allPossibleMoves(this, player1.symbol);
//    						if(possibleMoves == 0)
//    						display.setText("No player1 moves possible so player2's turn");
//    						player2Turn = true;
//        					player1Turn = false;
//        					board.allPossibleMoves(this, player2.symbol);
    						}
    						else
    							board.allPossibleMoves(this, player2.symbol);
						
    					System.out.println();
    					
    					scorePrint();
//    					System.out.println("SCORE ");
//    					System.out.println("palyer1 = "+player1.score);
//    					System.out.println("palyer2 = "+player2.score);
    					if(gameStatus() == INCOMPLETE)
    	    				return;
    				}
    			
    		}
    		if(gameStatus() == 1){
    			display.setText("Player 1 Wins !!!!!");
    			System.out.println("Player 1 Wins !!!!!");
    		}
    		else if(gameStatus() == 2){
    			display.setText("Player 2 Wins !!!!!");
    			System.out.println("Player 2 Wins !!!!!");
    		}
    		else if(gameStatus() == 3){
    			display.setText("MATCH DRAW!!");
    			System.out.println("Match Draw !!");
    		}
//    	}
    	
    

    	
    }
	

	int player1WON =1;
	int player2WON =2;
	int DRAW =3;
	int INCOMPLETE = 4;
	OthelloBoard board = new OthelloBoard();
	Player player1 = new Player(1);
	Player player2 = new Player(2);
	
	boolean player1Turn = true; 
	boolean player2Turn = false;
	boolean done;
	{
	board.board[3][3] = player1.symbol;
	board.board[4][4] = player1.symbol;
	board.board[3][4] = player2.symbol;
	board.board[4][3] = player2.symbol;
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
//    	clear();
//    	display.setText("SCORE-           \n  PLAYER1 = "+player1.score+"\nPLAYER2 = "+player2.score);
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
