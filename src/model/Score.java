
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

package model;

import java.io.Serializable;

public class Score implements Serializable{
	
	// -------------------------------------
	// Constants
	// -------------------------------------
	public final static String PATH ="data/info.dat";
	
	//-------------------------------------
	// Atributtes 
	//-------------------------------------
	private boolean empty;
	private Time record;
	private int recordKills;

	
	//-------------------------------------
	// Constructor
	//-------------------------------------
	public Score() {
		empty=true;
		recordKills = 0;
	}
	
	public boolean update(String time, int kills) {
		
		boolean updte = false;
		
		String[] parts = time.split(":");
		int minutes = Integer.parseInt(parts[0]);
		int seconds = Integer.parseInt(parts[1]);
		int centiSeconds = Integer.parseInt(parts[1]);
		
		Time tempTime = new Time(minutes, seconds, centiSeconds);
	
		
		if(empty) {
			
			record = tempTime;
			recordKills = kills;
			empty = false;
			updte = true;
			
		}else{
						
			if(kills > recordKills) {
				
				recordKills = kills;
				record = tempTime;
				updte = true;
				
			}else if(kills == recordKills) {
				
				if(tempTime.timeCompareTo(record)==-1) {
					record = tempTime;
					updte = true;
				}
				
			}

		}
		
		return updte;
		
	}
	
	public String record() {
		

		int i = record.getMinutes();
		int j = record.getSeconds();
		int k = record.getCentiSeconds();
		
		if(j<10)
			return ""+i+":0"+j+":"+k;
		else
			return ""+i+":"+j+":"+k;
	
	}
	
	

	// -------------------------------------
	// Getters and Setters
	// -------------------------------------
		
	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public Time getRecord() {
		return record;
	}

	public void setRecord(Time record) {
		this.record = record;
	}
	
	public int getRecordKills() {
		return recordKills;
	}



	



}
