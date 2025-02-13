package jtetris.model;

public interface IModel {

	public int getNumColumnsOfBoard();

	public int getNumRowsOfBoard();

	public String getPlayerName();

	public void setPlayerName(String playerName);

	public int getScore();

	public void incrementScore(int increment);

	public int numBlocksOfPreviewPiece();

	public int iRelOfPreviewPiece(int blockLabel);

	public int jRelOfPreviewPiece(int blockLabel);

	public int numBlocksOfFallingPiece();

	public int iRelOfFallingPiece(int blockLabel);

	public int jRelOfFallingPiece(int blockLabel);

	public String getNameOfFallingPiece();

	public String getNameOfPreviewPiece();
	
	public void initGame();
	
	public int iIndexInit();
	
	public int jIndexInit();

	public int iIndex();

	public int jIndex();

	public void setIIndex(int i);

	public void setJIndex(int j);
	
	public void rotateFallingPiece();
	
	public void fillBoardCellsWithBlocksOfFallingPiece();
	
	public void removeRow(int indexOfRow);

	public boolean isEmptyCell(int i, int j);
	
	public String getNameOfPieceAtCell(int i, int j);
	
	public void nextFallingAndPreviewPieces();

} // end interface
