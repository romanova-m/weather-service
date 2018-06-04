package ru.mirea.weather;

import ru.mirea.data.DataSource;


public class TaskGenerator implements Runnable {
    private CustomQueue inQueue;
    private int id = 0;

    public TaskGenerator(CustomQueue inQueue) {
        this.inQueue = inQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (String city : DataSource.Weather.cities()) {
                    putTaskInQueue(city);
                    Thread.sleep(10);
                }
            }
        } catch (InterruptedException e) {}
    }

    private void putTaskInQueue(String city) {
        Task task = new Task(id, city);
        inQueue.add(task);
        id++;
    }

}

