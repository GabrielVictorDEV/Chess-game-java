package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private int turn;
	private Color currentPlayer; 
	private Board board; // uma partida precisa de um tabuleiro.
	private boolean check; 
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		board = new Board(8, 8); // Responsabilidade da partida saber o tamanho do tabuleiro;
		turn = 1; 
		currentPlayer = Color.WHITE;
		check = false; 
		initialSetup();
	}

	public int getTurn() {
		return turn;
	}
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	public boolean getCheck() {
		return check; 
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
		
		//Testa se deixou seu rei em xeque;
		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check");
		}

		//Testa se deixou o rei oponente em xeque;
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		nexTurn(); 
		return (ChessPiece)capturedPiece; 
	}
	
	//move a peça da origem para o destino; 
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);//remover a peça da posição de dstino; 
		board.placePiece(p, target); //move para a posição de destino; 
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece; 
	}
	
	//defazer um movimento; 
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		Piece p = board.removePiece(target);
		board.placePiece(p, source);

		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
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
		piecesOnTheBoard.add(piece);
	}

	private void nexTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE; 
	} 
	
	//tes
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board");
	}

	//testa constantemente se o rei está em xeque; 
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;//significa que o rei está em xeque; 
			}
		}
		return false;
	} 
	
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
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
