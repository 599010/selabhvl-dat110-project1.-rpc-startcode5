package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;
//import no.hvl.dat110.TODO;

public class RPCUtils {
    
    public static byte[] encapsulate(byte rpcid, byte[] payload) {
        
        byte[] rpcmsg = new byte[payload.length + 1];
        rpcmsg[0] = rpcid; // First byte for rpcid
        System.arraycopy(payload, 0, rpcmsg, 1, payload.length); // Copying payload into rpcmsg
        
        return rpcmsg;
    }
    
    public static byte[] decapsulate(byte[] rpcmsg) {
        
        byte[] payload = Arrays.copyOfRange(rpcmsg, 1, rpcmsg.length); // Extracting payload from rpcmsg
        
        return payload;
    }

    // convert String to byte array
    public static byte[] marshallString(String str) {
        
        byte[] encoded = str.getBytes(); // Convert String to byte array
        
        return encoded;
    }

    // convert byte array to a String
    public static String unmarshallString(byte[] data) {
        
        String decoded = new String(data); // Convert byte array to String
        
        return decoded;
    }
    
    public static byte[] marshallVoid() {
        
        byte[] encoded = new byte[0]; // Void has no data, so return an empty array
        
        return encoded;
    }
    
    public static void unmarshallVoid(byte[] data) {
        
        // Nothing to do here for void return type
    }

    // convert boolean to a byte array representation
    public static byte[] marshallBoolean(boolean b) {
        
        byte[] encoded = new byte[1];
                
        if (b) {
            encoded[0] = 1; // True is represented as byte 1
        } else {
            encoded[0] = 0; // False is represented as byte 0
        }
        
        return encoded;
    }

    // convert byte array to a boolean representation
    public static boolean unmarshallBoolean(byte[] data) {
        
        return (data[0] > 0); // If data[0] is greater than 0, it's true; otherwise false
        
    }

    // integer to byte array representation
    public static byte[] marshallInteger(int x) {
        
        byte[] encoded = ByteBuffer.allocate(4).putInt(x).array(); // Convert int to 4-byte array
        
        return encoded;
    }
    
    // byte array representation to integer
    public static int unmarshallInteger(byte[] data) {
        
        int decoded = ByteBuffer.wrap(data).getInt(); // Convert 4-byte array to int
        
        return decoded;
    }
}
