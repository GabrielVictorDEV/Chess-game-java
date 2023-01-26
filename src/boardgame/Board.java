package boardgame;

public class Board {
		
	private int row;
	private int column; 
	private Piece[][] pieces; //Contem uma matriz de peças; 
	
	public Board(int row, int column) {
		if (row < 1 || column  < 1) {
			throw new BoardException("Erro creating board: there must be at least 1 row and 1 column! "); 
		}
		this.row = row;
		this.column = column;
		pieces = new Piece[row][column]; 
	}

	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	
	//retoorna uma peça, de acordo com a linha e coluna;
	public Piece piece(int row, int column) { 
		if(!positionExist(row, column)) {
			throw new BoardException("Position not on the board"); 
		}
		return pieces[row][column]; 
	}
	
	public Piece piece(Position position) {
		if(!positionExist(position)) {
			throw new BoardException("Position not on the board"); 
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	//na coluna e linha informada, vai receber a peça informada; 
	public void placePiece(Piece piece, Position position) {
		if (thereIsApiece(position)) {
			throw new BoardException("There is already a piece on position " + position); 
		}
		pieces[position.getRow()][position.getColumn()] = piece; 
		piece.position = position; 
	}
	
	//remove peça
	public Piece removePiece(Position position) {
		if(!positionExist(position)) {
			throw new BoardException("Position not on the board"); 
		}
		if(piece(position) == null) {
			return null; 
		}
		Piece aux = piece(position); 
		aux.position = null; 
		pieces[position.getRow()][position.getColumn()] = null; 
		return aux; 
	}
	
	//testa se a posição inserida existe; '
	private boolean positionExist(int row, int column) { 
		return row >= 0 && row < this.row && column >= 0 && column < this.column; 
	}
	
	//sobrecarga usando a position indireta; 
	public boolean positionExist(Position position) { 
		return positionExist(position.getRow(), position.getColumn()); 
	}
	
	//verifica se á uma peça no local;
	public boolean thereIsApiece(Position position) {
		if(!positionExist(position)) { //se não existir uma peça nessa posição, dá uma exceção; 
			throw new BoardException("Position not on the board"); 
		}
		return piece(position) != null; 
 	}

}
