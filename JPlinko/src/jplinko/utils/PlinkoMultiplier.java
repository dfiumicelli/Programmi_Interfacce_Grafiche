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
                baseMin = 3.7;
                baseMax = 180;
                exp = 6;
            }
            case "medium" -> {
                baseMin = 2.7;
                baseMax = 580.0;
                exp = 8;
            }
            case "high" -> {
                baseMin = 1.7;
                baseMax = 1080.0;
                exp = 10;
            }
            default -> {
                baseMin = 1.0;
                baseMax = 1.0;
                exp = 1;
            }
        }

        // Adattiamo i valori min/max in base alle righe
        double minCenter = baseMin / Math.sqrt(rows + 1);
        double maxEdge = baseMax * Math.log(rows + 1);

        double[] multipliers = new double[rows + 1];
        int mid = rows / 2;

        for (int i = 0; i <= rows; i++) {
            double distanceFromCenter = Math.abs(mid - i) / (double) mid;
            double rawMultiplier = minCenter + (maxEdge - minCenter) * Math.pow(distanceFromCenter, exp);
            multipliers[i] = Math.round(rawMultiplier * 100.0) / 100.0; //arrotondamento alla seconda cifra decimale
        }

        return multipliers;
    }

    public static void main(String[] args) {
        double mult[] = generateMultipliers(16, "low");
        for (int i = 0; i < mult.length; i++) {
            System.out.print(mult[i] + "  ");
        }
    }
}
