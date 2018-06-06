package ru.mirea.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import ru.mirea.weather.Task;

public class EchoClientHandler extends SimpleChannelInboundHandler<Task>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Task msg) throws Exception{
        System.out.println("[" + ctx.channel().localAddress() + "][id:" + msg.id + "] " + msg.date + " " +
                msg.city + " " + msg.weather);
    }
}