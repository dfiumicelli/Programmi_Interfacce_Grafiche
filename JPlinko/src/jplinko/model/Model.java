/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jplinko.model;

import jplinko.controller.ControllerForView;

/**
 *
 * @author dfiumicelli
 */
public class Model implements IModel {

    private static Model instance = null;
    private int rows;
    private int currentBetIndex;
    private double[] betValues = {0.10, 0.20, 0.50, 1.00, 2.00, 3.00, 4.00, 5.00, 10.00, 15.00, 25.00, 50.00, 75.00, 100.00};

    private Model() {
       this.rows = 16;
       this.currentBetIndex = 4;
    }

    public static IModel getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    @Override
    public void setRows(int rows) {
        this.rows = rows;
    }
    
    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getCurrentBetIndex() {
        return currentBetIndex;
    }

    @Override
    public void setCurrentBetIndex(int currentBetIndex) {
        this.currentBetIndex = currentBetIndex;
    }

    @Override
    public double[] getBetValues() {
        return betValues;
    }

    
}
