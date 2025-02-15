/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jplinko.utils;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    private int cornerRadius;

    public RoundedButton(String text, int radius) {
        super(text);
        this.cornerRadius = radius;
        setContentAreaFilled(false); // Rimuove il riempimento predefinito
        setFocusPainted(false); // Rimuove il bordo di focus
        setBorderPainted(false); // Rimuove il bordo standard
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Sfondo colorato
        g2.setColor(getModel().isPressed() ? Color.GRAY : getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Disegna il testo del bottone
        //g2.setColor(getForeground());
        //g2.setFont(getFont());
        //FontMetrics fm = g2.getFontMetrics();
        //int textX = (getWidth() - fm.stringWidth(getText())) / 2;
        //int textY = (getHeight() + fm.getAscent()) / 2 - 2;
        //g2.drawString(getText(), textX, textY);

        g2.dispose();
        super.paintComponent(g);
    }
}

