package ru.mirea.weather;
import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

    public int id;
    public Date date;
    public String city;
    public String weather;

    public Task(int id, String city) {
        this.id = id;
        this.date = new Date();
        this.city = city;
        this.weather = "";
    }
}
