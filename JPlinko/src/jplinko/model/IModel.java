package jplinko.model;

public interface IModel {

    public void setRows(int rows);

    public int getRows();

    public void setCurrentBetIndex(int CurrentBetIndex);

    public int getCurrentBetIndex();

    public double[] getBetValues();

    public void setRisk(String risk);

    public double[] getMultipliers();

    public double getBalance();

    public void setMode(String mode);

    public String getMode();

    public String getRisk();

    public int getRounds();

    public void setRounds(int rounds);

    public int[][] simulatePlinko(int rows, int numMultipliers);
    
    public int[] getFinalPosition();
    
    public void setBalance(double balance);
    
    public boolean isGameOver();
}
