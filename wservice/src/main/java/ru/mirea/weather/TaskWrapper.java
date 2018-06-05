package ru.mirea.weather;

import io.netty.channel.ChannelHandlerContext;
import ru.mirea.weather.Task;

public class TaskWrapper {

    public Task task;
    public ChannelHandlerContext ctx;
	
    public TaskWrapper(Task task, ChannelHandlerContext ctx) {
	    this.task=task;
        this.ctx=ctx;
    }
}