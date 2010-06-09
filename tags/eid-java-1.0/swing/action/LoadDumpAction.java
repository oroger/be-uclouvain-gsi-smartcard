package be.uclouvain.gsi.smartcard.eid.swing.action;

import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import be.uclouvain.gsi.smartcard.eid.swing.view.MainFrame;
import be.uclouvain.gsi.smartcard.util.Logging;

@SuppressWarnings("serial")
public class LoadDumpAction extends AbstractAction{
	final JFileChooser fc = new JFileChooser();
	MainFrame app;
	
	public LoadDumpAction(MainFrame app, String text){
		super(text);
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		DataInputStream dis;
        int length;
        byte[] data;
        String text;
        
        app.clear();
        
        int returnVal = fc.showOpenDialog(app);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            try{
				dis = new DataInputStream(new FileInputStream(file));
				try{
					text = dis.readUTF();
					text += dis.readUTF();
					length = dis.readInt();
					data = new byte[length];
					dis.read(data, 0, length);
					app.load(text, data);
					Logging.info(file.getPath() + " Loaded");
				} finally{
					dis.close();
				}
			} catch (IOException e) {
				Logging.severe(e);
			}
        }
	}
}
