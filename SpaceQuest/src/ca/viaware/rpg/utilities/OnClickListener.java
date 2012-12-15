package ca.viaware.rpg.utilities;


import java.util.EventListener;
import java.util.EventObject;

import javax.swing.event.EventListenerList;

import org.lwjgl.input.Mouse;

@SuppressWarnings("serial")
public
class OnClickListener extends EventObject {
  public OnClickListener(Butt source) {
    super(source);
    if(Mouse.getX()>source.getX()){
    	
    }
  }
}

