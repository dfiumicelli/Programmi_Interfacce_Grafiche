package jtetris.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import jtetris.controller.ControllerForView;

public class NewGameWindow extends JFrame {

	private JButton jbutStartGame;
	private JTextField jtfName;

	public NewGameWindow() {
		super("JTetris:: set player's name");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel jlabName = new JLabel("Player name: ");
		this.jtfName = new JTextField(20);
		this.jtfName.setText(ControllerForView.getInstance().getPlayerName());

		this.jbutStartGame = new JButton("Start");
		this.jbutStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleStartGameEvent(jtfName.getText());
			}
		});

		Container contPane = this.getContentPane();

		GroupLayout layout = new GroupLayout(contPane);
		contPane.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(jlabName)
					.addComponent(jbutStartGame))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(jtfName))
		);

		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(jlabName)
					.addComponent(jtfName))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(jbutStartGame))
		);

		this.pack();
	} // end constructor

	private void handleStartGameEvent(String playerName) {
		//System.out.println("Event start game");
		ControllerForView.getInstance().setPlayerName(playerName);
		ControllerForView.getInstance().openMainGUI();
	}

} // end class
