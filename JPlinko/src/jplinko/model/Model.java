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

    
}
