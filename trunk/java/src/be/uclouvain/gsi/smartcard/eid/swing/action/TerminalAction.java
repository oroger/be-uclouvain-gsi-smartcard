package be.uclouvain.gsi.smartcard.eid.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JRadioButtonMenuItem;

@SuppressWarnings("serial")
public class TerminalAction extends AbstractAction {
	private JRadioButtonMenuItem button;
	
	public TerminalAction(JRadioButtonMenuItem button, String text){
		super(text);
		this.button = button;
	}
	
	public void actionPerformed(ActionEvent e) {
		button.setSelected(true);
		button.setActionCommand(button.getText());
	}
}
