package ru.mirea.weather;

import java.lang.*;
import ru.mirea.data.DataSource;

public class TaskExecutor implements Runnable {

    CustomQueue inQueue_;
    CustomQueue outQueue_;
    Task currentTask;
    DataSource dc;


    public TaskExecutor(CustomQueue inQueue, CustomQueue outQueue, DataSource dc){
        inQueue_ = inQueue;
        outQueue_ = outQueue;
	this.dc = dc;
    }

    public synchronized void run(){
        while (true) {
            if (!inQueue_.isEmpty()) {
                synchronized (inQueue_){
                    if (!inQueue_.isEmpty())
                    currentTask = inQueue_.pull();
                }
                currentTask.execute(dc);
                if (!outQueue_.isFull())
                    synchronized (outQueue_) {
                        if (!outQueue_.isFull())
                        outQueue_.push(currentTask);
                    }
            } else try {
                wait(10);
            } catch (InterruptedException e) {
                System.out.println("THREAD WAS INTERRUPTED");
                return;
            }
        }
    }
}
