package be.uclouvain.gsi.smartcard.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Olivier Roger
 * @date 11-11-09
 */
public class TLVParser {
	
	public static Map<Byte,byte[]> parse(byte[] data){
		Map<Byte,byte[]> results = new HashMap<Byte,byte[]>();
		byte tag, tmp[];
		int length, offset = 0;
		while (offset < data.length -1){
			tag = data[offset];
			offset++;
			length = data[offset];
			offset++;
			tmp = new byte[length];
			System.arraycopy(data, offset, tmp, 0, length);
			results.put(tag, tmp);
			offset += length;
		}
		return results;
	}
}
