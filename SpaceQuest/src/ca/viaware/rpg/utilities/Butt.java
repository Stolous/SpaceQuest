package ca.viaware.rpg.utilities;

import javax.swing.event.EventListenerList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entity.AbstractEntity;

public class Butt extends AbstractEntity {
	private TexturedQuad Butt;
	protected EventListenerList listenerList = new EventListenerList();

	public Butt(int X,int Y,int width,int height){
		super(X,Y,width,height);
		TextureHandler tt =new TextureHandler();
		Texture t = tt.loadSprite("other/button");
		Butt = new TexturedQuad(width,height,x,y,t);
		
		
		
		
		
	}
	public void AddOnClickListener(ClickListener listener) {
	    listenerList.add(ClickListener.class, listener);
	  }
	
	
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

public void updateOnClickListener(OnClickListener OCL){
	
	if(Mouse.isButtonDown(0)){
		
		Object[] listeners = listenerList.getListenerList();
		for(int i = 0; i < listeners.length; i = i+2){
			System.out.println("updated");
			if(this.intersectsMouse()){
				((ClickListener) listeners[i+1]).ClickListener(OCL);
				
			}
		}}
}
	
	@Override
	public void update(int delta ) {
		
		
		Butt.update();
		
				
			
		}
		
	}
	

