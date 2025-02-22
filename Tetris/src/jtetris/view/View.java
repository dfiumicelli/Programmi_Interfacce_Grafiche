package jtetris.view;

import javax.swing.JOptionPane;

public class View implements IView {

	//---------------------------------------------------------------
	// STATIC FIELDS
	//---------------------------------------------------------------
	private static View instance = null;

	//---------------------------------------------------------------
	// INSTANCE ATTRIBUTES
	//---------------------------------------------------------------
	protected StartWindow startWindow = null;
	protected NewGameWindow newGameWindow = null;
	protected MainGUI mainGUI = null;

	private View() {
		//TO-DO
	}

	//---------------------------------------------------------------
	// INSTANCE METHODS
	//---------------------------------------------------------------
	public void openStartWindow() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (startWindow == null)
					startWindow = new StartWindow();
				startWindow.setVisible(true);
			}
		});
	}

	public void closeStartWindow() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (startWindow != null)
					startWindow.setVisible(false);
			}
		});
	}

	public void openNewGameWindow() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (newGameWindow == null)
					newGameWindow = new NewGameWindow();
				newGameWindow.setVisible(true);
			}
		});
	}

	public void closeNewGameWindow() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (newGameWindow != null)
					newGameWindow.setVisible(false);
			}
		});
	}

	public void openMainGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (mainGUI == null)
					mainGUI = new MainGUI();
				mainGUI.setVisible(true);
			}
		});
	}
	
	public void updateScoreLabel(int score) {
		this.mainGUI.updateScoreLabel(score);
	}
	
	public void gameOverDialog() {
		this.mainGUI.setEndGameOrNotStarted();
		JOptionPane.showMessageDialog(this.mainGUI, "Game Over!\nPress Start to start a new game");
		this.mainGUI.updateScoreLabel(0);
	}

	//---------------------------------------------------------------
	// STATIC METHODS
	//---------------------------------------------------------------
	public static IView getInstance() {
		if (instance == null)
			instance = new View();
		return instance;
	}

} // end class
