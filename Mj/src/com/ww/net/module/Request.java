package com.ww.net.module;

public class Request {
	//ģ���
	private short module;
	//�����
	private short cmd;
	//���ݲ���
	private byte[] data;

	public short getModule() {
		return module;
	}

	public void setModule(short module) {
		this.module = module;
	}

	public short getCmd() {
		return cmd;
	}

	public void setCmd(short cmd) {
		this.cmd = cmd;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	public int getDataLenth(){
		if(this.data == null) {
			return 0;
		}
		return this.data.length;
	}
}
