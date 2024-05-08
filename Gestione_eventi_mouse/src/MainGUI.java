/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jfunc
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
public class MainGUI extends JFrame {
    private JLabel mouseCoordinates;

    public MainGUI() {
        super("Move a graphical object ...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        DrawingPanel drawPanel = new DrawingPanel(this);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("Mouser pointer position (x, y) = "));
        this.mouseCoordinates = new JLabel("(0, 0)");
        bottomPanel.add(this.mouseCoordinates);

        Container contPane = getContentPane();
        contPane.setLayout(new BorderLayout());

        contPane.add(drawPanel, BorderLayout.CENTER);
        contPane.add(bottomPanel, BorderLayout.PAGE_END);
        pack();
    }

    public void setMouseCoordinates(int x, int y) {
        this.mouseCoordinates.setText("(" + x + ", " + y + ")");
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run () {
                new MainGUI().setVisible(true);
            }
        });
    } // end main()

} // end class