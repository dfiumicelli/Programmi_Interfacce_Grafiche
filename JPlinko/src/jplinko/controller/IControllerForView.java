package jplinko.controller;

public interface IControllerForView {

    public void openJPlinkoGUI();

    public int getRows();

    public void setRows(int rows);

    public int getCurrentBetIndex();

    public void setCurrentBetIndex(int CurrentBetIndex);

    public double[] getBetValues();

    public void setRisk(String risk);

    public double[] getMultipliers();

    public double getBalance();

    public void setMode(String mode);

    public String getMode();

    public String getRisk();

    public int getRounds();

    public void setRounds(int rounds);

}
