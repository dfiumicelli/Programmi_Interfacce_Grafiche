/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jplinko.model;

/**
 *
 * @author dfium
 */
public class Multipliers {

    private static final double TARGET_PAYOUT = 0.94;

    private static double[] generateRawMultipliers(int rows, String riskLevel) {
        double factorCenter, factorEdge;

        // Valori factor per il rischio
        switch (riskLevel.toLowerCase()) {
            case "low" -> {
                factorCenter = 100.0;
                factorEdge = 10.0;
            }
            case "medium" -> {
                factorCenter = 25.0;
                factorEdge = 5.0;
            }
            case "high" -> {
                factorCenter = 5.0;
                factorEdge = 2.0;
            }
            default -> {
                factorCenter = 1.0;
                factorEdge = 1.0;
            }
        }

        // Adattiamo i valori min/max in base alle righe
        double minCenter = factorCenter / (0.5 * Math.log(rows + 1));
        double maxEdge = factorEdge * Math.pow(100, rows / 10.00);

        double[] multipliers = new double[rows + 1];
        double center = (rows) / 2.0; // Centro virtuale (può essere un numero frazionario per righe dispari)

        for (int i = 0; i <= rows; i++) {
            // Calcola la distanza normalizzata dal centro (tra 0 e 1)
            double distanceFromCenter = Math.abs(center - i) / center;
            multipliers[i] = minCenter + (maxEdge - minCenter) * Math.pow(distanceFromCenter, 5);
        }

        return multipliers;
    }

    public static double[] generate(int rows, String riskLevel) {
        // Il numero di bin è rows + 1

        // Calcolo delle probabilità binomiali per ogni bin
        double[] probabilities = calculateBinomialProbabilities(rows);

        // Genera i moltiplicatori in base al fattore di rischio
        double[] multipliers = generateRawMultipliers(rows, riskLevel);

        // Aggiusta i moltiplicatori per ottenere il payout target
        adjustMultipliersForTargetPayout(multipliers, probabilities);

        // Arrotonda i moltiplicatori a 2 decimali
        for (int i = 0; i < multipliers.length; i++) {
            multipliers[i] = Math.round(multipliers[i] * 10) / 10.0;
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

    //Calcola il coefficiente binomiale C(n, k)
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

}
