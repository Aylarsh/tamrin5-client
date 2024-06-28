package Logic;

import Model.User;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class UploadingLogic extends TaskLogic {
    private WorkerPoolLogic workerPoolLogic;
    private static final int CHUNK_COUNT = 10;

    public UploadingLogic(String username, String fileUrl, String fileName) {
        super(username, fileUrl, fileName);
        this.workerPoolLogic = new WorkerPoolLogic(CHUNK_COUNT);
        run();
    }

    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", 8080);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            out.println("UPLOAD");
            File file = new File(fileUrl);
            long fileSize = file.length();
            System.out.println(fileSize);
            out.println(fileSize);
            out.println(fileName);

//            DatagramSocket udp_socket = new DatagramSocket();
//
//            InetAddress serverIP = InetAddress.getByName("127.0.0.1");

//            System.out.println("SHIT");
//            while (true){
//                System.out.println("Im sending you bastard");
//                byte[] buffer = new byte[1];
//                buffer[0] = 8;
//                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverIP, 8000);
//                udp_socket.send(packet);
//            }





           uploadFile();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void uploadFile() {
        File file = new File(fileUrl);
        long fileSize = file.length();
        long chunkSize = fileSize / CHUNK_COUNT;
        boolean overallSuccess = true;

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            for (int i = 0; i < CHUNK_COUNT; i++) {
                long startByte = i * chunkSize;
                long endByte = (i == CHUNK_COUNT - 1) ? fileSize : (i + 1) * chunkSize;
                FileChunkUploader2 uploader = new FileChunkUploader2(startByte, endByte, fileUrl, fileName, i);
                workerPoolLogic.addTask(uploader);
            }

            // Wait for all tasks to complete
            workerPoolLogic.waitForCompletion();

            // Check individual task success
            for (TaskLogic task : workerPoolLogic.getCompletedTasks()) {
                FileChunkUploader2 uploader = (FileChunkUploader2) task;
                if (!uploader.isSuccess()) {
                    overallSuccess = false;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            overallSuccess = false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // Shutdown the worker pool
            workerPoolLogic.shutdown();

            // Check overall success
            if (overallSuccess) {
                System.out.println("File uploaded successfully");
            } else {
                System.err.println("File upload failed");
            }
        }
    }
}



