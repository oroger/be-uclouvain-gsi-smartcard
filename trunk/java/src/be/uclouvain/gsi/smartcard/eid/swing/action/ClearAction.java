package be.uclouvain.gsi.smartcard.eid.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import be.uclouvain.gsi.smartcard.eid.swing.view.MainFrame;

@SuppressWarnings("serial")
public class ClearAction extends AbstractAction {
	private MainFrame app;
	
	public ClearAction(MainFrame app, String text){
		super(text);
		this.app = app;
	}

	public void actionPerformed(ActionEvent arg0) {
		app.clear();
	}
}
