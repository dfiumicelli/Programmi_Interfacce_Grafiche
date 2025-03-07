package jplinko.model;

import java.util.Random;

public class Model implements IModel {

    private static Model instance = null;
    private int rows;
    private int currentBetIndex;
    private int rounds;
    private double[] betValues = {0.10, 0.20, 0.50, 1.00, 2.00, 3.00, 4.00, 5.00, 10.00, 15.00, 25.00, 50.00, 75.00, 100.00};
    private String risk;
    private double[] multipliers;
    private double balance;
    private String mode;

    private Model() {
        this.rows = 16;
        this.currentBetIndex = 4;
        this.rounds = 5;
        this.risk = "Medium";
        this.multipliers = Multipliers.generate(rows, risk);
        this.balance = 5000.00;
        this.mode = "Manual";
    }

    public static IModel getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    @Override
    public void setRows(int rows) {
        this.rows = rows;
        this.multipliers = Multipliers.generate(rows, risk);
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getCurrentBetIndex() {
        return currentBetIndex;
    }

    @Override
    public void setCurrentBetIndex(int currentBetIndex) {
        this.currentBetIndex = currentBetIndex;
    }

    @Override
    public double[] getBetValues() {
        return betValues;
    }

    @Override
    public void setRisk(String risk) {
        this.risk = risk;
        this.multipliers = Multipliers.generate(rows, risk);
    }

    @Override
    public double[] getMultipliers() {
        return multipliers;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String getRisk() {
        return risk;
    }

    @Override
    public String getMode() {
        return mode;
    }

    @Override
    public int getRounds() {
        return rounds;
    }

    @Override
    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public static int simulatePlinko(int rows, int numMultipliers) {
        Random random = new Random();
        int position = 0; // Posizione iniziale (centrale)
        int[] positions = new int[rows];
        // Simula il percorso della pallina
        for (int i = 0; i < rows; i++) {
            // La pallina si sposta a sinistra (-1) o a destra (+1)
            position += random.nextBoolean() ? 1 : -1;
            System.out.println(position);
        }

        // Mappa la posizione finale su uno dei moltiplicatori
        // La posizione finale può variare tra -rows e +rows
        // Normalizziamo la posizione nell'intervallo [0, numMultipliers - 1]
        double minPosition = -rows;
        double maxPosition = rows;
        double normalizedPosition = (double) (position - minPosition) / (maxPosition - minPosition); // Normalizza tra 0 e 1
        int finalPosition = (int) (normalizedPosition * (numMultipliers - 1));

        return finalPosition;
    }
    
    public static void main(String[] args) {
        int rows = 16; // Numero di righe
        double[] multipliers = Multipliers.generate(rows, "high"); // Esempio di moltiplicatori

        // Simula il percorso della pallina
        int finalPosition = simulatePlinko(rows, multipliers.length);
        double multiplier = multipliers[finalPosition];

        System.out.println("Posizione finale: " + finalPosition);
        System.out.println("Moltiplicatore: " + multiplier);
    }



    
}


