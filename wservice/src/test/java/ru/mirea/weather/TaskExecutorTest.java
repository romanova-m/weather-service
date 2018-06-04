package ru.mirea.weather;

import org.junit.Test;

import static org.junit.Assert.*;
import ru.mirea.data.DataSource;

public class TaskExecutorTest {

    private int maxSize = 100;
    DataSource dc = new DataSource();
    private Task task = new Task(0, dc);

    @Test
    public void runTest() {

        CustomQueue inQueue = new CustomQueue();
        inQueue.push(task);

        CustomQueue outQueue = new CustomQueue();
        TaskExecutor taskExecutor = new TaskExecutor(inQueue,outQueue, dc);
        Thread executorStream = new Thread(taskExecutor);

        executorStream.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorStream.interrupt();

        assertTrue(inQueue.isEmpty());
        outQueue.pull();
        assertTrue(outQueue.isEmpty());
    }
}
