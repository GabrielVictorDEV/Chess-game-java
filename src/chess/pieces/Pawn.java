package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRow()][getBoard().getColumn()];

		Position p = new Position(0, 0);

		if (getColor() == Color.WHITE) {
			//Regra geral; 
			p.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExist(p) && !getBoard().thereIsApiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			//Jogada inicial; 
			p.setValues(position.getRow() - 2, position.getColumn());
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExist(p) && !getBoard().thereIsApiece(p) && getBoard().positionExist(p2) && !getBoard().thereIsApiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			//verificar captura direita; 
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBoard().positionExist(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}			
			//verificar captura esquerda; 
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExist(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}			
		}
		else {
			p.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExist(p) && !getBoard().thereIsApiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 2, position.getColumn());
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExist(p) && !getBoard().thereIsApiece(p) && getBoard().positionExist(p2) && !getBoard().thereIsApiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExist(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}			
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExist(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}	
		}
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}

}