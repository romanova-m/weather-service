package ru.mirea.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import ru.mirea.weather.CustomQueue;

public class EchoServerInitializer extends ChannelInitializer<SocketChannel>{

    CustomQueue inQueue;
    public EchoServerInitializer(CustomQueue inQueue){
        this.inQueue = inQueue;
    }
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();

        p.addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(getClass().getClassLoader())));
        p.addLast("encoder", new ObjectEncoder());
        p.addLast("handler", new EchoServerHandler(inQueue));
    }
}