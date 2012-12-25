package ca.viaware.rpg.audio;

import org.newdawn.slick.openal.Audio;

import ca.viaware.rpg.game.Globals;

public class MusicPlaylist {
	private int count = 0;
	private float oldPos = 0;
	private int count2 = 0;
	public Audio nextSong(Audio current){
		if (oldPos == current.getPosition()){
			count++;
			if (count > Globals.musicLibrary.size()){
				System.out.println("All songs played, returning to start");
				count = 1;
			}
			
			System.out.println("Playing next song: " + (count - 1));
			oldPos = current.getPosition();
			return Globals.musicLibrary.get(count - 1);
		}else{
			oldPos = current.getPosition();
			return current;
		}
		
	}
}
