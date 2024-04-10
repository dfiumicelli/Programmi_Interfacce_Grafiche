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
import java.io.IOException;

public class ReadCSV {

    public static LinkedList<String[]> getRows(String fileName, String charset) throws FileNotFoundException, IOException {
        LinkedList<String[]> lstRows = null;

        BufferedReader buffRead = null; //ci permette di fare un'acquisizione bufferizzata più efficiente. Si inoltre carico del metodo readLine()
        try {
            buffRead = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(fileName), charset)); //charset è la codifica, ad esempio utf-8

            lstRows = new LinkedList<String[]>();
            String s = null;

            while ((s = buffRead.readLine()) != null)
                if (!s.isEmpty() && s.contains(";"))
                    lstRows.add(s.trim().split(";"));
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        finally {
            if (buffRead != null)
                buffRead.close();
        }

        return lstRows;
    }
} // end class