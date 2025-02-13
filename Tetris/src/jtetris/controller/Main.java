package jtetris.controller;

import jtetris.utils.Config;

public class Main {

	public static void main(String[] args) {
		Config.getInstance(); // just to test at the beginning whether the configuration file is properly red
		ControllerForView.getInstance().openStartWindow();
	}

} // end class