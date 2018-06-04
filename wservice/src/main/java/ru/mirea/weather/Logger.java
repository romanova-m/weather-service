package ru.mirea.weather;

public class Logger implements Runnable {

    CustomQueue outQueue;

    public Logger(CustomQueue outQueue) {
        this.outQueue = outQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                print();
                Thread.sleep(10);
            }
        }
        catch (InterruptedException e) {}
    }

    private void print(){
        if (!outQueue.isEmpty()) {
            Task task = outQueue.poll();
            System.out.println(task.id + " " + task.date + " " +
                    task.city + " " + task.weather + " ");
        }            
    }
}
