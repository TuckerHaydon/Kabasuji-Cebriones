package builderController;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import builderBoundary.KabasujiBuilderApplication;
import builderEntity.BuilderModel;
import builderEntity.Level;
import builderEntity.LevelParser;

/**
 * Adds a level to a game in the Game Editor. This class is used by
 * the corresponding handler to add made levels to a game being constructed.
 * @see AddLevelToGameHandler
 * @see GameEditor
 * @author tuckerhaydon
 * @author jwilder
 */
public class AddLevelToGameMove extends Move {
	
	int levelIndex;
	ArrayList<String> allnames;
	String levelname;
	String folderName;
	
	/**
	 * The AddLevelToGameMove constructor.
	 * @param app - top-level Kabasuji builder application
	 * @param m - top-level BuilderModel
	 * @param levelIndex - index of the level to add
	 */
	public AddLevelToGameMove(KabasujiBuilderApplication app, BuilderModel m, int levelIndex) {
		super(app, m);
		
		this.levelIndex = levelIndex;
		
		folderName = null;
		
		switch(levelIndex%3){
		case 0:
			folderName = "src/resources/levels/puzzle/";
			break;
		case 1: 
			folderName = "src/resources/levels/lightning/";
			break;
		case 2:
			folderName = "src/resources/levels/release/";
			break;
		default:
			System.err.println("Error in AddLevelToGameMove");
			break;
		
		}
		
		File folder = new File(folderName);
		
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> levelNames = new ArrayList<>();
		
		// Get all of the levels in the levels directory
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && !listOfFiles[i].getName().substring(0, 1).equals(".")) {
				levelNames.add(listOfFiles[i].getName());
		    }
		}
		
		allnames = levelNames;
		
	}
	
	/**
	 * Checks to see if the move is valid,
	 * in which case the move is done.
	 */
	@Override
	public boolean execute(){
		
		if(!this.isValid()){
			return false;
		}
		
		this.requestName();
		return this.doMove();
	}

	/**
	 * Creates the selected level object
	 * and adds it to the game
	 */
	@Override
	public boolean doMove() {

		String path = folderName+levelname;
		Level lvl = LevelParser.getLevel(path);
		lvl.setLevelName(levelname);
		lvl.setLevelNum(levelIndex);
		
		m.getGame().setLevel(lvl, levelIndex);
		app.getGameEditor().refreshLevel(levelIndex);
		return true;
	}
	
	/**
	 * Prompts the builder to select a level to add
	 * 
	 */
	public boolean requestName(){
		levelname = (String) JOptionPane.showInputDialog(null, "Choose a level to add", "Level "+levelIndex,
		        JOptionPane.QUESTION_MESSAGE, null, allnames.toArray(), allnames.get(0));

		return true;
	}
	
	/**
	 * Determines if adding the level is valid
	 * Which is always true
	 */
	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public boolean undoMove() {
		
//		m.getGame().setLevel(null, levelIndex);
		
		return false;
	}
	
	/**
	 * Allows the player to set the name for the level
	 * 
	 * @param name
	 */
	public void setLevelName(String name){
		this.levelname = name;
	}

}
