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
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import javax.swing.event.MouseInputListener;

class DrawingPanel extends JPanel implements MouseInputListener{
    private final static int MARGIN = 100;

    private Rectangle2D.Double rect;
    private MainGUI mainGUI;
    private Rectangle clipRect;
    private boolean draggable; //it is true iff a mouse drag event must drag the rect

    public DrawingPanel(MainGUI mainGUI) {
        super();
        this.mainGUI = mainGUI;
        this.rect = new Rectangle2D.Double(100.0, 100.0, 10.0, 10.0);
        this.draggable=false;
        this.clipRect = new Rectangle();
        setBackground(Color.white);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Insert here our drawing
        Graphics2D g2d = (Graphics2D)g;
        this.clipRect.setRect(
                (this.rect.getX() - MARGIN),
                (this.rect.getY() - MARGIN),
                (this.rect.getWidth() + 2 * MARGIN),
                (this.rect.getHeight() + 2 * MARGIN));
        g2d.setClip(this.clipRect);
        
        g2d.setColor(Color.red);
        g2d.fill(this.rect);
        g2d.setColor(Color.black);
        g2d.draw(this.rect);
    }
    
    public void mouseClicked(MouseEvent e) {
        // do nothing
        //System.out.println("EVENT mouseClicked(MouseEvent e)");
        //System.out.println("CLICK on point (" + e.getX() + ", " + e.getY() + ")");
    }

    public void mouseEntered(MouseEvent e)  {
        // do nothing
        //System.out.println("EVENT mouseEntered(MouseEvent e)");
        //System.out.println("ENTERED on point (" + e.getX() + ", " + e.getY() + ")");
    }

    public void mouseExited(MouseEvent e) {
        // do nothing
        //System.out.println("EVENT mouseExited(MouseEvent e)");
        //System.out.println("EXITED on point (" + e.getX() + ", " + e.getY() + ")");
    }

    public void mousePressed(MouseEvent e) {
        //System.out.println("EVENT mousePressed(MouseEvent e)");
        //System.out.println("PRESS on point (" + e.getX() + ", " + e.getY() + ")");
        if (this.rect.contains(e.getX(), e.getY()))
            this.draggable = true;
    }

    public void mouseReleased(MouseEvent e)  {
        // do nothing
        //System.out.println("EVENT mouseReleased(MouseEvent e)");
        //System.out.println("RELEASE on point (" + e.getX() + ", " + e.getY() + ")");
        this.draggable=false;
    }

    //--------------------------------------------
    // java.awt.event.MouseMotionListener methods
    //--------------------------------------------

    public void mouseDragged(MouseEvent e)  {
        //System.out.println("EVENT mouseDragged(MouseEvent e)");
        //System.out.println("DRAGGED on point (" + e.getX() + ", " + e.getY() + ")");
        this.mainGUI.setMouseCoordinates(e.getX(), e.getY());
        if(this.draggable){
            this.rect.setRect(e.getX(), e.getY(), this.rect.getWidth(), this.rect.getHeight());
            this.repaint();
            /*this.repaint(0,
                    e.getX() - MARGIN,
                    e.getY() - MARGIN,
                    2 * MARGIN + (int)this.rect.getWidth(),
                    2 * MARGIN + (int)this.rect.getHeight());*/
        }
    }

    public void mouseMoved(MouseEvent e)  {
        // to do
        //System.out.println("EVENT mouseMoved(MouseEvent e)");
        //System.out.println("MOVED on point (" + e.getX() + ", " + e.getY() + ")");
        this.mainGUI.setMouseCoordinates(e.getX(), e.getY());
    }

} // end class
