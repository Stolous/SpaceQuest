package ca.viaware.rpg.game;

import ca.viaware.rpg.utilities.TextRenderer;

public class FPSCounter {

	private int count = 0;
	private int FPS = 0;
	private int totalTime = 0;
	TextRenderer tRender = new TextRenderer(50,0,TextRenderer.Font.WHITE);
	
	public void calculateFPS(int delta){
		totalTime += delta;
		count++;
		if (totalTime > 1000){
			FPS = count;
			count = 0;
			totalTime = 0;
		}
		
		tRender.writeToScreen(0, 0, Integer.toString(FPS));
		tRender.finish();
	}
}
