package jtetris.model;

import java.util.HashMap;

public class Piece {

	final static Piece[] FALLING_PIECE_ARRAY = new Piece[7];
	final static Piece[] PREVIEW_PIECE_ARRAY = new Piece[7];
	final static HashMap<String, Piece> mapStringNameToFallingPieceObj= new HashMap<String, Piece>();

	static {
		FALLING_PIECE_ARRAY[0] = new PieceI();
		FALLING_PIECE_ARRAY[1] = new PieceJ();
		FALLING_PIECE_ARRAY[2] = new PieceL();
		FALLING_PIECE_ARRAY[3] = new PieceO();
		FALLING_PIECE_ARRAY[4] = new PieceS();
		FALLING_PIECE_ARRAY[5] = new PieceT();
		FALLING_PIECE_ARRAY[6] = new PieceZ();

		PREVIEW_PIECE_ARRAY[0] = new PieceI();
		PREVIEW_PIECE_ARRAY[1] = new PieceJ();
		PREVIEW_PIECE_ARRAY[2] = new PieceL();
		PREVIEW_PIECE_ARRAY[3] = new PieceO();
		PREVIEW_PIECE_ARRAY[4] = new PieceS();
		PREVIEW_PIECE_ARRAY[5] = new PieceT();
		PREVIEW_PIECE_ARRAY[6] = new PieceZ();
		
		for (int i = 0; i < FALLING_PIECE_ARRAY.length; i++)
			mapStringNameToFallingPieceObj.put((FALLING_PIECE_ARRAY[i]).getName(), FALLING_PIECE_ARRAY[i]);
	}

	protected int[][] shapeArray;
	protected String pieceName;

	protected Piece() {
		//do nothing
	}

	protected int numBlocks() {
		return shapeArray.length;
	}

	protected int iRel(int blockLabel) {
		return shapeArray[blockLabel][0];
	}

	protected int jRel(int blockLabel) {
		return shapeArray[blockLabel][1];
	}

   /**
	 * Performs a 90 degree clockwise rotation of this piece.
	 * This method must be overridden in case it does not produce a good effect.
	 * A clockwise 90 degree rotation is given by the following formula:
	 *    x' = - y
	 *    y' = x
	 * Namely, for a given block, the new abscissa x' is the opposite of the previous ordinate;
	 * while the new ordinate y' coincides with the previous abscissa.
	 */
	protected void rotate() {
		int tmp;
		for (int k = 1; k < shapeArray.length; k++) {
			tmp = shapeArray[k][0];
			shapeArray[k][0] = shapeArray[k][1];
			shapeArray[k][1] = -tmp;
		}
	}

	protected void randomRotation() {
		int numRotation = (int)(3 * Math.random());
		for (int k = 0; k < numRotation; k++)
			rotate();
	}

	protected void update(Piece piece) {
		for (int k = 1; k < shapeArray.length; k++) {
			shapeArray[k][0] = piece.iRel(k);
			shapeArray[k][1] = piece.jRel(k);
		}
	}

	protected String getName() {
		return this.pieceName;
	}

	//---------------------------------------------------------------
	// STATIC METHODS
	//---------------------------------------------------------------
	
	static Piece getFallingPieceByName(String fallingPieceName) {
		return mapStringNameToFallingPieceObj.get(fallingPieceName);
	}

	static Piece nextFallingPiece(Piece previewPiece) {
		Piece fallingPiece = null;
		int pieceIndex = 0;
		for (int k = 0; k < PREVIEW_PIECE_ARRAY.length; k++)
			if (previewPiece == PREVIEW_PIECE_ARRAY[k])
				pieceIndex = k;
		fallingPiece = FALLING_PIECE_ARRAY[pieceIndex];
		fallingPiece.update(previewPiece);
		return fallingPiece;
	}

	static Piece randomPreviewPiece() {
		Piece previewPiece = null;
		int pieceIndex = (int)(Math.random() * PREVIEW_PIECE_ARRAY.length) % PREVIEW_PIECE_ARRAY.length;
		previewPiece = PREVIEW_PIECE_ARRAY[pieceIndex];
		previewPiece.randomRotation();
		return previewPiece;
	}

} // end class
