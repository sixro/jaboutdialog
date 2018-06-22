package com.github.sixro.jaboutdialog;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;

/**
 * Represents an <i>about</i> dialog used to describe your application, version, credits, system info.
 * 
 * <p>
 * It contains two tab panes. The first one is called &quot;Application&quot; and contains a description of your application.<br/>
 * In order to set the text use {@link #setApplicationContent(String)}. It accepts an HTML text that you
 * can use to describe every aspect of your application.<br/>
 * Take a look at the {@code test} folder of the library containing an example on how to use it.
 * 
 * The second tab contains some system info. E.g. it contains the list of all system properties
 * available at the moment in the application.
 * </p>
 */
public class JAboutDialog extends JDialog {

	private static final int BORDER_WIDTH = Integer.getInteger("swing.default.border.width", 12);
	
	private static final long serialVersionUID = 1L;

	private JEditorPane applicationContentPane;

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

	public void setApplicationContent(String html) {
		applicationContentPane.setText(html);
	}
	
	private void createUI() {
		setLayout(new BorderLayout());
		((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));

		// Application content
		JTabbedPane tabbedPane = new JTabbedPane();
		applicationContentPane = new JEditorPane();
		applicationContentPane.setEditable(false);
		HTMLEditorKit editorKit = new HTMLEditorKit();
		applicationContentPane.setEditorKit(editorKit);
		Document document = editorKit.createDefaultDocument();
		applicationContentPane.setDocument(document);
		tabbedPane.add("Application", new JScrollPane(applicationContentPane));

		// System info content
		DefaultTableModel tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableModel.addColumn("Property");
		tableModel.addColumn("Value");
		Properties props = System.getProperties();
		List<String> propertyNames = new ArrayList<>(props.stringPropertyNames());
		Collections.sort(propertyNames);
		for (String propName: propertyNames) {
			System.out.println(propName);
			tableModel.addRow(new Object[] { propName, System.getProperty(propName) });
		}
		JTable systemPropsPane = new JTable(tableModel);
		TableColumn propNameCol = systemPropsPane.getColumnModel().getColumn(0);
		propNameCol.setMaxWidth(300);
		propNameCol.setPreferredWidth(200);
		tabbedPane.add("System", new JScrollPane(systemPropsPane));
		
		add(tabbedPane, BorderLayout.CENTER);
		
		// Button Bar
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(e -> { JAboutDialog.this.dispose(); });
		
		JPanel buttonBar = ButtonBarBuilder.aButtonBar()
			.withButton(btnClose)
			.build();
		add(buttonBar, BorderLayout.SOUTH);
		
		getRootPane().setDefaultButton(btnClose);
	}
	
}
