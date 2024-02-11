package no.hvl.dat110.system.display;

import no.hvl.dat110.rpc.RPCRemoteImpl;
import no.hvl.dat110.rpc.RPCUtils;
import no.hvl.dat110.rpc.RPCServer;

public class DisplayImpl extends RPCRemoteImpl {

    public DisplayImpl(byte rpcid, RPCServer rpcserver) {
        super(rpcid, rpcserver);
    }

    public void write(String message) {
        System.out.println("DISPLAY:" + message);
    }
    
    public byte[] invoke(byte[] param) {
        
        // Unmarshalling: convert byte[] param to a String
        String message = RPCUtils.unmarshallString(param);
        
        // Call the write method with the unmarshalled String
        write(message);
        
        // Marshalling: for a void method, you typically return an empty byte array
        // Alternatively, you could return some form of acknowledgment if necessary
        byte[] returnval = RPCUtils.marshallVoid();
        
        return returnval;
    }
}
