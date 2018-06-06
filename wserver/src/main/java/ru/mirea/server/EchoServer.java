package ru.mirea.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import ru.mirea.weather.CustomQueue;
import ru.mirea.weather.Logger;
import ru.mirea.weather.TaskExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public final class EchoServer implements Runnable {

	final int port = Integer.parseInt(System.getProperty("port", "8080"));

	public void run() {
		int size = 100;

		CustomQueue inQueue = new CustomQueue(size);
		CustomQueue outQueue = new CustomQueue(size);

		TaskExecutor te = new TaskExecutor(inQueue, outQueue);
		Logger lg = new Logger(outQueue);
		Thread tte = new Thread(te);
		Thread tte1 = new Thread(te);
		Thread tte2 = new Thread(te);
		Thread tlg = new Thread(lg);

		tte.start();
		tte1.start();
		tte2.start();
		
		tlg.start();

		// Configure the server.
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new EchoServerInitializer(inQueue));

			// Start the server.
			ChannelFuture f = b.bind(port).sync();

			// Wait until the server socket is closed.
			f.channel().closeFuture().sync();
		} catch (InterruptedException e){}
		finally {
			tte.interrupt();
			tte1.interrupt();
			tte2.interrupt();
			tlg.interrupt();

			// Shut down all event loops to terminate all threads.
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}