import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageDrawer extends JFrame {

    private BufferedImage img;
    //private Image img = null;

    public ImageDrawer(String fileImage) {
        super("ImageDrawer");

        loadImage(fileImage);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        Container contPane = getContentPane();
        contPane.add(new DrawingCanvas(), BorderLayout.CENTER);
        
        pack();
    } // end constructor

    private void loadImage(String fileImage) {
        this.img = null;
        try {
            this.img = ImageIO.read(new File(fileImage));
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    } // end loadImage()

    class DrawingCanvas extends JPanel {

        DrawingCanvas() {
            super();
            setBackground(Color.white);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            draw(g);
        }

    } // end inner class

    private void draw(Graphics g) {
        // add below the code for drawing
        // ...
        //g.drawLine(50, 50, 100, 100);
        //g.fillOval(110, 110, 200, 200);
        //g.drawImage(this.img, 200, 200, null);
        g.drawImage(this.img, 100, 100, 165, 165, 0, 1386, 64, 1451, Color.white, null);
        //g.drawImage(this.img, 100, 100, 165, 165, 0, 1386, 64, 1451, null);
        //g.drawImage(this.img, 100, 100, 230, 230, 0, 1386, 64, 1451, null);
    }

    public static void main(String[] args) {

        //String fileImage = "C:\\Users\\LucaGrilli\\Documents\\pigdm2024\\src\\20240418\\strawberry.jpg";

        if (args.length < 1) {
            String errorMsg = "Usage: java ImageDrawer <file-image>";
            System.out.println(errorMsg);
            System.exit(-1);
        }

        String fileImage = args[0];

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ImageDrawer(fileImage).setVisible(true);
            }
        });
    }

} // end class