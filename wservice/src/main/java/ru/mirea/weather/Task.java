package ru.mirea.weather;
import java.util.Date;

public class Task {

    public int id;
    public Date date;
    public String city;
    public String weather;

    Task(int id, String city) {
        this.id = id;
        this.date = new Date();
        this.city = city;
        this.weather = "";
    }
}
