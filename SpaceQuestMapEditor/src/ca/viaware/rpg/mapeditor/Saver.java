package ca.viaware.rpg.mapeditor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Saver {

	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	void saveMap(List<String> aLines) throws IOException {
		try{
		Files.createFile(Paths.get("res/testMap.txt"));
		}catch(FileAlreadyExistsException e){
		System.out.println("File: " + "res/testMap" + " exists, erasing and recreating");
		Files.delete(Paths.get("res/testMap.txt"));
		Files.createFile(Paths.get("res/testMap.txt"));
		}
		
		Path path = Paths.get("res/testMap.txt");
	    try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)){
	      for(String line : aLines){
	    	writer.write(line);
	        writer.newLine();
	      }
	    }
	  }
}
