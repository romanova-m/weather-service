package ru.mirea.weather;

public class Logger implements Runnable{

    CustomQueue queue;
    Task task;
    public static int counter;

    public Logger(CustomQueue queueArg) {
        counter = 0;
        queue = queueArg;
    }

    public synchronized void run() {
        while(true) {
            if (!queue.isEmpty()) {
                synchronized(queue) {
                    if (!queue.isEmpty())
                    task = queue.pull();
                }
                counter++;
                System.out.println("ID: " + task.id + "\n\tDate: " + task.date.toString() + "; \n\tcity: " +
                        task.city + "; \n\tweather: " + task.weather + "\n");
            }
            if (Thread.interrupted()) {
                System.out.println("LOGGER WAS INTERRUPTED");
                return;
            }
        }
    }
}
