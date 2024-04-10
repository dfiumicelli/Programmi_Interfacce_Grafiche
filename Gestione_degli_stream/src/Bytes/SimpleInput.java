/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bytes;

/**
 *
 * @author jfunc
 */
import java.io.IOException;

public class SimpleInput {

    public static void main(String[] args) throws IOException {
        int car;
        while ((car = System.in.read()) >= 0)
            System.out.write(car);
    }

} // end class

