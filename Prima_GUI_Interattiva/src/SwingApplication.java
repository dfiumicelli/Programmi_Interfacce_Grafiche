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

public class SwingApplication extends JFrame implements ActionListener {
    private String labelPrefix= "Number of button clicks: ";
    private int numClicks=0;
    private JLabel jlabel; //fava

    public SwingApplication() {
        super("SwingApplication");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jpanel = new JPanel(new GridLayout(0, 1)); //0=n righe, 1=una colonna, è anti intuitivo ma è così
        jpanel.setBorder(BorderFactory.createEmptyBorder(
                                 30, //top
                                 30, //left
                                 10, //bottom
                                 30) //right
                                 );

        JButton jbutton = new JButton("I'm a Swing button");
        //JLabel jlabel = new JLabel("Number of button clicks: 0");
        jbutton.addActionListener(this); //abbiamo passato al metodo add l'oggetto SwingApplication di tipo ActionListener
        jlabel=new JLabel(labelPrefix + numClicks + " ");

        jpanel.add(jbutton);
        jpanel.add(jlabel);

        Container contPane = this.getContentPane();
        contPane.add(jpanel, BorderLayout.CENTER);

        this.pack();
    } // end constructor

    public void actionPerformed(ActionEvent e){
        numClicks++;
        jlabel.setText(labelPrefix + numClicks);
    } 


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SwingApplication().setVisible(true);
            }
        });
    } // end method main()

} // end class
