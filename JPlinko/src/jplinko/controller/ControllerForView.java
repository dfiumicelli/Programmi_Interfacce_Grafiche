/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jplinko.controller;
import jplinko.view.View;
import jplinko.model.Model;
/**
 *
 * @author dfiumicelli
 */
public class ControllerForView implements IControllerForView {

    private static ControllerForView instance = null;

    private ControllerForView() {
        //to do
    }
    
    @Override
    public void openJPlinkoGUI(){
        View.getInstance().openJPlinkoGUI();
    }

    @Override
    public int getRows() {
        return Model.getInstance().getRows();
    }

    @Override
    public void handleRowChange(int rows) {
        Model.getInstance().setRows(rows);
    }

    @Override
    public int getCurrentBetIndex() {
        return Model.getInstance().getCurrentBetIndex();
    }

    @Override
    public void setCurrentBetIndex(int CurrentBetIndex) {
        Model.getInstance().setCurrentBetIndex(CurrentBetIndex);
    }
    
    
    

    public static IControllerForView getInstance() {
        if (instance == null) {
            instance = new ControllerForView();
        }
        return instance;
    }
}
