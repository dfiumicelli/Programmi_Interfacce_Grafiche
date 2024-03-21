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

public class SwingApplication9 extends JFrame {

    private static String labelPrefix = "Number of button clicks: ";
    private static String lab2Prefix = "Executions of Slow Method: ";
    private int numClicks = 0;
    private int numSlowClicks = 0;
    private JButton jbutton;
    private JLabel jlabel;
    private JButton jbut2;
    private JLabel jlab2;

    public SwingApplication9() {
        super("SwingApplication9");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jpanel = new JPanel(new GridLayout(0, 1));
        jpanel.setBorder(BorderFactory.createEmptyBorder(
                                30, //top
                                30, //left
                                10, //bottom
                                30) //right
                                );

        jbutton = new JButton("I'm a Swing button");
        jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                HandlerBut1();
            }
        });
        jbutton.setMnemonic(KeyEvent.VK_I);
        jlabel = new JLabel(labelPrefix + numClicks + "   ");

        jbut2 = new JButton("Start Slow Method");
        jbut2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                HandlerBut2();
            }
        }); //abbiamo messo tutto dentro l'argomento della funzione per evitare di usare nuovi metodi, è quello usato da NetBeans.
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

    private void HandlerBut1(){
        numClicks++;
        jlabel.setText(labelPrefix + numClicks);
    }

    private void HandlerBut2(){
        slowMethod(1);
        numSlowClicks++;
        jlab2.setText(lab2Prefix + numSlowClicks);
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
                new SwingApplication9().setVisible(true);
            }
        });

    } // end method main()
} // end class