package ca.viaware.rpg.mapeditor;

import javax.swing.JOptionPane;

public class Waypoint {
String name;
String pointTo;
String pointToMap;
int Type;
	
	public void setType(int type){
		Type = type;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setPointTo(String p){
		pointTo = p;
	}
	
	public void setPointToMap(String p){
		pointToMap = p;
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
