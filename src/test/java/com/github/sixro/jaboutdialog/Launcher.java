package com.github.sixro.jaboutdialog;

import java.awt.Window;

import javax.swing.JFrame;

public class Launcher {

	public static void main(String... args) {
		//System.setProperty("swing.default.hgap", "12");
		
		JAboutDialog dialog = new JAboutDialog((Window) null, "About...");
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setSize(400, 300);
		dialog.setVisible(true);
	}
	
}
