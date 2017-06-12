package com.ww.net;

import io.netty.channel.ChannelHandlerContext;

public class NetManager {
	public static NetManager instance = null;
	
	private ChannelHandlerContext channelContext = null;
	
	private NetManager()
	{
		
	}
	
	/*
	 * 设置ChannelHandlerContext
	 */
	public void SetChannelHandlerContent(ChannelHandlerContext ctx)
	{
		this.channelContext = ctx;
	}
	
	public static NetManager getInstance()
	{
		if(null == instance) {
			instance = new NetManager();
		}
		return instance;
	}
	
	public void NewClientEnter()
	{
		System.out.println("有新客户端接入");
	}
	
	public void NewClientExit()
	{
		System.out.println("有新客户端离开");
	}
	
	public Boolean recvClientData(String msg)
	{
		
		return false;
	}
	
	public Boolean sendClientData(String msg)
	{
		if(this.channelContext == null) {
			return false;
		} 
		/*
		 * StringEncoder 数据也会转换为字符串
		 * 都用json字符串传递
		 */
		this.channelContext.writeAndFlush(msg);
		return true;
	}
}
