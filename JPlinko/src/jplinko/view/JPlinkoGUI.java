/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jplinko.view;
import jplinko.utils.BallIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import javax.swing.UIManager;

/**
 *
 * @author dfiumicelli
 */
public class JPlinkoGUI extends JFrame{

    public JPlinkoGUI() {
        setTitle("Plinko Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Left panel (Menu)
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 40));
        menuPanel.setPreferredSize(new Dimension(getWidth() * 30 / 100, getHeight()));
        
        JToggleButton manualToggle = new JToggleButton("Manual",true);
        manualToggle.setIcon(new BallIcon(15, Color.LIGHT_GRAY));
        manualToggle.setSelectedIcon(new BallIcon(15, Color.GREEN));
        manualToggle.setPreferredSize(new Dimension(100,40));

        
        JToggleButton autoToggle = new JToggleButton("Auto",false);
        autoToggle.setIcon(new BallIcon(15, Color.LIGHT_GRAY));
        autoToggle.setSelectedIcon(new BallIcon(15, Color.GREEN));
        autoToggle.setPreferredSize(new Dimension(100,40));
        autoToggle.addItemListener(e-> handleAuto(e,manualToggle));
        manualToggle.addItemListener(e -> handleManual(e,autoToggle));
        menuPanel.add(manualToggle);
        menuPanel.add(autoToggle);
        
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
        betButton.setPreferredSize(new Dimension(200,50));
        menuPanel.add(betButton);
        
        JLabel balanceLabel = new JLabel("Demo Balance: €5000.00", SwingConstants.CENTER);
        menuPanel.add(balanceLabel);
        
        add(menuPanel, BorderLayout.WEST);
        
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
                
                int rows = 16;
                int startX = getWidth() / 2;
                int gap = 40;
                int startY = (getHeight() - (rows * gap)) / 2;;
                
                for (int i = 0; i < rows; i++) {
                    int offsetX = startX - (i * gap / 2);
                    for (int j = 0; j <= i; j++) {
                        int circleSize = 14;
                        
                        // Create a 3D effect for the pegs
                        GradientPaint sphereGradient = new GradientPaint(
                            offsetX + j * gap, startY + i * gap, Color.LIGHT_GRAY,
                            offsetX + j * gap + circleSize, startY + i * gap + circleSize, Color.WHITE);
                        g2d.setPaint(sphereGradient);
                        g2d.fillOval(offsetX + j * gap, startY + i * gap, circleSize, circleSize);
                        
                        // Add a slight shadow effect
                        g2d.setColor(new Color(50, 50, 50, 80));
                        g2d.fillOval(offsetX + j * gap + 2, startY + i * gap + 2, circleSize, circleSize);
                    }
                }
            }
        };
        pyramidPanel.setPreferredSize(new Dimension(getWidth() * 70 / 100, getHeight()));
        add(pyramidPanel, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    public void handleManual(ItemEvent e, JToggleButton autoToggle){
        if (e.getStateChange() == ItemEvent.SELECTED)
            autoToggle.setSelected(false);
        
    }
    
    public void handleAuto(ItemEvent e, JToggleButton manualToggle){
        if (e.getStateChange() == ItemEvent.SELECTED)
            manualToggle.setSelected(false);
        
    }
    
    public static void main(String args[]) throws Exception{
        
        //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JPlinkoGUI().setVisible(true);
            }
        });
    }
}
