package playerController;

import playerBoundary.KabasujiPlayerApplication;
import playerEntity.GameModel;

public class CompleteLevelMove implements IMove{
	GameModel m;
	
	public CompleteLevelMove(GameModel m){
		this.m=m;
	}
	
	/*Finished*/
	public boolean doMove(KabasujiPlayerApplication app) {
		if(isValid(app)){
			if(m.getCurrentAM().updateAchievement_whengotonextlevel()){
				m.getCurrentAM().popUpScreen();
				m.getCurrentAM().reset();
			}
			m.getCurrentLevel().reset();
			return true;
		}
		return false;
	}

	/*Finished*/
	public boolean isValid(KabasujiPlayerApplication app) {
		if(m.getCurrentLevel().hasWon()){
			return true;
		}
		return false;
	}
}
