/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jplinko.model;

/**
 *
 * @author dfiumicelli
 */
public class Model implements IModel {

    private static Model instance = null;

    private Model() {
        //to do
    }

    public static IModel getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

}
