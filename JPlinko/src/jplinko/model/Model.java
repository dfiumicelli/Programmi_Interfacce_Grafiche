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
    private int[] finalPosition;

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

    @Override
    public int[] getFinalPosition() {
        return finalPosition;
    }

    @Override
    public int[][] simulatePlinko(int rows, int numMultipliers) {
        int numBalls;
        if (this.mode.equals("Auto")) {
            numBalls = this.rounds;
        } else {
            numBalls = 1;
        }
        this.finalPosition = new int[numBalls];
        int[][] positions = new int[numMultipliers + 1][numBalls];
        for (int k = 0; k < numBalls; k++) {
            this.balance -= this.betValues[this.currentBetIndex];
            Random random = new Random();
            
            int position = 0; // Posizione iniziale (centrale)
            positions[0][k] = position;
            boolean goRight;
            // Simula il percorso della pallina
            for (int i = 1; i < positions.length; i++) {

                goRight = random.nextBoolean();

                if (goRight) {
                    position++;
                } else {
                    position--;
                }
                positions[i][k] = position;

            }

            // Normalizza la posizione per mappare al contenitore corretto
            // La posizione finale dovrebbe essere nell'intervallo [0, rows]
            this.finalPosition[k] = (positions[positions.length - 1][k] + rows) / 2;

            // Assicurati che la posizione sia nell'intervallo valido
            if (this.finalPosition[k] < 0) {
                this.finalPosition[k] = 0;
            }
            if (this.finalPosition[k] >= numMultipliers) {
                this.finalPosition[k] = numMultipliers - 1;
            }
            for (int i = 0; i < positions.length; i++) {
                System.out.println(positions[i][k]);
            }
            System.out.println(this.finalPosition);
            this.balance += this.betValues[this.currentBetIndex] * this.multipliers[this.finalPosition[k]];
            this.balance = Math.round(balance * 10) / 10.0;
            
        }
        return positions;
    }

}
