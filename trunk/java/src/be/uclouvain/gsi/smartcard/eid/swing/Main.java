package be.uclouvain.gsi.smartcard.eid.swing;

import javax.swing.SwingUtilities;

import be.uclouvain.gsi.smartcard.eid.swing.view.MainFrame;

public class Main {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				MainFrame main = new MainFrame();
				main.setVisible(true);
			}
		});
	}	
}
