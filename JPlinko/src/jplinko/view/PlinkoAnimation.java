package jplinko.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.Point;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import jplinko.controller.ControllerForView;

public class PlinkoAnimation {
    private JPanel pyramidPanel;
    private Timer animationTimer;
    private List<Point> ballPath;
    private int currentStep;
    private final Color ballColor = Color.RED;
    private final int ballSize = 12;
    private int finalPosition;
    private double finalMultiplier;
    
    public PlinkoAnimation(JPanel pyramidPanel) {
        this.pyramidPanel = pyramidPanel;
        this.currentStep = 0;
    }
    
    public void startAnimation(int rows) {
        // Calcola il percorso della pallina

        this.ballPath = calculateBallPath(rows, finalPosition);
        this.currentStep = 0;

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
                ((Timer)e.getSource()).stop();
                // Qui puoi aggiungere il codice per aggiornare il saldo, mostrare un messaggio, ecc.
                updateBalanceAfterBet();
            }
        });

        animationTimer.start();
    }
    
    private List<Point> calculateBallPath(int rows, int finalPosition) {
        List<Point> path = new ArrayList<>();
        Random random = new Random();
        
        // Calcola le dimensioni del pannello per posizionare la pallina
        int panelWidth = pyramidPanel.getWidth();
        int panelHeight = pyramidPanel.getHeight();
        int gap = panelWidth / 33;
        int startX = panelWidth / 2;
        int startY = (panelHeight - ((rows + 2) * gap)) / 2;
        
        // Aggiungi la posizione iniziale (centro in alto)
        path.add(new Point(startX, startY));
        
        // Simula il percorso della pallina attraverso i pioli
        int position = 0;
        for (int i = 1; i <= rows+1; i++) {
            // Determina se la pallina va a sinistra o a destra ad ogni riga
            boolean goRight;
            
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
            // Calcola la nuova posizione sullo schermo
            int newX = startX + (position * gap / 2);
            int newY = startY + (i * gap);
            if (i == rows+1) {
                finalPosition = position;
            }
            // Aggiungi punti intermedi per un'animazione più fluida
            Point previousPoint = path.get(path.size() - 1);
            int steps = 10; // Numero di passi intermedi
            
            for (int step = 1; step <= steps; step++) {
                int interpolatedX = previousPoint.x + ((newX - previousPoint.x) * step / steps);
                int interpolatedY = previousPoint.y + ((newY - previousPoint.y) * step / steps);
                path.add(new Point(interpolatedX, interpolatedY));
            }
        }
        
        // //Aggiungi il movimento finale verso il contenitore
        // int containerWidth = gap;
        // int firstPegX = startX - (rows * gap / 2) + 4;
        // int containerX = firstPegX + (finalPosition * containerWidth) + (containerWidth / 2);
        // int containerY = startY + (rows * gap) + (int)(panelHeight * 0.06);
        
        // Point lastPoint = path.get(path.size() - 1);
        // int finalSteps = 50; // Più passi per un movimento fluido verso il contenitore
        
        // for (int step = 1; step <= finalSteps; step++) {
        //     int interpolatedX = lastPoint.x + ((containerX - lastPoint.x) * step / finalSteps);
        //     int interpolatedY = lastPoint.y + ((containerY - lastPoint.y) * step / finalSteps);
        //     path.add(new Point(interpolatedX, interpolatedY));
        // }
        
        // //Aggiungi il punto finale esplicito
        // path.add(new Point(containerX, containerY));
        
        return path;
    }
    
    public void paintBall(Graphics2D g2d) {
        if (ballPath != null && currentStep < ballPath.size() && currentStep > 0) {
            Point ballPosition = ballPath.get(currentStep);
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
