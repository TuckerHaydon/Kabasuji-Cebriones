package builderEntity;

public class PlayableBoardElt extends BoardElt{

	boolean isHint;
	
	PlayableBoardElt(int row, int col, boolean isHint) {
		super(row, col);
		this.isHint = isHint;
		// TODO Auto-generated constructor stub
	}
	
	void setIsHint(boolean h){
		this.isHint = h;
	}
	
	public boolean getIsHint(){
		return this.isHint;
	}

}
