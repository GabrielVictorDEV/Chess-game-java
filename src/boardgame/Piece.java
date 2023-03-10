package boardgame;

public abstract class Piece {
	
	protected Position position; //uma posição na matriz, que ainda não é a peça. 
	private Board board; //associação da peça com o tabuleiro; 
	
	public Piece(Board board) {
		this.board = board;
		position = null; //posição de uma peça recem crida é nula. 
	}

	//Somente classes do pacote ou do subpacote vão poder acessar o tabuleiro; 
	protected Board getBoard() {
		return board;
	}	
	
	public abstract boolean[][] possibleMoves(); 
	
	//metodo concreto, que depende de um metodo abstrato; 
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()]; 
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat.length; j++) {
				if(mat[i][j]) {
					return true; 
				}
			}
		}
		return false; 
	}
}
