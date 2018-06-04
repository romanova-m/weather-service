package ru.mirea.weather;

import org.junit.Test;
import ru.mirea.data.DataSource;

import static org.junit.Assert.*;

public class TaskGeneratorTest {

    @Test
    public void runTest() {

	DataSource dc = new DataSource();
        CustomQueue inQueue = new CustomQueue();
        TaskGenerator taskGenerator = new TaskGenerator(inQueue, dc);

        Thread generatorStream = new Thread(taskGenerator);
        generatorStream.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        generatorStream.interrupt();

        assertTrue(!inQueue.isEmpty());
        assertTrue(inQueue.pull() != null);
    }
}
