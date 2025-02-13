package jtetris.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import jtetris.controller.ControllerForView;
import jtetris.utils.Config;

public class MainGUI extends JFrame implements ComponentListener, ActionListener {

	//---------------------------------------------------------------
	// STATIC CONSTANTS
	//---------------------------------------------------------------
	private final static int WINDOW_PREFERRED_WIDTH = 400;
	private final static int WINDOW_PREFERRED_HEIGHT = 600;
	private final static String START_BUTTON_LABEL = "Start";
	private final static String PAUSE_BUTTON_LABEL = "Pause";
	private final static String PLAY_BUTTON_LABEL = "Play";

	//---------------------------------------------------------------
	// INSTANCE ATTRIBUTES
	//---------------------------------------------------------------
	private BoardPanel boardPanel;
	private PreviewPanel previewPanel;

	private JButton menuBut;
	private JButton startPauseBut;
	private JLabel playerNameLab;
	private JLabel playerNamePrefixLab;
	private JLabel playerScoreLab;
	private JLabel playerScorePrefixLab;
	private JLabel nextPiecePrefixLab;
	private JPanel rightPanel;

	private Timer timer;
	private boolean isGameStarted; // a game can start only once at the beginning
	private boolean isGameRunning; // a started game can be running or in pause

	public MainGUI() {
		super("JTetris: a very simple Java version of Tetris");
		this.createGUI();
		this.timer = new Timer(Config.getInstance().getDelayTime(), this);
		this.isGameStarted = false;
		this.isGameRunning = false;
	}

	//---------------------------------------------------------------
	// PRIVATE INSTANCE METHODS
	//---------------------------------------------------------------
	private void createGUI() {
		this.addComponentListener(this);
		//this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		//this.setPreferredSize(new Dimension(WINDOW_PREFERRED_WIDTH, WINDOW_PREFERRED_HEIGHT));
		this.boardPanel = new BoardPanel();
		this.setRightPanel();
		Container contPane = this.getContentPane();
		contPane.setLayout(new BorderLayout());
		contPane.add(this.boardPanel, BorderLayout.CENTER);
		contPane.add(rightPanel, BorderLayout.EAST);
		this.pack();
	}

	private void setRightPanel() {
		this.rightPanel = new JPanel();
		//this.rightPanel.setBackground(Color.ORANGE);
		this.rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		this.rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		this.playerNamePrefixLab = new JLabel("Player Name");
		this.playerNameLab = new JLabel(ControllerForView.getInstance().getPlayerName());
		this.playerScorePrefixLab = new JLabel("Score");
		this.playerScoreLab = new JLabel(ControllerForView.getInstance().getScore());
		this.nextPiecePrefixLab = new JLabel("Next Piece");
		this.previewPanel = new PreviewPanel();
		this.previewPanel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.startPauseBut = new JButton("Start");
		this.startPauseBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startPauseEvent();
			}
		});
		this.startPauseBut.setMnemonic(KeyEvent.VK_P);
		
		this.menuBut = new JButton("Menu");
		this.menuBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuEvent();
			}
		});		

		this.playerNamePrefixLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.playerNameLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.playerScorePrefixLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.playerScoreLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.nextPiecePrefixLab.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.startPauseBut.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		this.menuBut.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

		this.rightPanel.add(playerNamePrefixLab);
		this.rightPanel.add(this.playerNameLab);
		this.rightPanel.add(Box.createVerticalGlue());
		this.rightPanel.add(playerScorePrefixLab);
		this.rightPanel.add(this.playerScoreLab);
		this.rightPanel.add(Box.createVerticalGlue());
		this.rightPanel.add(nextPiecePrefixLab);
		this.rightPanel.add(this.previewPanel);
		this.rightPanel.add(Box.createVerticalGlue());
		this.rightPanel.add(this.startPauseBut);
		this.rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		this.rightPanel.add(this.menuBut);
	} // end method setRightPanel()
	
	private void startPauseEvent() {
		if (!this.isGameStarted) {
			this.isGameStarted = true;
			this.isGameRunning = true;
			ControllerForView.getInstance().initGame();
			this.previewPanel.setPreviewPieceAvailable();
			this.boardPanel.setFallingPieceAvailable();
			this.boardPanel.requestFocusInWindow();
			this.timer.start();
			this.startPauseBut.setText(PAUSE_BUTTON_LABEL);
			this.menuBut.setEnabled(false);
		}
		else if (!this.isGameRunning) {
			this.isGameRunning = true;
			this.boardPanel.requestFocusInWindow();
			this.timer.start();
			this.startPauseBut.setText(PAUSE_BUTTON_LABEL);
			this.menuBut.setEnabled(false);
		}
		else {
			this.isGameRunning = false;
			this.timer.stop();
			this.startPauseBut.setText(PLAY_BUTTON_LABEL);
			this.menuBut.setEnabled(true);
		}
	} // end methos startStopEvent()
	
	private void menuEvent() {
		//to-do
	}

	//---------------------------------------------------------------
	// PUBLIC INSTANCE METHODS
	//---------------------------------------------------------------
	public void setEndGameOrNotStarted() {
		this.isGameStarted = false;
		this.isGameRunning = false;
		this.timer.stop();
		this.startPauseBut.setText(START_BUTTON_LABEL);
		this.menuBut.setEnabled(true);
		this.previewPanel.setPreviewPieceUnavailable();
		this.boardPanel.setFallingPieceUnavailable();
	}
	
	public void updateScoreLabel(int score) {
		this.playerScoreLab.setText(String.valueOf(score));
	}

	//-------------------------------------------------------------------------
	// To implement the interface java.awt.event.ComponentListener
	//-------------------------------------------------------------------------
	public void componentHidden(ComponentEvent e) {
		//do-nothing
	}

	public void componentMoved(ComponentEvent e) {
		//do-nothing
	}

	public void componentResized(ComponentEvent e) {
		this.boardPanel.setGridUnit();
		this.previewPanel.setGridUnit();
	}

	public void componentShown(ComponentEvent e) {
		//do-nothing
	}

	//-------------------------------------------------------------------------
	// To implement the interface java.awt.event.ActionListener
	//-------------------------------------------------------------------------
	/* Method to implement the interface java.awt.ActionListener. */
	public void actionPerformed(ActionEvent e) {
		ControllerForView.getInstance().next();
		this.boardPanel.repaint();
		this.previewPanel.repaint();
	}

} // end class
