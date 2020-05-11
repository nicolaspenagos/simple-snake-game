
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

package externalThreads;

import model.BoardGame;
import model.Chronometer;

public class IntroThread extends Thread{
	
	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private BoardGame boardGame;
	private Chronometer chronometer;
	
	// -------------------------------------
	// Constructor
	// -------------------------------------
	public IntroThread(BoardGame boardGame,Chronometer chronometer) {
		
		this.boardGame  = boardGame;
		this.chronometer = chronometer;
		
	}
	
	// -------------------------------------
	// Methods
	// -------------------------------------
	@Override 
	public void run() {
		
		try {
			
			boardGame.setBtitle(true);
			sleep(3000);
			boardGame.setBtitle(false);
			boardGame.setBinstructions(true);
			sleep(6000);
			boardGame.setBinstructions(false);
			boardGame.setB3(true);
			sleep(1000);
			boardGame.setB3(false);
			boardGame.setB2(true);
			sleep(1000);
			boardGame.setB2(false);
			boardGame.setB1(true);
			sleep(1000);
			boardGame.setB1(false);
			boardGame.setIncreaseDifficulty(true);
			boardGame.setMove(true);
			boardGame.setMouse(true);
			boardGame.setGo(true);
			new Thread(boardGame).start();
			chronometer.startThread();
			sleep(8000);
			boardGame.setIncreaseDifficulty(false);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
