
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

package ui;

import externalThreads.IntroThread;
import model.BoardGame;
import model.Chronometer;
import model.Square;
import processing.core.PApplet;
import processing.core.PImage;

public class SnakeGame extends PApplet{
	
	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private BoardGame boardGame;
	private PImage title;
	private PImage instructions;
	private PImage _3;
	private PImage _2;
	private PImage _1;
	private PImage increaseDifficulty;
	private IntroThread introThread;
	private Chronometer chronometer;


	// -------------------------------------
	// Main Method
	// -------------------------------------
	public static void main(String[] args){
		// TODO Auto-generated method stub
		PApplet.main("ui.SnakeGame");
	}
	

	// -------------------------------------
	// Processing Methods
	// -------------------------------------
	public void settings() {

		size(600, 600);
		
	}
	
	public void setup() {
		
		boardGame = new BoardGame();
		instructions = loadImage("instImage.png");
		title = loadImage("titleImage.png");
		_3 =  loadImage("3.png");
		_2 =  loadImage("2.png");
		_1 =  loadImage("1.png");
		increaseDifficulty = loadImage("incDifficulty.png"); 
		
		introThread = new IntroThread(boardGame);
		introThread.setDaemon(true);
		introThread.start();
		
		chronometer = new Chronometer();
		
	}
	
	public void draw() {
		
		Square[][] boardGameMatrix = boardGame.getBoardGame();
		
		for (int i = 0; i < boardGameMatrix.length; i++) {
			for (int j = 0; j < boardGameMatrix[0].length; j++) {
				
				Square sq = boardGameMatrix[i][j];
				
				
				if(sq.getCurrentColor()==Square.BLACK) {
					fill(0,0,0);
				}else if(sq.getCurrentColor()==Square.DARK_GRAY) {
					fill(28,28,28);
				}else if(sq.getCurrentColor()==Square.GREEN) {
					fill(228,255,41);
				}else if(sq.getCurrentColor()==Square.DARK_GREEN){
					fill(153,180,0);
				}else {
					fill(255,0,90);
				}
		
				rect(sq.getPosX(), sq.getPosY(), sq.getSize(), sq.getSize());
			}
		}
		
		if(boardGame.isBtitle()) {
			image(title, 0, 0);
		}
		
		if(boardGame.isBinstructions()) {
			image(instructions, 0, 0);
		}
		
		if(boardGame.isB3()) {
			image(_3, 0, 0);
		}
		
		if(boardGame.isB2()) {
			image(_2, 0, 0);
		}
		
		if(boardGame.isB1()) {
			image(_1, 0, 0);
		}
		
		if(boardGame.isIncreaseDifficulty()) {
			image(increaseDifficulty, 0, 0);
		}
		
		
		
	
	
	}
	
	
	public void keyPressed() {
		
		switch(keyCode) {
		
			case 65:
				boardGame.changeDirection(Square.LEFT);
				break;
			case 87:
				boardGame.changeDirection(Square.UP);
				break;
			case 68:
				boardGame.changeDirection(Square.RIGHT);
				break;
			case 83:
				boardGame.changeDirection(Square.DOWN);
				break;
			case 37:
				boardGame.changeDirection(Square.LEFT);
				break;
			case 38:
				boardGame.changeDirection(Square.UP);
				break;	
			case 39:
				boardGame.changeDirection(Square.RIGHT);
				break;
			case 40:
				boardGame.changeDirection(Square.DOWN);
				break;
		
		}
	}


}
