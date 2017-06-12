package com.ww.net.constant;

import java.util.List;

import com.ww.net.module.Request;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * ���������
 * <pre>
 * ���ݰ���ʽ
 * +����----����+����-----����+����----����+����----����+����-----����+
 * | ��ͷ          | ģ���        | �����      |  ����        |   ����       |
 * +����----����+����-----����+����----����+����----����+����-----����+
 * </pre>
 * ��ͷ4�ֽ�
 * ģ���2�ֽ�short
 * �����2�ֽ�short
 * ����4�ֽ�(�������ݲ����ֽڳ���)
 * 
 */

public class RequestDecoder extends ByteToMessageDecoder{
	/**
	 * ���ݰ���������
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
					//��ͷ��ȷ
					break;
				}
				//δ������ͷ���Թ�һ���ֽ�
				in.resetReaderIndex();
				in.readByte();
				
				//�����ֱ�ò�����
				if(in.readableBytes() < BASE_LENTH){
					return;
				}
			}
			
			//ģ���
			short module = in.readShort();
			//�����
			short cmd = in.readShort();
			//����
			int length = in.readInt();
			
			//�ж��������ݰ������Ƿ���
			if(in.readableBytes() < length){
				//��ԭ��ָ��
				in.readerIndex(beginReader);
				return;
			}
			
			//��ȡdata����
			byte[] data = new byte[length];
			in.readBytes(data);
			
			Request request = new Request();
			request.setModule(module);
			request.setCmd(cmd);
			request.setData(data);
			
			//�������´��� 
			//return request;
		}
	}
	
}