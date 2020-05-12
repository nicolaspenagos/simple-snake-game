
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	private long speed;
	private boolean go;
	private int difficulty;
	private String gameTime;
	private Score score;
	private boolean update;
	private boolean gameEnded;
	private boolean firstTime;

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
		setEnemy();
		speed = 300;

		go = false;
		difficulty = 1;
		gameOver = false;
		gameEnded = false;
		firstTime = true;
		
		loadScore();

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

	public void increaseSpeed() {

		if (difficulty < 4) {
			difficulty++;
		}

		if (difficulty == 2) {
			speed = 150;
		} else if (difficulty == 3) {
			speed = 75;
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {

			while (move) {

				toMove();
				Thread.sleep(speed);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void toMove() {
		System.out.println("");
		System.out.println(snake.size()+" F");
		try {
			Queue<Pair<Integer, Integer>> temp = new LinkedList<Pair<Integer, Integer>>();

			Pair<Integer, Integer> p = snake.peek();
			
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

			int limit = 1;

			if (boardGame[i][j].getCurrentColor() == Square.RED) {

				limit = 0;
				kills++;
				setEnemy();

			}else if(boardGame[i][j].getCurrentColor() == Square.GREEN) {
				
				gameOver = true;
				move = false;
				
			}

			temp.offer(new Pair<Integer, Integer>(i, j));

			int tailI = 0;
			int tailJ = 0;

			while (snake.size() > limit) {

				p = snake.poll();
				temp.offer(p);
				tailI = p.getFirst();
				tailJ = p.getSecond();

			}

			if (!snake.isEmpty()) {

				p = snake.poll();
				i = p.getFirst();
				j = p.getSecond();
				boardGame[i][j].setSnake(false);

			} else {
				if (tailI < 29 && tailI > -2 && tailJ < 29 && tailJ > -2) {

					if (direction == Square.UP) {
						tailJ++;
						boardGame[tailI][tailJ].setSnake(false);
					} else if (direction == Square.DOWN) {
						tailJ--;
						boardGame[tailI][tailJ].setSnake(false);
					} else if (direction == Square.RIGHT) {
						tailI--;
						boardGame[tailI][tailJ].setSnake(false);
					} else {
						tailI++;
						boardGame[tailI][tailJ].setSnake(false);
					}

				}

			}

			snake = temp;
			System.out.println(snake.size()+" S");
			for (Pair<Integer, Integer> pair : snake) {
				i = pair.getFirst();
				j = pair.getSecond();

				boardGame[i][j].setSnake(true);
			}

			p = snake.peek();
			i = p.getFirst();
			j = p.getSecond();
			boardGame[i][j].setCurrentColor(Square.DARK_GREEN);

		} catch (IndexOutOfBoundsException e) {
			
			gameOver = true;
			
		}
		
		if(gameOver && !gameEnded) {
	
			gameEnded = true;
			
		}

	}

	public boolean snakeContains(Pair<Integer, Integer> enemy) {

		boolean contains = false;

		for (Pair<Integer, Integer> pair : snake) {

			if (pair.compareTo(enemy) == 0)
				contains = true;

		}

		return contains;

	}

	public void setEnemy() {

		boolean contains = true;

		while (contains) {

			int i = 0 + (int) (Math.random() * 30);
			int j = 0 + (int) (Math.random() * 30);

			Pair<Integer, Integer> enemy = new Pair<Integer, Integer>(i, j);

			contains = snakeContains(enemy);

			if (!contains) {
				boardGame[i][j].setCurrentColor(Square.RED);
			}
		}

	}

	public void changeDirection(char direction) {
		
		if(this.direction == Square.UP && direction != Square.DOWN && direction != Square.UP) {
			this.direction = direction;
		}else if(this.direction == Square.DOWN && direction != Square.UP && direction != Square.DOWN) {
			this.direction = direction;
		}else if(this.direction == Square.RIGHT && direction != Square.LEFT && direction != Square.RIGHT) {
			this.direction = direction;
		}else if(this.direction == Square.LEFT && direction != Square.RIGHT && direction != Square.LEFT ) {
			this.direction = direction;
		}
	

	}

	public String toStringKills() {

		String _kills = "";
		if (kills > 0) {
			_kills += kills;
		}

		return _kills;

	}
	
	public void loadScore() {
		
		File file = new File(Score.PATH);
		
		if(file.exists()) {
			
				firstTime = false;
				
			  try {
					ObjectInputStream io=new  ObjectInputStream(new FileInputStream(file));
					score=(Score) io.readObject();
					io.close();
				   } catch (IOException e) {
					   // TODO Auto-generated catch block
					   e.printStackTrace();
				   } catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				   }
		}else {
			score = new Score();
			firstTime = true;
		}
		
	}
	
	public void saveScore() {
		
		update = score.update(gameTime, kills);
		
		try {
			
			ObjectOutputStream io = new ObjectOutputStream(new FileOutputStream(new File(Score.PATH)));
			io.writeObject(score);
			io.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public long getSpeed() {
		return speed;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
	}

	public void setGo(boolean go) {
		this.go = go;
	}

	public boolean getGo() {
		return go;
	}

	public String getGameTime() {
		return gameTime;
	}

	public void setGameTime(String gameTime) {
		this.gameTime = gameTime;
	}
	
	public int getKills() {
		return kills;
	}

	public boolean isFirstTime() {
		return firstTime;
	}

	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}
	
	public Score getScore() {
		return score;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}
	
}
