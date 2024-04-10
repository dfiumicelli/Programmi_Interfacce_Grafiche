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

public class BasicCopyBytes {
    
    public static void main(String[] args) throws Exception {

        FileInputStream in = new FileInputStream("origine.x");
        FileOutputStream out = new FileOutputStream("destinazione.x");

        int c;
        while ((c = in.read()) != -1)
            out.write(c);

        in.close();
        out.close();
    }
}
