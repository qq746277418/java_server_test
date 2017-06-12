package com.ww.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/*
 * TCP Socket
 */

public class ServerSocket {
	
	/*
	 *����ģʽ 
	 */
	public static ServerSocket instance = null;
	
	private static final String ip = "127.0.0.1";
	private static final int port = 8080;
	
	private ServerSocket()
	{
		
	}
	
	public static ServerSocket getInstance()
	{
		if(null == instance) {
			instance = new ServerSocket();
		}
		return instance;
	}
	
	public Boolean initServer(String ip, int port)
	{
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(boss, worker)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						//ch.pipeline().addLast(new StringDecoder());
						//ch.pipeline().addLast(new StringEncoder());
						ch.pipeline().addLast(new ServerCusHandler());
					}
				});
			
			//serverSocketchannel�����ã����ӻ���صĴ�С
			bootstrap.option(ChannelOption.SO_BACKLOG, 2048);
			//socketchannel������,ά�����ӵĻ�Ծ�����������
			bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			//socketchannel������,�ر��ӳٷ���
			bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
			
			ChannelFuture future =  bootstrap.bind(ip, port).sync();
			
			System.out.println("server start success!");
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			/*
			 * �˳��߳����ͷ���Դ
			 */
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
		return false;
	}
}
