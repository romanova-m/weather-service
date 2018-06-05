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
        TaskWrapper wrapper = null;
        if (!outQueue.isEmpty()) {
            synchronized (outQueue) {
                if (!outQueue.isEmpty()) wrapper = outQueue.poll();
            }
            wrapper.ctx.channel().write(wrapper.task);
            wrapper.ctx.channel().flush();
        }            
    }
}
