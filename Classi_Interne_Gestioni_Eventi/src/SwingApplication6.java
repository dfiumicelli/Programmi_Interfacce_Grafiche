import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
//import javax.swing.UIManager;

public class SwingApplication6 extends JFrame {

    private static String labelPrefix = "Number of button clicks: ";
    private static String lab2Prefix = "Executions of Slow Method: ";
    private int numClicks = 0;
    private int numSlowClicks = 0;
    private JButton jbutton;
    private JLabel jlabel;
    private JButton jbut2;
    private JLabel jlab2;

    public SwingApplication6() {
        super("SwingApplication6");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jpanel = new JPanel(new GridLayout(0, 1));
        jpanel.setBorder(BorderFactory.createEmptyBorder(
                                30, //top
                                30, //left
                                10, //bottom
                                30) //right
                                );

        jbutton = new JButton("I'm a Swing button");
        jbutton.addActionListener(eventHandlerBut1());
        jbutton.setMnemonic(KeyEvent.VK_I);
        jlabel = new JLabel(labelPrefix + numClicks + "   ");

        jbut2 = new JButton("Start Slow Method");
        jbut2.addActionListener(eventHandlerBut2());
        jbut2.setMnemonic(KeyEvent.VK_S);
        jlab2 = new JLabel(lab2Prefix + numSlowClicks + "   ");

        jpanel.add(jbutton);
        jpanel.add(jlabel);
        jpanel.add(jbut2);
        jpanel.add(jlab2);

        Container contPane = this.getContentPane();
        contPane.add(jpanel, BorderLayout.CENTER);

        this.pack();
    }

    /*public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jbutton) {
            numClicks++;
            jlabel.setText(labelPrefix + numClicks);
        }
        else if (e.getSource() == jbut2) {
            slowMethod(1);
            numSlowClicks++;
            jlab2.setText(lab2Prefix + numSlowClicks);
        }
    }*/

    

    private ActionListener eventHandlerBut1(){
        class EventHandlerBut1 implements ActionListener{
            @Override
                public void actionPerformed(ActionEvent e){
                numClicks++;
                jlabel.setText(labelPrefix + numClicks);
            }
        
        }
        return new EventHandlerBut1();
    }

    private ActionListener eventHandlerBut2(){
        class EventHandlerBut2 implements ActionListener{
            public void actionPerformed(ActionEvent e){
                slowMethod(1);
                numSlowClicks++;
                jlab2.setText(lab2Prefix + numSlowClicks);
            }
        } 
        return new EventHandlerBut2(); 
    }

    private void slowMethod(int sec) {
        try {
            Thread.sleep(1000 * sec);
        }
        catch(InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SwingApplication6().setVisible(true);
            }
        });

    } // end method main()
} // end class