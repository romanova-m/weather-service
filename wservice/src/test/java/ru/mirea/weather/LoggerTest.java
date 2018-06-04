package ru.mirea.weather;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class LoggerTest {

    CustomQueue testQueue;
    int testSize;

    public LoggerTest() {
	testSize = 3;
	testQueue = new CustomQueue(testSize);
    }

    @Before
    public void runTest() {
        Task task = new Task(1,"Moscow");
        task.weather = "+15";
        testQueue.add(task);
        Thread lgStream = new Thread(new Logger(testQueue));

        lgStream.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        lgStream.interrupt();

    }

    @Test
    public void testQueueIsEmpty() {
	assertTrue(testQueue.isEmpty());
    }
}
