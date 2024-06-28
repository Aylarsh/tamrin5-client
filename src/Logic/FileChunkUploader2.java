package Logic;

import java.io.*;
import java.net.Socket;

import java.io.*;
import java.net.Socket;

import java.io.*;
import java.net.*;

public class FileChunkUploader2 extends TaskLogic {
    private long startByte;
    private long endByte;
    private String fileUrl;
    private String fileName;
    private boolean success;
    private int ID;

    private static final int PACKAGE_SIZE = 1000; // Adjust packet size as needed

    public FileChunkUploader2(long startByte, long endByte, String fileUrl, String fileName, int id) {
        super(null, fileUrl, fileName);
        this.startByte = startByte;
        this.endByte = endByte;
        this.fileUrl = fileUrl;
        this.fileName = fileName;
        this.success = false;
        this.ID = id;
    }

    @Override
    public void run() {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileUrl, "r");
             DatagramSocket socket = new DatagramSocket()) {

            InetAddress serverIP = InetAddress.getByName("127.0.0.1");
            int serverPort = 8000;

            byte[] buffer = new byte[PACKAGE_SIZE + 1];
            long currentByte = startByte;

            while (currentByte <= endByte) {
                int bytesToRead = (int) Math.min(PACKAGE_SIZE, endByte - currentByte + 1);

                randomAccessFile.seek(currentByte);
                randomAccessFile.readFully(buffer, 0, bytesToRead);

                // Place the ID at the last byte of the buffer
                buffer[PACKAGE_SIZE] = (byte) ID;

                // Send the packet
                DatagramPacket packet = new DatagramPacket(buffer, bytesToRead + 1, serverIP, serverPort);
                socket.send(packet);

                currentByte += bytesToRead;
            }

            System.out.println("File section sent successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isSuccess() {
        return success;
    }
}



