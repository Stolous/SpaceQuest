package ca.viaware.rpg.utilities;


import java.util.EventObject;

@SuppressWarnings("serial")
public
class OnClickListener extends EventObject {
  public OnClickListener(SButton source) {
    super(source);
    
  }
}

