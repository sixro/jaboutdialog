package com.github.sixro.jaboutdialog;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Represents a builder of a <i>button bar</i>.
 * 
 * <p>
 * Buttons are aligned on the right.<br/>
 * The gap between buttons is set to 5, but you can change setting the system property {@code swing.default.hgap}.<br/>
 * </p>
 */
public class ButtonBarBuilder {

	private static final int HGAP = Integer.getInteger("swing.default.hgap", 5);
	private static final int BUTTONBAR_TOP_GAP = Integer.getInteger("swing.default.buttonbar.topgap", 17);
	
	private final List<AbstractButton> buttons;
	
	private ButtonBarBuilder() { 
		this.buttons = new LinkedList<>();
	}
	
	public static ButtonBarBuilder aButtonBar() {
		return new ButtonBarBuilder();
	}
	
	public ButtonBarBuilder withButton(AbstractButton button) {
		buttons.add(button);
		return this;
	}
	
	public JPanel build() {
		if (buttons.isEmpty())
			throw new IllegalStateException("No buttons specified");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(BUTTONBAR_TOP_GAP, 0, 0, 0));
		panel.add(Box.createHorizontalGlue());
		panel.add(buttons.get(0));
		if (buttons.size() > 1) {
			for (int i = 1; i < buttons.size(); i++) {
				panel.add(Box.createRigidArea(new Dimension(HGAP, 0)));
				panel.add(buttons.get(i));
			}
		}
		
		int maxBtnWidth = maxButtonWidth();
		for (AbstractButton button: buttons)
			button.setPreferredSize(new Dimension(maxBtnWidth, button.getPreferredSize().height));

		return panel;
	}

	private int maxButtonWidth() {
		int maxWidth = 0;
		for (AbstractButton button: buttons) {
			int width = button.getMinimumSize().width;
			if (width > maxWidth)
				maxWidth = width;
		}
		return maxWidth;
	}
	
}
