package playerController;

import java.awt.event.MouseEvent;

import playerBoundary.BoardView;
import playerBoundary.KabasujiPlayerApplication;
import playerEntity.GameModel;


public class SquareController extends java.awt.event.MouseAdapter{
	
	KabasujiPlayerApplication app;
	BoardView bv;
	GameModel m;	
	
	public SquareController (KabasujiPlayerApplication app, BoardView boardview, GameModel model){
		super();
		
		this.app = app;
		this.bv = boardview;
		this.m = model;
	}
	
	public void mouseDragged(MouseEvent me){
		
		//GameWindow gw = .draggedTile
		
	}
	
	public void mousePressed(MouseEvent me){
		
	}
	
	public void mouseReleased(MouseEvent me){
		
	}

}