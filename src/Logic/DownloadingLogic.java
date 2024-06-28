package Logic;

import Model.User;

import java.io.*;
import java.net.Socket;


public class DownloadingLogic extends TaskLogic{


    public DownloadingLogic(String username, String fileUrl, String fileName) {
        super(username, fileUrl, fileName);
    }

    @Override
    public void run() {
     }
}
