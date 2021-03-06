package builderController;

import static org.junit.Assert.*;


import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

import org.junit.Test;

import builderBoundary.KabasujiBuilderApplication;
import builderController.EditGameHandler;
import builderEntity.BuilderModel;
import builderEntity.Game;

public class testEditGameHandler {

	@Test
	public void test() {
		BuilderModel m = BuilderModel.instance();
		KabasujiBuilderApplication builder = new KabasujiBuilderApplication(m);
		builder.init();
		
		EditGameHandler egh = new EditGameHandler(builder, m);
		egh.loadGame("src/resources/games/ExampleGame1");
		ArrayList<String> gameNames = egh.addGames();
		egh.attemptLoadGame("src/resources/games/ExampleGame1", "newName");
		Game currGame = m.getGame();
		assertTrue(currGame != null);
	}

}
