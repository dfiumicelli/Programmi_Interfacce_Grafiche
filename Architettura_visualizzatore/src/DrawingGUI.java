/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jfunc
 */
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class DrawingGUI extends JFrame implements ActionListener{

    private final static int JFRAME_WIDTH = 800;
    private final static int JFRAME_HEIGHT = 600;
    
    private final static String ZOOM_IN = "zoom in";
    private final static String ZOOM_OUT = "zoom out";
    private final static double[] SCALE_FACTOR_ARRAY = {0.01, 0.025, 0.05, 0.10, 0.25, 0.50, 0.75,
                            1.00, 1.25, 1.50, 1.75, 2.00, 4.00, 8.00,
                            16.00, 24.00, 32.00, 64.00};
    private final static int DEFAULT_SCALE_INDEX = 7;

    private int scaleIndex;
    private DrawingPanel drawPanel;

    
    public DrawingGUI(String title, AbstractDrawing dw) {
        super(title);
        this.scaleIndex = DEFAULT_SCALE_INDEX;
        
        JToolBar toolBar = new JToolBar("Still draggable");
        addButtons(toolBar);
        this.drawPanel = new DrawingPanel(dw);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(JFRAME_WIDTH, JFRAME_HEIGHT));
        JScrollPane scrollPane = new JScrollPane(drawPanel);
        Container contPane=getContentPane();
        contPane.add(scrollPane, BorderLayout.CENTER);
        contPane.add(toolBar, BorderLayout.PAGE_START);
        pack();
    }
    
    private void addButtons(JToolBar toolBar) {
        // Insert the "zoom in" button.
        JButton zoomIn = new JButton(ZOOM_IN);
        zoomIn.addActionListener(this);
        zoomIn.setActionCommand(ZOOM_IN);
        toolBar.add(zoomIn);

        // Insert the "zoom out" button.
        JButton zoomOut = new JButton(ZOOM_OUT);
        zoomOut.addActionListener(this);
        zoomOut.setActionCommand(ZOOM_OUT);
        toolBar.add(zoomOut);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        // Handle each button.
        if (ZOOM_IN.equals(cmd)) { // "zoom in" button clicked
            zoomIn();
            System.out.println("Pressed button: " + ZOOM_IN + " - scaleFactor = " + SCALE_FACTOR_ARRAY[this.scaleIndex]);
            drawPanel.revalidate();
            drawPanel.repaint();
        }
        else if (ZOOM_OUT.equals(cmd)) { // "zoom out" button clicked
            zoomOut();
            System.out.println("Pressed button: " + ZOOM_OUT + " - scaleFactor = " + SCALE_FACTOR_ARRAY[this.scaleIndex]);
            drawPanel.revalidate();
            drawPanel.repaint();
        }
    }
    
    private void zoomIn() {
        if ((this.scaleIndex + 1) < SCALE_FACTOR_ARRAY.length) {
            this.scaleIndex++;
            this.drawPanel.setScaleFactor(SCALE_FACTOR_ARRAY[this.scaleIndex]);
        }
    }

    private void zoomOut() {
        if ((this.scaleIndex - 1) > 0) {
            this.scaleIndex--;
            this.drawPanel.setScaleFactor(SCALE_FACTOR_ARRAY[this.scaleIndex]);
        }
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

