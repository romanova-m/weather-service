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
			tlg.interrupt();
			//TO REMOVE
			String url = "https://yandex.ru/pogoda/moscow";

			URL obj = null;
			try {
				obj = new URL(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			HttpURLConnection connection = null;
			try {
				connection = (HttpURLConnection) obj.openConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				connection.setRequestMethod("GET");
			} catch (ProtocolException e) {
				e.printStackTrace();
			}

			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			try {
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println(response.toString());
			//TO REMOVE
			// Shut down all event loops to terminate all threads.
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}