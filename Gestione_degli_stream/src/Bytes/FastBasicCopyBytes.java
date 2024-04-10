/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bytes;

/**
 *
 * @author jfunc
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FastBasicCopyBytes {

    public static void main(String[] args) throws Exception {

        FileInputStream in = new FileInputStream("origine.x");
        FileOutputStream out = new FileOutputStream("destinazione.x");

        byte[] buffer = new byte[1000];
        int bytesNumber;

        while ((bytesNumber = in.read(buffer)) >= 0)
            out.write(buffer, 0, bytesNumber);

        in.close();
        out.close();
    }
} //è bufferizzato
