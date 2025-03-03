/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jplinko.view;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dfiumicelli
 */
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

    @Override
    public int getRows() {
        return jplinkoGUI.getRows();
    }

    public static IView getInstance() {
        if (instance == null) {
            instance = new View();
        }
        return instance;
    }

}
