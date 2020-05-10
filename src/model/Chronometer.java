
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

package model;

import externalThreads.ChronometerThread;

public class Chronometer {
	
	//-------------------------------------
	// Atributtes 
	//-------------------------------------
	private int min;
	private int sec;
	private String time;
	private ChronometerThread chronometerThread;
	
	//-------------------------------------
	// Constructor
	//-------------------------------------
	public Chronometer() {
		
		int min=0;
		int sec=0;
		time="";
		
	}
	
	// -------------------------------------
	// Methods
	// -------------------------------------
	public void startThread() {
		
		chronometerThread = new ChronometerThread(this);
		chronometerThread.setDaemon(true);
		chronometerThread.start();
		
	}
	
	public void changeTime(String time) {
		this.time=time;
	}
	
	public String getTime() {
		return time;
	}
	
	public String toString() {
		
		return min + " " + sec;
				
	}

	
	
}