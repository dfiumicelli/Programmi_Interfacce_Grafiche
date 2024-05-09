/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Parte2;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainGUI extends JFrame {

    public MainGUI() {
        super("Scalable drawing ...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        DrawingPanel drawPanel = new DrawingPanel();

        Container contPane = getContentPane();
        contPane.setLayout(new BorderLayout());

        contPane.add(drawPanel, BorderLayout.CENTER);

        pack();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run () {
                new MainGUI().setVisible(true);
            }
        });
    } // end main()

} // end class
