package ru.mirea.weather;

import java.util.LinkedList;
import java.util.Queue;

public class CustomQueue {

    private Queue list = new LinkedList();
    private int capacity = 100;
    private int size = 0;

    void push (Task task) {
        list.add(task);
        size++;
    }

    Task pull() {
        if (size != 0) {
            size--;
            return (Task)list.remove();
        }
        else return null;
    }

    boolean isEmpty () {
        return (size == 0? true : false);
    }

    boolean isFull () {
        return (size == capacity);
    }

}
