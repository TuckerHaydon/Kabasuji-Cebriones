package playerController;

import playerBoundary.KabasujiPlayerApplication;
import playerEntity.GameModel;

public class ResetLevelMove implements IMove{
	KabasujiPlayerApplication app;
	GameModel m;
	
	public ResetLevelMove(KabasujiPlayerApplication app, GameModel m){
		this.app=app;
		this.m=m;
	}
	
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