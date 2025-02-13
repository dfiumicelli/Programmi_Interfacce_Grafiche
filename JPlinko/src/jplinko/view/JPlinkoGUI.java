/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jplinko.view;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
/**
 *
 * @author dfiumicelli
 */
public class JPlinkoGUI extends JFrame{

    public JPlinkoGUI() {
        setTitle("Plinko Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));
        
        // Left panel (Menu)
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(10, 1));
        
        JToggleButton manualAutoToggle = new JToggleButton("Manual/Auto");
        menuPanel.add(manualAutoToggle);
        
        JPanel riskPanel = new JPanel(new FlowLayout());
        riskPanel.add(new JLabel("Risk: "));
        ButtonGroup riskGroup = new ButtonGroup();
        JRadioButton lowRisk = new JRadioButton("Low");
        JRadioButton mediumRisk = new JRadioButton("Medium", true);
        JRadioButton highRisk = new JRadioButton("High");
        riskGroup.add(lowRisk);
        riskGroup.add(mediumRisk);
        riskGroup.add(highRisk);
        riskPanel.add(lowRisk);
        riskPanel.add(mediumRisk);
        riskPanel.add(highRisk);
        menuPanel.add(riskPanel);
        
        JPanel rowsPanel = new JPanel(new FlowLayout());
        rowsPanel.add(new JLabel("Rows: "));
        JComboBox<Integer> rowsCombo = new JComboBox<>(new Integer[]{8, 9, 10, 11, 12, 13, 14, 15, 16});
        rowsCombo.setSelectedItem(16);
        rowsPanel.add(rowsCombo);
        menuPanel.add(rowsPanel);
        
        JPanel betPanel = new JPanel(new FlowLayout());
        betPanel.add(new JLabel("Bet Amount: "));
        JTextField betAmountField = new JTextField("€2.00", 5);
        betPanel.add(betAmountField);
        menuPanel.add(betPanel);
        
        JButton betButton = new JButton("BET");
        menuPanel.add(betButton);
        
        JLabel balanceLabel = new JLabel("Demo Balance: €5000.00", SwingConstants.CENTER);
        menuPanel.add(balanceLabel);
        
        add(menuPanel);
        
        // Right panel (Pyramid)
        JPanel pyramidPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                
                // Gradient background
                GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, getWidth(), getHeight(), Color.CYAN);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                g2d.setColor(Color.WHITE);
                int rows = 16;
                int startX = getWidth() / 2;
                int startY = 50;
                int gap = 40;
                for (int i = 0; i < rows; i++) {
                    int offsetX = startX - (i * gap / 2);
                    for (int j = 0; j <= i; j++) {
                        g2d.fill(new Ellipse2D.Double(offsetX + j * gap, startY + i * gap, 10, 10));
                    }
                }
            }
        };
        pyramidPanel.setBackground(Color.DARK_GRAY);
        add(pyramidPanel);
        
        setVisible(true);
    }
    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JPlinkoGUI().setVisible(true);
            }
        });
    }
}
