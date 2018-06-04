package ru.mirea.weather;

import java.lang.*;
import ru.mirea.data.DataSource;

public class TaskExecutor implements Runnable{

    CustomQueue inQueue;
    CustomQueue outQueue;

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
        Task task = inQueue.poll();

        if (task != null) {
            task.weather = DataSource.Weather.getByCity(task.city);
            outQueue.add(task);
        }
    }
}
