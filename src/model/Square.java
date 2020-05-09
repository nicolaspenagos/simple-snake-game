
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

public class Square{

	// -------------------------------------
	// Constants
	// -------------------------------------
	public final static char BLACK = 'B';
	public final static char DARK_GRAY = 'D';
	public final static char GREEN = 'G';
	public final static char DARK_GREEN = 'E';
	public final static char RED = 'R';
	public final static char UP = 'u';
	public final static char DOWN = 'd';
	public final static char LEFT = 'l';
	public final static char RIGHT = 'R';

	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private char currentColor;
	private final char color;
	private int size;
	private int posX;
	private int posY;
	private boolean snake;
	private boolean move;
	private char direction;

	// -------------------------------------
	// Constructor
	// -------------------------------------
	public Square(char color, int size, int posX, int posY) {

		this.color = color;
		this.currentColor = color;
		this.size = size;
		this.posX = posX;
		this.posY = posY;
		this.snake = false;
		this.move = false;
		this.direction = UP;

	}

	// -------------------------------------
	// Methods
	// -------------------------------------



	// -------------------------------------
	// Getters and Setters
	// -------------------------------------
	public char getColor() {
		return color;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public char getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(char currentColor) {
		this.currentColor = currentColor;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public boolean isSnake() {
		return snake;
	}

	public void setSnake(boolean snake) {
		this.snake = snake;
		if (snake)
			currentColor = GREEN;
		else
			currentColor = color;
	}

	public boolean isMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}
	
	public char getDirection() {
		return direction;
	}
	
	public void setDirection(char direction) {
		this.direction = direction;
	}

}
