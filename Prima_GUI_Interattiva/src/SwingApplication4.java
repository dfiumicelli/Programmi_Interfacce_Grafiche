import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import javax.swing.UIManager;
import javax.swing.SwingWorker;

public class SwingApplication4 extends JFrame implements ActionListener {
    private String labelPrefix= "Number of button clicks: ";
    private String lab2Prefix= "Number of Slow Method";
    private int numClicks=0;
    private int numSlowClicks=0;
    private JLabel jlabel; //fava
    private JLabel jlab2;
    private JButton jbutton;
    private JButton jbut2;

    public SwingApplication4() {
        super("SwingApplication4");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jpanel = new JPanel(new GridLayout(0, 1)); //0=n righe, 1=una colonna, è anti intuitivo ma è così
        jpanel.setBorder(BorderFactory.createEmptyBorder(
                                 30, //top
                                 30, //left
                                 10, //bottom
                                 30) //right
                                 );

        jbutton = new JButton("I'm a Swing button");
        jbutton.addActionListener(this);
        jbutton.setMnemonic(KeyEvent.VK_I);
        jlabel = new JLabel(labelPrefix + numClicks + " ");
                         
        jbut2 = new JButton("Start Slow Method");
        jbut2.addActionListener(this);
        jbut2.setMnemonic(KeyEvent.VK_S);
        jlab2 = new JLabel(lab2Prefix + numSlowClicks + " ");

        jpanel.add(jlabel);
        jpanel.add(jbutton);
        jpanel.add(jlab2);
        jpanel.add(jbut2);
        

        Container contPane = this.getContentPane();
        contPane.add(jpanel, BorderLayout.CENTER);

        this.pack();
    } // end constructor

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbutton) {
            numClicks++;
            jlabel.setText(labelPrefix + numClicks);
        }
        else if (e.getSource() == jbut2) {
            //slowMethod(10);
            //numSlowClicks++;
            //jlab2.setText(lab2Prefix + numSlowClicks);
            executeSlowMethodInBackground(10);
        }
    }

    private void slowMethod(int sec) {
        try {
            Thread.sleep(1000 * sec);
        }
        catch(InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    private void executeSlowMethodInBackground(final int sec) {
        SwingWorker worker = new SwingWorker<Void, Void>() {
            @Override
            public Void doInBackground() {
                System.out.println("in ... doInBackground()");
                slowMethod(sec);
                return null;
            }

            @Override
            public void done() {
                System.out.println("in ... done()");
                numSlowClicks++;
                jlab2.setText(lab2Prefix + numSlowClicks);
            }
        };
        worker.execute();
    } // end method executeSlowMethodInBackground()


    public static void main(String[] args) throws Exception{
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SwingApplication4().setVisible(true);
            }
        });
    } // end method main()

} // end class
