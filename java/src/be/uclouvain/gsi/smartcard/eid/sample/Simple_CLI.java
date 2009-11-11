package be.uclouvain.gsi.smartcard.eid.sample;

import java.io.IOException;
import java.util.List;

import javax.smartcardio.Card;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;

import be.uclouvain.gsi.smartcard.eid.model.EID;

@SuppressWarnings("restriction")
public class Simple_CLI {
	
	/** Read the content of the Belgian eID (electronic Identity Card). 
	 * @param args
	 * @throws CardException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws CardException, IOException {
        // show the list of available terminals
        TerminalFactory factory = TerminalFactory.getDefault();
        List<CardTerminal> terminals = factory.terminals().list();
        
        // get the first terminal
        CardTerminal terminal = terminals.get(0);
        
        // establish a connection with the card
        Card card = terminal.connect("T=0");
        
        EID e = new EID(card);
        System.out.println(e.toString());
        e.savePicture("face.jpg");
        
		// disconnect
		card.disconnect(false);
	}
	
}
