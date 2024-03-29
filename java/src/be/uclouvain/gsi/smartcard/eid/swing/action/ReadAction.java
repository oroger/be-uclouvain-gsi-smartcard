package be.uclouvain.gsi.smartcard.eid.swing.action;

import java.awt.event.ActionEvent;

import javax.smartcardio.CardException;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

import be.uclouvain.gsi.smartcard.eid.model.EID;
import be.uclouvain.gsi.smartcard.eid.swing.view.MainFrame;
import be.uclouvain.gsi.smartcard.exception.ReaderException;
import be.uclouvain.gsi.smartcard.util.Logging;
import be.uclouvain.gsi.smartcard.util.Terminal;

@SuppressWarnings({ "serial", "restriction" })
public class ReadAction extends AbstractAction {
	private ButtonGroup group;
	private MainFrame app;
	
	public ReadAction(ButtonGroup group, MainFrame app, String text){
		super(text);
		this.group = group;
		this.app = app;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (group.getSelection() == null) {
			JOptionPane.showMessageDialog(null, 
					"Please Select a reader first.", 
					"No reader selected",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		String selected = group.getSelection().getActionCommand();
		try {
			EID eid = new EID(Terminal.getCard(selected));
			app.setEid(eid);
			Logging.info("eID read: " + eid.getFullName());
		} catch (ReaderException re) {
			Logging.severe(re);
		} catch (CardException ce) {
			Logging.severe(ce);
		}
	}
}