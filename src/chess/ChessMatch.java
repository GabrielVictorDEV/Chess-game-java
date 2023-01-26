package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board; //uma partida precisa de um tabuleiro. 
	
	public ChessMatch() {
		board = new Board(8, 8); //Responsabilidade da partida saber o tamanho do tabuleiro; 
		initialSetup(); 
	}
	
	public ChessPiece[][] getPieces(){
		//matriz de peças de xadrez que recebem as linhas e colunas do tabuleiro acima; 
		ChessPiece[][] mat = new ChessPiece[board.getRow()][board.getColumn()];  
		for (int i=0; i<board.getRow(); i++) {
			for(int j=0; j<board.getColumn(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j); 
				//faz um dowgrade de uma peça para uma peça de xadrez pois a partida de xadrez deve ter apenas peças de xadrez; 
			}
		}
		
		return mat; //retorna a matriz de peças da partida de xadrez correspondente a partida; 
	}
	
	//inicia a partida colocando as peças no tabuleiro; 
	private void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position(3,4));
		board.placePiece(new King(board, Color.WHITE), new Position(3,6));

	}
}
