package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		//instancia uma nova partida de xadrez;
		ChessMatch chessMatch = new ChessMatch();  
		
		while(true) {
			UI.printBoard(chessMatch.getPieces());
			System.out.println();
			System.out.println("Source: "); 
			ChessPosition source = UI.readChessPosition(sc); //posição de origem;
			
			System.out.println();
			System.out.println("Target: ");
			ChessPosition target = UI.readChessPosition(sc);//posição de destino;    

			ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
		}
	}
}