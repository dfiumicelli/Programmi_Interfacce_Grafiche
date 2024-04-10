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

public class FastSimpleInput {

    public static void main(String[] args) throws IOException {

        byte[] byteArr = new byte[8];
        int count; //rappresenta il numero di byte letti
        while ((count = System.in.read(byteArr)) >= 0)
            System.out.write(byteArr, 0, count);
    }

} // crea un buffer di 8 byte
