package jtetris.model;

public class Model implements IModel {

	//---------------------------------------------------------------
	// STATIC CONSTANTS
	//---------------------------------------------------------------
	private final static int DEFAULT_NUM_ROWS = 20;
	private final static int DEFAULT_NUM_COLUMNS = 10;
	private final static int I_INDEX_INIT = DEFAULT_NUM_ROWS - 3;
	private final static int J_INDEX_INIT = DEFAULT_NUM_COLUMNS / 2; // integer division

	private final static int EMPTY_CELL = 0;
	private final static int I_CELL = 1;
	private final static int J_CELL = 2;
	private final static int L_CELL = 3;
	private final static int O_CELL = 4;
	private final static int S_CELL = 5;
	private final static int T_CELL = 6;
	private final static int Z_CELL = 7;

	//---------------------------------------------------------------
	// STATIC FIELDS
	//---------------------------------------------------------------
	private static Model instance = null;

	//---------------------------------------------------------------
	// INSTANCE ATTRIBUTES
	//---------------------------------------------------------------
	private int score;
	private String playerName;
	private int[][] boardArray;
	private int iIndex; // iIndex of the reference cell of the falling piece
	private int jIndex; // jIndex of the reference cell of the falling piece
	private Piece fallingPiece;
	private Piece previewPiece;

	private Model() {
		this.boardArray = new int[DEFAULT_NUM_ROWS][DEFAULT_NUM_COLUMNS];
		this.initGame();
	}

	//---------------------------------------------------------------
	// PRIVATE INSTANCE METHODS
	//---------------------------------------------------------------
	private void initBoardArray(int rows, int columns) {
		for (int i = 0; i < this.boardArray.length; i++)
			for (int j = 0; j < this.boardArray[i].length; j++)
				this.boardArray[i][j] = EMPTY_CELL;
	}
	
	private int getIntegerEncodingOfPiece(Piece piece) {
		int integerEncoding = -1;
		if (piece instanceof PieceI)
			integerEncoding = I_CELL;
		else if (piece instanceof PieceJ)
			integerEncoding = J_CELL;
		else if (piece instanceof PieceL)
			integerEncoding = L_CELL;		
		else if (piece instanceof PieceO)
			integerEncoding = O_CELL;		
		else if (piece instanceof PieceS)
			integerEncoding = S_CELL;
		else if (piece instanceof PieceT)
			integerEncoding = T_CELL;
		else if (piece instanceof PieceZ)
			integerEncoding = Z_CELL;
		return integerEncoding;
	}
	
	private Piece getPieceWithIntegerEncoding(int i) {
		Piece piece = null;
		if (i == I_CELL)
			piece = Piece.getFallingPieceByName("PieceI");
		else if (i == J_CELL)
			piece = Piece.getFallingPieceByName("PieceJ");
		else if (i == L_CELL)
			piece = Piece.getFallingPieceByName("PieceL");
		else if (i == O_CELL)
			piece = Piece.getFallingPieceByName("PieceO");
		else if (i == S_CELL)
			piece = Piece.getFallingPieceByName("PieceS");
		else if (i == T_CELL)
			piece = Piece.getFallingPieceByName("PieceT");
		else if (i == Z_CELL)
			piece = Piece.getFallingPieceByName("PieceZ");
		return piece;
	}

	//---------------------------------------------------------------
	// PUBLIC INSTANCE METHODS
	//---------------------------------------------------------------
	public int getNumColumnsOfBoard() {
		return this.boardArray[0].length;
	}

	public int getNumRowsOfBoard() {
		return this.boardArray.length;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public int getScore() {
		return this.score;
	}

	public void incrementScore(int increment) {
		this.score += increment;
	}

	public int numBlocksOfPreviewPiece() {
		return this.previewPiece.numBlocks();
	}

	public int iRelOfPreviewPiece(int blockLabel) {
		return this.previewPiece.iRel(blockLabel);
	}

	public int jRelOfPreviewPiece(int blockLabel) {
		return this.previewPiece.jRel(blockLabel);
	}

	public int numBlocksOfFallingPiece() {
		return this.fallingPiece.numBlocks();
	}

	public int iRelOfFallingPiece(int blockLabel) {
		return this.fallingPiece.iRel(blockLabel);
	}

	public int jRelOfFallingPiece(int blockLabel) {
		return this.fallingPiece.jRel(blockLabel);
	}

	public String getNameOfFallingPiece() {
		return this.fallingPiece.getName();
	}

	public String getNameOfPreviewPiece() {
		return this.previewPiece.getName();
	}
	
	public void initGame() {
		this.score = 0;
		if (this.playerName == null)
			this.playerName = "Unknown";
		this.initBoardArray(DEFAULT_NUM_ROWS, DEFAULT_NUM_COLUMNS);
		this.iIndex = I_INDEX_INIT;
		this.jIndex = J_INDEX_INIT;
		this.previewPiece = Piece.randomPreviewPiece();
		this.fallingPiece = Piece.nextFallingPiece(this.previewPiece);
		this.previewPiece = Piece.randomPreviewPiece();
	}
	
	public int iIndexInit() {
		return I_INDEX_INIT;
	}
	
	public int jIndexInit() {
		return J_INDEX_INIT;
	} 

	public int iIndex() {
		return this.iIndex;
	}

	public int jIndex() {
		return this.jIndex;
	}

	public void setIIndex(int i) {
		if ((0 <= i) && (i < DEFAULT_NUM_ROWS))
			this.iIndex = i;
	}

	public void setJIndex(int j) {
		if ((0 <= j) && (j < DEFAULT_NUM_COLUMNS))
			this.jIndex = j;
	}
	
	public void rotateFallingPiece() {
		this.fallingPiece.rotate();
	}
	
	public void fillBoardCellsWithBlocksOfFallingPiece() {
		int iCell = -1;
		int jCell = -1;
		for (int k = 0; k < this.fallingPiece.numBlocks(); k++) {
			iCell = this.iIndex + this.fallingPiece.iRel(k);
			jCell = this.jIndex + this.fallingPiece.jRel(k);
			this.boardArray[iCell][jCell] = this.getIntegerEncodingOfPiece(this.fallingPiece);
		}
	}
	
	public void removeRow(int indexOfRow) {
		for (int i = indexOfRow; i < this.boardArray.length; i++)
			for (int j = 0; j < this.boardArray[i].length; j++)
				if ((i + 1) < this.boardArray[i].length)
					this.boardArray[i][j] = this.boardArray[(i + 1)][j];
				else
					this.boardArray[i][j] = EMPTY_CELL;
	}

	public boolean isEmptyCell(int i, int j) {
		boolean isEmptyCell = false;
		if ((0 <= i) && (i < DEFAULT_NUM_ROWS) && (0 <= j) && (j < DEFAULT_NUM_COLUMNS) && (this.boardArray[i][j] == EMPTY_CELL))
			isEmptyCell = true;
		return isEmptyCell;
	}
	
	public String getNameOfPieceAtCell(int i, int j) {
		String name = "EmptyCell";
		if (!isEmptyCell(i, j))
			name = this.getPieceWithIntegerEncoding(this.boardArray[i][j]).getName();
		return name;
	}
	
	public void nextFallingAndPreviewPieces() {
		this.fallingPiece = Piece.nextFallingPiece(this.previewPiece);
		this.previewPiece = Piece.randomPreviewPiece();
	}

	//---------------------------------------------------------------
	// STATIC METHODS
	//---------------------------------------------------------------
	public static IModel getInstance() {
		if (instance == null)
			instance = new Model();
		return instance;
	}

}
