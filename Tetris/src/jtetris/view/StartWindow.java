package jtetris.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jtetris.controller.ControllerForView;

public class StartWindow extends JFrame {

	private JButton jbutNewGame;
	private JButton jbutLoadGame;

	public StartWindow() {
		super("JTetris: start to play ...");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.jbutNewGame = new JButton("New");
		this.jbutNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleNewGameEvent();
			}
		});
		JLabel jlabNewGame = new JLabel("Start a new game");

		this.jbutLoadGame = new JButton("Load");
		this.jbutLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleLoadGameEvent();
			}
		});
		JLabel jlabLoadGame = new JLabel("Load a previously saved game");

		Container contPane = this.getContentPane();

		GroupLayout layout = new GroupLayout(contPane);
		contPane.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(jbutNewGame)
					.addComponent(jbutLoadGame))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(jlabNewGame)
					.addComponent(jlabLoadGame))
		);

		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(jbutNewGame)
					.addComponent(jlabNewGame))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(jbutLoadGame)
					.addComponent(jlabLoadGame))
		);

		this.pack();
	} // end constructor

	private void handleNewGameEvent() {
		//System.out.println("Event: new game");
		ControllerForView.getInstance().closeStartWindow();
		ControllerForView.getInstance().openNewGameWindow();
	}

	private void handleLoadGameEvent() {
		//System.out.println("Event: load a previously saved game");
		//TO-DO
	}

} // end class
