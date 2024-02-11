package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;

public class DisplayStub extends RPCLocalStub {

	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public void write(String message) {
		Byte rpcid = null;
		// marshal the string into a byte array
		byte[] messageEncoded = RPCUtils.marshallString(message);
		
		// encapsulate the message and rpcid in a byte array
		byte[] request = RPCUtils.encapsulate((byte)0, messageEncoded);
		
		// make the RPC call
		byte[] response = rpcclient.call((byte)Common.READ_RPCID,request);
		
		// unmarshall the response (void method has no return value)
		RPCUtils.unmarshallVoid(response);
		
	}
}
