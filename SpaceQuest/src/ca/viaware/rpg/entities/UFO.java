package ca.viaware.rpg.entities;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entity.Bullet;
import ca.viaware.rpg.entity.RangedEnemy;
import ca.viaware.rpg.utilities.TextureHandler;
import ca.viaware.rpg.utilities.TexturedQuad;

public class UFO extends RangedEnemy{
	
	private Texture[] textures = new Texture[29];
	private int index = 1;
	private int slower = 0;

	public UFO(int spawnx, int spawny) {
		
		super(50,50, 30, 5, 2, spawnx, spawny, 1, 200, 50, 500,100,500,"bullets/LazerGreen",250,0,0);
		// TODO Auto-generated constructor stub

		setT(new TexturedQuad((int) width, (int) height, 0, 0, 0, "res/sprites/enemies/UFO/1.png"));

		String s = "enemies/UFO/";
		TextureHandler t = new TextureHandler();
		for (int count = 1; count < 30; count++) {
			textures[count - 1] = t.loadSprite(s + count);
		}
	}

	public void draw() {
	drawbullets();
		
		if (slower == 5) {
			slower = 0;
			getT().changetexture(textures[index]);
			index++;
		} else {
			slower++;
		}

		getT().setlocation(getmx(), getmy());
		//System.out.println(getmx());

		getT().update();
		if (!(index == 29)) {// so the index doesn't go over nbr of sprites

		} else {
			index = 0;
		}

		getB().draw();
	}

}


