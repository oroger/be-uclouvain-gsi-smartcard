package be.uclouvain.gsi.smartcard.eid.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import be.uclouvain.gsi.smartcard.eid.model.EID;
import be.uclouvain.gsi.smartcard.eid.swing.action.AboutAction;
import be.uclouvain.gsi.smartcard.eid.swing.action.ClearAction;
import be.uclouvain.gsi.smartcard.eid.swing.action.ExitAction;
import be.uclouvain.gsi.smartcard.eid.swing.action.ExportPDFAction;
import be.uclouvain.gsi.smartcard.eid.swing.action.LoadDumpAction;
import be.uclouvain.gsi.smartcard.eid.swing.action.ReadAction;
import be.uclouvain.gsi.smartcard.eid.swing.action.RefreshAction;
import be.uclouvain.gsi.smartcard.eid.swing.action.SaveDumpAction;
import be.uclouvain.gsi.smartcard.eid.swing.action.SavePictureAction;
import be.uclouvain.gsi.smartcard.eid.swing.action.SaveTextAction;

@SuppressWarnings({ "serial" })
public class MainFrame extends JFrame{
	
	JLabel picture;
	JTextArea txt;
	EID eid;
	
	public MainFrame(){
		super();
		build();
	}
	
	private void build(){
		setTitle("Smartcard.eid.swing"); 
		setMinimumSize(new Dimension(500,360));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(buildContentPane());
		setJMenuBar(buildMenu());
	}
	
	private JPanel buildContentPane(){
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.white);
		
		picture = new JLabel();
		panel.add(picture,BorderLayout.WEST);
		
		txt = new JTextArea();
		txt.setEditable(false);
		panel.add(txt);
		
		clear();
		return panel;
	}
	
	private JMenuBar buildMenu(){
		JMenuBar menu = new JMenuBar();
		
		// File
		JMenu file = new JMenu("File");
		JMenuItem clear = new JMenuItem(new ClearAction(this, "Clear"));
		file.add(clear);

		JMenu open = new JMenu("Open");
		JMenuItem LoadDump = new JMenuItem(new LoadDumpAction(this,"Dump"));
		open.add(LoadDump);
		file.add(open);
		
		JMenu save = new JMenu("Save");
		JMenuItem SaveDump = new JMenuItem(new SaveDumpAction(this,"Dump"));
		save.add(SaveDump);
		JMenuItem SaveText = new JMenuItem(new SaveTextAction(this,"Data"));
		save.add(SaveText);
		JMenuItem SavePicture = new JMenuItem(new SavePictureAction(this,"Picture"));
		save.add(SavePicture);
		file.add(save);
		
		JMenu export = new JMenu("Export");
		JMenuItem PDF = new JMenuItem(new ExportPDFAction(this,"PDF"));
		export.add(PDF);
		file.add(export);	

		JMenuItem exit = new JMenuItem(new ExitAction("Exit"));
		file.add(exit);
		menu.add(file);
		
		// Terminal
		JMenu terminal = new JMenu("Terminal");
		JMenu list = new JMenu("List");
		ButtonGroup group = new ButtonGroup();
		new RefreshAction(list, group, null).actionPerformed(null);
		terminal.add(new ReadAction(group, this, "Read"));
		terminal.add(list);
		menu.add(terminal);
			
		// Help
		JMenu help = new JMenu("?");
		JMenuItem about = new JMenuItem(new AboutAction(this, "About"));
		help.add(about);
		menu.add(help);
		
		return menu;
	}
	
	public EID getEid() {
		return eid;
	}

	public void setEid(EID eid) {
		this.eid = eid;
		if (eid != null){
			txt.setText(eid.getData().toString());
			txt.append("\n");
			txt.append(eid.getAddress().toString());
			setImage(new ImageIcon(eid.getPicture()));
		} else{
			clearText();
			clearPicture();
		}
	}

	// Temporary, only to load dump
	public void load(String data, byte[] picture){
		clearText();
		addText(data);
		setImage(new ImageIcon(picture));
	}
	
	// Temporary, only to load dump
	private void addText(String data){
		txt.append(data);
	}

	// Temporary, only to load dump
	private void setImage(ImageIcon icon){
		picture.setIcon(icon);
	}	
	
	public void clear() {
		setEid(null);
	}
	
	private void clearPicture(){
		setImage(new ImageIcon(this.getClass().getClassLoader().getResource(
				"be/uclouvain/gsi/smartcard/eid/swing/res/empty.jpg")));
	}
	
	private void clearText(){
		txt.setText("");
	}
}