
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

import java.io.Serializable;

public class Time implements Serializable{
	
	//-------------------------------------
	// Atributtes 
	//-------------------------------------
	
	private int minutes;
	private int seconds;
	private int centiSeconds;
	
	//-------------------------------------
	// Constructor
	//-------------------------------------
	public Time(int minutes, int seconds, int centiSeconds) {
		
		this.minutes = minutes;
		this.seconds = seconds;
		this.centiSeconds = centiSeconds;
		
	}
	
	public int timeCompareTo(Time time) {
		
		if(minutes > time.getMinutes() ) {
			return 1;
		}else {
			if(minutes == time.getMinutes()) {
				if(seconds > time.getSeconds()) {
					return 1;
				}else {
					if(seconds == time.getSeconds()) {
						if(centiSeconds  > time.getCentiSeconds()) {
							return 1;
						}else {
							if(centiSeconds == time.getCentiSeconds()) {
								return 0;
							}
							return -1;
						}
					}
					return -1;
				}
			}
			return -1;
		}
		
	}
	
	// -------------------------------------
	// Getters and Setters
	// -------------------------------------
	
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	public int getCentiSeconds() {
		return centiSeconds;
	}
	public void setCentiSeconds(int centiSeconds) {
		this.centiSeconds = centiSeconds;
	}
	
	
}
