package com.ww.net.module;

public class Response {
	//模块号
	private short module;
	//命令号
	private short cmd;
	//数据部分
	private byte[] data;
	//状态码
	private int state;
	
	
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getDataLenth(){
		if(this.data == null) {
			return 0;
		}
		return this.data.length;
	}	
}
