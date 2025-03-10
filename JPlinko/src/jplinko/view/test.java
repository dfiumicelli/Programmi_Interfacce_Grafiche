/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jplinko.view;

import java.util.Random;
import jplinko.model.Multipliers;

/**
 *
 * @author dfium
 */
public class test {

    public static int[] simulatePlinko(int rows, int numMultipliers) {
        Random random = new Random();
        int[] positions = new int[numMultipliers+1];
        int position = 0; // Posizione iniziale (centrale)
        positions[0] = position;
        boolean goRight;
        // Simula il percorso della pallina
        for (int i = 1; i < positions.length; i++) {
            // La pallina si sposta a sinistra (-1) o a destra (+1)

            // Se stiamo calcolando l'ultimo percorso, assicuriamoci di arrivare alla posizione finale
            // if (i == rows+1) {
            //     goRight = position < finalPosition;
            // } else {
            goRight = random.nextBoolean();
            // }

            if (goRight) {
                position++;
            } else {
                position--;
            }
            positions[i] = position;
            
        }

        // Normalizza la posizione per mappare al contenitore corretto
        // In un gioco Plinko con rows righe, ci sono rows+1 contenitori
        // La posizione finale dovrebbe essere nell'intervallo [0, rows]
//        positions[rows] = (positions[rows] + rows) / 2;
//
//        // Assicurati che la posizione sia nell'intervallo valido
//        if (positions[rows] < 0) {
//            positions[rows] = 0;
//        }
//        if (positions[rows] >= numMultipliers) {
//            positions[rows] = numMultipliers - 1;
//        }

        return positions;
    }

    public static void main(String[] args) {
        int rows = 16; // Numero di righe
        int[] positions = simulatePlinko(rows, rows+1);
        for (int i = 0; i<positions.length; i++)
            System.out.println(positions[i]);
    }
}