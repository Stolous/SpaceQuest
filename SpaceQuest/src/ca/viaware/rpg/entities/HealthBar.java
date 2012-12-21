package ca.viaware.rpg.entities;

import org.lwjgl.opengl.Display;

import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.utilities.TexturedQuad;
import ca.viaware.rpg.utilities.NoTexQuad;

public class HealthBar {
	private int tot, rat, hl;
	private double r, g, b;
	TexturedQuad barf;
	NoTexQuad barb;

	public HealthBar() {
		barf = new TexturedQuad(Display.getWidth(), (Display.getHeight() / 10), Display.getWidth() / 2, Display.getHeight() - Display.getHeight() / 20, 0, "res/sprites/other/healthbar.png");
		barb = new NoTexQuad((int) (Display.getWidth() / 1.21), (int) (Display.getHeight() / 40), Display.getWidth() / 2, Display.getHeight() - (int) (Display.getHeight() / 54.3), 0);// this
		tot = (int) (Display.getWidth() / 1.21);
		rat = tot / 100;
		r = 0;
		g = 0.51;
		b = 0;
		hl = 100;

	}

	public void update() {

		barb.setcol(r, g, b);
		barf.update();
		barb.update();
	}

	public void change(int percent) {
		hl = hl - percent;
		percent = percent * rat;
		barb.resize(percent, 0);
		barb.move((percent / 2), 0);
		r = r + hl * 0.0002;
		g = g - (hl * 0.0002);

	}

	public void windowResized() {
		System.out.println("Size changed");
		//barf.setSize(Display.getWidth(), (Display.getHeight() / 9));
		//barf.setlocation(Display.getWidth() / 2, Display.getHeight() - Display.getHeight() / 25);

		//barb.setlocation(Display.getWidth() / 2, Display.getHeight() - (int) (Display.getHeight() / 150));
		//barb.setSize(hl * rat, (Display.getHeight() / 54.3));
		
		barf.move((Globals.dispWidth - Globals.dispWidthBK) / 2, (Globals.dispHeight - Globals.dispHeightBK));
		barb.move((Globals.dispWidth - Globals.dispWidthBK) / 2, (Globals.dispHeight - Globals.dispHeightBK));
	}

	public void set(int amount) {
		hl = amount;
		amount = amount * rat;
		barb.setXsize(amount);
		barb.move((amount / 2), 0);
		r = r + hl * 0.000075;
		g = g - (hl * 0.000075);

	}
}
