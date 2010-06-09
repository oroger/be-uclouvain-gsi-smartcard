package be.uclouvain.gsi.smartcard.eid.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class ExitAction extends AbstractAction {
	public ExitAction(String text){
		super(text);
	}
	
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
