package no.hvl.dat110.rpc;

//import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

public class RPCClient {

    // underlying messaging client used for RPC communication
    private MessagingClient msgclient;

    // underlying messaging connection used for RPC communication
    private MessageConnection connection;
    
    public RPCClient(String server, int port) {
    
        msgclient = new MessagingClient(server,port);
    }
    
    public void connect() {
        
        // Connect using the RPC client
        connection = msgclient.connect(); // Assuming connect() method returns a MessageConnection object
    }
    
    public void disconnect() {
        
        // Disconnect by closing the underlying messaging connection
        if (connection != null) {
            connection.close();
        }
    }

    /*
     Make a remote call on the method on the RPC server by sending an RPC request message and receive an RPC reply message

     rpcid is the identifier on the server side of the method to be called
     param is the marshalled parameter of the method to be called
    */
    public byte[] call(byte rpcid, byte[] param) {
        
        byte[] returnval = null;

        // Encapsulate the rpcid and param according to the RPC message format
        byte[] rpcmsg = RPCUtils.encapsulate(rpcid, param);

        // Send the RPC message
        Message request = new Message(rpcmsg);
        connection.send(request);

        // Receive the RPC reply
        Message reply = connection.receive();

        // Decapsulate the return value from the RPC call
        returnval = RPCUtils.decapsulate(reply.getData());
        
        return returnval;
    }
}
