package builderEntity;

import builderEntity.Anchor;
import builderEntity.Square;
import builderEntity.TileParser;

public class Tile {
	Square[] squares;
	Anchor anchor;
	String location;
	int referenceNumber;
	
	Tile(int[][] coords, String location) {
		//first coordinate must be that of the anchor
		if(coords.length != 6) throw new RuntimeException("Error in tile constructor.");
		if(coords[0][0] != 0 && coords[0][1] != 0) throw new RuntimeException("Error in tile constructor.");
		anchor = new Anchor(0,0,this);
		for(int i = 0; i < 6; i++) {
			squares[i].setX(coords[i][0]) ;
			squares[i].setY(coords[i][1]) ;
			squares[i].setTile(this);
		}
		
		this.location = location;
	}
	
	public Tile(int referenceNumber, String location){
		
		this.squares = TileParser.getSquares(referenceNumber, this);
		this.location = location;
		this.referenceNumber = referenceNumber;
		
		anchor = new Anchor(0,0, this);
	}
	
	public void rotateRight() {

		int temp;
		for(Square square : squares) {
			temp = square.anchorRelX;
			square.anchorRelX = square.anchorRelY;
			square.anchorRelY = -temp;
		}
	}
	
	public void rotateLeft() {
		int temp;
		for(Square square : squares) {
			temp = square.anchorRelX;
			square.anchorRelX = -square.anchorRelY;
			square.anchorRelY = temp;
		}
	}
	
	public void flipVertical() {
		for(Square square : squares) {
			square.anchorRelY = -square.anchorRelY;
		}
	}
	
	public void flipHorizontal() {
		for(Square square : squares) {
			square.anchorRelX = -square.anchorRelX;
		}
	}
	
	public Tile() {
		squares = new Square[6];
	}
	
	public void setSquares(Square s[]){
		this.squares = s;
	}
	
	public Square[] getSquares(){
		return this.squares;
	}
	
	public int getReferenceNumber(){
		return this.referenceNumber;
	}
	
	
	
}
