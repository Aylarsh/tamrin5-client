package Logic;

import java.io.*;
import java.net.Socket;

import java.io.*;
import java.net.Socket;

import java.io.*;
import java.net.*;

public class FileChunkUploader extends TaskLogic {
    private long startByte;
    private long endByte;
    private String fileUrl;
    private String fileName;
    private boolean success;
    private int ID;

    private static final int PACKET_SIZE = 1000; // Adjust packet size as needed

    public FileChunkUploader(long startByte, long endByte, String fileUrl, String fileName, int id) {
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
        DatagramSocket socket = null;
        RandomAccessFile randomAccessFile = null;
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 8080;

            System.out.println(startByte+" "+endByte);
            // Send file upload request
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeLong(startByte);
            dos.writeLong(endByte);
            dos.writeUTF(fileName);
            byte[] requestData = baos.toByteArray();
            DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, serverAddress, serverPort);
            socket.send(requestPacket);

            randomAccessFile = new RandomAccessFile(fileUrl, "r");
            randomAccessFile.seek(startByte);

            byte[] buffer = new byte[PACKET_SIZE];
            long bytesToRead = endByte - startByte;

            while (bytesToRead > 0) {
                int bytesRead = randomAccessFile.read(buffer, 0, (int) Math.min(buffer.length, bytesToRead));
                if (bytesRead == -1) break;

                DatagramPacket dataPacket = new DatagramPacket(buffer, bytesRead, serverAddress, serverPort);
                socket.send(dataPacket);

                bytesToRead -= bytesRead;
            }

            this.success = true; // Assuming no exceptions mean success
            System.out.println("Chunk uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            this.success = false;
        } finally {
            if (socket != null) socket.close();
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isSuccess() {
        return success;
    }
}



