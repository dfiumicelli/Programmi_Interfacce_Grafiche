/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caratteri;

/**
 *
 * @author jfunc
 */
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class ReadCSV {

    public static LinkedList<String[]> getRows(String fileName, String charset) throws FileNotFoundException, IOException {
        LinkedList<String[]> lstRows = null;

        BufferedReader buffRead = null; //ci permette di fare un'acquisizione bufferizzata più efficiente. Si inoltre carico del metodo readLine()
        try {
            //buffRead = new BufferedReader(new FileReader(fileName));
            //metodo alternativo tramite la libreria nio;
            buffRead = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(fileName), charset)); //charset è la codifica, ad esempio utf-8

            lstRows = new LinkedList<String[]>();
            String s = null;

            while ((s = buffRead.readLine()) != null)
                if (!s.isEmpty() && s.contains(";"))
                    lstRows.add(s.trim().split(";")); //trim ripulisce la stringa da eventuali spaziature, split rstituisce l'arrey splittato in base al token ";"
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        finally { //lo esegue in ogni caso, sia se ci sono eccezione sia che non ci sono.
            if (buffRead != null)
                buffRead.close();
        }

        return lstRows;
    }
} // end class