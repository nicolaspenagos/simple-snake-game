
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package ui;

import model.BoardGame;
import model.Square;
import processing.core.PApplet;

public class SnakeGame extends PApplet{
	
	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private BoardGame boardGame;

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
	}
	
	public void draw() {
		
		Square[][] boardGameMatrix = boardGame.getBoardGame();
		
		for (int i = 0; i < boardGameMatrix.length; i++) {
			for (int j = 0; j < boardGameMatrix[0].length; j++) {
				
				Square sq = boardGameMatrix[i][j];
				
				noStroke();
				if(sq.getCurrentColor()==Square.BLACK) {
					fill(0,0,0);
				}else if(sq.getCurrentColor()==Square.DARK_GRAY) {
					fill(41,41,41);
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
	}
	
	public void mousePressed() {
		
		boardGame.setMove(true);
		new Thread(boardGame).start();
		
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
