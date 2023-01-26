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
		System.out.println("   ----------------------");
		System.out.println("   A  B  C  D  E  F  G  H");
			
	}
	
	private static void printPiece(ChessPiece piece) { //imprime as peças; 
		if (piece == null) {
			System.out.print(" -"); //Se não tiver peça, imprime lugar vazio; 
		}
		else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
}
