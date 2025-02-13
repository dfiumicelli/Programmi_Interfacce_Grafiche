package jtetris.model;

public class PieceS extends Piece {

	PieceS() {
		this.shapeArray = new int[4][2];
		this.pieceName = "PieceS";

		// Reference block
		this.shapeArray[0][0] = 0; // iRel
		this.shapeArray[0][1] = 0; // jRel

		// Block with label 1
		this.shapeArray[1][0] = 0; // iRel
		this.shapeArray[1][1] = -1; // jRel

		// Block with label 2
		this.shapeArray[2][0] = 1; // iRel
		this.shapeArray[2][1] = 0; // jRel

		// Block with label 3
		this.shapeArray[3][0] = 1; // iRel
		this.shapeArray[3][1] = 1; // jRel
	}

}
