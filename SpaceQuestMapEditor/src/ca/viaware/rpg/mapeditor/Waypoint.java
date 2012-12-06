package ca.viaware.rpg.mapeditor;

import javax.swing.JOptionPane;

public class Waypoint {
String name;
String pointTo;
String pointToMap;
int Type;

	public Waypoint(int Type, String pTo, String pToMap, String name){
		this.Type = Type;
		pointTo = pTo;
		pointToMap = pToMap;
		this.name = name;
	}
	
	public void changePoint(String p){
		pointTo = p;
	}
	
	public void changePointMap(String p){
		pointToMap = p;
	}
	
	public int getType(){
		return Type;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPointTo(){
		return pointTo;
	}
	
	public String getPointToMap(){
		return pointToMap;
	}
}
