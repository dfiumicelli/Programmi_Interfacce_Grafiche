/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jfunc
 */
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Dimension;
public class DrawingPanel extends JPanel {
    
    private static final int HORIZONTAL_GAP = 10;
    private static final int VERTICAL_GAP = 10;

    
    private AbstractDrawing dw = null;

    public DrawingPanel(AbstractDrawing dw) {
        super();
        this.dw=dw;
        //updatePanelPreferredSize();
    }
    
    

    public void setDrawing(AbstractDrawing dw) {
        this.dw = dw;
        //updatePanelPreferredSize();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        /* add instructions for your specific drawing */
        dw.draw(g);
    }
    
    /*private void updatePanelPreferredSize() {
        setPreferredSize(new Dimension(
                        dw.getDrawingWidth() + HORIZONTAL_GAP,
                        dw.getDrawingHeight() + VERTICAL_GAP));
    } Metodo di supporto, lo andiamo ad implementare tramite l'override del metodo preferredSize*/

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(
                dw.getDrawingWidth() + HORIZONTAL_GAP,
                dw.getDrawingHeight() + VERTICAL_GAP);
    }
    
    public void setScaleFactor(double scaleFactor) {
        dw.setScaleFactor(scaleFactor);
    }
} // end class
