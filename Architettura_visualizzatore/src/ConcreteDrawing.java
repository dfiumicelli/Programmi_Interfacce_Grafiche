/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jfunc
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class ConcreteDrawing extends AbstractDrawing {

    private Rectangle2D.Double rect;

    public ConcreteDrawing() {
        this.rect = new Rectangle2D.Double(100, 100, 300, 300); // x, y, width, height
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(Color.red);
        g2d.fill(rect);
        g2d.setColor(Color.black);
        g2d.draw(rect);
    }
    
    @Override
    public int getDrawingWidth() {
        return ((int)Math.round(rect.getX() + rect.getWidth() + 0.5));
    }

    @Override
    public int getDrawingHeight() {
        return ((int)Math.round(rect.getY() + rect.getHeight() + 0.5));
    }
    
    @Override
    protected void rescaleDrawing() {
        this.rect.setRect(
                this.rect.getX() * this.scaleFactor / this.previousScaleFactor,
                this.rect.getY() * this.scaleFactor / this.previousScaleFactor,
                this.rect.getWidth() * this.scaleFactor / this.previousScaleFactor,
                this.rect.getHeight() * this.scaleFactor / this.previousScaleFactor);
    }

} // end class
