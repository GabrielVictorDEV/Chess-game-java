package application;

import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {

		ChessMatch chessMatch = new ChessMatch(); //instancia uma nova partida de xadrez; 
		UI.printBoard(chessMatch.getPieces());
	}
}