package com.ww.net;

public class NetDataHandler {
	
	public static NetDataHandler instance = null;
	
	private NetDataHandler()
	{
		
	}
	
	public static NetDataHandler getInstance()
	{
		if(null == instance) {
			instance = new NetDataHandler();
			
		}
		return instance;
	}
	
	public void recvData(String msg)
	{
		
	}
	
	
}
