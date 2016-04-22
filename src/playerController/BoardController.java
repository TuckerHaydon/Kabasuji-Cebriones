package playerController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import playerBoundary.GameWindow;
import playerBoundary.KabasujiPlayerApplication;
import playerBoundary.TileView;
import playerEntity.Anchor;
import playerEntity.Board;
import playerEntity.BoardElt;
import playerEntity.GameAchievementMonitor;
import playerEntity.GameModel;
import playerEntity.LevelAchievementMonitor;
import playerEntity.NumberBoardElt;
import playerEntity.PlayableBoardElt;
import playerEntity.ReleaseLevel;
import playerEntity.Tile;
import playerEntity.UnplayableBoardElt;

/**
 * 
 * @author tuckerhaydon
 *
 */
public class BoardController extends MouseAdapter{
	Board b;
	KabasujiPlayerApplication app;
	int eltWidth;

	public BoardController(Board b, KabasujiPlayerApplication app, int eltWidth){
		super();
		this.app=app;
		this.b=b;
		this.eltWidth = eltWidth;
	}

	public void mouseDragged(MouseEvent me){

		//update x,y position of tile
		app.getGameWindow().getDraggedTile().setLocation(me.getX(), me.getY());
		app.getGameWindow().displayDraggedTile();
	}

	//activity here depends on what kind of level we have 
	public void mousePressed(MouseEvent me){

		// Get the XY location of mouse event
		int x = me.getX();
		int y = me.getY();

		// Determine which BoardElt that is
		int row = y / eltWidth;
		int col = x / eltWidth;
		BoardElt elt = b.getBoardElt(row, col);


		if(elt instanceof PlayableBoardElt){
			PlayableBoardElt pbElt = (PlayableBoardElt) elt;
			//check if elt is covered and if it is release level
			if(pbElt.getCovered() && (app.getGameModel().getCurrentLevel() instanceof ReleaseLevel)){
				System.out.println("PlayableBoardElt");
				Tile thisTile = b.getTile(row, col);

				//create new tileview
				TileView tv = new TileView(thisTile);
				app.getGameWindow().setDraggedTile(tv);

				//set dragged tile view, update coordinates, etc
				PickUpTileBoardMove pbm = new PickUpTileBoardMove (thisTile, b);
				if(pbm.isValid(app)){
					pbm.doMove(app);
				}
			}
			else if (pbElt.getCovered() && !(app.getGameModel().getCurrentLevel() instanceof ReleaseLevel)){
				return;
			}

		}			
		//System.out.println(row + " "+col);

	}


	public void mouseReleased(MouseEvent me){

		GameWindow gw = app.getGameWindow();
		GameModel m = app.getGameModel();

		int x = me.getX();
		int y = me.getY();

		// Determine which BoardElt that is
		int row = y / eltWidth;
		int col = x / eltWidth;

		// Find the element at that location
		//Check if covered
		//if covered, send tile back to anchor location
		
		//if uncovered and 

		//if(elt instanceof PlayableBoardElt)
		
		
		LevelAchievementMonitor AM = m.getCurrentAM();
		GameAchievementMonitor GAM = m.getGAM();
		TileView tileview = gw.getDraggedTile();
		if(tileview==null){
			System.err.println("Null TileView::BoardController::mouseReleased");
			gw.releaseDraggedTile();
			return;
		}

		Tile tile = tileview.getTile();
		if(tile==null){
			System.err.println("Null Tile::BoardController::mouseReleased");
			gw.releaseDraggedTile();
			return;
		}
		gw.releaseDraggedTile();

		//achievement stuff goes here!

		IMove move = new CompleteLevelMove(m);
		if(move.isValid(app)){
			move.doMove(app);	
			app.displayLevelSelectionMenu();
			app.getGameWindow().updateView();
			if(GAM.updateAchievement(m.getCurrentLevel().getLevelNum())){
				GAM.pop();
			}
		}


		//This Anchor is just there for console reason
		Anchor a = new Anchor(row,col,tile);

		IMove move2 = new TileToBoardMove(b,a,row,col);
		if(move2.isValid(app)){
			move2.doMove(app);
		}

		//TODO move and app change stuff go there

		//			if(AM.updateAchievement_releaseonboard()){
		//				AM.popUpScreen();
		//			}
		else{
			if(AM.updateAchievement_wheninvalidmove()){
				AM.popUpScreen();
			}
		}
	}

}
