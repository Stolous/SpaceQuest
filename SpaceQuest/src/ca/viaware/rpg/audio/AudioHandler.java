package ca.viaware.rpg.audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;

public class AudioHandler {

	public Audio loadAudio(String input){
		Audio a = null;
		try {
			a = AudioLoader.getAudio("OGG", new FileInputStream("res/audio/" + input + ".ogg"));
		} catch (IOException e) {
			System.out.println("ERROR - Could not load audio file");
		}
		
		return a;
	}
	
	public Audio loadAudioStream(String input){
		Audio a = null;
		try {
			a = AudioLoader.getStreamingAudio("OGG", new File("res/audio/" + input + ".ogg").toURL());
		} catch (IOException e) {
			System.out.println("ERROR - Could not load audio file");
		}
		
		return a;
	}
}
