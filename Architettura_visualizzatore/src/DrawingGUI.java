/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jfunc
 */
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class DrawingGUI extends JFrame {

    private final static int JFRAME_WIDTH = 800;
    private final static int JFRAME_HEIGHT = 600;
    
    
    public DrawingGUI(String title, AbstractDrawing dw) {
        super(title);
        
        DrawingPanel drawPanel = new DrawingPanel(dw);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(JFRAME_WIDTH, JFRAME_HEIGHT));
        JScrollPane scrollPane = new JScrollPane(drawPanel);
        Container contPane=getContentPane();
        contPane.add(scrollPane, BorderLayout.CENTER);
        
        pack();
    }


    public static void main(String[] args) {

        final AbstractDrawing drawing = new ConcreteDrawing(); //final è necessario per evitare che drawing sia riassegnata visto che siamo in un altro thread

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DrawingGUI("Drawing GUI", drawing).setVisible(true);

            }
        }); // end method invokeLater()
    }

} // end class

