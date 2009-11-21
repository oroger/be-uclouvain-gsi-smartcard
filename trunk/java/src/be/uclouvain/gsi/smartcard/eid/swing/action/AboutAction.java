package be.uclouvain.gsi.smartcard.eid.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class AboutAction extends AbstractAction {
	private JFrame frame;
	
	public AboutAction(JFrame frame, String text){
		super(text);
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(frame, "Application written by Olivier Roger (Olivier.Roger@gmail.com)\n please visite http://code.google.com/p/be-uclouvain-gsi-smartcard/ for more details.");
	}
}
