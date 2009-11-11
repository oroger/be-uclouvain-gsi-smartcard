package be.uclouvain.gsi.smartcard.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IO {
	
    public static void save(String path, byte[] data) throws IOException{
    	FileOutputStream fos = new FileOutputStream(path);
    	fos.write(data);
    	fos.flush();
    	fos.close();
    }
}
