/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jplinko.utils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.*;
/**
 *
 * @author dfiumicelli
 */
public class BallIcon implements Icon{
    private int size;
    private Color color;

    public BallIcon(int size, Color color) {
        this.size = size;
        this.color = color;
    }
    
    

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        g.fillOval(x, y, size-1, size-1);
        g.setColor(Color.BLACK);
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    @Override
    public int getIconHeight() {
        return size;
    }
    
}
