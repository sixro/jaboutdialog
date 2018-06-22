package com.github.sixro.jaboutdialog;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class JAboutDialog extends JDialog {

	private static final int BORDER_WIDTH = Integer.getInteger("swing.default.border.width", 12);
	
	private static final long serialVersionUID = 1L;

	public JAboutDialog(Window owner, ModalityType modalityType) {
		super(owner, modalityType);
		createUI();
	}

	public JAboutDialog(Window owner, String title, ModalityType modalityType, GraphicsConfiguration gc) {
		super(owner, title, modalityType, gc);
		createUI();
	}

	public JAboutDialog(Window owner, String title, ModalityType modalityType) {
		super(owner, title, modalityType);
		createUI();
	}

	public JAboutDialog(Window owner, String title) {
		super(owner, title);
		createUI();
	}

	private void createUI() {
		setLayout(new BorderLayout());
		((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Application", new JPanel());
		tabbedPane.add("System", new JPanel());
		add(tabbedPane, BorderLayout.CENTER);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(e -> { JAboutDialog.this.dispose(); });
		
		JPanel buttonBar = ButtonBarBuilder.aButtonBar()
			.withButton(btnClose)
			.build();
		add(buttonBar, BorderLayout.SOUTH);
		
		getRootPane().setDefaultButton(btnClose);
	}
	
}
