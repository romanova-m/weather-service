package ru.mirea.weather;

import org.junit.Test;
import ru.mirea.data.DataSource;

import static org.junit.Assert.*;

public class CustomQueueTest {
    DataSource dc = new DataSource();
    CustomQueue testQueue = new CustomQueue();
    Task testTask = new Task(0, dc);

    @Test
    public void push() {
        testQueue.push(testTask);
        assertEquals(testTask, testQueue.pull());
    }

    @Test
    public void pull() {
        assertEquals(null, testQueue.pull());
        testQueue.push(testTask);
        assertEquals(testTask, testQueue.pull());
    }

    @Test
    public void isEmpty() {
        assertTrue(testQueue.isEmpty());
        testQueue.push(testTask);
        assertTrue(!testQueue.isEmpty());
        testQueue.pull();
    }
}
