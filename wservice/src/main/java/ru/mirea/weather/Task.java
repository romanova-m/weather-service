package ru.mirea.weather;

import ru.mirea.data.DataSource;
import java.util.Date;
import java.util.Random;
import java.util.Set;

public class Task {

    int id;
    Date date;
    String city;
    String weather;

    public Task(int id, DataSource dc){
        this.id = id;
        this.date = new Date();
        synchronized (dc) {
	setCity(dc);
	}
    }

    public void execute(DataSource dc) {
        System.out.println("City " + city + "requested\n");
	synchronized (dc) {
	this.weather = dc.getByCity(city);
	}
    }

    public void setCity(DataSource dc) {
        Random rand = new Random();
	synchronized (dc) {
        city = dc.cities().toArray()[rand.nextInt(dc.cities().size() - 1) + 0].toString();
	}
    }
}
