package be.uclouvain.gsi.smartcard.eid.swing.action;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import be.uclouvain.gsi.smartcard.util.Logging;
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
		String selected = null; 
		
		// Keep name of the selected item to set 
		// it again if the reader is still connected
		if (group.getSelection() != null){
			selected = group.getSelection().getActionCommand();
		}
		
		// Remove all old menu and group items
		group.clearSelection();
		for (Component item : menu.getMenuComponents()) {
			if (item instanceof JRadioButtonMenuItem){
				group.remove(((JRadioButtonMenuItem) item));
			}
		}
		menu.removeAll();
		
		// Add all plugged readers
		try {
			JRadioButtonMenuItem term;
			for (CardTerminal ct : Terminal.getList()) {
				term = new JRadioButtonMenuItem();
				term.setAction(new TerminalAction(term, ct.getName().toString()));
				group.add(term);
				if (selected != null && ct.getName().toString().equals(selected)){
					term.setActionCommand(ct.getName().toString());
					group.setSelected(term.getModel(), true);
				}
				menu.add(term);
			}
		} catch (CardException ce) {
			Logging.warning(ce.getMessage());
		}
		
		menu.addSeparator();
		menu.add(new JMenuItem(new RefreshAction(menu, group, "Refresh")));
	}
}
