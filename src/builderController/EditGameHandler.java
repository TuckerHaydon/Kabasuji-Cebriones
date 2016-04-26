package builderController;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import builderBoundary.BoardView;
import builderBoundary.BullpenView;
import builderBoundary.GameEditor;
import builderBoundary.KabasujiBuilderApplication;
import builderBoundary.LevelBuilderView;
import builderBoundary.LevelEditor;
import builderEntity.Board;
import builderEntity.BoardElt;
import builderEntity.BuilderModel;
import builderEntity.Bullpen;
import builderEntity.Game;
import builderEntity.Level;
import builderEntity.LevelParser;
import builderEntity.NumberedBoardElt;
import builderEntity.PlayableBoardElt;
import builderEntity.UnplayableBoardElt;
/**
 * 
 * @author jwilder
 *
 */
public class EditGameHandler implements ActionListener {
	KabasujiBuilderApplication app;
	BuilderModel m;
	
	public EditGameHandler(KabasujiBuilderApplication app, BuilderModel m) {
		this.app = app;
		this.m = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		File folder = new File("src/resources/games/");
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> gameNames = new ArrayList<>();
		
		// Get all of the levels in the levels directory
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && !listOfFiles[i].getName().substring(0, 1).equals(".")) {
				gameNames.add(listOfFiles[i].getName());
		    }
		}
		
		String chosenLevelName = (String) JOptionPane.showInputDialog(null, "Choose a Game to load", "Game Loader",
		        JOptionPane.QUESTION_MESSAGE, null, gameNames.toArray(), gameNames.get(0));

		String path = "src/resources/games/"+chosenLevelName;
		
		loadGame(path);
		m.getGame().setName(chosenLevelName);
		app.displayGameEditor();
	}
	
	public void loadGame(String path){
		try (Scanner fileScanner = new Scanner(new File(path))){
			parseFile(fileScanner);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	public void parseFile(Scanner fileScanner){
		
		Level lvls[] = new Level[15];
		
		String next;
		int levelNum = 1;
		
		do
		{
			next = fileScanner.next();
			if(next.equals("BeginLevel")){
				Level lvl = parseLevel(fileScanner, levelNum);
				lvls[levelNum - 1] = lvl;
			}
			else if(next.equals("EndLevel"))
			{
				levelNum++;
			}
			
		}
		while(!next.equals("EndGame"));
		
		m.setGame(new Game("unnamed", lvls));
		
	}
	
	Level parseLevel(Scanner fileScanner, int levelNum){
		
		String levelType = fileScanner.next();
		int levelData = Integer.parseInt(fileScanner.next());
		
		ArrayList<Integer> hexominoes = parseHexominoes(fileScanner);
		BoardElt elts[][] = parseBoardElts(fileScanner);
		
		
		Bullpen bp = new Bullpen(hexominoes);
		Board b = new Board(elts);
		
		// System.out.println(b);
	
		switch(levelType)
		{
			case "Lightning":
				return new Level(bp, b, 1, "Lightning", 1);
			case "Puzzle":
				return new Level(bp, b, 1, "Puzzle", 1);
			case "Release":
				return new Level(bp, b, 1, "Release", 1);
			default:
				System.err.println("Invalid level type from parser");
				return null;
		}
	}

	BoardElt[][] parseBoardElts(Scanner fileScanner) {
		BoardElt elts[][] = new BoardElt[12][12];
		
		// Parse the BeginElts
		fileScanner.next();
		
		for(int row = 0; row < 12; row++){
			for(int col = 0; col < 12; col++){
				String input = fileScanner.next();
				BoardElt elt = null;
				
				switch(input.substring(0, 1)){
					// Playable board elt
					case "P":
						// Check if hint
						if(input.length() == 2){ 	elt = new PlayableBoardElt(row, col, true);}
						else{						elt = new PlayableBoardElt(row, col, false);}
						break;
					// Unplayable board elt
					case "U":
						elt = new UnplayableBoardElt(row, col);
						break;
					// Numbered board elt
					case "N":
						// Parse number and color data out
						int num = Integer.parseInt(input.substring(1, 2));
						String c = input.substring(2, 3);
						Color color = null;
						switch(c){
							case "R":
								color = Color.RED;
								break;
							case "B":
								color = Color.BLUE;
								break;
							case "G":
								color = Color.GREEN;
								break;
						}
						// Check if hint
						if(input.length() == 4){	elt = new NumberedBoardElt(row, col, true, color, num);}
						else{						elt = new NumberedBoardElt(row, col, false, color, num);}
						break;
					default:
						System.out.println(input);
				}
				elts[row][col] = elt;
			}
		}
		
		// Parse the EndElts
		fileScanner.next();
		
		return elts;
	}

	ArrayList<Integer> parseHexominoes(Scanner fileScanner) {
		
		ArrayList<Integer> hexominoes = new ArrayList<>();
		
		//Parse the BeginHexominos
		String next = fileScanner.next();
		next = fileScanner.next();

		while(!next.equals("EndHexomino"))
		{
			hexominoes.add(Integer.parseInt(next));
			next = fileScanner.next();	
		}
		
		return hexominoes;
	}

}