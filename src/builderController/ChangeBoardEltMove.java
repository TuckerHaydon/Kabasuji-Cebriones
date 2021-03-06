package builderController;

import java.awt.Color;

import builderBoundary.KabasujiBuilderApplication;
import builderEntity.Board;
import builderEntity.BoardElt;
import builderEntity.BuilderModel;
import builderEntity.NumberedBoardElt;
import builderEntity.PlayableBoardElt;
import builderEntity.UnplayableBoardElt;

/**
 * The move changing the board element properties
 * @author tuckerhaydon
 *
 */
public class ChangeBoardEltMove extends Move {
	
	String selectedBoardEltType, selectedColor, prevType;
	Color prevColor;
	int selectedNumber, prevNumber;
	int row, col;
	Board board;
	boolean isHint, prevHint;
	
	/**
	 * ChangeBoardEltMove constructor
	 * @param app
	 * @param model
	 * @param board
	 * @param row
	 * @param col
	 */
	public ChangeBoardEltMove(KabasujiBuilderApplication app, BuilderModel model, Board board, int row, int col) {
		super(app, model);
		
		this.board = board;
		this.row = row;
		this.col = col;
		this.selectedBoardEltType = model.getSelectedBoardEltType();
		this.selectedColor = model.getSelectedColor();
		this.selectedNumber = model.getSelectedNumber();
		isHint = model.isHintSelected();
	}

	@Override
	public boolean doMove() {
		
		BoardElt b = board.getBoardElts()[row][col];
		if (b instanceof PlayableBoardElt) {
			prevHint = ((PlayableBoardElt)board.getBoardElts()[row][col]).isHint();
			if(b instanceof NumberedBoardElt) {
				prevColor = ( (NumberedBoardElt) board.getBoardElts()[row][col]).getColor();
				prevType = "numbered"; 
			} else {
				prevType = "playable";
			}
		} else {
			prevType = "unplayable";
		}
		
		switch(selectedBoardEltType){
		case "playable":
			board.getBoardElts()[row][col] = new PlayableBoardElt(row, col, isHint);
			break;
		case "unplayable": 
			board.getBoardElts()[row][col] = new UnplayableBoardElt(row, col);
			break;
		case "numbered":
			
			Color c = Color.BLACK;
			if(selectedColor.equals("red")){c = Color.RED;}
			else if(selectedColor.equals("blue")){c = Color.BLUE;}
			else if(selectedColor.equals("green")){c = Color.GREEN;}
			
			board.getBoardElts()[row][col] = new NumberedBoardElt(row, col, isHint, c, selectedNumber); 
			break;
		default:
			System.err.println("Something wrong with the board elt type selection in ChangeBoardEltMove");
			return false;
		}
		
		return true;
	}

	@Override
	boolean isValid() {
		
		if(board.getBoardElts()[row][col] instanceof UnplayableBoardElt && selectedBoardEltType.equals("unplayable")) {return false;}
		if(board.getBoardElts()[row][col] instanceof NumberedBoardElt && selectedBoardEltType.equals("numbered")) {
			
			Color c = Color.BLACK;
			if(selectedColor.equals("red")){c = Color.RED;}
			else if(selectedColor.equals("blue")){c = Color.BLUE;}
			else if(selectedColor.equals("green")){c = Color.GREEN;}
			
			return !((NumberedBoardElt)(board.getBoardElts()[row][col])).equals(new NumberedBoardElt(row, col, isHint, c, selectedNumber));
			}
		if(board.getBoardElts()[row][col] instanceof PlayableBoardElt && selectedBoardEltType.equals("playable")) {
			return !((PlayableBoardElt)(board.getBoardElts()[row][col])).equals(new PlayableBoardElt(row, col, isHint));
			}

		// Make sure the row/col are within bounds.
		if(row < 0 || row > 11 || col < 0 || col > 11){
			return false;
		}
		
		return true;
	}

	@Override
	public boolean undoMove() {
		final String type  = prevType;
		if(type == null)return false;
		switch(prevType){
		case "playable":
			board.getBoardElts()[row][col] = new PlayableBoardElt(row, col, prevHint);
			break;
		case "unplayable": 
			board.getBoardElts()[row][col] = new UnplayableBoardElt(row, col);
			break;
		case "numbered":
			board.getBoardElts()[row][col] = new NumberedBoardElt(row, col, isHint, prevColor, selectedNumber); 
			break;
		default:
			System.err.println("Something wrong with the board elt type selection in ChangeBoardEltMove");
			return false;
		}
		
		return true;
	}

}
