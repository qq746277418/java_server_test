package com.ww.net;

import com.ww.net.module.Request;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerCusHandler extends SimpleChannelInboundHandler<Request> {

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Request req)throws Exception {
		//收到request对象
		NetManager.getInstance().SetChannelHandlerContent(ctx);
		NetManager.getInstance().recvClientData("");
	}
	
	/**
	 * 新客户端接入
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		NetManager.getInstance().NewClientEnter();
	}

	/**
	 * 客户端断开
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		NetManager.getInstance().NewClientExit();
	}

	/**
	 * 异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
	}
}
