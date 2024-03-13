import java.awt.Dimension;
import javax.swing.JFrame;
public class HelloWorldSwing2 {
    public final static int FRAME_WIDTH=800; //definisco variabili statiche per una migliore leggibilità, potevo non farlo
    public final static int FRAME_HEIGHT=300;
    public static void main(String[] args) {
        JFrame frame=new JFrame("HelloWorldSwingGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Stoppa il programma quando viene chiusa la finestra
        frame.setSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT)); //Ridimensiona la finestra
        frame.setVisible(true); //La rende visibile
    }
    
    
}
