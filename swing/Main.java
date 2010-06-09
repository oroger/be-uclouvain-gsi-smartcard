package be.uclouvain.gsi.smartcard.eid.swing;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.SwingUtilities;

import be.uclouvain.gsi.smartcard.eid.swing.view.MainFrame;
import be.uclouvain.gsi.smartcard.util.Logging;

public class Main {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
			    Handler fh;
				try {
					fh = new FileHandler("java-eid-swing.log");
					fh.setFormatter(new SimpleFormatter());
					Logger.getLogger("").addHandler(fh);
					Logging.info("Launch application");
				} catch (IOException e) {
					e.printStackTrace();
				}

				MainFrame main = new MainFrame();
				main.setVisible(true);
			}
		});
	}	
}
