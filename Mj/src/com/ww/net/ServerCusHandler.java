package com.ww.net;

import com.ww.net.module.Request;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerCusHandler extends SimpleChannelInboundHandler<Request> {

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Request req)throws Exception {
		//�յ�request����
		NetManager.getInstance().SetChannelHandlerContent(ctx);
		NetManager.getInstance().recvClientData("");
	}
	
	/**
	 * �¿ͻ��˽���
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		NetManager.getInstance().NewClientEnter();
	}

	/**
	 * �ͻ��˶Ͽ�
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		NetManager.getInstance().NewClientExit();
	}

	/**
	 * �쳣
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
	}
}
