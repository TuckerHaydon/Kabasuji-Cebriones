package playerController;

import playerBoundary.KabasujiPlayerApplication;
import playerBoundary.TileView;
import playerEntity.Anchor;
import playerEntity.Board;
import playerEntity.GameModel;
import playerEntity.LightningLevel;
import playerEntity.PlayableBoardElt;
import playerEntity.ReleaseLevel;
import playerEntity.Square;
import playerEntity.Tile;

/**
 * Picks up a Tile from the Board
 * This class is instantiated in the Board Controller
 * and used to pick up Tiles in Release Levels
 * @see BoardController
 * @see Board
 * @author tuckerhaydon, ndiwakar, dgwalder, kacper puczydlowski
 *
 */
public class PickUpTileBoardMove extends Move{
	
	Tile tile;
	Board board;
	int[] rowcol;
	
	/**
	 * PickUp Tile Board Move Constructor
	 * @param app - top level application
	 * @param m - top level game model
	 * @param tile - tile being removed from the board
	 * @param board - board of the level being controlled
	 */
	public PickUpTileBoardMove(KabasujiPlayerApplication app, GameModel m, Tile tile, Board board){
		super(app, m);
		this.tile=tile;
		this.board=board;
	}
	
	/**
	 * Finds the tile on the board corresponding to the mouse events and picks it up
	 * Sets those board elements to uncovered
	 */
	@Override
	boolean doMove() {
		
		// rowcol of anchor for undo purposes
		rowcol = ((Anchor) tile.getSquare(0, 0)).getRowCol();
		
		// Remove the tile from the board
		board.removeTile(tile);
		
		// Set all of the covered elts to uncovered
		for(Square s: tile.getSquares()){
			
			int row = tile.getAnchor().getRowCol()[0] - s.getRelY();
			int col = tile.getAnchor().getRowCol()[1] + s.getRelX();
			
			PlayableBoardElt elt = (PlayableBoardElt)board.getBoardElt(row, col);
			
			elt.setCovered(false);
		}
		
		// Update the dragged tile view
		TileView tv = new TileView(app, m, tile);
		app.getGameWindow().setDraggedTile(tv);
		
		// Update the GUI to show the picked up tile
		UpdateDraggedTileLocationMove move = new UpdateDraggedTileLocationMove(app, m);
		move.execute();
		
		// Repaint the board
		app.getGameWindow().getLevelView().getBoardView().repaint();
		//update the number of moves but don't repaint the window until you've placed the tile
		//this works because the only actions you can do are reset the level, exit, or place the tile
		//all of which will update to the correct number of moves
		//((PuzzleLevel) m.getCurrentLevel()).updateMoves(-1);
		//update puzzle level moves and repaint the label
		//((PuzzleLevelView) app.getGameWindow().getLevelView()).refreshMoveLabel();
		
		return true;
	}
	
	@Override
	public boolean undo() {
		return board.addTile(tile, rowcol[0], rowcol[1]);
	}

	/**
	 * Checks validity of move
	 * Cannot pick up tiles from board during lightning level
	 */
	@Override
	boolean isValid() {
		if(m.getCurrentLevel() instanceof LightningLevel || m.getCurrentLevel() instanceof ReleaseLevel) {
			return false;
		}
		return true;
	}

}
