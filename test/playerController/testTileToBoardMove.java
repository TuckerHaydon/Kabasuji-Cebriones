package playerController;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import playerEntity.Board;
import playerBoundary.KabasujiPlayerApplication;
import playerBoundary.TileView;
import playerEntity.GameModel;
import playerEntity.LightningAchievementMonitor;
import playerEntity.PuzzleAchievementMonitor;
import playerEntity.Tile;

public class testTileToBoardMove {

	@Test
	public void testPuzzle() {
		GameModel gm = GameModel.instance();
		KabasujiPlayerApplication player = new KabasujiPlayerApplication(gm);
		
		//setup
		gm.loadGame("src/resources/games/ExampleGame1");
		gm.setCurrentLevel(0);
		
		//get all the necessary elements for the move
		ArrayList<Tile> tiles = gm.getCurrentLevel().getBullpen().getTiles();
		Board board = gm.getCurrentLevel().getBoard();
		TileView tv = new TileView(player, gm, tiles.get(2));
		PuzzleAchievementMonitor pam = new PuzzleAchievementMonitor(null);
		gm.selectCurrentAM(1);

		//TileToBoardMove ttbm = new TileToBoardMove(player, gm, board, tiles.get(2), 2, 2);

		Tile t = tiles.get(3);
		TileToBoardMove ttbm = new TileToBoardMove(player, gm, board, tiles.get(3), 2, 2);

		ttbm.setAM(pam);
		player.getGameWindow().setDraggedTile(tv);
		
		ttbm.isValid();
		ttbm.execute();

		ttbm.undo();	//put some assert statements back in here

	//	assertEquals(gm.getCurrentLevel().getBoard().getTiles().size(), 1); 
		
//		//after the move is undone, the board should have zero tiles again
//		ttbm.undo();
//		assertEquals(gm.getCurrentLevel().getBoard().getTiles().size(), 0);
//		
//		ttbm.execute();
//		TileToBullpenMove ttbpm = new TileToBullpenMove(player, gm, t, gm.getCurrentLevel().getBullpen());
//		player.getGameWindow().setDraggedTile(new TileView(player,gm,t));
//		assertTrue(ttbpm.execute());
//		assertFalse(ttbpm.execute());
//		assertTrue(ttbpm.undo());

	}
	
	@Test
	public void testLightning(){
		GameModel gm = GameModel.instance();
		KabasujiPlayerApplication player = new KabasujiPlayerApplication(gm);
		
		//setup
		gm.loadGame("src/resources/games/ExampleGame1");
		gm.setCurrentLevel(1);
		
		//get all the necessary elements for the move
		ArrayList<Tile> tiles = gm.getCurrentLevel().getBullpen().getTiles();
		Board board = gm.getCurrentLevel().getBoard();
		TileView tv = new TileView(player, gm, tiles.get(0));
		LightningAchievementMonitor lam = new LightningAchievementMonitor(null);
		gm.selectCurrentAM(2);
		
		TileToBoardMove ttbm = new TileToBoardMove(player, gm, board, tiles.get(0), 3, 1);
		ttbm.setAM(lam);
		player.getGameWindow().setDraggedTile(tv);
		
		//this move is valid
		ttbm.isValid();
		assertTrue(ttbm.isValid());
		
		//doing this move means there should be one tile on the board
		ttbm.execute();
		assertEquals(gm.getCurrentLevel().getBoard().getTiles().size(), 1);
		
		//undoing shouldn't change the number of tiles on the board, because this is a lightning level
		ttbm.undo();
		assertEquals(gm.getCurrentLevel().getBoard().getTiles().size(), 1);
	}
	
	@Test
	public void testInvalidLightning(){
		GameModel gm = GameModel.instance();
		KabasujiPlayerApplication player = new KabasujiPlayerApplication(gm);
		
		//setup
		gm.loadGame("src/resources/games/ExampleGame1");
		gm.setCurrentLevel(1);
		
		//get all the necessary elements for the move
		ArrayList<Tile> tiles = gm.getCurrentLevel().getBullpen().getTiles();
		Board board = gm.getCurrentLevel().getBoard();
		TileView tv = new TileView(player, gm, tiles.get(0));
		LightningAchievementMonitor lam = new LightningAchievementMonitor(null);
		gm.selectCurrentAM(2);
		
		//drop the tile outside the board
		TileToBoardMove ttbm = new TileToBoardMove(player, gm, board, tiles.get(0), 14, 14);
		ttbm.setAM(lam);
		player.getGameWindow().setDraggedTile(tv);
		
		//this move should be invalid
		ttbm.isValid();
		assertTrue(!ttbm.isValid());
		
		//after executing this move, there should still be zero tiles on the board
		ttbm.execute();
		assertEquals(gm.getCurrentLevel().getBoard().getTiles().size(), 0);
		
		//undoing should not change this
		ttbm.undo();
		assertEquals(gm.getCurrentLevel().getBoard().getTiles().size(), 0);
	}
}
