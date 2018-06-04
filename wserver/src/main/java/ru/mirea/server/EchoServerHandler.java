package ru.mirea.server;

import ru.mirea.data.DataSource;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.EventExecutor;
import java.util.Date;
import io.netty.channel.Channel;

/**
 * Handler implementation for the echo server.
 */
public class EchoServerHandler extends SimpleChannelInboundHandler<String>{

	private static final ChannelGroup channels = new DefaultChannelGroup(
			(EventExecutor) new DefaultEventExecutor());
        int id = 0;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            Channel incoming = ctx.channel();
		ctx.channel().write("["+incoming.remoteAddress()+ "] ID" + ++id + " " +
				new Date() + ":" + msg + " " + DataSource.Weather.getByCity(msg)+ "\r\n");
		ctx.flush();
	}


	/*@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ctx.write(msg);
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
	*/

}