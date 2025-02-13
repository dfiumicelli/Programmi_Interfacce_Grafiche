package jtetris.view;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

import jtetris.controller.ControllerForView;
import jtetris.utils.Config;

public class PreviewPanel extends JPanel {

	//---------------------------------------------------------------
	// STATIC CONSTANTS
	//---------------------------------------------------------------
	private final static int CELL_SIZE = 15; // numer of pixels
	private final static int NUM_ROWS = 5; // number of rows in the preview grid
	private final static int NUM_COLUMNS = 5; // number of rows in the preview grid
	private final static int I_INDEX_REFERENCE_BLOCK = 2;
	private final static int J_INDEX_REFERENCE_BLOCK = 2;
	private final static Dimension PREFERRED_SIZE = new Dimension(NUM_COLUMNS * CELL_SIZE, NUM_ROWS * CELL_SIZE);
	private final static int X_MARGIN = 2;
	private final static int Y_MARGIN = 2;

	//---------------------------------------------------------------
	// INSTANCE ATTRIBUTES
	//---------------------------------------------------------------
	private boolean isGridVisibleInPreview;
	private boolean isRefBlockRecognizableInPreview;
	private boolean isPreviewPieceAvailable;
	public double uX;
	public double uY;
	private Line2D.Double line;
	private Rectangle2D.Double block;

	public PreviewPanel() {
		super();
		this.isGridVisibleInPreview = Config.getInstance().isGridVisibleInPreview();
		this.isRefBlockRecognizableInPreview = Config.getInstance().isRefBlockRecognizableInPreview();
		this.isPreviewPieceAvailable = false;
		this.line = new Line2D.Double();
		this.block = new Rectangle2D.Double();
		this.setBackground(ColorSettings.getInstance().getColorBackgroundPreview());
	}

	//---------------------------------------------------------------
	// PRIVATE INSTANCE METHODS
	//---------------------------------------------------------------

	private void paintGrid(Graphics2D g2d) {
		Color oldColor = g2d.getColor();
		g2d.setColor(ColorSettings.getInstance().getColorGridLinePreview());

		// Paint the horizontal grid lines
		for (int i = 0; i <= NUM_ROWS; i++) {
			this.line.setLine(X_MARGIN, Y_MARGIN + i * this.uY,
							  X_MARGIN + NUM_COLUMNS * this.uX, Y_MARGIN + i * this.uY);
			g2d.draw(this.line);
		}

		// Paint the vertical grid lines
		for (int j = 0; j <= NUM_COLUMNS; j++) {
			this.line.setLine(X_MARGIN + j * this.uX, Y_MARGIN,
							  X_MARGIN + j * this.uX, Y_MARGIN + NUM_ROWS * this.uY);
			g2d.draw(this.line);
		}

		g2d.setColor(oldColor);
	} // end method paintGrid()

	private void paintPreviewPiece(Graphics2D g2d) {
		Color oldColor = g2d.getColor();
		g2d.setColor(ColorSettings.getInstance().getColorOfPiece(ControllerForView.getInstance().getNameOfPreviewPiece()));

		for (int blockLabel = 0; blockLabel < ControllerForView.getInstance().numBlocksOfPreviewPiece(); blockLabel++) {
			this.block.setRect(X_MARGIN + this.uX * (double)(J_INDEX_REFERENCE_BLOCK + ControllerForView.getInstance().jRelOfPreviewPiece(blockLabel)),
							   Y_MARGIN + this.uY * (double)(NUM_ROWS - 1 - I_INDEX_REFERENCE_BLOCK - ControllerForView.getInstance().iRelOfPreviewPiece(blockLabel)),
							   this.uX, this.uY);
			g2d.fill(this.block);
		}

		g2d.setColor(ColorSettings.getInstance().getColorForOutlineOfPiece(ControllerForView.getInstance().getNameOfPreviewPiece()));
		for (int blockLabel = 0; blockLabel < ControllerForView.getInstance().numBlocksOfPreviewPiece(); blockLabel++) {
			this.block.setRect(X_MARGIN + this.uX * (double)(J_INDEX_REFERENCE_BLOCK + ControllerForView.getInstance().jRelOfPreviewPiece(blockLabel)),
							   Y_MARGIN + this.uY * (double)(NUM_ROWS - 1 - I_INDEX_REFERENCE_BLOCK - ControllerForView.getInstance().iRelOfPreviewPiece(blockLabel)),
							   this.uX, this.uY);
			g2d.draw(this.block);
		}

		// It makes the reference block recognizable if it is enabled the flag isRefBlockRecognizableInPreview
		if (this.isRefBlockRecognizableInPreview) {
			this.block.setRect(X_MARGIN + this.uX * 0.25 + this.uX * (double)(J_INDEX_REFERENCE_BLOCK),
							   Y_MARGIN + this.uY * 0.25 + this.uY * (double)(NUM_ROWS - 1 - I_INDEX_REFERENCE_BLOCK),
							   this.uX * 0.5, this.uY * 0.5);
			g2d.draw(this.block);
		}

		g2d.setColor(oldColor);
	} // end method paintPreviewPiece()

	//---------------------------------------------------------------
	// PUBLIC INSTANCE METHODS
	//---------------------------------------------------------------
	public void setGridUnit() {
		this.uX = (double)(getWidth() - 2 * X_MARGIN) / (double)NUM_COLUMNS;
		this.uY = (double)(getHeight() - 2 * Y_MARGIN) / (double)NUM_ROWS;
	}
	
	public void setPreviewPieceAvailable() {
		this.isPreviewPieceAvailable = true;
	}
	
	public void setPreviewPieceUnavailable() {
		this.isPreviewPieceAvailable = false;
	}

	@Override
	public Dimension getMaximumSize() {
		return PREFERRED_SIZE;
	}

	@Override
	public Dimension getPreferredSize() {
		return PREFERRED_SIZE;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Insert here our drawing
		Graphics2D g2d = (Graphics2D)g;
		if (this.isGridVisibleInPreview)
			this.paintGrid(g2d);
		if (this.isPreviewPieceAvailable)
			this.paintPreviewPiece(g2d);
	}

} // end class
