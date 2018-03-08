package org.tools;

import java.sql.Blob;
import java.io.BufferedInputStream;
import java.io.IOException;


public class Convert {
	
	public static byte[] blobToBytes(Blob blob) {  
		BufferedInputStream is = null;  
        try {  
            is = new BufferedInputStream(blob.getBinaryStream());  
            byte[] bytes = new byte[(int) blob.length()];  
            int len = bytes.length;  
            int offset = 0;  
            int read = 0;  
            while (offset < len  
                    && (read = is.read(bytes, offset, len - offset)) >= 0) {  
                offset += read;  
            }  
            return bytes;  
        } catch (Exception e) {  
            return null;  
        } finally {  
            try {  
                is.close();  
                is = null;  
            } catch (IOException e) {  
                return null;  
            }  
        }  
    }
}
