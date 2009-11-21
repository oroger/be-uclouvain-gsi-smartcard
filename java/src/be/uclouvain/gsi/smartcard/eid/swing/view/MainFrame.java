package be.uclouvain.gsi.smartcard.eid.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import be.uclouvain.gsi.smartcard.eid.swing.action.AboutAction;
import be.uclouvain.gsi.smartcard.eid.swing.action.ExitAction;
import be.uclouvain.gsi.smartcard.eid.swing.action.ReadAction;
import be.uclouvain.gsi.smartcard.eid.swing.action.RefreshAction;

@SuppressWarnings({ "serial" })
public class MainFrame extends JFrame{
	
	JButton picture;
	JTextArea txt;
	
	public MainFrame(){
		super();
		build();
	}
	
	private void build(){
		setTitle("Smartcard.eid.swing"); 
		setSize(600,400); 
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
		setJMenuBar(buildMenu());
	}
	
	private JPanel buildContentPane(){
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.white);
		
		picture = new JButton();
		clearPicture();
		
		panel.add(picture,BorderLayout.WEST);
		
		txt = new JTextArea();
		txt.setEditable(false);
		panel.add(txt);
		
		return panel;
	}
	
	private JMenuBar buildMenu(){
		JMenuBar menu = new JMenuBar();
		
		// File
		JMenu file = new JMenu("File");
		JMenuItem exit = new JMenuItem(new ExitAction("Exit"));
		file.add(exit);
		menu.add(file);
		
		// Terminal
		JMenu terminal = new JMenu("Terminal");
		
		
		JMenu terminalList = new JMenu("List");
		ButtonGroup terminalGroup = new ButtonGroup();
		new RefreshAction(terminalList, terminalGroup, null).actionPerformed(null);

		terminal.add(new ReadAction(terminalGroup, this, "Read"));
		
		terminal.add(terminalList);
		menu.add(terminal);
			
		// Help
		JMenu help = new JMenu("?");
		JMenuItem about = new JMenuItem(new AboutAction(this, "About"));
		help.add(about);
		menu.add(help);
		
		return menu;
	}

	public void setImage(ImageIcon icon){
		picture.setIcon(icon);
	}	
	
	public void setImage(String path){
		ImageIcon icon = new ImageIcon(path);
		picture.setIcon(icon);
	}
	
	public void appendText(String data){
		txt.append(data);
	}

	public void clear() {
		clearPicture();
		clearText();		
	}
	
	private void clearPicture(){
		setImage("src\\be\\uclouvain\\gsi\\smartcard\\eid\\swing\\ressource\\empty.jpg");
	}
	
	private void clearText(){
		txt.setText("");
	}
}