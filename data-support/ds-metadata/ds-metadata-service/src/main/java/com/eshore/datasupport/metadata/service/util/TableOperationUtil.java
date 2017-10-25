package com.eshore.datasupport.metadata.service.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.eshore.datasupport.common.util.DataSourceEnum;
import com.eshore.datasupport.metadata.pojo.Xxzd;
import org.apache.log4j.Logger;


public class TableOperationUtil {
	private static final Logger logger = Logger.getLogger(TableOperationUtil.class);

	private TableOperationUtil(){
		
	}
	/**
	 * 创建表的 SQL语句 入口   （执行前先查询一次，看是否存在该表）
	 * @param driverClass
	 * @param list
	 * @param tableName
	 * @param tableCommont
	 * @return  list按顺序执行sql
	 */
	public static List<String> createTable(String driverClass, List<Xxzd> list,String tableName,String tableCommont) {
    	StringBuilder createSb=new StringBuilder();
    	List<String> alertSb=new ArrayList<String>();
    	if(StringUtils.isNoneBlank(tableName) && list!=null  && !list.isEmpty()){
    		if(DataSourceEnum.ORACLE.getDriverName().equalsIgnoreCase(driverClass)||DataSourceEnum.GREENPLUM.getDriverName().equalsIgnoreCase(driverClass)){
		    	List<String> pKey=new ArrayList<String>();
		    	if(StringUtils.isNoneBlank(tableCommont)){//表注释
					alertSb.add("comment on table "+tableName+" is '"+tableCommont.replaceAll("'", "\"")+"'");
				}
		    	if(StringUtils.isNoneBlank(tableName)){  
			    	createSb.append("create table ");
			    	createSb.append(tableName);
			    	createSb.append("(");
			    	for(int i=0;i<list.size();i++){
			    		Xxzd xxzd=list.get(i);
			    		if(i>0){
			    			createSb.append(",");
			    		}	    		
			    		createSb.append(createField(driverClass,xxzd));
			    		if(StringUtils.isNoneBlank(xxzd.getFd_zdbz())){//字段注释
			    			alertSb.add("comment on column "+tableName+"."+xxzd.getFd_zdmc()+" is '"+xxzd.getFd_zdbz().replaceAll("'", "\"")+"'");
			    		}
			    		if("Y".equalsIgnoreCase(xxzd.getFd_sfzj())){
			    			pKey.add(xxzd.getFd_zdmc());
			    		}
			    	}
			    	createSb.append(")");
			    	if(!pKey.isEmpty()){//主键
			    		alertSb.add(createPkey(driverClass,pKey,tableName));				
					}
		    	}
		    	
			}
	    	alertSb.add(0, createSb.toString());
    	}
        return alertSb;
    }

	/**
	 * 字段
	 * @param driverClass
	 * @param xxzd
	 * @return
	 */
	private static String createField(String driverClass,Xxzd xxzd){
		StringBuilder sb=new StringBuilder();
		String fieldName=xxzd.getFd_zdmc();
		String fieldType=xxzd.getFd_zdlx();
		
		if(StringUtils.isNoneBlank(fieldName) && StringUtils.isNoneBlank(fieldType)){
			if(DataSourceEnum.ORACLE.getDriverName().equalsIgnoreCase(driverClass) ||DataSourceEnum.GREENPLUM.getDriverName().equalsIgnoreCase(driverClass)){
				int length=xxzd.getFd_zdcd()==null?0:xxzd.getFd_zdcd();
				int jd = xxzd.getFd_zdjd()==null?0:xxzd.getFd_zdjd();
				//名称 类型
				sb.append(fieldName).append(" ");
				if("VARCHAR".equalsIgnoreCase(fieldType) || "NUMBER".equalsIgnoreCase(fieldType)){
					sb.append(fieldType).append("(").append(length).append(")"); 
				}else if("NUMERIC".equalsIgnoreCase(fieldType)){
					sb.append("NUMERIC").append("(").append(length).append(",").append(jd).append(")"); 
				}else {
					sb.append(fieldType);
				}
				//默认值
				if(StringUtils.isNoneBlank(xxzd.getFd_qsz())){
					sb.append(" default ").append(createQsz(driverClass,xxzd.getFd_qsz(),fieldType));
				}

			}
		}		 
		return sb.toString();
	}
	/**
	 * 编辑greeplum字段的sql语句
	 * @param driverClass  
	 * @param xxzd 
	 * @param tableName
	 * @return
	 */
	private static List<String> createFieldOfgreeplum(String driverClass,Xxzd xxzd,String tableName){
		StringBuilder sb=new StringBuilder();
		String fieldName=xxzd.getFd_zdmc();
		String fieldType=xxzd.getFd_zdlx();
		List<String> list=new ArrayList<String>();
		if(DataSourceEnum.GREENPLUM.getDriverName().equalsIgnoreCase(driverClass)){
			int length=xxzd.getFd_zdcd()==null?0:xxzd.getFd_zdcd();
			int jd = xxzd.getFd_zdjd()==null?0:xxzd.getFd_zdjd();
			if("VARCHAR".equalsIgnoreCase(fieldType) || "NUMBER".equalsIgnoreCase(fieldType)){
				sb.append(fieldType).append("(").append(length).append(")"); 
				list.add("alter table "+tableName+" ALTER COLUMN "+fieldName+" TYPE "+sb.toString());
			}else if("NUMERIC".equalsIgnoreCase(fieldType)){
				sb.append("NUMBER").append("(").append(length).append(",").append(jd).append(")");
				list.add("alter table "+tableName+" ALTER COLUMN "+fieldName+" TYPE "+sb.toString());
			}else {
				sb.append(fieldType);
				list.add("alter table "+tableName+" ALTER COLUMN "+fieldName+" TYPE "+sb.toString());
			}
			
			if(StringUtils.isNoneBlank(xxzd.getFd_qsz())){//默认值
				String qsz=createQsz(driverClass,xxzd.getFd_qsz(),fieldType);
				list.add("alter table "+tableName+" ALTER COLUMN "+fieldName+" SET DEFAULT "+qsz);
			}
		}
		return list;
		
	}

	
	/**
	 * 默认值
	 * @param driverClass
	 * @param comment
	 * @param fieldType
	 * @return
	 */
	private static String createQsz(String driverClass,String comment,String fieldType){
		if(DataSourceEnum.ORACLE.getDriverName().equalsIgnoreCase(driverClass)||DataSourceEnum.GREENPLUM.getDriverName().equalsIgnoreCase(driverClass)){
			String comment1 ="";
			if(StringUtils.isNoneBlank(comment)){
				if("VARCHAR".equalsIgnoreCase(fieldType)){
					  //单引号全部替换掉
					  comment1="'"+comment.replaceAll("'", "")+"'";
				}else if("NUMBER".equalsIgnoreCase(fieldType)||"NUMERIC".equalsIgnoreCase(fieldType)||
						"BIGINT".equalsIgnoreCase(fieldType)||"INTEGER".equalsIgnoreCase(fieldType)){
					comment1 = comment.replaceAll("'", "");
				}
				return comment1;
			}
		}		
		return "";
	}
	/**
	 * 创建主键
	 * @param driverClass
	 * @param pKey
	 * @param tableName
	 * @return
	 */
	private static String createPkey(String driverClass,List<String> pKey,String tableName){
		if(DataSourceEnum.ORACLE.getDriverName().equalsIgnoreCase(driverClass) ||DataSourceEnum.GREENPLUM.getDriverName().equalsIgnoreCase(driverClass)){
			StringBuilder p=new StringBuilder();
			for(int i=0;i<pKey.size();i++){
				if(i>0){p.append(",");}
				p.append(pKey.get(i));
			}
			return "alter table "+tableName+" add constraint "+((tableName.length()>20)?tableName.substring(0, 20):tableName) + "_dsjzc_key primary key ("+p.toString()+")";
		}
		return "";	
		
	}
		
	public static void main(String[] args) {
		List<Xxzd> list=new ArrayList<Xxzd>();
		Xxzd xxzd=new Xxzd();
		xxzd.setFd_zdmc("ddd");
		xxzd.setFd_zdlx("varchar");
		xxzd.setFd_zdcd((short)2);
		xxzd.setFd_zdbz("ttttt4");
		xxzd.setFd_sfzj("Y");
		list.add(xxzd);
		
		 xxzd=new Xxzd();
		xxzd.setFd_zdmc("ddd2");
		xxzd.setFd_zdlx("number");
		xxzd.setFd_zdcd((short)2);
		xxzd.setFd_zdbz("ttttt3");
		list.add(xxzd);
		
		 xxzd=new Xxzd();
			xxzd.setFd_zdmc("ddd3");
			xxzd.setFd_zdlx("NUMERIC");
			xxzd.setFd_zdcd((short)2);
			xxzd.setFd_zdjd((short)1);
			xxzd.setFd_zdbz("tttt2");
			list.add(xxzd);
		
		xxzd=new Xxzd();
				xxzd.setFd_zdmc("ddd4");
				xxzd.setFd_zdlx("Date");
				xxzd.setFd_zdcd((short)2);
				xxzd.setFd_zdjd((short)1);
				xxzd.setFd_zdbz("ttttt");;
				list.add(xxzd);
				
		List<String> sql=createTable(DataSourceEnum.GREENPLUM.getDriverName(),list,"aaa1","sdfsd");
		
		for(int j=0;j<sql.size();j++){
			logger.info(sql.get(j));
		}
		
	}
	
	
	/**
	 * 添加字段
	 * @param tableName
	 * @param driverClass
	 * @param xxzd
	 * @return
	 */
	public static List<String> addField(String driverClass,Xxzd xxzd,String tableName){
		List<String> list=new ArrayList<String>();
		if(DataSourceEnum.ORACLE.getDriverName().equalsIgnoreCase(driverClass)||DataSourceEnum.GREENPLUM.getDriverName().equalsIgnoreCase(driverClass)){
			list.add("alter table "+tableName+" add "+createField(driverClass,xxzd));
			if(StringUtils.isNoneBlank(xxzd.getFd_zdbz())){//字段注释
				list.add("comment on column "+tableName+"."+xxzd.getFd_zdmc()+" is '"+xxzd.getFd_zdbz().replaceAll("'", "\"")+"'");
    		}
		}		
		return list;
	}
	/**
	 * 编辑字段
	 * @param tableName
	 * @param driverClass
	 * @param xxzd
	 * @return
	 */
	public static List<String> modifyField(String driverClass,Xxzd xxzd,String tableName){
		List<String> list=new ArrayList<String>();
		if(DataSourceEnum.ORACLE.getDriverName().equalsIgnoreCase(driverClass)){
			list.add("alter table "+tableName+" modify "+createField(driverClass,xxzd));
		}else if(DataSourceEnum.GREENPLUM.getDriverName().equalsIgnoreCase(driverClass)){
			list=createFieldOfgreeplum(driverClass, xxzd, tableName);
		}	
		if(StringUtils.isNoneBlank(xxzd.getFd_zdbz())){//字段注释
			list.add("comment on column "+tableName+"."+xxzd.getFd_zdmc()+" is '"+xxzd.getFd_zdbz().replaceAll("'", "\"")+"'");
		}
		return list;
	}
	
	/**
	 * 修改key sql
	 * @param driverClass
	 * @param keyNew
	 * @param hasKey
	 * @param tableName
	 * @return
	 */
	public static List<String> modifyKey(String driverClass,List<String> keyNew,boolean hasKey,String tableName){
		List<String> list=new ArrayList<String>();
		if(DataSourceEnum.ORACLE.getDriverName().equalsIgnoreCase(driverClass)){
			if(hasKey){//删除原有的key
				list.add("alter table "+tableName+"  drop constraint "+((tableName.length()>20)?tableName.substring(0, 20):tableName) + "_dsjzc_key cascade");
			}
			list.add(createPkey(driverClass,keyNew,tableName));			
		}else if(DataSourceEnum.GREENPLUM.getDriverName().equalsIgnoreCase(driverClass)){
			if(hasKey){//删除原有的key
				list.add("alter table "+tableName+"  drop CONSTRAINT "+((tableName.length()>20)?tableName.substring(0, 20):tableName) + "_dsjzc_key");
			}
			list.add(createPkey(driverClass,keyNew,tableName));			
		}
		return list;
	}
	/**
	 * 查询主键key的  sql
	 * @param driverClass
	 * @param tableName
	 * @return
	 */
	public static String searchKey(String driverClass,String tableName){
		if(DataSourceEnum.ORACLE.getDriverName().equalsIgnoreCase(driverClass)){
			 return "select CONSTRAINT_NAME keyName from user_constraints  where  CONSTRAINT_TYPE='P' and TABLE_NAME='"+tableName.toUpperCase()+"'";
		}else if(DataSourceEnum.GREENPLUM.getDriverName().equalsIgnoreCase(driverClass)){
		
			return "select a.attname as COLUMN_NAME, d.contype as constraint_type "+
		  "from  pg_attribute a "+
		   " inner join pg_class c on a.attrelid = c.oid  "+
		    
		   " inner join  pg_constraint d on d.conrelid=a.attrelid and a.attnum = any(d.conkey)"+ 
		     
		   " where    c.relname = '"+tableName+"'";
		    
		}
		return "";
	}
	
	/**
	 * 查询表
	 * @param driverClass
	 * @param tableName
	 * @return
	 */
	public static String searchTable(String driverClass,String tableName){
		if(DataSourceEnum.ORACLE.getDriverName().equalsIgnoreCase(driverClass)){
			 return "select * from user_tables where   TABLE_NAME='"+tableName+"'";
		}else if(DataSourceEnum.GREENPLUM.getDriverName().equalsIgnoreCase(driverClass)){
			 return "select a.relname table_name,COALESCE(b.description,'') as comment from pg_class a left join  pg_description b on a.oid=b.objoid where a.relname='"+tableName+"'";
		}
		return "";
	}
}
