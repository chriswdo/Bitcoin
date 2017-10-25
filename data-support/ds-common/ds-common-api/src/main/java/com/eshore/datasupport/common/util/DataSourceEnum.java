package com.eshore.datasupport.common.util;

public enum DataSourceEnum {
	
	MYSQL("mysql","com.mysql.jdbc.Driver"),
	ORACLE("oracle","oracle.jdbc.driver.OracleDriver"),
	DB2("db2","com.ibm.db2.jcc.DB2Driver"),
	GREENPLUM("greenplum","org.postgresql.Driver"),
	SYBASE("sybase","net.sourceforge.jtds.jdbc.Driver");
	
	private String value;
	private String driverName;
	
	private  DataSourceEnum(String value,String driverName){
		this.value=value;
		this.driverName=driverName;
	}
	
	public String getValue(){
		return value;
	}
	
	public String getDriverName(){
		return driverName;
	}

}
