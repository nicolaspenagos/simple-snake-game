
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

package model;


import java.util.LinkedList;
import java.util.Queue;

import myCollections.Pair;

public class BoardGame implements Runnable {

	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private Square[][] boardGame;
	private boolean move;
	private boolean gameOver;
	private char direction;
	private Queue<Pair<Integer, Integer>> snake;
	private boolean btitle;
	private boolean binstructions;
	private boolean b3;
	private boolean b2;
	private boolean b1;
	private boolean increaseDifficulty;
	private boolean mouse;
	private int kills;
	private Pair<Integer, Integer> enemy;

	// -------------------------------------
	// Constructor
	// -------------------------------------
	public BoardGame() {

		boardGame = new Square[30][30];
		move = false;
		setGameOver(false);
		snake = new LinkedList<Pair<Integer, Integer>>();
		snake.offer(new Pair<Integer, Integer>(14, 14));
		snake.offer(new Pair<Integer, Integer>(13, 14));
		kills = 0;
		
		loadBoardGame();
		btitle = false;
		binstructions = false;
		b3 = false;
		b2 = false;
		b1 = false;
		mouse = false;
		increaseDifficulty = false;
		
		direction = Square.RIGHT;

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

		for (Pair<Integer, Integer> pair : snake) {
			int i = pair.getFirst();
			int j = pair.getSecond();

			boardGame[i][j].setSnake(true);
		}

		Pair<Integer, Integer> pair = snake.peek();
		int i = pair.getFirst();
		int j = pair.getSecond();
		boardGame[i][j].setCurrentColor(Square.DARK_GREEN);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {

			while (move) {

				toMove();
				Thread.sleep(250);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void toMove() {
		
		Queue<Pair<Integer, Integer>> temp = new LinkedList<Pair<Integer, Integer>>();
		
		Pair<Integer, Integer> p =  snake.peek();
		int i = p.getFirst();
		int j = p.getSecond();
		
		switch (direction) {

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
		
		temp.offer(new Pair<Integer, Integer>(i,j));
		
		while(snake.size()>1) {
			p =  snake.poll();
			temp.offer(p);
		}
		
		p = snake.poll();
		i = p.getFirst();
		j = p.getSecond();
		boardGame[i][j].setSnake(false);
		
		snake = temp;
		
		for (Pair<Integer, Integer> pair : snake) {
			i = pair.getFirst();
			j = pair.getSecond();

			boardGame[i][j].setSnake(true);
		}
		
		p = snake.peek();
		i = p.getFirst();
		j = p.getSecond();
		boardGame[i][j].setCurrentColor(Square.DARK_GREEN);

	}

	public void changeDirection(char direction) {

		this.direction = direction;

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

	public boolean isBtitle() {
		return btitle;
	}

	public void setBtitle(boolean btitle) {
		this.btitle = btitle;
	}

	public boolean isBinstructions() {
		return binstructions;
	}

	public void setBinstructions(boolean binstructions) {
		this.binstructions = binstructions;
	}

	public boolean isB3() {
		return b3;
	}

	public void setB3(boolean b3) {
		this.b3 = b3;
	}

	public boolean isB2() {
		return b2;
	}

	public void setB2(boolean b2) {
		this.b2 = b2;
	}

	public boolean isB1() {
		return b1;
	}

	public void setB1(boolean b1) {
		this.b1 = b1;
	}

	public boolean isIncreaseDifficulty() {
		return increaseDifficulty;
	}

	public void setIncreaseDifficulty(boolean increaseDifficulty) {
		this.increaseDifficulty = increaseDifficulty;
	}

	public boolean isMouse() {
		return mouse;
	}

	public void setMouse(boolean mouse) {
		this.mouse = mouse;
	}
}
