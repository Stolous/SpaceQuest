package ca.viaware.rpg.game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;

import ca.viaware.rpg.audio.MusicPlaylist;
import ca.viaware.rpg.entity.Bullet;
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

		else if (Globals.gameState == Globals.gState.MAIN_MENU) {
			Globals.mainMenu.update(delta);
		} else{ 
			if (count > 2) {
			TimeHandler.updateTime(delta);
			fpsCounter.calculateFPS(delta);
			songTimer += delta;
		}

		if (songTimer > 1000) {
			currentSong = mPlaylist.nextSong(currentSong);
			if (!currentSong.isPlaying()) {
				System.out.println("Starting...");

				currentSong.playAsMusic(1.0f, 1.0f, false);
			}
			songTimer = 0;
		}

		SoundStore.get().poll(0);
		SoundStore.get().setMusicVolume(Globals.musiclevel);
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

		for (Bullet bullet : Globals.bullets) {
			bullet.update(delta);
			
		}

		for (int i = 0; i < Globals.bullets.size(); i++) {
			if (Globals.bullets.get(i).getremoved()) {
				Globals.bullets.remove(i);
			}
		}
		}
		Globals.cursor.update(delta);

	}

}
