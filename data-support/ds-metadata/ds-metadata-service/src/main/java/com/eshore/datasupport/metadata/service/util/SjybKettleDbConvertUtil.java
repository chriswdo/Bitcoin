package com.eshore.datasupport.metadata.service.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.pentaho.di.core.encryption.KettleTwoWayPasswordEncoder;

import com.eshore.datasupport.common.util.DataSourceEnum;
import com.eshore.datasupport.common.util.DataSourceUtil;
import com.eshore.datasupport.metadata.pojo.R_database;
import com.eshore.datasupport.metadata.pojo.R_database_attribute;
import com.eshore.datasupport.metadata.pojo.Sjyb;

public class SjybKettleDbConvertUtil {
	private static final Logger logger = Logger.getLogger(SjybKettleDbConvertUtil.class);
	
	private SjybKettleDbConvertUtil(){
		
	}
	
	private static Properties getProperties(){
		 Properties pro = new Properties();
		 try {
			 pro.load(SjybKettleDbConvertUtil.class.getClassLoader().getResourceAsStream("databaseAttributes.properties"));
		 } catch (FileNotFoundException e) {
			 logger.info(e);
		 } catch (IOException e) {
			logger.info(e);
		 }
		 return pro;
	}

	
	/**
	 * 获取database_attributes列表
	 * @param sjyb  sjyb实例
	 * @param database  database实例
	 * @return 返回需要插入的database_attribute列表
	 */
	public static List<R_database_attribute> getDatabaseAttributes(Sjyb sjyb,R_database database){
		
		List<R_database_attribute> list = null;
		DataSourceEnum[] datasources = DataSourceEnum.values();
		for(DataSourceEnum datasource : datasources){
			//找到匹配对应数据库
			if(datasource.getValue().equals(sjyb.getFd_sjklx())){
				list = generateAttributes(datasource,sjyb,database);
			}
		}
		return list==null?new ArrayList<R_database_attribute>():list;
	}
	
	//对应数据库
	private static List<R_database_attribute> generateAttributes(DataSourceEnum datasource,Sjyb sjyb,R_database database){
		List<R_database_attribute> list = new ArrayList<R_database_attribute>();
		Properties pro = getProperties();
		Enumeration keys =  pro.keys();
		while(keys.hasMoreElements()){
			String key = keys.nextElement().toString();
			if(key.startsWith(datasource.getValue().toUpperCase())){
				R_database_attribute r = new R_database_attribute();
				if("PORT_NUMBER".equals(key.substring(datasource.getValue().length()+1))){
					r.setId_database(database.getId_database());
					r.setCode(key.substring(datasource.getValue().length()+1));
					r.setValue_str(sjyb.getFd_dk()+"");
					list.add(r);
					continue;
				}
				r.setId_database(database.getId_database());
				r.setCode(key.substring(datasource.getValue().length()+1));
				r.setValue_str(pro.getProperty(key));
				list.add(r);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param sjyb sjyb实例
	 * @param database  database实例
	 * @return 返回database实例
	 */
	public static R_database getRDatabase(Sjyb sjyb,R_database database ){
		database.setName(sjyb.getFd_mc());
		database.setId_database_contype(1);
		database.setDatabase_name(sjyb.getFd_sjkmc());
		database.setUsername(sjyb.getFd_yhm());
		database.setHost_name(sjyb.getFd_ip());
		database.setPort(sjyb.getFd_dk());
		database.setPassword(sjyb.getFd_mm());
		return database;
	}
	
	/**
	 * 加密密码
	 * @param password
	 * @return
	 */
	public static String encryPassword(String password){
		return KettleTwoWayPasswordEncoder.PASSWORD_ENCRYPTED_PREFIX+KettleTwoWayPasswordEncoder.encryptPassword(password);
	}
	
	/**
	 * 解密密码
	 * @param encryStr
	 * @return
	 */
	public static String decryPassword(String encryStr){
		KettleTwoWayPasswordEncoder kp = new KettleTwoWayPasswordEncoder();
		return kp.decode(encryStr);
	}
}
