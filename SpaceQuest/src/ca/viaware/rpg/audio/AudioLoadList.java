package ca.viaware.rpg.audio;

import ca.viaware.rpg.game.Globals;

public class AudioLoadList {

	public static void loadAudio(){
		AudioHandler ah = new AudioHandler();
		Globals.musicLibrary.add(ah.loadAudioStream("music/song1"));
		Globals.musicLibrary.add(ah.loadAudioStream("music/song2"));
		
		System.out.println(Globals.musicLibrary.size() + " songs loaded into playlist");
	}
}
