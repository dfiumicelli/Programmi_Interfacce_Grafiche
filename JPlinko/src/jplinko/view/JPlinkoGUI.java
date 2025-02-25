/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jplinko.view;

import jplinko.utils.BallIcon;
import jplinko.utils.RoundedButton;
import jplinko.utils.RoundedToggleButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author dfiumicelli
 */
public class JPlinkoGUI extends JFrame {

    private BufferedImage backgroundImage;
    private final Dimension screenSize;
    private JPanel menuPanel, versionPanel, riskPanel, rowPanel, betAmountPanel;
    private RoundedButton betButton, increaseBet, decreaseBet;
    private RoundedToggleButton manualToggle, autoToggle, lowRisk, mediumRisk, highRisk;
    private JLabel riskLabel, rowLabel, betAmountLabel, balanceLabel;
    private JSlider rowSlider;

    public JPlinkoGUI() {
        super("JPlinkoGUI");
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.createGUI();

    }

    private void createGUI() {

        setTitle("Plinko Game");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setLeftPanel();
        setRightPanel();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ridimensiona il pannello di sinistra in base alla nuova dimensione della finestra
                int newWidth = getWidth();
                int newHeight = getHeight();
                menuPanel.setPreferredSize(new Dimension((int) (newWidth * 0.2), newHeight)); // 20% della larghezza
                menuPanel.revalidate(); // Aggiorna il layout
            }
        });

        setVisible(true);

    }

    private void setLeftPanel() {

        menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 40));
        int width = (int) (screenSize.width * 0.2);
        int height = (screenSize.height);
        menuPanel.setPreferredSize(new Dimension(width, height));
        menuPanel.setBackground(new Color(1, 56, 156));

        createVersionPanel(width, height);

        createRiskPanel(width, height);

        createRowSlider(width, height);

        createBetPanel(width, height);

        createBetButton(width, height);

        menuPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ricalcola le dimensioni dei componenti in base alla nuova dimensione del pannello
                int newWidth = menuPanel.getWidth();
                int newHeight = menuPanel.getHeight();

                // Ridimensiona i pulsanti
                betButton.setPreferredSize(new Dimension((int) (newWidth * 0.9), (int) (newHeight * 0.08)));
                versionPanel.setPreferredSize(new Dimension((int) (newWidth * 0.9), (int) (newHeight * 0.08)));
                manualToggle.setPreferredSize(new Dimension((int) (newWidth * 0.4), (int) (newHeight * 0.05)));
                autoToggle.setPreferredSize(new Dimension((int) (newWidth * 0.4), (int) (newHeight * 0.05)));
                lowRisk.setPreferredSize(new Dimension((int) (newWidth * 0.25), (int) (newHeight * 0.05)));
                mediumRisk.setPreferredSize(new Dimension((int) (newWidth * 0.25), (int) (newHeight * 0.05)));
                highRisk.setPreferredSize(new Dimension((int) (newWidth * 0.25), (int) (newHeight * 0.05)));
                riskPanel.setPreferredSize(new Dimension((int) (newWidth * 0.9), (int) (newHeight * 0.1)));
                rowPanel.setPreferredSize(new Dimension((int) (newWidth * 0.9), (int) (newHeight * 0.1)));
                betAmountPanel.setPreferredSize(new Dimension((int) (newWidth * 0.9), (int) (newHeight * 0.08)));
                decreaseBet.setPreferredSize(new Dimension((int) (newWidth * 0.25), (int) (newHeight * 0.05)));
                increaseBet.setPreferredSize(new Dimension((int) (newWidth * 0.25), (int) (newHeight * 0.05)));
                betAmountLabel.setPreferredSize(new Dimension((int) (newWidth * 0.25), (int) (newHeight * 0.05)));
                balanceLabel.setPreferredSize(new Dimension((int) (newWidth * 0.9), (int) (newHeight * 0.05)));
                // Ridisegna il pannello
                menuPanel.revalidate();
                menuPanel.repaint();
            }
        });

        add(menuPanel, BorderLayout.WEST);

    }

    private void createVersionPanel(int width, int height) {
        versionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcVersion = new GridBagConstraints();
        gbcVersion.fill = GridBagConstraints.HORIZONTAL;
        gbcVersion.insets = new Insets(5, 5, 5, 5);
        gbcVersion.gridx = 1;
        versionPanel.setPreferredSize(new Dimension((int) (width * 0.9), (int) (height * 0.1)));
        versionPanel.setBackground(menuPanel.getBackground());
        versionPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        ButtonGroup version = new ButtonGroup();
        manualToggle = new RoundedToggleButton("Manual", (int) (height * 0.05), true);
        manualToggle.setIcon(new BallIcon(15, Color.LIGHT_GRAY));
        manualToggle.setSelectedIcon(new BallIcon(15, Color.GREEN));
        manualToggle.setPreferredSize(new Dimension((int) (width * 0.4), (int) (height * 0.05)));

        autoToggle = new RoundedToggleButton("Auto", (int) (height * 0.05), false);
        autoToggle.setIcon(new BallIcon(15, Color.LIGHT_GRAY));
        autoToggle.setSelectedIcon(new BallIcon(15, Color.GREEN));
        autoToggle.setPreferredSize(new Dimension((int) (width * 0.4), (int) (height * 0.05)));
        autoToggle.addItemListener(e -> handleAuto(e));
        manualToggle.addItemListener(e -> handleManual(e));
        version.add(manualToggle);
        version.add(autoToggle);
        versionPanel.add(manualToggle, gbcVersion);
        gbcVersion.gridx++;
        versionPanel.add(autoToggle, gbcVersion);
        menuPanel.add(versionPanel, BorderLayout.CENTER);
    }

    private void createRiskPanel(int width, int height) {
        riskPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcRisk = new GridBagConstraints();
        gbcRisk.fill = GridBagConstraints.HORIZONTAL;
        gbcRisk.insets = new Insets(5, 2, 5, 2);
        gbcRisk.weightx = 1;
        gbcRisk.gridy = 0;
        riskLabel = new JLabel("Risk: ");
        riskLabel.setForeground(Color.WHITE);
        riskPanel.add(riskLabel, gbcRisk);
        gbcRisk.gridy++;
        riskPanel.setBackground(menuPanel.getBackground());
        riskPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        riskPanel.setPreferredSize(new Dimension((int) (width * 0.9), (int) (height * 0.1)));
        ButtonGroup riskGroup = new ButtonGroup();
        lowRisk = new RoundedToggleButton("Low", (int) (height * 0.05), false);
        mediumRisk = new RoundedToggleButton("Medium", (int) (height * 0.05), true);
        highRisk = new RoundedToggleButton("High", (int) (height * 0.05), false);
        lowRisk.setPreferredSize(new Dimension((int) (width * 0.25), (int) (height * 0.05)));
        mediumRisk.setPreferredSize(new Dimension((int) (width * 0.25), (int) (height * 0.05)));
        highRisk.setPreferredSize(new Dimension((int) (width * 0.25), (int) (height * 0.05)));
        riskGroup.add(lowRisk);
        riskGroup.add(mediumRisk);
        riskGroup.add(highRisk);
        gbcRisk.gridx = 0;
        riskPanel.add(lowRisk, gbcRisk);
        gbcRisk.gridx++;
        riskPanel.add(mediumRisk, gbcRisk);
        gbcRisk.gridx++;
        riskPanel.add(highRisk, gbcRisk);
        gbcRisk.gridx++;

        menuPanel.add(riskPanel, BorderLayout.SOUTH);
    }

    private void createRowSlider(int width, int height) {
        rowPanel = new JPanel();
        rowPanel.setLayout(new BorderLayout());
        rowPanel.setPreferredSize(new Dimension((int) (width * 0.9), (int) (height * 0.1)));
        // Creiamo lo slider (da 8 a 16 righe)
        rowSlider = new JSlider(JSlider.HORIZONTAL, 8, 16, 16);
        rowSlider.setMajorTickSpacing(1);
        rowSlider.setPaintTicks(true);
        rowSlider.setPaintLabels(true);
        rowSlider.setBackground(menuPanel.getBackground());
        rowSlider.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        rowSlider.setForeground(Color.WHITE);

        // Etichetta per mostrare il valore selezionato
        rowLabel = new JLabel("  Rows: " + rowSlider.getValue());

        // Aggiungiamo un listener per aggiornare l'etichetta
        rowSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                rowLabel.setText("Rows: " + rowSlider.getValue());
            }
        });
        // Aggiungiamo i componenti al pannello
        rowPanel.add(rowLabel, BorderLayout.NORTH);
        rowPanel.add(rowSlider, BorderLayout.CENTER);

        // Aggiungiamo il pannello al menu principale
        menuPanel.add(rowPanel, BorderLayout.SOUTH);
    }

    public void createBetPanel(int width, int height) {
        // Bet Amount Panel in stile Plinko
        betAmountPanel = new JPanel(new GridBagLayout());
        betAmountPanel.setPreferredSize(new Dimension((int) (width * 0.9), (int) (height * 0.08)));
        betAmountPanel.setBackground(menuPanel.getBackground());
        betAmountPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        GridBagConstraints gbcBet = new GridBagConstraints();
        gbcBet.fill = GridBagConstraints.HORIZONTAL;
        gbcBet.insets = new Insets(5, 5, 5, 5);
        gbcBet.weightx = 1;

        // Array di valori della puntata
        double[] betValues = {0.10, 0.20, 0.50, 1.00, 2.00, 3.00, 4.00, 5.00, 10.00, 15.00, 25.00, 50.00, 75.00, 100.00};
        final int[] currentBetIndex = {4}; // Index iniziale per €2.00

        betAmountLabel = new JLabel("€" + betValues[currentBetIndex[0]], SwingConstants.CENTER);
        betAmountLabel.setForeground(Color.WHITE);
        betAmountLabel.setPreferredSize(new Dimension((int) (width * 0.25), (int) (height * 0.05)));
        // Pulsante per diminuire la puntata
        decreaseBet = new RoundedButton("-", (int) (height * 0.05));
        decreaseBet.setPreferredSize(new Dimension((int) (width * 0.25), (int) (height * 0.05)));
        decreaseBet.addActionListener(e -> {
            if (currentBetIndex[0] > 0) {
                currentBetIndex[0]--;
                betAmountLabel.setText("€" + betValues[currentBetIndex[0]]);
            }
        });

        // Pulsante per aumentare la puntata
        increaseBet = new RoundedButton("+", (int) (height * 0.05));
        increaseBet.setPreferredSize(new Dimension((int) (width * 0.25), (int) (height * 0.05)));
        increaseBet.addActionListener(e -> {
            if (currentBetIndex[0] < betValues.length - 1) {
                currentBetIndex[0]++;
                betAmountLabel.setText("€" + betValues[currentBetIndex[0]]);
            }
        });

        // Aggiunta dei componenti al pannello
        gbcBet.gridx = 0;
        betAmountPanel.add(decreaseBet, gbcBet);

        gbcBet.gridx = 1;
        betAmountPanel.add(betAmountLabel, gbcBet);

        gbcBet.gridx = 2;
        betAmountPanel.add(increaseBet, gbcBet);

        // Aggiungi il pannello del Bet Amount al menuPanel
        menuPanel.add(betAmountPanel);

    }

    public void createBetButton(int width, int height) {
        betButton = new RoundedButton("BET", (int) (height * 0.08));
        betButton.setPreferredSize(new Dimension((int) (width * 0.9), (int) (height * 0.08)));
        betButton.setForeground(Color.BLUE);
        menuPanel.add(betButton);
        balanceLabel = new JLabel("Demo Balance: €5000.00", SwingConstants.CENTER);
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setPreferredSize(new Dimension((int) (width * 0.9), (int) (height * 0.05)));
        menuPanel.add(balanceLabel);
    }

    private void setRightPanel() {
        loadBackgroundImage();
        JPanel pyramidPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                if (backgroundImage != null) {
                    g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }

                int rows = 16;
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                int startX = panelWidth / 2;
                int gap = panelWidth / 35;
                int startY = (panelHeight - ((rows + 2) * gap)) / 2;

                createPyramid(this, g2d, rows);
                createContainers(this, startX, startY, gap, rows); // Disegna i contenitori

            }
        };

        pyramidPanel.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        pyramidPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                pyramidPanel.repaint();
            }
        });

        add(pyramidPanel, BorderLayout.CENTER);
    }

    private void createPyramid(JPanel pyramidPanel, Graphics2D g2d, int rows) {
        int panelWidth = pyramidPanel.getWidth();
        int panelHeight = pyramidPanel.getHeight();
        int startX = panelWidth / 2;
        int gap = panelWidth / 35;
        int startY = (panelHeight - ((rows + 2) * gap)) / 2;

        for (int i = 2; i < rows + 2; i++) {
            int offsetX = startX - (i * gap / 2);
            for (int j = 0; j <= i; j++) {
                BallIcon nail = new BallIcon(8, Color.YELLOW);
                nail.paintIcon(this, g2d, offsetX + j * gap, startY + i * gap);
            }
        }
    }

    private void createContainers(JPanel pyramidPanel, int startX, int startY, int gap, int rows) {
    // Rimuovi tutte le JLabel esistenti
    pyramidPanel.removeAll();

    int width = (int) (screenSize.width * 0.8);
    int height = screenSize.height;

    int containerWidth = gap;
    int containerHeight = (int) (height * 0.03);
    int numContainers = rows + 1;

    // Calcola la posizione del primo piolo dell'ultima riga
    int firstPegX = startX - (rows * gap / 2)+4; // Posizione X del primo piolo dell'ultima riga

    // Calcola la posizione di partenza dei contenitori
    int containerStartX = firstPegX - (containerWidth / 2); // Allinea i contenitori al centro del primo piolo
    int containerStartY = startY + (rows * gap) + (int)(height*0.06);

    for (int i = 0; i < numContainers; i++) {
        JLabel containerLabel = new JLabel("x" + (i + 1), SwingConstants.CENTER);
        containerLabel.setOpaque(true);
        containerLabel.setBackground(menuPanel.getBackground());
        containerLabel.setForeground(Color.WHITE);
        containerLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        containerLabel.setPreferredSize(new Dimension(containerWidth, containerHeight));

        // Posiziona i contenitori in base al primo piolo
        containerLabel.setBounds(containerStartX + i * containerWidth, containerStartY, containerWidth, containerHeight);

        pyramidPanel.add(containerLabel);
    }

    // Rivalida e ridisegna il pannello
    pyramidPanel.revalidate();
    pyramidPanel.repaint();
}

    public void handleManual(ItemEvent e) {
        //to do
    }

    public void handleAuto(ItemEvent e) {
        //to do
    }

    public void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(getClass().getResource("../utils/background.jpg")); // Percorso relativo
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) throws Exception {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;

// Regola la dimensione del font in base all'altezza dello schermo
        int fontSize = screenHeight / 70; // Formula scalabile

        try {
            InputStream fontStream = JPlinkoGUI.class.getResourceAsStream("../utils/Orbitron-VariableFont_wght.ttf");
            if (fontStream == null) {
                throw new RuntimeException("Font file not found!");
            }
            Font globalFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(Font.PLAIN, fontSize);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(globalFont);
            UIManager.put("Label.font", globalFont);
            UIManager.put("Button.font", globalFont);
            UIManager.put("ToggleButton.font", globalFont);
            UIManager.put("RadioButton.font", globalFont);
            UIManager.put("ComboBox.font", globalFont);
            UIManager.put("TextField.font", globalFont);
            UIManager.put("Panel.font", globalFont);

        } catch (Exception e) {
            e.printStackTrace();
        }

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JPlinkoGUI().setVisible(true);
            }
        });
    }
}
