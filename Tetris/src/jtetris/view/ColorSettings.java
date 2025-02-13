package jtetris.view;

import java.awt.Color;
import java.lang.reflect.Field;
import jtetris.utils.Config;

class ColorSettings {

	//---------------------------------------------------------------
	// STATIC ATTRIBUTE
	//---------------------------------------------------------------
	private static ColorSettings instance = null;

	//---------------------------------------------------------------
	// INSTANCE ATTRIBUTE
	//---------------------------------------------------------------
	private Color colorBackgroundBoard;
	private Color colorBackgroundPreview;
	private Color colorGridLineBoard;
	private Color colorGridLinePreview;
	private Color colorPieceI;
	private Color colorPieceJ;
	private Color colorPieceL;
	private Color colorPieceO;
	private Color colorPieceS;
	private Color colorPieceT;
	private Color colorPieceZ;
	private Color colorOutlinePieceI;
	private Color colorOutlinePieceJ;
	private Color colorOutlinePieceL;
	private Color colorOutlinePieceO;
	private Color colorOutlinePieceS;
	private Color colorOutlinePieceT;
	private Color colorOutlinePieceZ;

	private ColorSettings() {
		try {
			this.setColorBackgroundBoard();
			this.setColorBackgroundPreview();
			this.setColorGridLineBoard();
			this.setColorGridLinePreview();
			this.setColorOfPieceI();
			this.setColorOfPieceJ();
			this.setColorOfPieceL();
			this.setColorOfPieceO();
			this.setColorOfPieceS();
			this.setColorOfPieceT();
			this.setColorOfPieceZ();
			this.setColorForOutlineOfPieceI();
			this.setColorForOutlineOfPieceJ();
			this.setColorForOutlineOfPieceL();
			this.setColorForOutlineOfPieceO();
			this.setColorForOutlineOfPieceS();
			this.setColorForOutlineOfPieceT();
			this.setColorForOutlineOfPieceZ();
		}
		catch(NoSuchFieldException nsfe) {
			nsfe.printStackTrace();
		}
		catch(IllegalAccessException iae) {
			iae.printStackTrace();
		}
	}

	//---------------------------------------------------------------
	// PRIVATE INSTANCE METHODS
	//---------------------------------------------------------------
	private void setColorBackgroundBoard() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorBackgroundBoard());
		this.colorBackgroundBoard = (Color)fieldObjectByString.get(null);
	}

	private void setColorBackgroundPreview() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorBackgroundPreview());
		this.colorBackgroundPreview = (Color)fieldObjectByString.get(null);
	}

	private void setColorGridLineBoard() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorGridLineBoard());
		this.colorGridLineBoard = (Color)fieldObjectByString.get(null);
	}

	private void setColorGridLinePreview() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorGridLinePreview());
		this.colorGridLinePreview = (Color)fieldObjectByString.get(null);
	}

	private void setColorOfPieceI() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorOfPieceI());
		this.colorPieceI = (Color)fieldObjectByString.get(null);
	}

	private void setColorOfPieceJ() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorOfPieceJ());
		this.colorPieceJ = (Color)fieldObjectByString.get(null);
	}

	private void setColorOfPieceL() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorOfPieceL());
		this.colorPieceL = (Color)fieldObjectByString.get(null);
	}

	private void setColorOfPieceO() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorOfPieceO());
		this.colorPieceO = (Color)fieldObjectByString.get(null);
	}

	private void setColorOfPieceS() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorOfPieceS());
		this.colorPieceS = (Color)fieldObjectByString.get(null);
	}

	private void setColorOfPieceT() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorOfPieceT());
		this.colorPieceT = (Color)fieldObjectByString.get(null);
	}

	private void setColorOfPieceZ() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorOfPieceZ());
		this.colorPieceZ = (Color)fieldObjectByString.get(null);
	}

	private void setColorForOutlineOfPieceI() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorForOutlineOfPieceI());
		this.colorOutlinePieceI = (Color)fieldObjectByString.get(null);
	}

	private void setColorForOutlineOfPieceJ() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorForOutlineOfPieceJ());
		this.colorOutlinePieceJ = (Color)fieldObjectByString.get(null);
	}

	private void setColorForOutlineOfPieceL() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorForOutlineOfPieceL());
		this.colorOutlinePieceL = (Color)fieldObjectByString.get(null);
	}

	private void setColorForOutlineOfPieceO() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorForOutlineOfPieceO());
		this.colorOutlinePieceO = (Color)fieldObjectByString.get(null);
	}

	private void setColorForOutlineOfPieceS() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorForOutlineOfPieceS());
		this.colorOutlinePieceS = (Color)fieldObjectByString.get(null);
	}

	private void setColorForOutlineOfPieceT() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorForOutlineOfPieceT());
		this.colorOutlinePieceT = (Color)fieldObjectByString.get(null);
	}

	private void setColorForOutlineOfPieceZ() throws NoSuchFieldException, IllegalAccessException {
		Field fieldObjectByString = Color.class.getDeclaredField(Config.getInstance().getColorForOutlineOfPieceZ());
		this.colorOutlinePieceZ = (Color)fieldObjectByString.get(null);
	}

	private Color getColorOfPieceI() {
		return this.colorPieceI;
	}

	private Color getColorOfPieceJ() {
		return this.colorPieceJ;
	}

	private Color getColorOfPieceL() {
		return this.colorPieceL;
	}

	private Color getColorOfPieceO() {
		return this.colorPieceO;
	}

	private Color getColorOfPieceS() {
		return this.colorPieceS;
	}

	private Color getColorOfPieceT() {
		return this.colorPieceT;
	}

	private Color getColorOfPieceZ() {
		return this.colorPieceZ;
	}

	private Color getColorForOutlineOfPieceI() {
		return this.colorOutlinePieceI;
	}

	private Color getColorForOutlineOfPieceJ() {
		return this.colorOutlinePieceJ;
	}

	private Color getColorForOutlineOfPieceL() {
		return this.colorOutlinePieceL;
	}

	private Color getColorForOutlineOfPieceO() {
		return this.colorOutlinePieceO;
	}

	private Color getColorForOutlineOfPieceS() {
		return this.colorOutlinePieceS;
	}

	private Color getColorForOutlineOfPieceT() {
		return this.colorOutlinePieceT;
	}

	private Color getColorForOutlineOfPieceZ() {
		return this.colorOutlinePieceZ;
	}

	//---------------------------------------------------------------
	// PUBLIC INSTANCE METHODS
	//---------------------------------------------------------------
	public Color getColorBackgroundBoard() {
		return this.colorBackgroundBoard;
	}

	public Color getColorBackgroundPreview() {
		return this.colorBackgroundPreview;
	}

	public Color getColorGridLineBoard() {
		return this.colorGridLineBoard;
	}

	public Color getColorGridLinePreview() {
		return this.colorGridLinePreview;
	}

	public Color getColorOfPiece(String pieceName) {
		Color color = null;
		if (pieceName.equals("PieceI"))
			color = this.colorPieceI;
		else if (pieceName.equals("PieceJ"))
			color = this.colorPieceJ;
		else if (pieceName.equals("PieceL"))
			color = this.colorPieceL;
		else if (pieceName.equals("PieceO"))
			color = this.colorPieceO;
		else if (pieceName.equals("PieceS"))
			color = this.colorPieceS;
		else if (pieceName.equals("PieceT"))
			color = this.colorPieceT;
		else if (pieceName.equals("PieceZ"))
			color = this.colorPieceZ;
		return color;
	}

	public Color getColorForOutlineOfPiece(String pieceName) {
		Color color = null;
		if (pieceName.equals("PieceI"))
			color = this.colorOutlinePieceI;
		else if (pieceName.equals("PieceJ"))
			color = this.colorOutlinePieceJ;
		else if (pieceName.equals("PieceL"))
			color = this.colorOutlinePieceL;
		else if (pieceName.equals("PieceO"))
			color = this.colorOutlinePieceO;
		else if (pieceName.equals("PieceS"))
			color = this.colorOutlinePieceS;
		else if (pieceName.equals("PieceT"))
			color = this.colorOutlinePieceT;
		else if (pieceName.equals("PieceZ"))
			color = this.colorOutlinePieceZ;
		return color;
	}

	//---------------------------------------------------------------
	// STATIC METHODS
	//---------------------------------------------------------------
	public static ColorSettings getInstance() {
		if (instance == null)
			instance = new ColorSettings();
		return instance;
	}

} // end class