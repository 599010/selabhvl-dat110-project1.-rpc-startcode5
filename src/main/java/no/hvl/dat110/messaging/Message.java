package no.hvl.dat110.messaging;

public class Message {

    // the up to 127 bytes of data (payload) that a message can hold
    private byte[] data;

    // constructing a Message with the data provided
    public Message(byte[] data) {
        // Check if data is not null and its length is within the allowed range
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        if (data.length > 127) {
            throw new IllegalArgumentException("Data length cannot exceed 127 bytes");
        }

        this.data = data;
    }

    public byte[] getData() {
        return this.data; 
    }
}
