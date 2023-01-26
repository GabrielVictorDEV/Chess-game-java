package chess;

import boardgame.Board;
import boardgame.Piece;

//Subclass da peça, com cor e novos metodos; 
public abstract class ChessPiece extends Piece{
	
	private Color color; //cor da peça; 

	public ChessPiece(Board board, Color color) { //Peça que tem um tabuleiro é uma cor; 
		super(board);
		this.color = color;
	}

	public Color getColor() {//Cor dá peça apenas acessada, e não modificada. 
		return color;
	}	
	
}
