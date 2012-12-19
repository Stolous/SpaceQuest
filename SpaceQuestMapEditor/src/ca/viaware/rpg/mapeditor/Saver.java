package ca.viaware.rpg.mapeditor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Saver {

	//final static Charset ENCODING = StandardCharsets.UTF_8;

	void saveMap(List<String> mapData, String name) throws IOException {
		File mapPath = new File("res/maps/" + name + ".map");
		
		if (mapPath.exists()){
			System.out.println("File: " + mapPath.toString() + " exists, deleting and recreating");
			mapPath.delete();
			mapPath.createNewFile();
		}else{
			mapPath.createNewFile();
		}
		
		Writer outputStream = new OutputStreamWriter(new FileOutputStream(mapPath));
		BufferedWriter writer = new BufferedWriter(outputStream);
		
		for (String data : mapData){
			writer.write(data);
			writer.newLine();
			writer.flush();
		}
		
		for (Waypoint way : Globals.waypoints){
			writer.write("WP/" + Integer.toString(way.getType()) + "/" + way.getName() + "/" + way.getPointTo() + "/" + way.getPointToMap());
			writer.newLine();
			writer.flush();
		}
		
		for (Enemy enemy : Globals.enemies){
			System.out.println("Writing enemy " + enemy.getType().getName());
			writer.write("EN/" + Integer.toString(Globals.enemyTypes.indexOf(enemy.getType())) + "/" + enemy.getLevel());
			writer.newLine();
			writer.flush();
		}
		
		writer.close();
		outputStream.close();

		/* 1.7+ code
		try {
			Files.createFile(Paths.get("res/maps/" + name + ".map"));
		} catch (FileAlreadyExistsException e) {
			System.out.println("File: " + "res/maps/" + name + ".map exists, erasing and recreating");
			Files.delete(Paths.get("res/maps/" + name + ".map"));
			Files.createFile(Paths.get("res/maps/" + name + ".map"));
		}

		Path path = Paths.get("res/maps/" + name + ".map");
		try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)) {
			for (String line : mapData) {
				writer.write(line);
				writer.newLine();
			}
			
			for (Waypoint way : Globals.waypoints){
					System.out.println(way.name);
					writer.write("WP/" + Integer.toString(way.getType()) + "/" + way.getName() + "/" + way.getPointTo() + "/" + way.getPointToMap());
					writer.newLine();
			}
			
			
			for (Enemy enemy : Globals.enemies){
				writer.write("EN/" + Integer.toString(Globals.enemyTypes.indexOf(enemy.getType())) + "/" + enemy.getLevel());
				writer.newLine();
			}
			
		}
		
		*/
		Globals.isSaved = true;
	}
}
