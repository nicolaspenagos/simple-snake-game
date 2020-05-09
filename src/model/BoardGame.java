
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

public class BoardGame {
	
	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private Square[][] boardGame;
	
	//-------------------------------------
	// Constructor
	//-------------------------------------
	public BoardGame() {
		
		boardGame = new Square[30][30];
		loadBoardGame();
		
	}
	
	//-------------------------------------
	// Methods
	//-------------------------------------
	public void loadBoardGame() {
		
		for (int i = 0; i < boardGame.length; i++) {
			for (int j = 0; j < boardGame.length; j++) {
				
				char color;
				
				if(i%2 == 0) {
					if(j%2 == 0) {
						color = Square.BLACK;
					}else {
						color = Square.DARK_GRAY;
					}
				}else {
					if(j%2 == 0) {
						color = Square.DARK_GRAY;
					}else {
						color = Square.BLACK;
					}
				}
				
				Square sq = new Square(color, 20, i*20, j*20);
				boardGame[i][j] = sq;
			}
		}
		
	}
	
	//-------------------------------------
	// Getters and Setters
	//-------------------------------------
	public Square[][] getBoardGame(){
		return boardGame;
	}
}
