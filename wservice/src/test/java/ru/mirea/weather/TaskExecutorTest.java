package ru.mirea.weather;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TaskExecutorTest {

    /*CustomQueue inQueue;
    CustomQueue outQueue;
    int testSize;
    Task task;

    public TaskExecutorTest() {
        testSize = 3;
        task = new Task(1, "Moscow");
    }

    @Before
    public void set () {
        inQueue = new CustomQueue(testSize);
        outQueue = new CustomQueue(testSize);
    }

    @Test
    public void inQueueIsEmpty() {
	assertTrue(inQueue.isEmpty());
    }

    @Test
    public void outQueueContainsOneTask() {
        inQueue.add(task);
        Thread excStream = new Thread(new TaskExecutor(inQueue, outQueue));
        excStream.start();
        try {
            excStream.sleep(1000);
        } catch (InterruptedException e) {}
        excStream.interrupt();
        assertEquals(outQueue.size(), 1);
    }
    */
}
