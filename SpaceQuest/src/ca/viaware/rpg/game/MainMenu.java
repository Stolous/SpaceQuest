package ca.viaware.rpg.game;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.game.Globals.gState;
import ca.viaware.rpg.utilities.ClickListener;
import ca.viaware.rpg.utilities.OnClickListener;
import ca.viaware.rpg.utilities.SButton;
import ca.viaware.rpg.utilities.TextureHandler;
import ca.viaware.rpg.utilities.TexturedQuad;

public class MainMenu {
	private int movementstage = 0;
	Texture [] backGround = new Texture [3];
	SButton start,options,help,quit;
	TexturedQuad UFO;
	private int location=0;
public MainMenu(){
	TextureHandler t = new TextureHandler();
	String s = "other/main menu screen000";
	for(int i=0;i<3;i++){
		backGround[i]= t.loadSprite(s+(i+1));
	}
	
	UFO=new TexturedQuad(450, 450, 450, 300, 0, "res/sprites/enemies/UFO/1.png");
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
		//this is for UFO movement up and down
		if(movementstage == 0){
			UFO.move(0, 1);
		}else{
			UFO.move(0, -1);
		}
		if(UFO.gety()>350){
			movementstage=1;
		}
		if(UFO.gety()<250){
			movementstage=0;
		}
		
				
		backGround[location].bind();
		location++;
		if(location== 3){
			location=0;
		}
		glBegin(GL_QUADS);
		glTexCoord2f(0f, 0f);
		glVertex2i(0, 0);
		glTexCoord2f(1f, 0f);
		glVertex2i(Globals.dispWidth, 0);
		glTexCoord2f(1f, 1f);
		glVertex2i(Globals.dispWidth, Globals.dispHeight);
		glTexCoord2f(0f, 1f);
		glVertex2i(0, Globals.dispHeight);
		glEnd();
		quit.updateOnClickListener(new OnClickListener(quit));
		quit.AddOnClickListener(new ClickListener() {
			
			@Override
			public void ClickListener(OnClickListener OCL) {
				Globals.isRunning = false;
				
			}
		});
		
		
		start.updateOnClickListener(new OnClickListener(start));
		start.AddOnClickListener(new ClickListener() {
			
			@Override
			public void ClickListener(OnClickListener OCL) {
				
				Runnable r = new Runnable() {
					public void run() {
						try {
							Thread.sleep(50L);// this is so that the color shows
												// for a little while
							//System.out.println("Switching Gamestate to game");
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
