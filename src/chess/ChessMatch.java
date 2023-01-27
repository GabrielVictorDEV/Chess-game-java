package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private int turn;
	private Color currentPlayer; 
	private Board board; // uma partida precisa de um tabuleiro.
	
	
	public ChessMatch() {
		board = new Board(8, 8); // Responsabilidade da partida saber o tamanho do tabuleiro;
		turn = 1; 
		currentPlayer = Color.WHITE;
		initialSetup();
	}

	public int getTurn() {
		return turn;
	}
	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public ChessPiece[][] getPieces() {
		// matriz de peças de xadrez que recebem as linhas e colunas do tabuleiro acima;
		ChessPiece[][] mat = new ChessPiece[board.getRow()][board.getColumn()];
		for (int i = 0; i < board.getRow(); i++) {
			for (int j = 0; j < board.getColumn(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
				// faz um dowgrade de uma peça para uma peça de xadrez pois a partida de xadrez
				// deve ter apenas peças de xadrez;
			}
		}

		return mat; // retorna a matriz de peças da partida de xadrez correspondente a partida;
	}
	
	//mostra possiveis movimentos; 
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}

	//movendo da origem para o destino; 
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source); //verifica se a posição de origem existe; 
		validadeTargetPosition(source, target); 
		Piece capturedPiece = makeMove(source, target); //dowcasting para chessPiece; 
		nexTurn(); 
		return (ChessPiece)capturedPiece; 
	}
	
	//move a peça da origem para o destino; 
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturePiece = board.removePiece(target);//remover a peça da posição de dstino; 
		board.placePiece(p, target); //move para a posição de destino; 
		return capturePiece; 
		
	}
	
	//valida o local de origem; 
	private void validateSourcePosition(Position position) {
		if (!board.thereIsApiece(position)) {
			throw new ChessException("There is no piece on source position"); 
		}
		//faz dowgrade, testa a peça pela cor, e vê se é a dá vez;  
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()){
			throw new ChessException("The chose piece is not yours"); 
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {//testa se tem movimentos possiveis; 
			throw new ChessException("There is no possible moves for the chosen piece"); 
		}
	}
	
	private void validadeTargetPosition(Position source, Position target) {
		//verifica se a peça de origem possui movimentos validos; 
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chose piece can't move to taret position"); 
		}
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	private void nexTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE; 
	}
	
	// inicia a partida colocando as peças no tabuleiro;
	private void initialSetup() {
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));

	}
}
