package boardgame;

public class Piece {
	
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
	
}
