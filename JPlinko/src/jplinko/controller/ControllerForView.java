package jplinko.controller;

import jplinko.view.View;
import jplinko.model.Model;

public class ControllerForView implements IControllerForView {

    private static ControllerForView instance = null;

    private ControllerForView() {
        //to do
    }

    @Override
    public void openJPlinkoGUI() {
        View.getInstance().openJPlinkoGUI();
    }

    @Override
    public int getRows() {
        return Model.getInstance().getRows();
    }

    @Override
    public void setRows(int rows) {
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

    @Override
    public double[] getBetValues() {
        return Model.getInstance().getBetValues();
    }

    @Override
    public void setRisk(String risk) {
        Model.getInstance().setRisk(risk);
    }

    @Override
    public double[] getMultipliers() {
        return Model.getInstance().getMultipliers();
    }

    @Override
    public double getBalance() {
        return Model.getInstance().getBalance();
    }

    @Override
    public void setMode(String mode) {
        Model.getInstance().setMode(mode);
    }

    @Override
    public String getMode() {
        return Model.getInstance().getMode();
    }

    @Override
    public String getRisk() {
        return Model.getInstance().getRisk();
    }

    @Override
    public int getRounds() {
        return Model.getInstance().getRounds();
    }

    @Override
    public void setRounds(int rounds) {
        Model.getInstance().setRounds(rounds);
    }

    @Override
    public int[][] simulatePlinko(int rows, int numMultipliers) {
        return Model.getInstance().simulatePlinko(rows, numMultipliers);
    }
    
    @Override
    public void setBalance(double balance){
        Model.getInstance().setBalance(balance);
    }

    @Override
    public int[] getFinalPosition() {
        return Model.getInstance().getFinalPosition();
    }
    
    

    public static IControllerForView getInstance() {
        if (instance == null) {
            instance = new ControllerForView();
        }
        return instance;
    }
}
