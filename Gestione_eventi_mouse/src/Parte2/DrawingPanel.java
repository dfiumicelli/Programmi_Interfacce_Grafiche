/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Parte2;

import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.geom.Line2D;

class DrawingPanel extends JPanel {
    private final static int NUM_ROWS = 20;
    private final static int NUM_COLUMNS = 20;

    private final static int X_MARGIN = 10;
    private final static int Y_MARGIN = 10;
    private final static int NUM_CELLS = 10;
    private final static Color[] COLOR_ARRAY = {Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.yellow};
    
    private boolean showGrid;
    private double uX;
    private double uY;
    private Line2D.Double line;
    
    private int[] iIndexArray;
    private int[] jIndexArray;
    private Color[] colorArray;
    private Rectangle2D.Double rect;
    
    public DrawingPanel() {
        super();
        this.rect = new Rectangle2D.Double();
        this.showGrid = true;
        this.line = new Line2D.Double();
        setBackground(Color.white);
        setIndexArray();
        setColorArray();
    }
    
    private void setGridUnit() {
        this.uX = (double)(getWidth() - 2 * X_MARGIN) / (double)NUM_COLUMNS;
        this.uY = (double)(getHeight() - 2 * Y_MARGIN) / (double)NUM_ROWS;
    }
    
    
    private Color getRandomColor() {
        int i = ((int)(COLOR_ARRAY.length * Math.random())) % COLOR_ARRAY.length;
        return COLOR_ARRAY[i];
    }

    private void paintGrid(Graphics2D g2d){
        Color oldColor = g2d.getColor();
        
        //Print horizontal line
        for (int i=0; i<= NUM_ROWS; i++){
            this.line.setLine(X_MARGIN, Y_MARGIN + i * this.uY, 
                                 X_MARGIN + NUM_COLUMNS * this.uX, Y_MARGIN + i * this.uY);
            g2d.draw(this.line);
        }
        
        for (int j = 0; j <= NUM_COLUMNS; j++) {
            this.line.setLine(X_MARGIN + j * this.uX, Y_MARGIN,
                              X_MARGIN + j * this.uX, Y_MARGIN + NUM_ROWS * this.uY);
            g2d.draw(this.line);
        }
        
        g2d.setColor(oldColor);
    }
    
     private void setIndexArray() {
        this.iIndexArray = new int[NUM_CELLS];
        this.jIndexArray = new int[NUM_CELLS];
        for (int n = 0; n < NUM_CELLS; n++) {
            this.iIndexArray[n] = ((int)(NUM_ROWS * Math.random())) % NUM_ROWS;
            this.jIndexArray[n] = ((int)(NUM_COLUMNS * Math.random())) % NUM_COLUMNS;
        }
    }

    private void setColorArray() {
        this.colorArray = new Color[NUM_CELLS];
        for (int n = 0; n < NUM_CELLS; n++)
            this.colorArray[n] = getRandomColor();
    }
    
    private void paintCells(Graphics2D g2d) {
        Color oldColor = g2d.getColor();
        for (int n = 0; n < NUM_CELLS; n++) {
            this.rect.setRect(X_MARGIN + this.jIndexArray[n] * this.uX, Y_MARGIN + this.iIndexArray[n] * this.uY, this.uX, this.uY);
            g2d.setColor(this.colorArray[n]);
            g2d.fill(this.rect);
            g2d.setColor(Color.black);
            g2d.draw(this.rect);
        }
        g2d.setColor(oldColor);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setGridUnit();
        // Insert here our drawing
        Graphics2D g2d = (Graphics2D)g;
        if (this.showGrid)
            paintGrid(g2d);
        paintCells(g2d);
    }

} // end class
