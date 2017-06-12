package com.ww.demo.protobuf;

import java.util.Arrays;

import com.google.protobuf.ByteString;
import com.ww.demo.protobuf.PlayerModel.PBPlayer.Builder;
import com.ww.demo.protobuf.PlayerModel.PBPlayer;

public class PB2Bytes {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		byte[] bytes = toBytes();
		toPlayer(bytes);
	}
	
	public static byte[] toBytes()
	{
		//获取一个PBPlayer的构造器
		Builder builder = PlayerModel.PBPlayer.newBuilder();
		//设置数据
		builder.setPlayerId(101).setAge(20).setName("peter").addSkills(1001);
		//构造出对象
		PBPlayer player = builder.build();
		//序列化成字节数组
		byte[] byteArray = player.toByteArray();
				
		System.out.println(Arrays.toString(byteArray));
				
		return byteArray;
	}
	
	public  static void toPlayer(byte[] bs) throws Exception
	{
		PBPlayer player = PlayerModel.PBPlayer.parseFrom(bs);
		 
		 System.out.println("playerId:" + player.getPlayerId());
		 System.out.println("age:" + player.getAge());
		 System.out.println("name:" + player.getName());
		 System.out.println("skills:" + (Arrays.toString(player.getSkillsList().toArray())));
	}
}
