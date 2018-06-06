package ru.mirea.weather;

import io.netty.channel.ChannelHandlerContext;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TaskExecutorTest {

    CustomQueue inQueue;
    CustomQueue outQueue;
    int testSize;
    TaskWrapper task;
    ChannelHandlerContext ctx;

    public TaskExecutorTest() {
        testSize = 3;
        task = new TaskWrapper(new Task(1, "Moscow"), ctx);
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
}
