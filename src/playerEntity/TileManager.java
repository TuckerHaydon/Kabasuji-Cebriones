package playerEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TileManager {

	public static Square[] getSquares(int referenceNumber){
		
		String path = "src/resources/tiles/tiles";
		Square squares[] = new Square[6];
		
		try (Scanner fileScanner = new Scanner(new File(path))){
			squares = parseTile(fileScanner, referenceNumber);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		return squares;
	}
	
	static Square[] parseTile(Scanner fileScanner, int referenceNumber){
		
		// Move the scanner ahead to the desired tile
		for(int i = 0; i < referenceNumber - 1; i++){
			fileScanner.nextLine();
		}
		
		// Parse the 12 integers into the square array
		Square squares[] = new Square[6];
		for(int i = 0; i < 6; i++){
			int relX = fileScanner.nextInt();
			int relY = fileScanner.nextInt();
			squares[i] = new Square(relX, relY);
		}
		
		
		return squares;
	}
	
}
