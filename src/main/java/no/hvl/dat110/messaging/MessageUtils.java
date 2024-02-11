package no.hvl.dat110.messaging;

import java.util.Arrays;

public class MessageUtils {

    public static final int SEGMENTSIZE = 128;

    public static int MESSAGINGPORT = 8080;
    public static String MESSAGINGHOST = "localhost";

    public static byte[] encapsulate(Message message) {
        // Create a new segment with the size of SEGMENTSIZE
        byte[] segment = new byte[SEGMENTSIZE];
        byte[] data = message.getData();

        // First byte of the segment is the length of the data
        segment[0] = (byte) data.length;

        // Copy data to the segment starting from position 1
        System.arraycopy(data, 0, segment, 1, data.length);

        return segment;
    }

    public static Message decapsulate(byte[] segment) {
        // First byte of the segment represents the size of the data
        int size = segment[0];

        // Extract the data from the segment
        byte[] data = new byte[size];
        System.arraycopy(segment, 1, data, 0, size);

        // Create a new message with the extracted data
        return new Message(data);
    }
}
