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
import java.io.FileNotFoundException;
import java.io.IOException;

public class CopyBytes {
    public static void main(String[] args) throws IllegalArgumentException, IOException {

        if (args.length != 2)
            throw new IllegalArgumentException("\nUsage: CopyBytes <source file> <destination file>");

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream(args[0]);
            out = new FileOutputStream(args[1]);

            byte[] buffer = new byte[1000];
            int bytesNumber;

            while ((bytesNumber = in.read(buffer)) >= 0)
                out.write(buffer, 0, bytesNumber);
        }
        catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        finally {
            if (in != null)
                in.close();
            if (out != null)
                out.close();
        }
    } // end main()
} // end class
//sta classe va lanciata da linea di comando con "java CopyBites <copyfile> <destinationfile>"