
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
import processing.core.PFont;
import processing.core.PImage;

public class SnakeGame extends PApplet {

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
	private PImage mouse;
	private PImage gameOver;
	private PImage newRecord;
	private PFont camingoCode;
	private IntroThread introThread;
	private Chronometer chronometer;
	private boolean stop;

	// -------------------------------------
	// Main Method
	// -------------------------------------
	public static void main(String[] args) {
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
		_3 = loadImage("3.png");
		_2 = loadImage("2.png");
		_1 = loadImage("1.png");
		increaseDifficulty = loadImage("incDifficulty-01.png");
		mouse = loadImage("mouse.png");
		gameOver = loadImage("gameOverImage.png");
		camingoCode = loadFont("CamingoCode-Bold-22.vlw");
		newRecord = loadImage("newRecord.png");
		chronometer = new Chronometer();
		introThread = new IntroThread(boardGame, chronometer);
		introThread.setDaemon(true);
		introThread.start();
		stop = false;
	

	}

	public void draw() {

		Square[][] boardGameMatrix = boardGame.getBoardGame();

		for (int i = 0; i < boardGameMatrix.length; i++) {
			for (int j = 0; j < boardGameMatrix[0].length; j++) {

				Square sq = boardGameMatrix[i][j];

				if (sq.getCurrentColor() == Square.BLACK) {
					fill(0, 0, 0);
				} else if (sq.getCurrentColor() == Square.DARK_GRAY) {
					fill(28, 28, 28);
				} else if (sq.getCurrentColor() == Square.GREEN) {
					
					if(boardGame.getGo()) {
						fill(228, 255, 41);
					}else {
						
						if(sq.getColor() == Square.BLACK) {
							fill(0, 0, 0);
						}else {
							fill(28, 28, 28);
						}
					}
				} else if (sq.getCurrentColor() == Square.DARK_GREEN && boardGame.getGo()) {
					if(boardGame.getGo()) {
						fill(153, 180, 0);
					}else {
						
						if(sq.getColor() == Square.BLACK) {
							fill(0, 0, 0);
						}else {
							fill(28, 28, 28);
						}
						
					}
					
				} else{
					
					if(boardGame.getGo()) {
						fill(255, 0, 90);
					}else {
						
						if(sq.getColor() == Square.BLACK) {
							fill(0, 0, 0);
						}else {
							fill(28, 28, 28);
						}
						
					}
				}

				rect(sq.getPosX(), sq.getPosY(), sq.getSize(), sq.getSize());
			}
		}

		if (boardGame.isBtitle()) {
			image(title, 0, 0);
		}

		if (boardGame.isBinstructions()) {
			image(instructions, 0, 0);
		}

		if (boardGame.isB3()) {
			image(_3, 0, 0);
		}

		if (boardGame.isB2()) {
			image(_2, 0, 0);
		}

		if (boardGame.isB1()) {
			image(_1, 0, 0);
		}

		if (boardGame.isIncreaseDifficulty()) {
			image(increaseDifficulty, 0, 0);
		}

		if (boardGame.isMouse()&&!boardGame.isGameOver()) {
			image(mouse, 17, 19);
		}

		if(!boardGame.isGameOver()) {
			
			fill(255);
			textSize(32);
			textFont(camingoCode);
			text(chronometer.getTime(), 498, 38);
			text(boardGame.toStringKills(), 55, 38);
			
		}else {
			
			fill(255);
			textSize(32);
			textFont(camingoCode);
			
			if(boardGame.isFirstTime() || boardGame.isUpdate()) {
				image(newRecord, 0, 0);
			}else {
				
				image(gameOver, 0, 0);
		
			}
			
			
			if(!stop) {
				
				stop = true;
				boardGame.setGameTime(chronometer.getTime());
				boardGame.saveScore();
				
			}
			
			
			text(boardGame.getKills()+"  "+boardGame.getGameTime(), 303, 340);
			
			if(!boardGame.isFirstTime() && !boardGame.isUpdate())
				text(boardGame.getScore().getRecordKills()+"  "+boardGame.getScore().record(), 312, 377);
		}
		
		

	}

	public void keyPressed() {

		switch (keyCode) {

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
			
		case 32:
			boardGame.increaseSpeed();
			break;

		}
	}

}
