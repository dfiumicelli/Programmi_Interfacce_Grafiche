/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jplinko.utils;

import javax.swing.*;
import java.awt.*;

public class RoundedToggleButton extends JToggleButton {
    private int cornerRadius;

    public RoundedToggleButton(String text, int radius, boolean selected) {
        super(text, selected);
        this.cornerRadius = radius;
        setContentAreaFilled(false); // Rimuove il riempimento predefinito
        setFocusPainted(false); // Rimuove il bordo di focus
        setBorderPainted(false); // Rimuove il bordo standard
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
        Color defaultBg = UIManager.getColor("ToggleButton.background");
        Color selectedBg = new Color(155,161,157);
        Color borderColor = UIManager.getColor("ToggleButton.border");

        // Cambia colore se è premuto
        Color bgColor = isSelected() ? selectedBg : defaultBg;
        
        // Disegna il pulsante stondato
        g2.setColor(bgColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(2));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);


        g2.dispose();
        super.paintComponent(g);
    }
}

