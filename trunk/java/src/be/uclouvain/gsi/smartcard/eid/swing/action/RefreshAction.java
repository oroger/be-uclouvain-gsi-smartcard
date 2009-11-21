package be.uclouvain.gsi.smartcard.eid.swing.action;

import java.awt.event.ActionEvent;

import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import be.uclouvain.gsi.smartcard.util.Terminal;

@SuppressWarnings({ "serial", "restriction" })
public class RefreshAction extends AbstractAction {
	private JMenu menu;
	private ButtonGroup group;
	
	public RefreshAction(JMenu menu, ButtonGroup group, String text){
		super(text);
		this.menu = menu;
		this.group = group;
	}
	
	public void actionPerformed(ActionEvent e) {
		menu.removeAll();
		
		try {
			JRadioButtonMenuItem term;
			for (CardTerminal ct : Terminal.getList()) {
				term = new JRadioButtonMenuItem();
				term.setAction(new TerminalAction(term, ct.getName().toString()));
				group.add(term);
				menu.add(term);
			}
		} catch (CardException ce) { // Catch Silently
			ce.printStackTrace();
		}
		
		menu.addSeparator();
		menu.add(new JMenuItem(new RefreshAction(menu, group, "Refresh")));
	}
}
