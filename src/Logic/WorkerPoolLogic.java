package Logic;

import java.util.LinkedList;
import java.util.Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WorkerPoolLogic {
    private final Queue<TaskLogic> taskLogicsQueue;
    private final WorkerLogic[] workersLogic;
    private final List<TaskLogic> completedTasks;

    public WorkerPoolLogic(int numWorkers) {
        taskLogicsQueue = new LinkedList<>();
        workersLogic = new WorkerLogic[numWorkers];
        completedTasks = new ArrayList<>();

        for (int i = 0; i < numWorkers; i++) {
            workersLogic[i] = new WorkerLogic(taskLogicsQueue);
            workersLogic[i].start();
        }
    }

    public void addTask(TaskLogic taskLogic) {
        synchronized (taskLogicsQueue) {
            taskLogicsQueue.add(taskLogic);
            taskLogicsQueue.notify();
        }
    }

    public List<TaskLogic> getCompletedTasks() {
        synchronized (completedTasks) {
            return new ArrayList<>(completedTasks);
        }
    }

    public void waitForCompletion() throws InterruptedException {
        synchronized (completedTasks) {
            while (completedTasks.size() < workersLogic.length) {
                completedTasks.wait();
            }
        }
    }

    public void taskCompleted(TaskLogic taskLogic) {
        synchronized (completedTasks) {
            completedTasks.add(taskLogic);
            completedTasks.notifyAll();
        }
    }

    public void shutdown() {
        for (WorkerLogic workerLogic : workersLogic) {
            workerLogic.shutdown();
        }
    }
}

