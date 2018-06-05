package ru.mirea.server;

import io.netty.buffer.ByteBuf;
import ru.mirea.data.DataSource;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.EventExecutor;

import java.math.BigInteger;
import java.util.Date;
import io.netty.channel.Channel;
import ru.mirea.weather.*;

public class EchoServerHandler extends SimpleChannelInboundHandler<Task>{

		private static final ChannelGroup channels = new DefaultChannelGroup(
			(EventExecutor) new DefaultEventExecutor());
        int id = 0;
        static int size = 100;

        private static CustomQueue inQueue = new CustomQueue(size);
		private static CustomQueue outQueue = new CustomQueue(size);
public EchoServerHandler() {
	TaskExecutor te = new TaskExecutor(inQueue, outQueue);
	Logger lg = new Logger(outQueue);
	Thread tte = new Thread(te);
	Thread tlg = new Thread(lg);
	tte.run();
	tlg.run();
	try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Task msg) throws Exception {
		//Channel incoming = ctx.channel();

		synchronized (inQueue) {
			inQueue.add(new TaskWrapper(msg, ctx));
		}

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