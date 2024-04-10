/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package df;

/**
 *
 * @author jfunc
 */
import java.io.IOException;

public class FastSimpleInput {

    public static void main(String[] args) throws IOException {

        byte[] byteArr = new byte[8];
        int car;
        while ((car = System.in.read(byteArr)) >= 0)
            System.out.write(byteArr, 0, byteArr.length);
    }

} // end class 
