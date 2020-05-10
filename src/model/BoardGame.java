
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

import java.util.ArrayList;

import myCollections.Pair;

public class BoardGame implements Runnable {

	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private Square[][] boardGame;
	private ArrayList<Pair<Integer, Integer>> snake;
	private boolean move;
	private boolean gameOver;
	private char direction;

	// -------------------------------------
	// Constructor
	// -------------------------------------
	public BoardGame() {

		snake = new ArrayList<Pair<Integer, Integer>>();
		snake.add(new Pair<Integer, Integer>(14, 13));
		//snake.add(new Pair<Integer, Integer>(14, 12));
	
		boardGame = new Square[30][30];
		move = false;
		setGameOver(false);
		loadBoardGame();

	}

	// -------------------------------------
	// Methods
	// -------------------------------------
	public void loadBoardGame() {

		for (int i = 0; i < boardGame.length; i++) {
			for (int j = 0; j < boardGame.length; j++) {

				char color;

				if (i % 2 == 0) {
					if (j % 2 == 0) {
						color = Square.BLACK;
					} else {
						color = Square.DARK_GRAY;
					}
				} else {
					if (j % 2 == 0) {
						color = Square.DARK_GRAY;
					} else {
						color = Square.BLACK;
					}
				}

				Square sq = new Square(color, 20, i * 20, j * 20);
				boardGame[i][j] = sq;
			}
		}

		for (int index = 0; index < snake.size(); index++) {

			int i = snake.get(index).getFirst();
			int j = snake.get(index).getSecond();

			boardGame[i][j].setSnake(true);
			if (index == 0) {
				boardGame[i][j].setCurrentColor(Square.DARK_GREEN);
			}

		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {

			while (move) {

				toMove();
				Thread.sleep(300);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void toMove() {
		
		if(move) {
			
			for(int index = 0; index<snake.size(); index++) {
				
				int i = snake.get(index).getFirst();
				int j = snake.get(index).getSecond();
				
			
				Square sq = boardGame[i][j];
				char currentDirection = sq.getDirection();
				char currentColor = sq.getCurrentColor();
				if(index+1==snake.size())
					sq.setSnake(false);
				
				switch(sq.getDirection()) {
					
					case Square.UP: 
						j--;
						break;
						
					case Square.DOWN: 
						j++;
						break;
						
					case Square.LEFT: 
						i--;
						break;
						
					case Square.RIGHT:
						i++;
						break;
						
				}
			
				if(-1<i && i<boardGame.length && -1<j && j<boardGame[0].length) {
					
					boardGame[i][j].setSnake(true);
					boardGame[i][j].setDirection(currentDirection);
					boardGame[i][j].setCurrentColor(currentColor);
					snake.get(index).setFirst(i);
					snake.get(index).setSecond(j);
					
				}else {
					move = false;
				}
					
			}	
		}
		
		
	}
	
	public void changeDirection(char direction) {
	
		this.direction = direction;
		Square sq = boardGame[snake.get(0).getFirst()][snake.get(0).getSecond()];
		sq.setDirection(direction);
		
	}

	// -------------------------------------
	// Getters and Setters
	// -------------------------------------
	public Square[][] getBoardGame() {
		return boardGame;
	}
	
	public boolean getMove() {
		return move;
	}
	
	public void setMove(boolean move) {
		this.move = move;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}
