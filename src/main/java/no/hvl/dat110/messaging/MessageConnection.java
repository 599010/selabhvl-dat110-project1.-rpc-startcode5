package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//import no.hvl.dat110.TODO;


public class MessageConnection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection
	
	public MessageConnection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream (socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {
	    try {
	        // Encapsulate the data contained in the Message
	        byte[] data = MessageUtils.encapsulate(message);

	        // Write to the output stream
	        outStream.write(data);

	    } catch (IOException e) {
	        System.out.println("MessageConnection send error: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


	public Message receive() {
	    Message message = null; // Declare the message variable

	    try {
	        // Read a segment from the input stream
	        byte[] segment = new byte[MessageUtils.SEGMENTSIZE];
	        inStream.readFully(segment); // This method will block until the specified number of bytes are read

	        // Decapsulate data into a Message
	        message = MessageUtils.decapsulate(segment);

	    } catch (IOException e) {
	        System.out.println("MessageConnection receive error: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return message;
	}

	// close the connection by closing streams and the underlying socket	
	public void close() {

		try {
			
			outStream.close();
			inStream.close();

			socket.close();
			
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}