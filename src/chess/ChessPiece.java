package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

//Subclass da peça, com cor e novos metodos; 
public abstract class ChessPiece extends Piece{
	
	private Color color; //cor da peça; 
	private int moveCount; 
	
	public ChessPiece(Board board, Color color) { //Peça que tem um tabuleiro é uma cor; 
		super(board);
		this.color = color;
	}

	public Color getColor() {//Cor dá peça apenas acessada, e não modificada. 
		return color;
	}	

	public int getMoveCount() {
		return moveCount;
	}

	public void increaseMoveCount() {
		moveCount++;
	}

	public void decreaseMoveCount() {
		moveCount--;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	//Testa se é uma peça adversaria; 
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position); 
		return p != null && p.getColor() != color; //confere se a peça é nula e da cor oposta; 
	}
}
