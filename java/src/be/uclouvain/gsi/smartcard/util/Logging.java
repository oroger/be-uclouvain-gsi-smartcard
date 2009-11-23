package be.uclouvain.gsi.smartcard.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

// Wrapper around Java logger implementation in case it changes to a better one
public class Logging {
	
	private static String loggerName = "trace";
	
	public static void severe(String msg){
		log(loggerName, Level.SEVERE, msg);
	}

	public static void severe(Exception e){
		log(loggerName, Level.SEVERE, e.getMessage());
		JOptionPane.showMessageDialog(null, 
				e.getMessage(),
				e.getClass().getSimpleName(), 
				JOptionPane.ERROR_MESSAGE);
	}
	
	public static void warning(String msg){
		log(loggerName, Level.WARNING, msg);
	}
	
	public static void warning(Exception e){
		log(loggerName, Level.WARNING, e.getMessage());
		JOptionPane.showMessageDialog(null, 
				e.getMessage(),
				e.getClass().getSimpleName(), 
				JOptionPane.WARNING_MESSAGE);
	}	
	
	public static void info(String msg){
		log(loggerName, Level.INFO, msg);
	}
	
	public static void config(String msg){
		log(loggerName, Level.CONFIG, msg);
	}
	
//	public static void fine(String msg){
//		log(loggerName, Level.FINE, msg);
//	}
//	
//	public static void finer(String msg){
//		log(loggerName, Level.FINER, msg);
//	}
//
//	public static void finest(String msg){
//		log(loggerName, Level.FINEST, msg);
//	}
	
	private static void log(String logger, Level level, String msg){
		Logger log = Logger.getLogger(logger);
		log.log(level, msg);
	}
}
