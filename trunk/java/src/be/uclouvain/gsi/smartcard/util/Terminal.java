package be.uclouvain.gsi.smartcard.util;

import java.util.List;

import javax.smartcardio.Card;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;

import be.uclouvain.gsi.smartcard.exception.ReaderException;

@SuppressWarnings("restriction")
public class Terminal {
	
	public static List<CardTerminal> getList() throws CardException{
		TerminalFactory factory = TerminalFactory.getDefault();
		return (List<CardTerminal>) factory.terminals().list();
	}
	
	public static Card getCard(String name) throws ReaderException, CardException{
		for (CardTerminal terminal : getList()) {
			if (terminal.getName().equals(name)){
				return terminal.connect("T=0");
			}
		}
		throw new ReaderException("Smartcard Reader "+name+" could not be found.");
	}
	
	public static Card getCard(String name, String protocol) throws ReaderException, CardException{
		for (CardTerminal terminal : getList()) {
			if (terminal.getName().equals(name)){
				return terminal.connect(protocol);
			}
		}
		throw new ReaderException("Smartcard Reader "+name+" could not be found.");
	}	
	
}
