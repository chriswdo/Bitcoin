package com.eshore.datasupport.common.util;


public class Conts {
	
	
	/** 采集组名称   ：collect  */
    public final static String GROUP_CJ ="collect";
    /** 处理组名称  ：process*/
    public final static String GROUP_CL ="process";
    /** 挖掘组名称：      mining */
    public final static String GROUP_WJ ="mining";
     
    /**
     * 数据量单位:分钟/小时/天/周/月/年 
     */
    public final static String SJLDW_MINITUE="m";
    public final static String SJLDW_HOUR="h";
    public final static String SJLDW_DAY="d";
    public final static String SJLDW_WEEK="w";
    public final static String SJLDW_MONTH="M";
    public final static String SJLDW_YEAR="y";
    
    public final static String MYSQL_DATE="'%Y-%m-%d %H:%i:%s'";
    public final static String ORACLE_DATE="'YYYY-MM-DD HH24:MI:SS'";
    
    public final static String GJ_MESSAGE="1";
    public final static String GJ_EMAIL="0";
    
    public final static String ZT_Y = "Y";
    public final static String ZT_N = "N";
    
	public static final String SESSIONFLAG="userInfo";
	
	public static final int FD_GZLX_CJ= 0;
	public static final int FD_GZLX_CL= 1;
	public static final int FD_GZLX_WJ= 2;
	
	private Conts(){
		
	}
   
}
