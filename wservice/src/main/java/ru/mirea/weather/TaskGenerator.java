package ru.mirea.weather;

import ru.mirea.data.DataSource;
import io.netty.channel.Channel;
import java.util.Random;

public class TaskGenerator implements Runnable {
    Channel channel;
    int id;

    public TaskGenerator(Channel channel) {
        this.channel = channel;
        this.id = 0;
    }

    @Override
    public void run() {
        try {
        while (!Thread.interrupted()) {
            generate();
            Thread.sleep(5);
            }
        } catch (InterruptedException e) {}              
    }
    
    private void generate() {
        Random rand = new Random();
        String city = DataSource.WEATHER.cities().toArray()[rand.nextInt(
                DataSource.WEATHER.cities().size() - 1) + 0].toString();
        Task task = new Task(++id, city);
        channel.write(task);
        channel.flush();
    }
}

