package ru.mirea.weather;

import org.junit.Test;
import static org.junit.Assert.*;

public class CustomQueueTest {

   /* CustomQueue testQueue;
    int testSize;
    Task wrapper;

    public CustomQueueTest() {
	testSize = 3;
	testQueue = new CustomQueue(testSize);
	task = new Task(1, "Moscow");
	wrapper = new TaskWrapper ();
    }

    @Test
    public void testAdd() {
	testQueue.add(wrapper);
	testQueue.add(wrapper);
	testQueue.add(wrapper);
	assertFalse(testQueue.add(wrapper));
	assertEquals(testQueue.myQueue.size(), testSize);
    }

    @Test
    public void testPoll() {
        testQueue.myQueue.add(wrapper);
        Task polledTask = testQueue.poll();
        assert(testQueue.myQueue.isEmpty());
        assertEquals(polledTask, wrapper);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(testQueue.isEmpty());
        testQueue.myQueue.add(wrapper);
        assertFalse(testQueue.isEmpty());
        testQueue.myQueue.poll();
        assertTrue(testQueue.isEmpty());
    }

    @Test
    public void testSize() {
        assertTrue(testQueue.size() == 0);
        testQueue.myQueue.add(wrapper);
        assertTrue(testQueue.size() == 1);
    }*/
}
