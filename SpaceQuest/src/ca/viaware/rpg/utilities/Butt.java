package ca.viaware.rpg.utilities;

import javax.swing.event.EventListenerList;

public class Butt {
	private TexturedQuad Butt;
	protected EventListenerList listenerList = new EventListenerList();

	public Butt(int X,int Y,int width,int height){
		
		
		
		
		
		
		
		
	}
	public void AddOnClickListener(ClickListener listener) {
	    listenerList.add(ClickListener.class, listener);
	  }
	
	
	public void update(){
		
	
	}
	

}
