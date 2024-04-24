/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jfunc
 */
import java.awt.Graphics;

public abstract class AbstractDrawing {

    protected double scaleFactor = 1.0;
    protected double previousScaleFactor = 1.0;

    public void setScaleFactor(double scaleFactor) {
        this.previousScaleFactor = this.scaleFactor;
        this.scaleFactor = scaleFactor;
        this.rescaleDrawing();
    }

    public abstract void draw(Graphics g);

    public abstract int getDrawingWidth();

    public abstract int getDrawingHeight();

    protected abstract void rescaleDrawing();

} // end class
