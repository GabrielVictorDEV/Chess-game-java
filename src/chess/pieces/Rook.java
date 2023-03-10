package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRow()][getBoard().getColumn()];

		Position p = new Position(0, 0);
		// Move up;
		p.setValues(position.getRow() - 1, position.getColumn());
		while (getBoard().positionExist(p) && !getBoard().thereIsApiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() -1);
		}
		if (getBoard().positionExist(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Move left;
		p.setValues(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExist(p) && !getBoard().thereIsApiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if (getBoard().positionExist(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Move rigth;
		p.setValues(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExist(p) && !getBoard().thereIsApiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if (getBoard().positionExist(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Move down; 
		p.setValues(position.getRow() + 1, position.getColumn());
		while (getBoard().positionExist(p) && !getBoard().thereIsApiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if (getBoard().positionExist(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		return mat;
	}
}
