package com.ww.net.constant;

import java.util.List;

import com.ww.net.module.Request;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 请求解码器
 * <pre>
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——-----——+
 * | 包头          | 模块号        | 命令号      |  长度        |   数据       |
 * +——----——+——-----——+——----——+——----——+——-----——+
 * </pre>
 * 包头4字节
 * 模块号2字节short
 * 命令号2字节short
 * 长度4字节(描述数据部分字节长度)
 * 
 */

public class RequestDecoder extends ByteToMessageDecoder{
	/**
	 * 数据包基本长度
	 */
	public static int BASE_LENTH = 4 + 2 + 2 + 4;
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if(in.readableBytes() >= BASE_LENTH) {
			int beginReader;
			while(true) {
				beginReader = in.readerIndex();
				in.markReaderIndex();
				if(in.readInt() == Constantvalue.FLAG) {
					//包头正确
					break;
				}
				//未读到包头，略过一个字节
				in.resetReaderIndex();
				in.readByte();
				
				//长度又变得不满足
				if(in.readableBytes() < BASE_LENTH){
					return;
				}
			}
			
			//模块号
			short module = in.readShort();
			//命令号
			short cmd = in.readShort();
			//长度
			int length = in.readInt();
			
			//判断请求数据包数据是否到齐
			if(in.readableBytes() < length){
				//还原读指针
				in.readerIndex(beginReader);
				return;
			}
			
			//读取data数据
			byte[] data = new byte[length];
			in.readBytes(data);
			
			Request request = new Request();
			request.setModule(module);
			request.setCmd(cmd);
			request.setData(data);
			
			//继续往下传递 
			//return request;
		}
	}
	
}