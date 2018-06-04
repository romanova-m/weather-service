package ru.mirea.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class EchoClientInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    public void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline p = ch.pipeline();

        p.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        p.addLast("decoder", new StringDecoder());
        p.addLast("encoder", new StringEncoder());
        p.addLast("handler", new EchoClientHandler());
    }
}