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
        while (!Thread.interrupted()) {
            Random rand = new Random();
            String city;
                city = DataSource.WEATHER.cities().toArray()[rand.nextInt(
                        DataSource.WEATHER.cities().size() - 1) + 0].toString();
                channel.write(city + "\r\n");
                channel.flush();
            }
    }
}

