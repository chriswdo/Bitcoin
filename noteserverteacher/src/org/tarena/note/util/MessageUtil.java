package org.tarena.note.util;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;


public class MessageUtil {
	
	/**
	 * MD5摘要算法
	 * @param msg
	 * @return
	 */
	public static String md5(String msg){
		try{
			MessageDigest md = 
					MessageDigest.getInstance("MD5");
			byte[] input = msg.getBytes();//要加密的字节
			byte[] output = md.digest(input);//加密后生成等长output字节
			//采用Base64算法将等长字节数组转成字符串(没有乱码)
			String ret_msg = Base64.encodeBase64String(output);
			return ret_msg;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 对字符串信息进行解码
	 * @param msg
	 * @return
	 */
	public static String base64Decode(String base64String){
		try{
			byte[] output = Base64.decodeBase64(base64String);
			return new String(output,"UTF-8");
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 登录成功分配一个唯一令牌编号
	 * @return
	 */
	public static String createToken(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
	
	/**
	 * 生成主键ID
	 * @return
	 */
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	public static void main(String[] args){
		System.out.println(md5("123456"));
		System.out.println(md5("abdddddddddddddddddddddddddddddddddcd"));
		System.out.println(md5("a"));
		System.out.println(createToken());
	}
	
	
}
