package jtetris.controller;

import jtetris.view.View;
import jtetris.model.Model;

public class ControllerForView implements IControllerForView {

	//---------------------------------------------------------------
	// STATIC FIELDS
	//---------------------------------------------------------------
	private final static int SCORE_FACTOR = 10;
	private static ControllerForView instance = null;

	private ControllerForView() {
		//to-do
	}

	//---------------------------------------------------------------
	// PRIVATE INSTANCE METHODS
	//---------------------------------------------------------------
	private int iAbs(int blockLabel) {
		return Model.getInstance().iIndex() + Model.getInstance().iRelOfFallingPiece(blockLabel);
	}

	private int jAbs(int blockLabel) {
		return Model.getInstance().jIndex() + Model.getInstance().jRelOfFallingPiece(blockLabel);
	}

	private boolean isMovableDown() {
		boolean movableDown = true;
		int iTmp = -1;
		int jTmp = -1;
		for (int k = 0; (k < Model.getInstance().numBlocksOfFallingPiece()) && movableDown; k++) {
			iTmp = this.iAbs(k) - 1; // a decrement of one in the model means a downward movement of one unit
			jTmp = this.jAbs(k);
			if (iTmp < 0)
				movableDown = false;
			else if (!Model.getInstance().isEmptyCell(iTmp, jTmp))
				movableDown = false;
		}
		return movableDown;
	} // end method isMovableDown()

	private boolean isMovableLeft() {
		boolean movableLeft = true;
		int iTmp = -1;
		int jTmp = -1;
		for (int k = 0; (k < Model.getInstance().numBlocksOfFallingPiece()) && movableLeft; k++) {
			iTmp = this.iAbs(k);
			jTmp = this.jAbs(k) - 1; // a decrement of one in the model means a leftward movement of one unit
			if (jTmp < 0)
				movableLeft = false;
			else if (!Model.getInstance().isEmptyCell(iTmp, jTmp))
				movableLeft = false;
		}
		return movableLeft;
	} // end method isMovableLeft()
	
	private boolean isMovableRight() {
		boolean movableRight = true;
		int iTmp = -1;
		int jTmp = -1;
		for (int k = 0; (k < Model.getInstance().numBlocksOfFallingPiece()) && movableRight; k++) {
			iTmp = this.iAbs(k);
			jTmp = this.jAbs(k) + 1; // an increment of one in the model means a rightward movement of one unit
			if (jTmp < 0)
				movableRight = false;
			else if (!Model.getInstance().isEmptyCell(iTmp, jTmp))
				movableRight = false;
		}
		return movableRight;
	} // end method isMovableRight()
	
   /**
	 * The iIndex and jIndex of the k-th rotated block are:
	 *    iIndex_BR = iIndex + fallingPiece.iRelOfFallingPiece(block)
	 *    jIndex_BR = jIndex + fallingPiece.jRelOfFallingPiece(block)
	 * 
	 *    iIndex_AR = iIndex + (jIndex_BR - jIndex) = iIndex + fallingPiece.jRelOfFallingPiece(block)
	 *    jIndex_AR = jIndex - (iIndex_BR - iIndex) = jIndex - fallingPiece.iRelOfFallingPiece(block)
	 * where, AR and BR mean After Rotation and Before Rotation, respectively.
	 */
	private boolean isRotatable() {
		boolean rotatable = true;

		int iIndexAR = -1;
		int jIndexAR = -1;
		for (int k = 1; (k < Model.getInstance().numBlocksOfFallingPiece()) && rotatable; k++) {
			iIndexAR = Model.getInstance().iIndex() + Model.getInstance().jRelOfFallingPiece(k);
			jIndexAR = Model.getInstance().jIndex() - Model.getInstance().iRelOfFallingPiece(k);
			/* Check whether the current block goes beyond the left boundary of the board. */
			if (jIndexAR < 0)
				rotatable = false;
			/* Check whether the current block goes beyond the right boundary of the board. */
			else if (jIndexAR >= Model.getInstance().getNumColumnsOfBoard())
				rotatable = false;
			/* Check whether the current block goes beyond the bottom boundary of the board. */
			else if (iIndexAR < 0)
				rotatable = false;
			/* Check whether the current block goes over a non-empty cell. */
			else if (!Model.getInstance().isEmptyCell(iIndexAR, jIndexAR))
				rotatable = false;
		}
		return rotatable;
	}
	
	private void resumeIndexes() {
		Model.getInstance().setIIndex(Model.getInstance().iIndexInit());
		Model.getInstance().setJIndex(Model.getInstance().jIndexInit());
	}
	
	private boolean isGameOver() {
		boolean isGameOver = false;
		if (Model.getInstance().iIndex() == Model.getInstance().iIndexInit())
			isGameOver = true;
		return isGameOver;
	}
	
	private boolean isRowFull(int iIndexOfRow) {
		boolean isRowFull = true;
		for (int j = 0; (j < Model.getInstance().getNumColumnsOfBoard()) && isRowFull; j++)
			isRowFull = !(Model.getInstance().isEmptyCell(iIndexOfRow, j));
		return isRowFull;
	}
	
	/**
	 * Returns the iIndex of the topmost full row; in case there is no full row it returns -1.
	 */
	private int topMostFullRow() {
		int topMostFullRow = -1;
		for (int i = 0; i < Model.getInstance().getNumRowsOfBoard(); i++)
			if (this.isRowFull(i))
				topMostFullRow = i;
		return topMostFullRow;
	}
	
	private void removeFullRows() {
		int i = Model.getInstance().getNumRowsOfBoard();
		int scoreIncrement = -1;
		while ((i = this.topMostFullRow()) >= 0) {
			Model.getInstance().removeRow(i);
			scoreIncrement = SCORE_FACTOR * (Model.getInstance().getNumRowsOfBoard() - i);
			Model.getInstance().incrementScore(scoreIncrement);
			View.getInstance().updateScoreLabel(Model.getInstance().getScore());
		}
	}
	
	//---------------------------------------------------------------
	// PUBLIC INSTANCE METHODS
	//---------------------------------------------------------------
	public void openStartWindow() {
		View.getInstance().openStartWindow();
	}

	public void closeStartWindow() {
		View.getInstance().closeStartWindow();
	}

	public void openNewGameWindow() {
		View.getInstance().openNewGameWindow();
	}

	public void loadPreviouslySavedGame(String file) {
		//TO-DO
	}

	public void closeNewGameWindow() {
		View.getInstance().closeNewGameWindow();
	}

	public void openMainGUI() {
		closeNewGameWindow();
		View.getInstance().openMainGUI();
	}

	public int getNumColumnsOfBoard() {
		return Model.getInstance().getNumColumnsOfBoard();
	}

	public int getNumRowsOfBoard() {
		return Model.getInstance().getNumRowsOfBoard();
	}

	public String getPlayerName() {
		return Model.getInstance().getPlayerName();
	}

	public void setPlayerName(String playerName) {
		Model.getInstance().setPlayerName(playerName);
	}

	public String getScore() {
		return String.valueOf(Model.getInstance().getScore());
	}

	public void incrementScore(int increment) {
		Model.getInstance().incrementScore(increment);
	}

	public int numBlocksOfPreviewPiece() {
		return Model.getInstance().numBlocksOfPreviewPiece();
	}

	public int iRelOfPreviewPiece(int blockLabel) {
		return Model.getInstance().iRelOfPreviewPiece(blockLabel);
	}

	public int jRelOfPreviewPiece(int blockLabel) {
		return Model.getInstance().jRelOfPreviewPiece(blockLabel);
	}

	public int numBlocksOfFallingPiece() {
		return Model.getInstance().numBlocksOfFallingPiece();
	}

	public int iRelOfFallingPiece(int blockLabel) {
		return Model.getInstance().iRelOfFallingPiece(blockLabel);
	}

	public int jRelOfFallingPiece(int blockLabel) {
		return Model.getInstance().jRelOfFallingPiece(blockLabel);
	}

	public String getNameOfFallingPiece() {
		return Model.getInstance().getNameOfFallingPiece();
	}

	public String getNameOfPreviewPiece() {
		return Model.getInstance().getNameOfPreviewPiece();
	}
	
	public void initGame() {
		Model.getInstance().initGame();
	}

	public int iIndex() {
		return Model.getInstance().iIndex();
	}

	public int jIndex() {
		return Model.getInstance().jIndex();
	}
	
	public boolean isEmptyCell(int i, int j) {
		return Model.getInstance().isEmptyCell(i, j);
	}
	
	public String getNameOfPieceAtCell(int i, int j) {
		return Model.getInstance().getNameOfPieceAtCell(i, j);
	}
	
	public void down() {
		if (this.isMovableDown())
			Model.getInstance().setIIndex((iIndex() - 1));
	}
	
	public void left() {
		if (this.isMovableLeft())
			Model.getInstance().setJIndex((jIndex() - 1));
	}
	
	public void right() {
		if (this.isMovableRight())
			Model.getInstance().setJIndex((jIndex() + 1));
	}
	
	public void rotate() {
		if (this.isRotatable())
			Model.getInstance().rotateFallingPiece();
	}
	
	public void next() {
		if (this.isMovableDown())
			Model.getInstance().setIIndex((iIndex() - 1));
		else {
			Model.getInstance().fillBoardCellsWithBlocksOfFallingPiece();
			this.removeFullRows();
			if (this.isGameOver())
				View.getInstance().gameOverDialog();
			else {
				Model.getInstance().nextFallingAndPreviewPieces();
				this.resumeIndexes();
			}
		}
	} // end method next()

	//---------------------------------------------------------------
	// STATIC METHODS
	//---------------------------------------------------------------
	public static IControllerForView getInstance() {
		if (instance == null)
			instance = new ControllerForView();
		return instance;
	}

} // end class
