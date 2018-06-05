package ru.mirea.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import ru.mirea.weather.Task;

public class EchoClientHandler extends SimpleChannelInboundHandler<Task>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Task msg) throws Exception{
        

        Task task = (Task) msg;

        System.out.println("[" + ctx.channel().localAddress() + "][id:" + task.id + "] " + task.date + " " +
                task.city + " " + task.weather);

    }
    /*private final ByteBuf firstMessage;

    public EchoClientHandler() {
        firstMessage = Unpooled.buffer(EchoClient.SIZE);
        for (int i = 0; i < firstMessage.capacity(); i ++) {
            firstMessage.writeByte((byte) i);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(firstMessage);
    }

    @Override
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