package ca.viaware.rpg.utilities;

public class TimeHandler {

	private static int minutes = 0, seconds = 0, milliseconds = 0;
	
	public static void updateTime(int delta){
		milliseconds += delta;
		
		if (milliseconds >= 1000){
			seconds++;
			milliseconds -= 1000;
		}
		
		if (seconds >= 60){
			minutes++;
			seconds -= 60;
		}
	}
	
	public static int getMins(){
		return minutes;
	}
	
	public static int getSecs(){
		return seconds;
	}
	
	public static int getMillSec(){
		return milliseconds;
	}
}
