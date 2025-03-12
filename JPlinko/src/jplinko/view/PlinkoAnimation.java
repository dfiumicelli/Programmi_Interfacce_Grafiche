package jplinko.view;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.Point;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import jplinko.controller.ControllerForView;
import jplinko.model.Model;

public class PlinkoAnimation {

    private JPanel pyramidPanel;
    private Timer animationTimer;
    private List<Point> ballPath;
    private int currentStep;
    private final Color ballColor = Color.RED;
    private final int ballSize = 12;
    private int finalPosition;
    private double finalMultiplier;
    private boolean animationCompleted = false;

    public PlinkoAnimation(JPanel pyramidPanel) {
        this.pyramidPanel = pyramidPanel;
        this.currentStep = 0;
    }

    public void startAnimation(int rows) {
        // Calcola il percorso della pallina
        animationCompleted = false;
        this.ballPath = calculateBallPath(rows);
        this.currentStep = 30;

        if (ballPath == null || ballPath.isEmpty()) {
            throw new IllegalStateException("Il percorso della pallina non è stato calcolato correttamente.");
        }

        // Crea il timer per l'animazione (15ms = circa 60fps)
        animationTimer = new Timer(15, e -> {
            if (currentStep < ballPath.size()) {
                currentStep++;
                pyramidPanel.repaint(); // Ridisegna il pannello per mostrare la nuova posizione
            } else {
                // Animazione completata
                ((Timer) e.getSource()).stop();
                animationCompleted = true;
                // Qui puoi aggiungere il codice per aggiornare il saldo, mostrare un messaggio, ecc.
                updateBalanceAfterBet();
            }
        });

        animationTimer.start();
    }

    private List<Point> calculateBallPath(int rows) {
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
        int[] positions = Model.getInstance().simulatePlinko(rows, rows + 1);
        int currentDirection = 0;
        for (int i = 0; i < positions.length; i++) {
            // Calcola la nuova posizione sullo schermo
            int newX = startX + (positions[i] * gap / 2);
            int newY = startY + ((i) * gap);

            // Aggiungi punti intermedi per un'animazione più fluida
            Point previousPoint = path.get(path.size() - 1);
            int steps = 10; // Numero di passi intermedi per i segmenti normali

            for (int step = 1; step <= steps; step++) {
                int interpolatedX = previousPoint.x + ((newX - previousPoint.x) * step / steps);
                int interpolatedY = previousPoint.y + ((newY - previousPoint.y) * step / steps);
                path.add(new Point(interpolatedX, interpolatedY));
            }

            if (i > 0) {
                if (positions[i] > positions[i - 1]) {
                    currentDirection = 1; // Destra
                } else if (positions[i] < positions[i - 1]) {
                    currentDirection = -1; // Sinistra
                }
                // Se positions[i] == positions[i - 1], mantieni la direzione precedente
            }
        }

        // Calcola la posizione finale
        // Modifica nella funzione calculateBallPath()
// Calcola la posizione finale
        int finalX = startX + (positions[positions.length - 1] * gap / 2);
        int finalY = startY + ((positions.length) * gap);

// Determina la direzione finale basandoti sugli ultimi due movimenti
//        if (positions.length > 1) {
//            int lastPosition = positions[positions.length - 1];
//            int previousPosition = positions[positions.length - 2];
//
//            if (lastPosition > previousPosition) {
//                // La pallina stava andando a destra
//                finalX = startX + 30 + (positions[positions.length - 1] * gap / 2); // Leggero offset a destra
//            } else if (lastPosition < previousPosition) {
//                // La pallina stava andando a sinistra
//                finalX = startX - 30 + (positions[positions.length - 1] * gap / 2); // Leggero offset a sinistra
//            }
//            // Se uguali, resta al centro
//        }

// Aggiungi un offset alla coordinata Y per far "cadere" la pallina nel contenitore
        int dropOffset = 0; // Puoi regolare questo valore per aumentare o diminuire la caduta
        int finalYWithOffset = finalY + dropOffset;

        // Aggiungi punti intermedi per l'ultimo segmento (caduta nel contenitore)
        Point previousPoint = path.get(path.size() - 1);
        int steps = 10; // Numero di passi intermedi per l'ultimo segmento

        for (int step = 1; step <= steps; step++) {
            int interpolatedX = previousPoint.x + ((finalX - previousPoint.x) * step / steps);
            int interpolatedY = previousPoint.y + ((finalYWithOffset - previousPoint.y) * step / steps);
            path.add(new Point(interpolatedX, interpolatedY));
        }

        this.finalPosition = Model.getInstance().getFinalPosition();
        this.finalMultiplier = ControllerForView.getInstance().getMultipliers()[finalPosition];
        return path;
    }

    public void paintBall(Graphics2D g2d) {
    if (ballPath != null && !ballPath.isEmpty()) {
        Point ballPosition;
        if (animationCompleted) {
            // Se l'animazione è completata, usa l'ultima posizione della pallina
            ballPosition = ballPath.get(ballPath.size() - 1);
        } else if (currentStep < ballPath.size() && currentStep > 0) {
            // Altrimenti, usa la posizione corrente della pallina
            ballPosition = ballPath.get(currentStep);
        } else {
            return; // Non disegnare la pallina se non ci sono posizioni valide
        }

        g2d.setColor(ballColor);
        g2d.fillOval(ballPosition.x - (ballSize / 2), ballPosition.y - (ballSize / 2), ballSize, ballSize);
    }
}

    private void updateBalanceAfterBet() {
        // Aggiorna il saldo in base alla scommessa e al moltiplicatore
        double currentBet = ControllerForView.getInstance().getBetValues()[ControllerForView.getInstance().getCurrentBetIndex()];
        double winAmount = currentBet * finalMultiplier;
        double finalMultiplier = ControllerForView.getInstance().getMultipliers()[finalPosition];

        // Notifica la vincita e aggiorna l'interfaccia
        // Questo è un punto dove dovrai implementare la logica specifica del tuo gioco
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(pyramidPanel,
                    "Hai vinto €" + winAmount + " (moltiplicatore: " + finalMultiplier + "x)!",
                    "Risultato", JOptionPane.INFORMATION_MESSAGE);

            // Qui dovresti aggiornare il saldo mostrato nell'interfaccia
            // balanceLabel.setText("Balance: €" + nuovoSaldo);
        });
    }
}
