package ru.mirea.weather;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TaskGeneratorTest {

    private CustomQueue testQueue;
    private  int testSize;

    public TaskGeneratorTest() {
	testSize = 3;
	testQueue = new CustomQueue(testSize);
    }

    @Before
    public void setUp() {
	Thread genStream = new Thread(new TaskGenerator(testQueue));
	genStream.start();
	try {
	    Thread.sleep(100);
	} catch (InterruptedException e) {}
	genStream.interrupt();
    }
	    

    @Test
    public void queueIsNotEmpty() {
	assertFalse(testQueue.isEmpty());
    }

    @Test
    public void queueSizeIsCorrect() {
	assertTrue(testQueue.size() <= testSize);
    }
}
