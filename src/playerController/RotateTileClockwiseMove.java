package playerController;

import playerBoundary.KabasujiPlayerApplication;
import playerEntity.Tile;

public class RotateTileClockwiseMove implements IMove{
	Tile tile;
	
	public RotateTileClockwiseMove(Tile tile){
		this.tile=tile;
	}
	
	@Override
	public boolean doMove(KabasujiPlayerApplication app) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValid(KabasujiPlayerApplication app) {
		// TODO Auto-generated method stub
		return false;
	}

}