package ru.mirea.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.EventExecutor;
import ru.mirea.weather.Task;
import ru.mirea.weather.CustomQueue;
import ru.mirea.weather.TaskWrapper;

public class EchoServerHandler extends SimpleChannelInboundHandler<Task>{

    private static final ChannelGroup channels = new DefaultChannelGroup(
			(EventExecutor) new DefaultEventExecutor());
        CustomQueue inQueue;

	public EchoServerHandler(CustomQueue inQueue) {
		this.inQueue = inQueue;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Task msg) throws Exception {
		synchronized (inQueue) {
			inQueue.add(new TaskWrapper(msg, ctx));
		}
	}

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