package boardgame;

public class Board {
		
	private int rows;
	private int columns; 
	private Piece[][] pieces; //Contem uma matriz de peças; 
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; 
	}

	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	
	public Piece piece(int row, int column) { //retoorna uma peça, de acordo com a linha e coluna; 
		return pieces[rows][columns]; 
	}
	
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}
}
