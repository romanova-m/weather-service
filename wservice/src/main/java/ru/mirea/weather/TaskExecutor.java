package ru.mirea.weather;

import java.lang.*;
import ru.mirea.data.DataSource;

public class TaskExecutor implements Runnable{

    private CustomQueue inQueue;
    private CustomQueue outQueue;

    public TaskExecutor(CustomQueue inQueue, CustomQueue outQueue){
        this.inQueue = inQueue;
        this.outQueue = outQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                execute();
                Thread.sleep(20);
            }
        } catch (InterruptedException e) {}
    }

    private void execute(){
        TaskWrapper wrapper = null;

        if (!inQueue.isEmpty()) {
            synchronized (inQueue) {
                if (!inQueue.isEmpty()) wrapper = inQueue.poll();
            }
        }

        if (wrapper != null) {
            wrapper.task.weather = DataSource.WEATHER.getByCity(wrapper.task.city);

            synchronized (outQueue) {
                outQueue.add(wrapper);
            }
        }
    }
}
