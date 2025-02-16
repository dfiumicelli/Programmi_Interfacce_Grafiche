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
import java.io.InputStream;

/**
 *
 * @author dfiumicelli
 */
public class JPlinkoGUI extends JFrame{
    
    private BufferedImage backgroundImage;
    private final Dimension screenSize;


    public JPlinkoGUI() {
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.createGUI();
        
    }
    
    private void createGUI(){
    
        setTitle("Plinko Game");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setLeftPanel();
        setRightPanel();
 
        setVisible(true);
        
    }
    
    private void setLeftPanel(){
        
        int menuWidth = (int) (screenSize.width * 0.2); // 20% della larghezza
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 40));
        menuPanel.setPreferredSize(new Dimension(menuWidth, screenSize.height));
        menuPanel.setBackground(new Color(1,56,156));

        
        ButtonGroup version = new ButtonGroup();
        RoundedToggleButton manualToggle = new RoundedToggleButton("Manual",50,true);
        manualToggle.setIcon(new BallIcon(15, Color.LIGHT_GRAY));
        manualToggle.setSelectedIcon(new BallIcon(15, Color.GREEN));
        manualToggle.setPreferredSize(new Dimension(160,50));

        
        RoundedToggleButton autoToggle = new RoundedToggleButton("Auto",50,false);
        autoToggle.setIcon(new BallIcon(15, Color.LIGHT_GRAY));
        autoToggle.setSelectedIcon(new BallIcon(15, Color.GREEN));
        autoToggle.setPreferredSize(new Dimension(160,50));
        autoToggle.addItemListener(e-> handleAuto(e));
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
        RoundedToggleButton lowRisk = new RoundedToggleButton("Low",30,false);
        RoundedToggleButton mediumRisk = new RoundedToggleButton("Medium", 30, true);
        RoundedToggleButton highRisk = new RoundedToggleButton("High",30,false);
        riskGroup.add(lowRisk);
        riskGroup.add(mediumRisk);
        riskGroup.add(highRisk);
        riskPanel.add(lowRisk);
        riskPanel.add(mediumRisk);
        riskPanel.add(highRisk);
        menuPanel.add(riskLabel);
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
        
        RoundedButton betButton = new RoundedButton("BET",50);
        betButton.setPreferredSize(new Dimension(320,50));
        betButton.setForeground(Color.BLUE);
        
        menuPanel.add(betButton);
        
        JLabel balanceLabel = new JLabel("Demo Balance: €5000.00", SwingConstants.CENTER);
        balanceLabel.setForeground(Color.WHITE);
        menuPanel.add(balanceLabel);
        
        add(menuPanel, BorderLayout.WEST); 
        
    }
    
    private void setRightPanel(){
        
        loadBackgroundImage();
    
        int pyramidWidth = (int) (screenSize.width * 0.8); // 80% della larghezza
        JPanel pyramidPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                
                if (backgroundImage != null) {
                    g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                    }
                createPyramid(this,g2d,16);
                
            }
        };
        pyramidPanel.setPreferredSize(new Dimension(pyramidWidth, screenSize.height));
        add(pyramidPanel, BorderLayout.CENTER);
    
    }
    
    private void createPyramid(JPanel pyramidPanel, Graphics2D g2d, int rows){
    
        int startX = pyramidPanel.getWidth() / 2;
        int gap = 40;
        int startY = (pyramidPanel.getHeight() - ((rows +2) * gap)) / 2;;
        for (int i = 2; i < rows+2; i++) {
            int offsetX = startX - (i * gap / 2);
            for (int j = 0; j <= i; j++) {
                BallIcon nail = new BallIcon(8, Color.YELLOW);    
                nail.paintIcon(this, g2d, offsetX + j * gap, startY + i * gap);
            }
        }
    }
    
    public void handleManual(ItemEvent e){
        //to do
    }
    
    public void handleAuto(ItemEvent e){
        //to do
    }
    
    public void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(getClass().getResource("../utils/background.jpg")); // Percorso relativo
        } catch (IOException e) {
            e.printStackTrace();
        }

}
    
    public static void main(String args[]) throws Exception{
        
        //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        
        try {
            InputStream fontStream = JPlinkoGUI.class.getResourceAsStream("../utils/Orbitron-VariableFont_wght.ttf");
            if (fontStream == null) {
                throw new RuntimeException("Font file not found!");
            }
            Font globalFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(Font.PLAIN,15);
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
