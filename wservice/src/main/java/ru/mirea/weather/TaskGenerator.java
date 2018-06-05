package ru.mirea.weather;

import ru.mirea.data.DataSource;
import ru.mirea.weather.CustomQueue;
import ru.mirea.weather.Task;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.Channel;
import java.util.Random;

public class TaskGenerator implements Runnable {
    Channel channel;

    public TaskGenerator(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        int id = 0;
        try {
        while (!Thread.interrupted()) {
            Random rand = new Random();
            String city = DataSource.WEATHER.cities().toArray()[rand.nextInt(
                    DataSource.WEATHER.cities().size() - 1) + 0].toString();
            Task task = new Task(++id, city);
            channel.write(task);
            channel.flush();
            Thread.sleep(5);
            }
        } catch (InterruptedException e) {}
    }
}

