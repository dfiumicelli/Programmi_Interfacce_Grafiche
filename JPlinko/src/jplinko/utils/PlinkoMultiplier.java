/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jplinko.utils;

/**
 *
 * @author dfium
 */
public class PlinkoMultiplier {

    public static double[] generateMultipliers(int rows, String riskLevel) {
        double baseMin, baseMax;
        int exp;

        // Valori base per il rischio
        switch (riskLevel.toLowerCase()) {
            case "low" -> {
                baseMin = 2.8;
                baseMax = 0.3;
                exp = 6;
            }
            case "medium" -> {
                baseMin = 2.7;
                baseMax = 0.9;
                exp = 10;
            }
            case "high" -> {
                baseMin = 1.7;
                baseMax = 2.1;
                exp = 12;
            }
            default -> {
                baseMin = 1.0;
                baseMax = 1.0;
                exp = 1;
            }
        }

        // Adattiamo i valori min/max in base alle righe
        double minCenter = baseMin / Math.sqrt(rows + 1);
        double maxEdge = baseMax * Math.pow(100,rows/10.00);

        double[] multipliers = new double[rows + 1];
        int mid = rows / 2;

        for (int i = 0; i <= rows; i++) {
            double distanceFromCenter = Math.abs(mid - i) / (double) mid;
            double rawMultiplier = minCenter + (maxEdge - minCenter) * Math.pow(distanceFromCenter, exp);
            multipliers[i] = Math.round(rawMultiplier * 10.0) / 10.0; //arrotondamento alla prima cifra decimale
        }

        return multipliers;
    }

}
