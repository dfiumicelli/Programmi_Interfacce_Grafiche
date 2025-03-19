package jplinko.view;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics2D;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;

import javax.swing.JPanel;

import jplinko.controller.ControllerForView;

public class BallAnimation {

    private final JPanel pyramidPanel;
    private int finalPosition;
    private final JLabel balanceLabel;
    private final List<JLabel> containers;
    private final List<BallThread> ballThreads;
    private ScheduledExecutorService executorService;
    private int[][] positions;

    public BallAnimation(JPanel pyramidPanel, JLabel balanceLabel, List<JLabel> containers) {
        this.pyramidPanel = pyramidPanel;
        this.balanceLabel = balanceLabel;
        this.containers = containers;
        this.ballThreads = new ArrayList<>();

    }

    public void startMultipleBalls(int rows, int numBalls, int delayBetweenBalls) {
        clearBalls();
        this.positions = ControllerForView.getInstance().simulatePlinko(rows, rows + 1);
        this.executorService = Executors.newScheduledThreadPool(numBalls);
        for (int i = 0; i < numBalls; i++) {

            BallThread ballThread = new BallThread(rows, i, this.positions);
            ballThreads.add(ballThread);

            // Avvia il thread con un ritardo specifico
            executorService.schedule(ballThread, i * delayBetweenBalls, TimeUnit.MILLISECONDS);
        }
    }

    public void paintBall(Graphics2D g2d) {
        for (BallThread ballThread : ballThreads) {
            if (ballThread.isStarted || ballThread.isFinished) {
                ballThread.paintBall(g2d); // Disegna solo le palline avviate o terminate
            }
        }
    }

    public void clearBalls() {
        ballThreads.clear(); // Rimuove tutte le palline
        pyramidPanel.repaint(); // Ridisegna il pannello
    }

    public boolean areAllThreadsFinished() {
        for (BallThread ballThread : ballThreads) {
            if (!ballThread.isFinished) {
                return false; // Se almeno un thread non è terminato, restituisce false
            }
        }
        return true; // Tutti i thread sono terminati
    }

    private List<Point> calculateBallPath(int rows, int k, int[][] positions) {
        List<Point> path = new ArrayList<>();

        // Calcola le dimensioni del pannello per posizionare la pallina
        int panelWidth = pyramidPanel.getWidth();
        int panelHeight = pyramidPanel.getHeight();
        int gap = panelWidth / 33;
        int startX = panelWidth / 2;
        int startY = (panelHeight - ((rows + 2) * gap)) / 2;

        // Aggiungi la posizione iniziale (centro in alto)
        path.add(new Point(startX, startY));

        // Simula il percorso della pallina attraverso i pioli
        for (int i = 0; i < positions[k].length; i++) {
            // Calcola la nuova posizione sullo schermo
            int newX = startX + (positions[k][i] * gap / 2);
            int newY = startY + (i * gap);

            // Aggiungi punti intermedi per un'animazione più fluida
            Point previousPoint = path.get(path.size() - 1);
            int steps = 10; // Numero di passi intermedi per i segmenti normali

            for (int step = 1; step <= steps; step++) {
                double t = (double) step / steps;

                // Linear interpolation for x-coordinate
                int interpolatedX = (int) (previousPoint.x + (newX - previousPoint.x) * t);

                // Parabolic interpolation for y-coordinate
                // We need the ball to appear to bounce upward slightly before falling to the next point
                double parabolicHeight = gap / 3.0; // Negative value for upward curve

                // Parabola formula: y = a*t^2 + b*t + c
                // This creates an upward parabola between the two points that still ends at the destination
                double a = 4 * parabolicHeight;
                double b = -4 * parabolicHeight + (newY - previousPoint.y);
                double c = previousPoint.y;

                // Calculate y-coordinate using parabolic formula
                int interpolatedY = (int) (a * t * t + b * t + c);

                path.add(new Point(interpolatedX, interpolatedY));
            }

        }

        // Aggiungi punti intermedi per l'ultimo segmento (caduta nel contenitore)
        Point previousPoint = path.get(path.size() - 1);
        int steps = 10; // Numero di passi intermedi per l'ultimo segmento
        this.finalPosition = ControllerForView.getInstance().getFinalPosition()[k];
        JLabel finalContainer = containers.get(finalPosition);
        int x = finalContainer.getX() + gap / 2;
        int y = finalContainer.getY() + gap / 2;

        for (int step = 1; step <= steps; step++) {
            double t = (double) step / steps;

            // Linear interpolation for x-coordinate
            int interpolatedX = (int) (previousPoint.x + (x - previousPoint.x) * t);

            // Accelerating y-coordinate for falling effect
            double tSquared = t * t;
            int interpolatedY = (int) (previousPoint.y + (y - previousPoint.y) * tSquared);

            path.add(new Point(interpolatedX, interpolatedY));
        }

        return path;
    }

//    
    private void updateBalanceAfterBet() {
        balanceLabel.setText("Balance: €" + ControllerForView.getInstance().getBalance());
    }

    private class BallThread extends Thread {

        private final List<Point> ballPath;
        private int currentStep;
        private boolean isStarted; // Indica se il thread è stato avviato
        private boolean isFinished; // Indica se l'animazione è terminata
        private Point finalPosition; // Posizione finale della pallina
        private final Color ballColor = Color.RED;
        private final int ballSize = 14;
        private final int[][] positions;
        private final int k;
        private volatile boolean stopRequested;
        private final int threadIndex;

        public BallThread(int rows, int k, int[][] positions) {
            this.k = k;
            this.positions = positions;
            this.ballPath = calculateBallPath(rows, k, this.positions);
            this.currentStep = 30;
            this.isStarted = false; // Inizialmente non avviato
            this.isFinished = false;
            this.finalPosition = null;
            this.stopRequested = false;
            this.threadIndex = k;
        }

        public void stopFutureThreads(int currentThreadIndex) {
            // Non interrompere i thread già avviati, ma solo quelli futuri
            if (executorService != null && !executorService.isShutdown()) {
                executorService.shutdown(); // Impedisce l'esecuzione di nuovi task
            }

            // Rimuovi i thread che non sono ancora partiti dalla lista
            for (int i = ballThreads.size() - 1; i > currentThreadIndex; i--) {
                if (!ballThreads.get(i).isStarted) {
                    ballThreads.remove(i);
                }
            }
        }

        public void stopThread() {
            this.stopRequested = true;
            this.interrupt(); // Interrompi anche eventuali sleep
        }

        @Override
        public void run() {
            isStarted = true; // Il thread è stato avviato
            double balance = ControllerForView.getInstance().getBalance() - ControllerForView.getInstance().getBetValues()[ControllerForView.getInstance().getCurrentBetIndex()];
            ControllerForView.getInstance().setBalance(balance);
            updateBalanceAfterBet();
            if (ControllerForView.getInstance().isGameOver()) {
                showGameOverMessage();
                return;
            }
            while (isStarted && currentStep < ballPath.size()) {
                currentStep++;
                pyramidPanel.repaint(); // Ridisegna il pannello

                try {
                    Thread.sleep(30); // Ritardo per l'animazione (circa 60fps)
                } catch (InterruptedException e) {
                    break;
                }
            }

            // Animazione completata
            isStarted = false;
            isFinished = true;
            finalPosition = ballPath.get(ballPath.size() - 1); // Memorizza la posizione finale
            balance = ControllerForView.getInstance().getBalance()
                    + ControllerForView.getInstance().getBetValues()[ControllerForView.getInstance().getCurrentBetIndex()]
                    * ControllerForView.getInstance().getMultipliers()[ControllerForView.getInstance().getFinalPosition()[k]];
            ControllerForView.getInstance().setBalance(balance);
            updateBalanceAfterBet(); // Aggiorna il saldo
        }

        public void paintBall(Graphics2D g2d) {
            if (isFinished) {
                // Disegna la pallina nella posizione finale
                g2d.setColor(ballColor);
                g2d.fillOval(finalPosition.x - (ballSize / 2), finalPosition.y - (ballSize / 2), ballSize, ballSize);
            } else if (isStarted && currentStep < ballPath.size() && currentStep > 0) {
                // Disegna la pallina in movimento
                Point ballPosition = ballPath.get(currentStep);
                g2d.setColor(ballColor);
                g2d.fillOval(ballPosition.x - (ballSize / 2), ballPosition.y - (ballSize / 2), ballSize, ballSize);
            }
        }

        private void showGameOverMessage() {
            javax.swing.SwingUtilities.invokeLater(() -> {
                javax.swing.JOptionPane.showMessageDialog(
                        pyramidPanel,
                        "Game Over! Il tuo saldo è esaurito.",
                        "Game Over",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                );
                stopFutureThreads(threadIndex);
            });
            
        }
    }
}
