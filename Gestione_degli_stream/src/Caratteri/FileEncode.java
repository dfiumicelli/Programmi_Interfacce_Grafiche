/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caratteri;

/**
 *
 * @author jfunc
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileEncode {

    public static void main(String[] args) throws IOException {

        if (args.length < 2)
            throw new IllegalArgumentException("Usage: FileEncode <file-name> <charset> [string]");

        // crea un output stream verso un file
        FileOutputStream fout = new FileOutputStream(args[0]);
        OutputStreamWriter foutWriter = new OutputStreamWriter(fout, args[1]);

        // scrive le stringhe nel file
        for (int i = 2; i < args.length; i++)
            foutWriter.write(args[i] + "\r\n");

        // chiude gli stream
        foutWriter.close();
        fout.close();
    }
} // va lanciato da terminal con "java FileEncode testo.txt utf-8 pippo pluto paperino"
