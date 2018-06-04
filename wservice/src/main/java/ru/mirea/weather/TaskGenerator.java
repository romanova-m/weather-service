package ru.mirea.weather;

import ru.mirea.data.DataSource;

public class TaskGenerator implements Runnable {

    DataSource dc;
    CustomQueue queue;
    public static int counter;

    public TaskGenerator(CustomQueue queueArg, DataSource dc){
        counter = 0;
	this.dc = dc;
        this.queue = queueArg;
    }

    public synchronized void run() {
        while(true) {
            if (!queue.isFull()) {
                synchronized (queue) {
                    if (!queue.isFull()) 
                    queue.push(new Task(++counter, dc));
                }
            }
            try {
                wait(5);
            }
            catch(InterruptedException e){
                    System.out.println("THREAD GENERATOR WAS INTERRUPTED");
                    return;
            }
        }
    }
}

