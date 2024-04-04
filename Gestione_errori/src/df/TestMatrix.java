/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package df;

/**
 *
 * @author jfunc
 */
import javax.swing.JOptionPane;
//import fond.io.InputWindow; non utilizziamo quello di DI JAck, troppo scrauso, ce lo creiamo da soli, bastardo inutile!
public class TestMatrix {

    /*private static int readInt(String message){
        String s= (String) JOptionPane.showInputDialog(
                                            null, 
                                            message, 
                                            "Input", 
                                            JOptionPane.PLAIN_MESSAGE, 
                                            null, 
                                            null, 
                                            "0");
    return Integer.parseInt(s);
        
    } 
    */
    
    private static void errorMsgDialog(String errorMsg){
        JOptionPane.showMessageDialog(null, errorMsg, "Insert Error", JOptionPane.ERROR_MESSAGE);
        
    }
    
    
    
    
    private static int readInt(String message){
        int redInt = Integer.MAX_VALUE;
        String s = null;
        while(redInt == Integer.MAX_VALUE){
            try{
                s = (String) JOptionPane.showInputDialog(
                                            null, 
                                            message, 
                                            "Input", 
                                            JOptionPane.PLAIN_MESSAGE, 
                                            null, 
                                            null, 
                                            "0");
                redInt=Integer.parseInt(s);
            }
            catch(NumberFormatException nfe){
                //System.out.println("Devi Inserire un valore intero.");
                errorMsgDialog("Devi Inserire un valore intero.");
            }
        }
        
            
        return redInt;     
    }
    
            
    public static void main(String[] args) {

        //InputWindow in = new InputWindow();
        int rows = readInt("Inserire il numero di righe");
        int columns = readInt("Inserire il numero di colonne");

        IntegerMatrix matrix = new IntegerMatrix(rows, columns);

        int ijVal = -1;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                ijVal = readInt("Inserire il valore di matrix[" + i + "][" + j + "]");
                matrix.setValue(i, j, ijVal);
            }

        boolean quit = false;
        String str = null;
        int i = -1;
        int j = -1;
        while (!quit) {
            int confirm = JOptionPane.showConfirmDialog(null, "Desideri proseguire?", "Visualizzazione interattiva", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.NO_OPTION)
                quit = true;
            else {
                i = readInt("Inserire indice di riga i");
                j = readInt("Inserire indice di colonna j");
                try{
                    String msg = "matrix[" + i + "][" + j + "] = " + matrix.getValue(i, j);
                    JOptionPane.showMessageDialog(null, msg, "Matrix cell value", JOptionPane.PLAIN_MESSAGE);
                    //System.out.println("matrix[" + i + "][" + j + "] = " + matrix.getValue(i, j));
                }
                catch(ArrayIndexOutOfBoundsException aioobe){
                    errorMsgDialog("Indici non validi");
                }
                
            }
        }
    }
} // end class
