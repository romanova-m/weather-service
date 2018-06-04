package ru.mirea.weather;

import org.junit.Test;

import static org.junit.Assert.*;
import ru.mirea.data.DataSource;

public class LoggerTest {

    @Test
    public void runTest() {
	
	DataSource dc = new DataSource();
        CustomQueue outQueue = new CustomQueue();
        Task task = new Task(0, dc);
        task.execute(dc);
        outQueue.push(task);

        Logger logger = new Logger(outQueue);
        Thread loggerStream = new Thread(logger);

        loggerStream.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loggerStream.interrupt();

        assertTrue(outQueue.isEmpty());

    }
}
