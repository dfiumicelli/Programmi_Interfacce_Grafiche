/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jplinko.utils;



/**
 *
 * @author dfium
 */
public class test {

    private static final double TARGET_PAYOUT = 0.94;

    public static double[] generateRawMultipliers(int rows, String riskLevel) {
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
        double maxEdge = baseMax * Math.pow(100, rows / 10.00);

        double[] multipliers = new double[rows + 1];
        int mid = rows / 2;

        for (int i = 0; i <= rows; i++) {
            double distanceFromCenter = Math.abs(mid - i) / (double) mid;
            double rawMultiplier = minCenter + (maxEdge - minCenter) * Math.pow(distanceFromCenter, exp);
            multipliers[i] = Math.round(rawMultiplier * 10.0) / 10.0; //arrotondamento alla prima cifra decimale
        }

        return multipliers;
    }

    public static double[] generateMultipliersReal(int rows, String riskLevel) {
        // Il numero di bin è rows + 1

        // Calcolo delle probabilità binomiali per ogni bin
        double[] probabilities = calculateBinomialProbabilities(rows);

        // Genera i moltiplicatori in base al fattore di rischio
        double[] multipliers = generateRawMultipliers(rows, riskLevel);

        // Aggiusta i moltiplicatori per ottenere il payout target
        adjustMultipliersForTargetPayout(multipliers, probabilities);

        // Arrotonda i moltiplicatori a 2 decimali
        for (int i = 0; i < multipliers.length; i++) {
            multipliers[i] = Math.round(multipliers[i] * 100) / 100.0;
        }

        return multipliers;
    }
    
    private static double[] calculateBinomialProbabilities(int rows) {
        int bins = rows + 1;
        double[] probabilities = new double[bins];

        for (int i = 0; i < bins; i++) {
            // Calcola il coefficiente binomiale C(rows, i)
            long coefficient = binomialCoefficient(rows, i);
            // Probabilità = C(rows, i) * (0.5^i) * (0.5^(rows-i))
            probabilities[i] = coefficient * Math.pow(0.5, rows);
        }

        return probabilities;
    }

    /**
     * Calcola il coefficiente binomiale C(n, k)
     *
     * @param n
     * @param k
     * @return Valore del coefficiente binomiale
     */
    private static long binomialCoefficient(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }

        long result = 1;
        for (int i = 1; i <= k; i++) {
            result = result * (n - (i - 1)) / i;
        }

        return result;
    }
    
    private static void adjustMultipliersForTargetPayout(double[] multipliers, double[] probabilities) {
        double currentPayout = calculateExpectedValue(probabilities, multipliers);
        double adjustmentFactor = TARGET_PAYOUT / currentPayout;

        for (int i = 0; i < multipliers.length; i++) {
            multipliers[i] *= adjustmentFactor;
        }
    }
    
    private static double calculateExpectedValue(double[] probabilities, double[] multipliers) {
        double expectedValue = 0;
        for (int i = 0; i < probabilities.length; i++) {
            expectedValue += probabilities[i] * multipliers[i];
        }
        return expectedValue;
    }
    
    public static void main(String[] args){
        double[] multi = generateMultipliersReal(8, "high");
        double[] probabilities = calculateBinomialProbabilities(8);
        double expectedPayout = calculateExpectedValue(probabilities, multi);
        for (int i = 0; i< multi.length; i++){
            System.out.println(multi[i]);
        }
        System.out.println("Expected Payout: " + Math.round(expectedPayout * 10000) / 100.0 + "%");
    }
}
