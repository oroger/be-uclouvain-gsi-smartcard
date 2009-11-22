package be.uclouvain.gsi.smartcard.eid.swing.action;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import be.uclouvain.gsi.smartcard.eid.model.EID;
import be.uclouvain.gsi.smartcard.eid.swing.view.MainFrame;
import be.uclouvain.gsi.smartcard.util.IO;

@SuppressWarnings("serial")
public class SavePictureAction extends AbstractAction{
	final JFileChooser fc = new JFileChooser();
	MainFrame app;
	
	public SavePictureAction(MainFrame app, String text){
		super(text);
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
        EID eid = app.getEid();
        
        if (eid == null) {
        	JOptionPane.showMessageDialog(app, "Nothing to save.");
        	return;
        }
        
        int returnVal = fc.showSaveDialog(app);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            if (file.exists()){
            	int confirm = JOptionPane.showConfirmDialog(app, "File already exist. Overwrite?");
            	if (confirm != JOptionPane.YES_OPTION) return;
            }
			try {
				IO.save(file.toString(), eid.getPicture());
			} catch (IOException e) {
				JOptionPane.showConfirmDialog(app, "Could not save image.");
				e.printStackTrace();
			}
        }
	}
}
