
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

package externalThreads;

import model.Chronometer;

public class ChronometerThread extends Thread{
	
	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private Chronometer c;
	
	// -------------------------------------
	// Constructor
	// -------------------------------------
	public ChronometerThread(Chronometer c) {
		this.c=c;
	}
	
	// -------------------------------------
	// Methods
	// -------------------------------------
	@Override 
	public void run() {
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 60; j++) {
				for (int j2 = 0; j2 < 100; j2++) {
					if(j<10)
						c.changeTime(i+":0"+j+":"+j2);
					else
						c.changeTime(i+":"+j+":"+j2);
					try {
						sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}