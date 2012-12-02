package ca.viaware.rpg.entities;



import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.entity.MeleeEnemy;
import ca.viaware.rpg.utilities.TextureHandler;
import ca.viaware.rpg.utilities.TexturedQuad;

public class WeakSlime extends MeleeEnemy {

	private Texture[] textures = new Texture[17];
	private int index = 1;
	private int slower = 0;

	//this should be one  of the initial enemies
	public WeakSlime(  int spawnx, int spawny) {
		super(40, 40, 20, 3, 1, spawnx, spawny, 1, 10, 40, 80,400);
		// TODO Auto-generated constructor stub

		setT(new TexturedQuad((int) width, (int) height, 0, 0, 0, "res/sprites/enemies/weakslimemoving/1.png"));

		String s = "enemies/weakslimemoving/";
		TextureHandler t = new TextureHandler();
		for (int count = 1; count < 18; count++) {
			textures[count - 1] = t.loadSprite(s + count);
		}
	}

	public void draw() {
		if (slower == 5) {
			slower = 0;
			getT().changetexture(textures[index]);
			index++;
		} else {
			slower++;
		}

		getT().setlocation(getmx(), getmy());

		getT().update();
		if (!(index == 17)) {// so the index doesn't go ver nbr of sprites

		} else {
			index = 0;
		}

	}

}
