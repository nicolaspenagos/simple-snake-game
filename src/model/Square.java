package model;

public class Square {
	
	//------------------------------------- 
	// Constants 
	//------------------------------------- 
	public final static char BLACK = 'B';
	public final static char DARK_GRAY = 'D';
	public final static char GREEN = 'G';
	public final static char RED = 'R';
	
	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private char currentColor;
	private final char color;
	private int size;
	private int posX;
	private int posY;
	
	//-------------------------------------
	// Constructor
	//-------------------------------------
	public Square(char color, int size, int posX, int posY) {
		
		this.color        = color;
		this.currentColor = color;
		this.size         = size;
		this.posX         = posX;
		this.posY         = posY; 
		
		
	}
	
	//-------------------------------------
	// Methods
	//-------------------------------------

	//-------------------------------------
	// Getters and Setters
	//-------------------------------------
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
	

}
