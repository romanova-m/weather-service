package ru.mirea.weather;

import io.netty.channel.ChannelHandlerContext;
import ru.mirea.weather.Task;

public class TaskWrapper {

    public ChannelHandlerContext ctx;
    public Task task;
	
    public TaskWrapper( Task task, ChannelHandlerContext ctx) {
        this.ctx=ctx;
        this.task=task;
    }
}