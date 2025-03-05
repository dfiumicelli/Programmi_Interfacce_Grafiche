/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package jplinko.model;

/**
 *
 * @author dfiumicelli
 */
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
}
