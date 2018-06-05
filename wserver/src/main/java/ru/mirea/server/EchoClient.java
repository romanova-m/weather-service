package ru.mirea.server;


import ru.mirea.data.DataSource;
import ru.mirea.weather.Task;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import ru.mirea.weather.TaskGenerator;

import java.util.Random;

public final class EchoClient implements Runnable{

    static final boolean SSL = System.getProperty("ssl") != null;
    final String host = "127.0.0.1";
    final int port = 8080;

    public void run () {

        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new EchoClientInitializer());

            // Start the client.
            Channel channel = b.connect(host, port).sync().channel();

            //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            
            Thread genStream = new Thread(new TaskGenerator(channel));
            genStream.start();
            Thread.sleep(3000);
            genStream.interrupt();


            // Wait until the connection is closed.
            channel.closeFuture().sync();
        }
        catch (InterruptedException e){}
        finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }
}