package playerEntity;

import java.util.Hashtable;
import java.util.LinkedList;

import playerBoundary.KabasujiPlayerApplication;
import playerController.IMove;
import playerController.ReturnToMenuMove;

public class LightningAchievementMonitor extends LevelAchievementMonitor{
	LightningLevel lv;
	
	public LightningAchievementMonitor(Hashtable<String,Achievement> achievements){
		this.achievements=achievements;
		this.moveCounter=0;
		this.popingUp=new LinkedList<String>();
	}
	
	
	
	/*Finished*/
	public boolean updateAchievement(IMove move) {
		return (checkSlowPoke(move) || checkRageQuit(move) || this.checkBabySteps(move) || this.checkRebel(move) || this.checkVictoryLap(move));
	}
	
	/*Finished*/ /*Have questions about isLevelDone */
	private boolean checkSlowPoke(IMove move){
		boolean typeMatched = move instanceof ReturnToMenuMove;
		if(this.notEarnSlowPoke() && lv.isTimeUsedUp() && typeMatched && !(lv.hasWon())){
			achievements.get("SlowPoke").setEarned();
			popingUp.push("SlowPoke");
			return true;
		}
		return false;
	}
	/*Finished*/
	boolean checkRageQuit(IMove move){
		boolean typeMatched = move instanceof ReturnToMenuMove;
		if(this.notEarnRageQuit() && !(lv.isTimeUsedUp()) && typeMatched && !(lv.hasWon())){
			achievements.get("RageQuit").setEarned();
			popingUp.push("RageQuit");
			return true;
		}
		return false;
	}

	@Override
	boolean checkVictoryLap(IMove move) {
		boolean typeMatched = move instanceof ReturnToMenuMove;
		if(typeMatched && lv.hasWon() && lv.getIsCompleted()){
			return true;
		}
		return false;
	}



	/*Finished*/
	public void setLevel(Level lv) {
		reset();
		this.lv=(LightningLevel) lv;
	}



	/*Finished*/
	protected void reset() {
		this.lv=null;
		this.moveCounter=0;
		this.popingUp=new LinkedList<String>();
	}
}
