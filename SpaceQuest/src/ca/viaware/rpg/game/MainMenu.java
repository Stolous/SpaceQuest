package ca.viaware.rpg.game;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.game.Globals.gState;
import ca.viaware.rpg.utilities.ClickListener;
import ca.viaware.rpg.utilities.OnClickListener;
import ca.viaware.rpg.utilities.SButton;
import ca.viaware.rpg.utilities.TextureHandler;
import ca.viaware.rpg.utilities.TexturedQuad;

public class MainMenu {
	Texture [] backGround = new Texture [3];
	SButton start,options,help,quit;
	TexturedQuad UFO;
public MainMenu(){
	TextureHandler t = new TextureHandler();
	String s = "other/main menu screen000";
	for(int i=0;i<3;i++){
		backGround[i]= t.loadSprite(s+(i+1));
	}
	
	UFO=new TexturedQuad(50, 50, 100, 500, 0, "res/sprites/enemies/UFO/1.png");
	start = new SButton(100,100,200,70);
	options = new SButton(80,200,200,70);
	help = new SButton(80,300,200,70);
	quit = new SButton(100,400,200,70);
	start.setText("Start");
	help.setText("Help");
	quit.setText("Quit");
	options.setText("Options");
}
	
	
	
	

		
		
		
	
	public void render(){
		
				
		
		start.updateOnClickListener(new OnClickListener(start));
		start.AddOnClickListener(new ClickListener() {
			
			@Override
			public void ClickListener(OnClickListener OCL) {
				
				Runnable r = new Runnable() {
					public void run() {
						try {
							Thread.sleep(50L);// this is so that the color shows
												// for a little while
							System.out.println("Switching Gamestate to game");
							Globals.gameState = Globals.gState.GAME;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				};
				Thread t = new Thread(r);
				t.start();
				
				
			}
		});
		
		
		Globals.buttonBuffer.add(quit);
		Globals.buttonBuffer.add(help);
		Globals.buttonBuffer.add(start);
		Globals.buttonBuffer.add(options);
		UFO.update();
	
	}
}
