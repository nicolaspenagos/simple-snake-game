
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
	private String time;
	private ChronometerThread chronometerThread;
	
	//-------------------------------------
	// Constructor
	//-------------------------------------
	public Chronometer() {
		
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
	
	// -------------------------------------
	// Getters and Setters
	// -------------------------------------
		
	public String getTime() {
		return time;
	}
		
	
}