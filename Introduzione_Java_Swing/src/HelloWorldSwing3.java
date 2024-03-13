import java.awt.Dimension;
import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JFrame;
public class HelloWorldSwing3 {
    public final static int FRAME_WIDTH=800; //definisco variabili statiche per una migliore leggibilità, potevo non farlo
    public final static int FRAME_HEIGHT=300;
    public static void main(String[] args) {
        JFrame frame=new JFrame("HelloWorldSwingGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Stoppa il programma quando viene chiusa la finestra
        JLabel label=new JLabel("Hello World"); //crea un'etichetta che però in questo modo ancora non vedo
        Container contPane=frame.getContentPane();
        contPane.add(label); //Aggiunge l'etichetta al panel del frame e calcola le dimensioni della finestra in base a quelle del componente
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT)); //Ridimensiona la finestra "ignorando" le dimensioni impostate dal contPane
        frame.pack(); //calcola le dimensioni della finestra in base alle preferenze impostate
        frame.setVisible(true); //La rende visibile.
    }
}
