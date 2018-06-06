package ru.mirea.weather;

import io.netty.channel.ChannelHandlerContext;

public class TaskWrapper {

    public ChannelHandlerContext ctx;
    public Task task;
	
    public TaskWrapper(Task task, ChannelHandlerContext ctx) {
        this.ctx=ctx;
        this.task=task;
    }
}