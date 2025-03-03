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
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.InputStream;
import javax.swing.event.ChangeEvent;
import jplinko.model.Model;
import jplinko.utils.SoundPlayer;

/**
 *
 * @author dfiumicelli
 */
public class JPlinkoGUI extends JFrame {

    private BufferedImage backgroundImage;
    private BufferedImage logoImage;
    private final Dimension screenSize;
    private JPanel menuPanel, versionPanel, riskPanel, rowPanel, betAmountPanel, betSliderPanel;
    private RoundedButton betButton, increaseBet, decreaseBet;
    private RoundedToggleButton manualToggle, autoToggle, lowRisk, mediumRisk, highRisk;
    private JLabel riskLabel, rowLabel, betAmountLabel, balanceLabel, betLabel, betIndicatorLabel;
    private JSlider rowSlider, betSlider;
    private SoundPlayer click, betClick;
    private int currentBetIndex;
    private double[] betValues = {0.10, 0.20, 0.50, 1.00, 2.00, 3.00, 4.00, 5.00, 10.00, 15.00, 25.00, 50.00, 75.00, 100.00};

    public JPlinkoGUI() throws Exception {
        super("JPlinkoGUI");
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.click = new SoundPlayer("click.wav");
        this.betClick = new SoundPlayer("bet_click.wav");
        this.currentBetIndex = 4;
        Image logo = loadImage("../utils/logo.png"); // Percorso relativo alla cartella delle risorse

        // Imposta l'icona della finestra
        if (logo != null) {
            setIconImage(logo);
        } else {
            System.err.println("Logo non trovato!");
        }
        this.setFont();
        this.createGUI();

    }

    private void createGUI() {

        setTitle("Plinko Game");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setLeftPanel();
        setRightPanel();

        setVisible(true);

    }

    private void setLeftPanel() {

        menuPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10); // Margini tra i componenti
        gbc.fill = GridBagConstraints.HORIZONTAL; // Riempi orizzontalmente
        gbc.weightx = 1.0; // Espandi i componenti orizzontalmente
        int width = (int) (screenSize.width * 0.2);
        int height = (screenSize.height);
        menuPanel.setPreferredSize(new Dimension(width, height));
        //menuPanel.setBackground(new Color(1, 56, 156));

        menuPanel.setBackground(new Color(1, 38, 126));

        createVersionPanel(width, height, gbc);

        createRiskPanel(width, height, gbc);

        createRowSlider(width, height, gbc);

        createBetPanel(width, height, gbc);

        createBetSlider(width, height, gbc);

        createBetButton(width, height, gbc);

        menuPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ridimensiona il pannello di sinistra in base alla nuova dimensione della finestra
                int newWidth = getWidth();
                int newHeight = getHeight();
                menuPanel.setPreferredSize(new Dimension((int) (newWidth * 0.2), newHeight)); // 20% della larghezza
                menuPanel.revalidate(); // Aggiorna il layout
            }
        });

        add(menuPanel, BorderLayout.WEST);

    }

    private void createVersionPanel(int width, int height, GridBagConstraints gbc) {

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
        gbc.gridy = 0; // Posizione nella griglia

        versionPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ricalcola le dimensioni dei componenti in base alla nuova dimensione del pannello
                int newWidth = menuPanel.getWidth();
                int newHeight = menuPanel.getHeight();

                // Ridimensiona i pulsanti
                versionPanel.setPreferredSize(new Dimension((int) (newWidth * 0.9), (int) (newHeight * 0.08)));
                manualToggle.setPreferredSize(new Dimension((int) (newWidth * 0.4), (int) (newHeight * 0.05)));
                autoToggle.setPreferredSize(new Dimension((int) (newWidth * 0.4), (int) (newHeight * 0.05)));

                // Ridisegna il pannello
                versionPanel.revalidate();
                versionPanel.repaint();
            }
        });

        menuPanel.add(versionPanel, gbc);
    }

    private void createRiskPanel(int width, int height, GridBagConstraints gbc) {
        riskPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcRisk = new GridBagConstraints();
        gbcRisk.fill = GridBagConstraints.HORIZONTAL;
        gbcRisk.insets = new Insets(5, 2, 5, 2);
        gbcRisk.weightx = 1;
        gbcRisk.gridy = 0;
        riskLabel = new JLabel("Risk: ");
        riskLabel.setForeground(Color.WHITE);
        gbc.gridy++;
        menuPanel.add(riskLabel, gbc);

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

        lowRisk.addItemListener(e -> handleLow(e));
        mediumRisk.addItemListener(e -> handleMedium(e));
        highRisk.addItemListener(e -> handleHigh(e));

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
        gbc.gridy++; // Posizione nella griglia

        riskPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ricalcola le dimensioni dei componenti in base alla nuova dimensione del pannello
                int newWidth = menuPanel.getWidth();
                int newHeight = menuPanel.getHeight();

                // Ridimensiona i pulsanti
                lowRisk.setPreferredSize(new Dimension((int) (newWidth * 0.25), (int) (newHeight * 0.05)));
                mediumRisk.setPreferredSize(new Dimension((int) (newWidth * 0.25), (int) (newHeight * 0.05)));
                highRisk.setPreferredSize(new Dimension((int) (newWidth * 0.25), (int) (newHeight * 0.05)));
                riskPanel.setPreferredSize(new Dimension((int) (newWidth * 0.9), (int) (newHeight * 0.1)));

                // Ridisegna il pannello
                riskPanel.revalidate();
                riskPanel.repaint();
            }
        });

        menuPanel.add(riskPanel, gbc);

    }

    private void createRowSlider(int width, int height, GridBagConstraints gbc) {
        rowPanel = new JPanel();
        rowPanel.setLayout(new BorderLayout());
        rowPanel.setPreferredSize(new Dimension((int) (width * 0.9), (int) (height * 0.1)));
        // Creiamo lo slider (da 8 a 16 righe)
        rowSlider = new JSlider(JSlider.HORIZONTAL, 8, 16, 16);
        rowSlider.setMajorTickSpacing(1);
        rowSlider.setPaintTicks(true);
        rowSlider.setPaintLabels(true);
        rowSlider.setBackground(menuPanel.getBackground());
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        rowSlider.setForeground(Color.WHITE);

        // Etichetta per mostrare il valore selezionato
        rowLabel = new JLabel("Rows: " + rowSlider.getValue());
        rowLabel.setOpaque(true);
        rowLabel.setBackground(menuPanel.getBackground());
        rowLabel.setForeground(Color.WHITE);
        // Aggiungiamo un listener per aggiornare l'etichetta
        rowSlider.addChangeListener((ChangeEvent e) -> handleRowSlider(e));
        // Aggiungiamo i componenti al pannello
        gbc.gridy++;
        menuPanel.add(rowLabel, gbc);
        rowPanel.add(rowSlider, BorderLayout.CENTER);

        gbc.gridy++;

        rowPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ricalcola le dimensioni dei componenti in base alla nuova dimensione del pannello
                int newWidth = menuPanel.getWidth();
                int newHeight = menuPanel.getHeight();

                // Ridimensiona i pulsanti
                rowPanel.setPreferredSize(new Dimension((int) (newWidth * 0.9), (int) (newHeight * 0.1)));

                // Ridisegna il pannello
                rowPanel.revalidate();
                rowPanel.repaint();
            }
        });

        menuPanel.add(rowPanel, gbc);
    }

    private void createBetSlider(int width, int height, GridBagConstraints gbc) {

        betSliderPanel = new JPanel();
        betSliderPanel.setLayout(new BorderLayout());
        betSliderPanel.setPreferredSize(new Dimension((int) (width * 0.9), (int) (height * 0.1)));
        // Creiamo lo slider 
        betSlider = new JSlider(JSlider.HORIZONTAL, 5, 30, 5);
        betSlider.setMajorTickSpacing(5);
        betSlider.setPaintTicks(true);
        betSlider.setPaintLabels(true);
        betSlider.setBackground(menuPanel.getBackground());
        betSliderPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        betSlider.setForeground(Color.WHITE);

        // Etichetta per mostrare il valore selezionato
        betLabel = new JLabel("Number of rounds: " + betSlider.getValue());
        betLabel.setOpaque(true);
        betLabel.setBackground(menuPanel.getBackground());
        betLabel.setForeground(Color.WHITE);
        // Aggiungiamo un listener per aggiornare l'etichetta
        betSlider.addChangeListener((ChangeEvent e) -> {
            betLabel.setText("Number of balls: " + betSlider.getValue());
        });
        // Aggiungiamo i componenti al pannello
        gbc.gridy = 7;
        gbc.weighty = 0.0;
        menuPanel.add(betLabel, gbc);
        betSliderPanel.add(betSlider, BorderLayout.CENTER);
        betSliderPanel.setVisible(false);
        betLabel.setVisible(false);
        // Aggiungiamo il pannello al menu principale
        gbc.gridy = 8;
        gbc.weighty = 0.0; // Posizione nella griglia

        betSliderPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ricalcola le dimensioni dei componenti in base alla nuova dimensione del pannello
                int newWidth = menuPanel.getWidth();
                int newHeight = menuPanel.getHeight();

                // Ridimensiona i pulsanti
                betSliderPanel.setPreferredSize(new Dimension((int) (newWidth * 0.9), (int) (newHeight * 0.1)));

                // Ridisegna il pannello
                betSliderPanel.revalidate();
                betSliderPanel.repaint();
            }
        });

        menuPanel.add(betSliderPanel, gbc);
    }

    public void createBetPanel(int width, int height, GridBagConstraints gbc) {
        // Bet Amount Panel in stile Plinko
        betAmountPanel = new JPanel(new GridBagLayout());
        betAmountPanel.setPreferredSize(new Dimension((int) (width * 0.9), (int) (height * 0.08)));
        betAmountPanel.setBackground(menuPanel.getBackground());
        betAmountPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        GridBagConstraints gbcBet = new GridBagConstraints();
        gbcBet.fill = GridBagConstraints.HORIZONTAL;
        gbcBet.insets = new Insets(5, 5, 5, 5);
        gbcBet.weightx = 1;

        betIndicatorLabel = new JLabel("Bet Amount:");
        betIndicatorLabel.setOpaque(true);
        betIndicatorLabel.setBackground(menuPanel.getBackground());
        betIndicatorLabel.setForeground(Color.WHITE);
        gbc.gridy++;
        menuPanel.add(betIndicatorLabel, gbc);

        betAmountLabel = new JLabel("€" + betValues[currentBetIndex], SwingConstants.CENTER);
        betAmountLabel.setForeground(Color.WHITE);
        betAmountLabel.setPreferredSize(new Dimension((int) (width * 0.25), (int) (height * 0.05)));
        Font font = new Font("Arial", Font.PLAIN, 20); // Aumenta la dimensione del font

        // Pulsante per diminuire la puntata
        decreaseBet = new RoundedButton("\u25BC", (int) (height * 0.05));
        decreaseBet.setFont(font);
        decreaseBet.setForeground(menuPanel.getBackground());
        decreaseBet.setPreferredSize(new Dimension((int) (width * 0.25), (int) (height * 0.05)));
        decreaseBet.addActionListener(e -> handleDecrese(e));

        // Pulsante per aumentare la puntata
        increaseBet = new RoundedButton("\u25B2", (int) (height * 0.05));
        increaseBet.setFont(font);
        increaseBet.setForeground(menuPanel.getBackground());
        increaseBet.setPreferredSize(new Dimension((int) (width * 0.25), (int) (height * 0.05)));
        increaseBet.addActionListener(e -> handleIncrease(e));

        // Aggiunta dei componenti al pannello
        gbcBet.gridx = 0;
        betAmountPanel.add(decreaseBet, gbcBet);

        gbcBet.gridx = 1;
        betAmountPanel.add(betAmountLabel, gbcBet);

        gbcBet.gridx = 2;
        betAmountPanel.add(increaseBet, gbcBet);

        // Aggiungi il pannello del Bet Amount al menuPanel
        gbc.gridy++; // Posizione nella griglia

        betAmountPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ricalcola le dimensioni dei componenti in base alla nuova dimensione del pannello
                int newWidth = menuPanel.getWidth();
                int newHeight = menuPanel.getHeight();

                // Ridimensiona i pulsanti
                betAmountPanel.setPreferredSize(new Dimension((int) (newWidth * 0.9), (int) (newHeight * 0.08)));
                decreaseBet.setPreferredSize(new Dimension((int) (newWidth * 0.25), (int) (newHeight * 0.05)));
                increaseBet.setPreferredSize(new Dimension((int) (newWidth * 0.25), (int) (newHeight * 0.05)));
                betAmountLabel.setPreferredSize(new Dimension((int) (newWidth * 0.25), (int) (newHeight * 0.05)));

                // Ridisegna il pannello
                betAmountPanel.revalidate();
                betAmountPanel.repaint();
            }
        });

        menuPanel.add(betAmountPanel, gbc);

    }

    public void createBetButton(int width, int height, GridBagConstraints gbc) {
        betButton = new RoundedButton("BET", (int) (height * 0.07));
        betButton.setPreferredSize(new Dimension((int) (width * 0.9), (int) (height * 0.07)));
        betButton.setForeground(Color.BLUE);
        gbc.gridy = 9; // Ultima riga disponibile
        gbc.weighty = 1.0; // Espandi lo spazio verticale sopra i componenti
        gbc.anchor = GridBagConstraints.PAGE_END; // Ancora i componenti in basso
        menuPanel.add(betButton, gbc);

        balanceLabel = new JLabel("Demo Balance: €5000.00", SwingConstants.CENTER);
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setPreferredSize(new Dimension((int) (width * 0.9), (int) (height * 0.05)));
        gbc.gridy = 10; // Riga successiva
        gbc.weighty = 0.0; // Non espandere ulteriormente lo spazio verticale

        menuPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ricalcola le dimensioni dei componenti in base alla nuova dimensione del pannello
                int newWidth = menuPanel.getWidth();
                int newHeight = menuPanel.getHeight();

                // Ridimensiona i pulsanti
                betButton.setPreferredSize(new Dimension((int) (newWidth * 0.9), (int) (newHeight * 0.07)));
                balanceLabel.setPreferredSize(new Dimension((int) (newWidth * 0.9), (int) (newHeight * 0.05)));
                // Ridisegna il pannello
                menuPanel.revalidate();
                menuPanel.repaint();
            }
        });

        betButton.addActionListener(e -> handleBet(e));
        menuPanel.add(balanceLabel, gbc);
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

                int rows = View.getInstance().getRows();
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                int startX = panelWidth / 2;
                int gap = panelWidth / 35;
                int startY = (panelHeight - ((rows + 2) * gap)) / 2;

                createPyramid(this, g2d, rows);
                createContainers(this, startX, startY, gap, rows); // Disegna i contenitori
                loadLogoImage(panelHeight);
                if (logoImage != null) {
                    int imageWidth = logoImage.getWidth();

                    // Calcola la posizione X e Y per centrare l'immagine sopra la piramide
                    int imageX = (panelWidth - imageWidth) / 2;
                    int imageY = -(int) (panelHeight * 0.04);

                    // Disegna l'immagine
                    g2d.drawImage(logoImage, imageX, imageY, null);
                }
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

        int height = screenSize.height;

        int containerWidth = gap;
        int containerHeight = (int) (height * 0.03);
        int numContainers = rows + 1;

        // Calcola la posizione del primo piolo dell'ultima riga
        int firstPegX = startX - (rows * gap / 2) + 4; // Posizione X del primo piolo dell'ultima riga

        // Calcola la posizione di partenza dei contenitori
        int containerStartX = firstPegX - (containerWidth / 2); // Allinea i contenitori al centro del primo piolo
        int containerStartY = startY + (rows * gap) + (int) (height * 0.06);

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
        click.playSound();
        betSliderPanel.setVisible(false);
        betLabel.setVisible(false);
    }

    public void handleAuto(ItemEvent e) {

        betSliderPanel.setVisible(true);
        betLabel.setVisible(true);
    }

    public void handleBet(ActionEvent e) {
        betClick.playSound(); // Riproduci l'effetto sonoro
    }

    public void handleIncrease(ActionEvent e) {
        click.playSound(); // Riproduci l'effetto sonoro
        if (currentBetIndex < betValues.length - 1) {
            currentBetIndex++;
            betAmountLabel.setText("€" + betValues[currentBetIndex]);
        }
    }

    public void handleDecrese(ActionEvent e) {
        click.playSound(); // Riproduci l'effetto sonoro
        if (currentBetIndex > 0) {
            currentBetIndex--;
            betAmountLabel.setText("€" + betValues[currentBetIndex]);
        }
    }

    public void handleLow(ItemEvent e) {
        click.playSound();
    }

    public void handleMedium(ItemEvent e) {
        click.playSound();
    }

    public void handleHigh(ItemEvent e) {
        click.playSound();
    }

    private void handleRowSlider(ChangeEvent e) {
        rowLabel.setText("Rows: " + rowSlider.getValue());
        //if (!rowSlider.getValueIsAdjusting()) { // Reagisci solo quando l'utente rilascia lo slider
        View.getInstance().handleRowChanges(rowSlider.getValue());
        //}

    }

    public void loadLogoImage(int panelHeigth) {
        try {
            BufferedImage originalImage = ImageIO.read(getClass().getResource("../utils/logo1.png"));
            double scaleFactor = panelHeigth * 0.0007; // Riduci l'immagine al 50%
            int newWidth = (int) (originalImage.getWidth() * scaleFactor);
            int newHeight = (int) (originalImage.getHeight() * scaleFactor);
            logoImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = logoImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g2d.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(getClass().getResource("../utils/background.jpg")); // Percorso relativo
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Image loadImage(String path) {
        try {
            return new ImageIcon(getClass().getResource(path)).getImage();
        } catch (Exception e) {
            System.err.println("Errore durante il caricamento dell'immagine: " + path);
            return null;
        }
    }

    private void setFont() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;

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
    }

    public int getRows() {
        return rowSlider.getValue();
    }

}
