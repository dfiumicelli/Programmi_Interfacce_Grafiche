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

/**
 *
 * @author dfiumicelli
 */
public class JPlinkoGUI extends JFrame {

    private BufferedImage backgroundImage;
    private final Dimension screenSize;
    private JPanel menuPanel;

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

        //int menuWidth = (int) (screenSize.width * 0.2); // 20% della larghezza
        menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 40));
        int width = (int) (screenSize.width * 0.2);
        int height = (int) (screenSize.height);
        menuPanel.setPreferredSize(new Dimension(width, height));
        menuPanel.setBackground(new Color(1, 56, 156));

        ButtonGroup version = new ButtonGroup();
        RoundedToggleButton manualToggle = new RoundedToggleButton("Manual", 50, true);
        manualToggle.setIcon(new BallIcon(15, Color.LIGHT_GRAY));
        manualToggle.setSelectedIcon(new BallIcon(15, Color.GREEN));
        manualToggle.setPreferredSize(new Dimension((int) (width * 0.45), 50));

        RoundedToggleButton autoToggle = new RoundedToggleButton("Auto", 50, false);
        autoToggle.setIcon(new BallIcon(15, Color.LIGHT_GRAY));
        autoToggle.setSelectedIcon(new BallIcon(15, Color.GREEN));
        autoToggle.setPreferredSize(new Dimension((int) (width * 0.45), 50));
        autoToggle.addItemListener(e -> handleAuto(e));
        manualToggle.addItemListener(e -> handleManual(e));
        version.add(manualToggle);
        version.add(autoToggle);
        menuPanel.add(manualToggle);
        menuPanel.add(autoToggle);

        JPanel riskPanel = new JPanel(new FlowLayout());
        JLabel riskLabel = new JLabel("Risk: ");
        riskLabel.setForeground(Color.WHITE);
        riskPanel.add(riskLabel);
        riskPanel.setBackground(menuPanel.getBackground());
        riskPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        ButtonGroup riskGroup = new ButtonGroup();
        RoundedToggleButton lowRisk = new RoundedToggleButton("Low", 30, false);
        RoundedToggleButton mediumRisk = new RoundedToggleButton("Medium", 30, true);
        RoundedToggleButton highRisk = new RoundedToggleButton("High", 30, false);
        lowRisk.setPreferredSize(new Dimension((int) (width * 0.25), 30));
        mediumRisk.setPreferredSize(new Dimension((int) (width * 0.25), 30));
        highRisk.setPreferredSize(new Dimension((int) (width * 0.25), 30));
        riskGroup.add(lowRisk);
        riskGroup.add(mediumRisk);
        riskGroup.add(highRisk);
        riskPanel.add(lowRisk);
        riskPanel.add(mediumRisk);
        riskPanel.add(highRisk);
        menuPanel.add(riskLabel, BorderLayout.NORTH);
        menuPanel.add(riskPanel, BorderLayout.SOUTH);

        JPanel rowsPanel = new JPanel(new FlowLayout());
        rowsPanel.add(new JLabel("Rows: "));
        JComboBox<Integer> rowsCombo = new JComboBox<>(new Integer[]{8, 9, 10, 11, 12, 13, 14, 15, 16});
        rowsCombo.setSelectedItem(16);
        rowsPanel.add(rowsCombo);
        menuPanel.add(rowsPanel);

        // Bet Amount Panel in stile Plinko
        JPanel betAmountPanel = new JPanel(new GridBagLayout());
        betAmountPanel.setPreferredSize(new Dimension((int) (width * 0.9), 50));
        betAmountPanel.setBackground(menuPanel.getBackground());
        betAmountPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        GridBagConstraints gbcBet = new GridBagConstraints();
        gbcBet.fill = GridBagConstraints.HORIZONTAL;
        gbcBet.insets = new Insets(5, 5, 5, 5);
        gbcBet.weightx = 1;

        // Array di valori della puntata
        double[] betValues = {0.10, 0.20, 0.50, 1.00, 2.00, 5.00, 10.00, 25.00, 50.00, 75.00, 100.00};
        final int[] currentBetIndex = {4}; // Index iniziale per €2.00

        JLabel betAmountLabel = new JLabel("€" + betValues[currentBetIndex[0]], SwingConstants.CENTER);
        betAmountLabel.setForeground(Color.WHITE);
        betAmountLabel.setPreferredSize(new Dimension((int) (width * 0.25), 30));
        // Pulsante per diminuire la puntata
        RoundedButton decreaseBet = new RoundedButton("-",30);
        decreaseBet.setPreferredSize(new Dimension((int) (width * 0.25), 30));
        decreaseBet.addActionListener(e -> {
            if (currentBetIndex[0] > 0) {
                currentBetIndex[0]--;
                betAmountLabel.setText("€" + betValues[currentBetIndex[0]]);
            }
        });

        // Pulsante per aumentare la puntata
        RoundedButton increaseBet = new RoundedButton("+",30);
        increaseBet.setPreferredSize(new Dimension((int) (width * 0.25), 30));
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

        RoundedButton betButton = new RoundedButton("BET", 50);
        betButton.setPreferredSize(new Dimension((int) (width * 0.9), 50));
        betButton.setForeground(Color.BLUE);

        menuPanel.add(betButton);

        JLabel balanceLabel = new JLabel("Demo Balance: €5000.00", SwingConstants.CENTER);
        balanceLabel.setForeground(Color.WHITE);
        menuPanel.add(balanceLabel);

        menuPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ricalcola le dimensioni dei componenti in base alla nuova dimensione del pannello
                int newWidth = menuPanel.getWidth();
                int newHeight = menuPanel.getHeight();

                // Ridimensiona i pulsanti
                manualToggle.setPreferredSize(new Dimension((int) (newWidth * 0.45), 50));
                autoToggle.setPreferredSize(new Dimension((int) (newWidth * 0.45), 50));
                betButton.setPreferredSize(new Dimension((int) (newWidth * 0.9), 50));
                // Ridisegna il pannello
                menuPanel.revalidate();
                menuPanel.repaint();
            }
        });

        add(menuPanel, BorderLayout.WEST);

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
                createPyramid(this, g2d, 16);

            }
        };
        pyramidPanel.setPreferredSize(new Dimension((int) (screenSize.width), screenSize.height));
        pyramidPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                pyramidPanel.repaint(); // Ridisegna la piramide
            }
        });

        add(pyramidPanel, BorderLayout.CENTER);

    }

    private void createPyramid(JPanel pyramidPanel, Graphics2D g2d, int rows) {

        int panelWidth = pyramidPanel.getWidth();
        int panelHeight = pyramidPanel.getHeight();
        int startX = panelWidth / 2;
        int gap = panelWidth / 40; // Gap proporzionale alla larghezza del pannello
        int startY = (panelHeight - ((rows + 2) * gap)) / 2;

        for (int i = 2; i < rows + 2; i++) {
            int offsetX = startX - (i * gap / 2);
            for (int j = 0; j <= i; j++) {
                BallIcon nail = new BallIcon(8, Color.YELLOW);
                nail.paintIcon(this, g2d, offsetX + j * gap, startY + i * gap);
            }
        }
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

        try {
            InputStream fontStream = JPlinkoGUI.class.getResourceAsStream("../utils/Orbitron-VariableFont_wght.ttf");
            if (fontStream == null) {
                throw new RuntimeException("Font file not found!");
            }
            Font globalFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(Font.PLAIN, 15);
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
