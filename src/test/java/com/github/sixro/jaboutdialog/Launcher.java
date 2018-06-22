package com.github.sixro.jaboutdialog;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import org.apache.commons.io.IOUtils;

public class Launcher {

	public static void main(String... args) {
		//System.setProperty("swing.default.hgap", "12");		 
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				
				JFrame frame = new JFrame("My App");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JMenuBar menubar = new JMenuBar();
				JMenu mnuHelp = new JMenu("Help");
				mnuHelp.add(new JMenuItem(new AbstractAction("About") {
					@Override
					public void actionPerformed(ActionEvent e) {
						JAboutDialog dialog = new JAboutDialog(frame, "About...");
						dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						String html = readContent("/app-content.html");
						dialog.setApplicationContent(html);
						dialog.setSize(400, 300);
						dialog.setVisible(true);
					}
				}));
				menubar.add(mnuHelp);
				frame.setJMenuBar(menubar);
				frame.setSize(800, 600);
				frame.setVisible(true);				
			}
		});
	}

	public static String readContent(String resourcePath) {
		try (InputStream in = Launcher.class.getResourceAsStream(resourcePath)) {
			return IOUtils.toString(in, "UTF-8");			
		} catch (IOException e) {
			throw new RuntimeException("Unable to read content of '" + resourcePath + "'", e);
		}
	}

}
