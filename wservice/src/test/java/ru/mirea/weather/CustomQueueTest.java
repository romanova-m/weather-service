package ru.mirea.weather;

import org.junit.Test;
import static org.junit.Assert.*;
import io.netty.channel.ChannelHandlerContext;

public class CustomQueueTest {

    CustomQueue testQueue;
    int testSize;
    TaskWrapper task;
    ChannelHandlerContext ctx;
    

    public CustomQueueTest() {
	testSize = 3;
	testQueue = new CustomQueue(testSize);
	task = new TaskWrapper(ctx, new Task(1, "Moscow"));
    }

    @Test
    public void testAdd() {
	testQueue.add(task);
	testQueue.add(task);
	testQueue.add(task);
	assertFalse(testQueue.add(task));
	assertEquals(testQueue.myQueue.size(), testSize);
    }

    @Test
    public void testPoll() {
        testQueue.myQueue.add(task);
        TaskWrapper polledTask = testQueue.poll();
        assert(testQueue.myQueue.isEmpty());
        assertEquals(polledTask, task);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(testQueue.isEmpty());
        testQueue.myQueue.add(task);
        assertFalse(testQueue.isEmpty());
        testQueue.myQueue.poll();
        assertTrue(testQueue.isEmpty());
    }

    @Test
    public void testSize() {
        assertTrue(testQueue.size() == 0);
        testQueue.myQueue.add(task);
        assertTrue(testQueue.size() == 1);
    }
}
