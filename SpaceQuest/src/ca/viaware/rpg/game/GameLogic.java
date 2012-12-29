package ca.viaware.rpg.game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;

import ca.viaware.rpg.audio.MusicPlaylist;
import ca.viaware.rpg.utilities.FullscreenHandler;
import ca.viaware.rpg.utilities.TimeHandler;

public class GameLogic {

	PlayerMovement pMovement = new PlayerMovement();
	FullscreenHandler fHandler = new FullscreenHandler();
	FPSCounter fpsCounter = new FPSCounter();
	MusicPlaylist mPlaylist = new MusicPlaylist();
	Audio currentSong = Globals.musicLibrary.get(0);
	private int timer = 0, count = 0;
	private int songTimer = 0;

	public void doLogic(int delta) {
		for (int ii = 0; ii < Globals.enemies.size(); ii++) {
			Globals.enemies.get(ii).reset();
		}
		if (Globals.gameState == Globals.gState.INTRO) {
			count++;
			if (count > 2) {
				timer += delta;
				if (timer > 1500) {
					System.out.println("Switching gamestate to main menu");
					Globals.gameState = Globals.gState.MAIN_MENU;
					Globals.cursor.setEnabled(true);
				}
			}
		}
		
		if (delta >= 0) {
			TimeHandler.updateTime(delta);
			fpsCounter.calculateFPS(delta);
			// Timer is here so that song position gets decent time to advance
			// between checks
			songTimer += delta;
		}
		if(Globals.musiclevel>0){
		if (songTimer > 1000) {
			currentSong = mPlaylist.nextSong(currentSong);
			if (!currentSong.isPlaying()) {
				System.out.println("Starting...");
				currentSong.playAsMusic(1.0f, 1.0f, false);
			}
			songTimer = 0;
		}
		}else{
			currentSong.stop();
		}
		SoundStore.get().poll(0);
		fHandler.handleFullscreen();

		// Allows user to leave the game screen if desired
		if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			Mouse.setGrabbed(true);
		} else {
			Mouse.setGrabbed(false);
		}

		pMovement.checkMovement(delta);

		for (int i = 0; i < Globals.enemies.size(); i++) {
			Globals.enemies.get(i).update(delta);
		}

		Globals.cursor.update(delta);

	}

}
