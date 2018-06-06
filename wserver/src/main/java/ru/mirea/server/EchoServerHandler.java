package ru.mirea.server;

import io.netty.buffer.ByteBuf;
import ru.mirea.data.DataSource;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.EventExecutor;

import io.netty.channel.Channel;
import ru.mirea.weather.*;
import ru.mirea.weather.Task;
import ru.mirea.weather.CustomQueue;
import ru.mirea.weather.TaskWrapper;

public class EchoServerHandler extends SimpleChannelInboundHandler<Task>{

    private static final ChannelGroup channels = new DefaultChannelGroup(
			(EventExecutor) new DefaultEventExecutor());
        int id = 0;
        CustomQueue inQueue;

	public EchoServerHandler(CustomQueue inQueue) {
		this.inQueue = inQueue;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Task msg) throws Exception {
		Channel incoming = ctx.channel();

		synchronized (inQueue) {
			inQueue.add(new TaskWrapper(msg, ctx));
		}

/*
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            Channel incoming = ctx.channel();
		ctx.channel().writeAndFlush("["+incoming.remoteAddress()+ "] ID" + ++id + " " +
				new Date() + ":" + msg + " " + DataSource.WEATHER.getByCity(msg)+ "\r\n");
*/
	}


	/*@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ctx.write(msg);
	}
*/
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}


}