package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.Rook;

public class ChessMatch { //De fato onde ocorre a partida; 
	
	private Board board; //uma partida precisa de um tabuleiro. 
	
	public ChessMatch() {
		board = new Board(8, 8); //Responsabilidade da partida saber o tamanho do tabuleiro; 
		initialSetup(); //Cria o tabuleiro e chama o setup; 
	}
	
	public ChessPiece[][] getPieces(){
		//matriz de peças de xadrez que recebem as linhas e colunas do tabuleiro acima; 
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];  
		for (int i=0; i<board.getRows(); i++) {
			for(int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j); 
				//faz um dowgrade de uma peça para uma peça de xadrez pois a partida de xadrez deve ter apenas peças de xadrez; 
			}
		}
		
		return mat; //retorna a matriz de peças da partida de xadrez correspondente a partida; 
	}
	
	private void initialSetup() {//inicia a partida colocando as peças no tabuleiro; 
		board.placePiece(new Rook(board, Color.WHITE), new Position(2,1));
	}
}
