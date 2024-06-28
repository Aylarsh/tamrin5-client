package Logic;

import Model.User;

public abstract class TaskLogic implements  Runnable {
    String username;
    String fileUrl;
    String fileName;

    public TaskLogic(String username, String fileUrl, String fileName) {
        this.username = username;
        this.fileUrl = fileUrl;
        this.fileName = fileName;
    }

}
