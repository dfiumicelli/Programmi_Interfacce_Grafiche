/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jfunc
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JPanel;


public class BasicDrawer extends JFrame {

    public BasicDrawer() {
        super("BasicDrawer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        Container contPane=getContentPane();
        contPane.add(new DrawingCanvas(),BorderLayout.CENTER);
        pack();
    }
    
    class DrawingCanvas extends JPanel{

        public DrawingCanvas() {
            super();    
        }
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            draw(g);
        }
    }

    private void draw(Graphics g){
        Color startColor=g.getColor();
            //g.drawRect(100, 100, 300, 200);
            g.setColor(Color.green);
            g.fillRect(150, 150, 300, 200);
            g.setColor(Color.pink);
            g.drawRect(150, 150, 300, 200);

            g.setColor(Color.red);
            g.fillOval(100, 100, 300, 200);
            g.setColor(Color.blue);
            g.drawOval(100, 100, 300, 200);
            g.setColor(startColor);
    
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BasicDrawer().setVisible(true);
            }
        });
    }

} // end class