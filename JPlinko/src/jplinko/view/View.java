package jplinko.view;

import java.util.logging.Level;
import java.util.logging.Logger;

public class View implements IView {

    private static View instance = null;
    protected JPlinkoGUI jplinkoGUI = null;

    private View() {
        //TO-DO
    }

    @Override
    public void openJPlinkoGUI() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (jplinkoGUI == null) {
                    try {
                        jplinkoGUI = new JPlinkoGUI();
                    } catch (Exception ex) {
                        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                jplinkoGUI.setVisible(true);
            }
        });
    }

    public static IView getInstance() {
        if (instance == null) {
            instance = new View();
        }
        return instance;
    }

}
