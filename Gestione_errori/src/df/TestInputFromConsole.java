/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package df;

/**
 *
 * @author mfiumicelli
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class TestInputFromConsole {

    public static void main(String[] args) {

        int dim = Integer.valueOf(args[0]);
        String[] strArray = new String[dim];

        BufferedReader buffRead = new BufferedReader(new InputStreamReader(System.in));

        int index = -1;
        String str = null;
        boolean end = false;
        while (!end) {
            try {
                System.out.println("Inserisci un indice i oppure q per terminare.");
                str = buffRead.readLine();
                if (str.toLowerCase().trim().equals("q")) {
                    end = true;
                    buffRead.close();
                    break;
                }
                index = Integer.valueOf(str);
                System.out.println("Inserisci una parola per strArray[" + index + "].");
                str = buffRead.readLine();
                strArray[index] = str;
            }
            catch(IOException ioe) {
                ioe.printStackTrace();
            }
            catch(ArrayIndexOutOfBoundsException aioobe) {
                aioobe.printStackTrace();
            }
            catch(NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        } // end while

        System.out.println("Valori inseriti:");
        for (int i = 0; i < strArray.length; i++)
            System.out.println("strArray[" + i + "] = " + strArray[i]);

    } // end method main()

} // end class