package ca.viaware.rpg.entity;

import org.newdawn.slick.opengl.Texture;

import ca.viaware.rpg.utilities.TextureHandler;
import ca.viaware.rpg.utilities.TexturedQuad;

public class EnemyBar extends AbstractEntity {
	private Enemy enemy;
	TexturedQuad BackGround;

	public EnemyBar(double x, double y, double width, double height, Enemy e, int Level, Effect[] effects) {
		super(x, y, width, height);
		TextureHandler loader = new TextureHandler();
		Texture t = loader.loadSprite("other/Enemy Bar");
		BackGround = new TexturedQuad((int) x, (int) y, width, height, t);
		this.enemy = e;

	}

	@Override
	public void draw() {
		BackGround.update();

	}

	@Override
	public void update(int delta) {

		System.out.println("Upd X is " + enemy.getX());

		BackGround.setlocation(enemy.getX(), enemy.getY());
		x = enemy.getX();
		y = enemy.getY();

	}

}
