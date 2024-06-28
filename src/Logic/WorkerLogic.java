package Logic;

import java.util.Queue;

public class WorkerLogic extends Thread {
    private final Queue<TaskLogic> taskLogicsQueue;
    private boolean isRunning = true;

    public WorkerLogic(Queue<TaskLogic> taskLogicsQueue) {
        this.taskLogicsQueue = taskLogicsQueue;
    }

    @Override
    public void run() {
        while (isRunning) {
            TaskLogic taskLogic;
            synchronized (taskLogicsQueue) {
                while (taskLogicsQueue.isEmpty()) {
                    try {
                        taskLogicsQueue.wait();
                    } catch (InterruptedException e) {
                        if (!isRunning) return;
                    }
                }
                taskLogic = taskLogicsQueue.poll();
            }
            if (taskLogic != null) {
                taskLogic.run();
            }
        }
    }

    public void shutdown() {
        isRunning = false;
        this.interrupt();
    }
}

