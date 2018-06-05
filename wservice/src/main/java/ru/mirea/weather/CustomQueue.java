package ru.mirea.weather;

import java.util.Queue;
import java.util.LinkedList;

public class CustomQueue {
    
    protected Queue<TaskWrapper> myQueue;
    protected int size;
    
    public CustomQueue(int size) {
        myQueue = new LinkedList<>();
        this.size = size;
    }
    
    public synchronized boolean add(TaskWrapper task) {
        if (myQueue.size() < size)  {
            myQueue.add(task);    
            return true;
        }
        else return false;
    }   
    
    public synchronized TaskWrapper poll() {
            return myQueue.poll();
    }
    
    public synchronized boolean isEmpty() {
        return myQueue.isEmpty();
    }
    
    public synchronized int size() {
        return myQueue.size();
    }
}
