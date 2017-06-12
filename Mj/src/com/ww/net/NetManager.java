package com.ww.net;

import io.netty.channel.ChannelHandlerContext;

public class NetManager {
	public static NetManager instance = null;
	
	private ChannelHandlerContext channelContext = null;
	
	private NetManager()
	{
		
	}
	
	/*
	 * ����ChannelHandlerContext
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
		System.out.println("���¿ͻ��˽���");
	}
	
	public void NewClientExit()
	{
		System.out.println("���¿ͻ����뿪");
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
		 * StringEncoder ����Ҳ��ת��Ϊ�ַ���
		 * ����json�ַ�������
		 */
		this.channelContext.writeAndFlush(msg);
		return true;
	}
}
