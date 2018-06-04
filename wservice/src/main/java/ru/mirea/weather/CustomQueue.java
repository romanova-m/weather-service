package ru.mirea.weather;

import java.util.Queue;
import java.util.LinkedList;

public class CustomQueue {
    
    protected Queue<Task> myQueue;
    protected int size;
    
    CustomQueue(int size) {
        myQueue = new LinkedList<Task>();
        this.size = size;
    }
    
    public synchronized boolean add(Task task) {
        if (myQueue.size() < size)  {
            myQueue.add(task);    
            return true;
        }
        else return false;
    }   
    
    public synchronized Task poll() {
            return myQueue.poll();
    }
    
    public synchronized boolean isEmpty() {
        return myQueue.isEmpty();
    }
    
    public synchronized int size() {
        return myQueue.size();
    }
}
