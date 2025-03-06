/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jplinko.model;

/**
 *
 * @author dfiumicelli
 */

import java.util.Arrays;

import java.util.Arrays;

public class PlinkoMultiplierGenerator {

    // Enumerazione per i livelli di rischio
    public enum RiskLevel {
        LOW,
        MEDIUM,
        HIGH
    }

    // Percentuale di payout desiderata (94%)
    private static final double TARGET_PAYOUT = 0.94;

    /**
     * Genera i moltiplicatori per il gioco del Plinko
     *
     * @param rows Numero di righe della piramide di Plinko
     * @param riskLevel Livello di rischio (LOW, MEDIUM, HIGH)
     * @return Array di moltiplicatori per ogni bin
     */
    public static double[] generateMultipliers(int rows, RiskLevel riskLevel) {
        // Il numero di bin è rows + 1
        int bins = rows + 1;

        // Calcolo delle probabilità binomiali per ogni bin
        double[] probabilities = calculateBinomialProbabilities(rows);

        // Genera i moltiplicatori in base al fattore di rischio
        double[] multipliers;
        switch (riskLevel) {
            case LOW:
                multipliers = generateLowRiskMultipliers(bins, rows);
                break;
            case MEDIUM:
                multipliers = generateMediumRiskMultipliers(bins, rows);
                break;
            case HIGH:
                multipliers = generateHighRiskMultipliers(bins, rows);
                break;
            default:
                multipliers = generateMediumRiskMultipliers(bins, rows);
        }

        // Aggiusta i moltiplicatori per ottenere il payout target
        adjustMultipliersForTargetPayout(multipliers, probabilities);

        // Arrotonda i moltiplicatori a 2 decimali
        for (int i = 0; i < multipliers.length; i++) {
            multipliers[i] = Math.round(multipliers[i] * 100) / 100.0;
        }

        return multipliers;
    }

    /**
     * Calcola le probabilità binomiali per ogni bin
     *
     * @param rows Numero di righe
     * @return Array di probabilità
     */
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

    /**
     * Genera moltiplicatori per un profilo di rischio basso
     * Distribuzione con valori più alti agli estremi e bassi all'interno
     *
     * @param bins Numero di bin
     * @param rows Numero di righe
     * @return Array di moltiplicatori
     */
    private static double[] generateLowRiskMultipliers(int bins, int rows) {
        double[] multipliers = new double[bins];
        double center = (bins - 1) / 2.0;

        for (int i = 0; i < bins; i++) {
            // Calcola la distanza normalizzata dal centro (0 al centro, 1 agli estremi)
            double distance = Math.abs(i - center) / center;

            // Per un rischio basso, la distribuzione è più equilibrata
            // ma mantiene comunque i valori più alti agli estremi
            double factor;
            if (distance < 0.2) {
                factor = 1.0 / Math.pow(2,rows); // Valori molto bassi nella zona centrale
            } else if (distance < 0.5) {
                factor = 1.0 + distance * 2.0; // Valori bassi nella zona intermedia
            } else if (distance < 0.8) {
                factor = 1.0 + distance * 4.0; // Valori alti verso gli estremi
            } else {
                factor = 1.0 + distance * 8.0; // Valori molto alti agli estremi
            }

            multipliers[i] = factor;
        }

        return multipliers;
    }

    /**
     * Genera moltiplicatori per un profilo di rischio medio
     * Distribuzione con valori più alti agli estremi e più bassi all'interno
     *
     * @param bins Numero di bin
     * @param rows Numero di righe
     * @return Array di moltiplicatori
     */
    private static double[] generateMediumRiskMultipliers(int bins, int rows) {
        double[] multipliers = new double[bins];
        double center = (bins - 1) / 2.0;

        for (int i = 0; i < bins; i++) {
            // Calcola la distanza normalizzata dal centro (0 al centro, 1 agli estremi)
            double distance = Math.abs(i - center) / center;

            // Per un rischio medio, i valori interni sono più bassi e quelli esterni più alti
            double factor;
            if (distance < 0.2) {
                factor = 1.0 / Math.pow(2,rows); // Valori molto bassi nella zona centrale
            } else if (distance < 0.5) {
                factor = 1.0 + distance * 3.0; // Valori bassi nella zona intermedia
            } else if (distance < 0.8) {
                factor = 1.0 + distance * 6.0; // Valori alti verso gli estremi
            } else {
                factor = 1.0 + distance * 12.0; // Valori molto alti agli estremi
            }

            multipliers[i] = factor;
        }

        return multipliers;
    }

    /**
     * Genera moltiplicatori per un profilo di rischio alto
     * Distribuzione con valori molto alti agli estremi e molto bassi all'interno
     *
     * @param bins Numero di bin
     * @param rows Numero di righe
     * @return Array di moltiplicatori
     */
    private static double[] generateHighRiskMultipliers(int bins, int rows) {
        double[] multipliers = new double[bins];
        double center = (bins - 1) / 2.0;

        for (int i = 0; i < bins; i++) {
            // Calcola la distanza normalizzata dal centro (0 al centro, 1 agli estremi)
            double distance = Math.abs(i - center) / center;

            // Per un rischio alto, estremo contrasto tra valori interni ed esterni
            double factor;
            if (distance < 0.2) {
                factor = 50000.0 / Math.pow(2,rows); // Valori molto bassi nella zona centrale
            } else if (distance < 0.5) {
                factor = 1.0 + distance * 4.0; // Valori bassi nella zona intermedia
            } else if (distance < 0.8) {
                factor = 1.0 + distance * 8.0; // Valori alti verso gli estremi
            } else {
                factor = 1.0 + distance * 16.0; // Valori molto alti agli estremi
            }

            multipliers[i] = factor;
        }

        return multipliers;
    }

    /**
     * Aggiusta i moltiplicatori per raggiungere esattamente il payout target
     *
     * @param multipliers Array di moltiplicatori da aggiustare
     * @param probabilities Array di probabilità
     */
    private static void adjustMultipliersForTargetPayout(double[] multipliers, double[] probabilities) {
        double currentPayout = calculateExpectedValue(probabilities, multipliers);
        double adjustmentFactor = TARGET_PAYOUT / currentPayout;

        for (int i = 0; i < multipliers.length; i++) {
            multipliers[i] *= adjustmentFactor;
        }
    }

    /**
     * Calcola il valore atteso (payout medio) dato un set di probabilità e moltiplicatori
     *
     * @param probabilities Array di probabilità
     * @param multipliers Array di moltiplicatori
     * @return Valore atteso
     */
    private static double calculateExpectedValue(double[] probabilities, double[] multipliers) {
        double expectedValue = 0;
        for (int i = 0; i < probabilities.length; i++) {
            expectedValue += probabilities[i] * multipliers[i];
        }
        return expectedValue;
    }

    /**
     * Metodo di test per visualizzare i moltiplicatori generati
     */
    public static void main(String[] args) {
        System.out.println("=== Plinko Multiplier Generator ===");

        // Test con diverse configurazioni
        int[] rowsToTest = {8, 12, 16};
        RiskLevel[] riskLevelsToTest = {RiskLevel.LOW, RiskLevel.MEDIUM, RiskLevel.HIGH};

        for (int rows : rowsToTest) {
            for (RiskLevel risk : riskLevelsToTest) {
                double[] multipliers = generateMultipliers(rows, risk);
                double[] probabilities = calculateBinomialProbabilities(rows);
                double expectedPayout = calculateExpectedValue(probabilities, multipliers);

                System.out.println("\nRows: " + rows + ", Risk: " + risk);
                System.out.println("Multipliers: " + Arrays.toString(multipliers));
                System.out.println("Expected Payout: " + Math.round(expectedPayout * 10000) / 100.0 + "%");

                // Verifica che i moltiplicatori più alti siano agli estremi
                boolean highAtExtremes = multipliers[0] > multipliers[1]
                        && multipliers[multipliers.length - 1] > multipliers[multipliers.length - 2];
                System.out.println("High multipliers at extremes: " + highAtExtremes);
            }
        }
    }
}