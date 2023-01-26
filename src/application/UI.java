package application;

import chess.ChessPiece;

public class UI {
	
	public static void printBoard(ChessPiece[][] pieces) { //imprime o tabuleiro; 
		for (int i=0; i<pieces.length; i++) {
			System.out.print((8 - i) + " "); //linhas;
			for(int j=0; j<pieces.length; j++) {
				printPiece(pieces[i][j]); //colunas;
			}
			System.out.println();
		}
		System.out.println("   a b c d e f g h");
			
	}
	
	private static void printPiece(ChessPiece piece) { //imprime as peças; 
		if (piece == null) {
			System.out.println("-"); //Se não tiver peça, imprime lugar vazio; 
		}
		else {
			System.out.println(piece);
		}
		System.out.println(" ");
	}
}
