package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		//instancia uma nova partida de xadrez;
		ChessMatch chessMatch = new ChessMatch();  
		List<ChessPiece> captured = new ArrayList<>(); 
		
		while(!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch , captured);
				System.out.println();
				System.out.println("Source: "); 
				ChessPosition source = UI.readChessPosition(sc); //posição de origem;
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.println("Target: ");
				ChessPosition target = UI.readChessPosition(sc);//posição de destino;    
	
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if(capturedPiece != null) {//se diferente de nulo, captura; 
					captured.add(capturedPiece);
				}
			}
			catch (ChessException e){
				System.out.println(e.getMessage());
				sc.nextLine(); 
			}
			catch (InputMismatchException e){
				System.out.println(e.getMessage());
				sc.nextLine(); 
			}
		}
		
		//Limpa tela e mostra partida finalizada; 
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
	}
}