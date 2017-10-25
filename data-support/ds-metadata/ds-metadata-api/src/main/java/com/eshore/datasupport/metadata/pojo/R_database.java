package com.eshore.datasupport.metadata.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="R_DATABASE")
public class R_database implements Serializable{
	private Long id_database;//����
	private String name;//name
	private Integer id_database_type;//id_database_type
	private Integer id_database_contype;//id_database_contype
	private String host_name;//host_name
	private String database_name;//database_name
	private Integer port;//port
	private String username;//username
	private String password;//password
	private String servername;//servername
	private String data_tbs;//data_tbs
	private String index_tbs;//index_tbs
	@Id
	
	@Column(name="ID_DATABASE")
	public Long getId_database(){
	  		return id_database;
	}
	
	public void setId_database(Long id_database) {
		this.id_database = id_database;
	}		
	
	@Column(name="NAME",length=255)
	public String getName(){
	  		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}		
	
	@Column(name="ID_DATABASE_TYPE")
	public Integer getId_database_type(){
	  		return id_database_type;
	}
	
	public void setId_database_type(Integer id_database_type) {
		this.id_database_type = id_database_type;
	}		
	
	@Column(name="ID_DATABASE_CONTYPE")
	public Integer getId_database_contype(){
	  		return id_database_contype;
	}
	
	public void setId_database_contype(Integer id_database_contype) {
		this.id_database_contype = id_database_contype;
	}		
	
	@Column(name="HOST_NAME",length=255)
	public String getHost_name(){
	  		return host_name;
	}
	
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}		
	
	@Column(name="DATABASE_NAME")
	public String getDatabase_name(){
	  		return database_name;
	}
	
	public void setDatabase_name(String database_name) {
		this.database_name = database_name;
	}		
	
	@Column(name="PORT")
	public Integer getPort(){
	  		return port;
	}
	
	public void setPort(Integer port) {
		this.port = port;
	}		
	
	@Column(name="USERNAME",length=255)
	public String getUsername(){
	  		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}		
	
	@Column(name="PASSWORD",length=255)
	public String getPassword(){
	  		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}		
	
	@Column(name="SERVERNAME",length=255)
	public String getServername(){
	  		return servername;
	}
	
	public void setServername(String servername) {
		this.servername = servername;
	}		
	
	@Column(name="DATA_TBS",length=255)
	public String getData_tbs(){
	  		return data_tbs;
	}
	
	public void setData_tbs(String data_tbs) {
		this.data_tbs = data_tbs;
	}		
	
	@Column(name="INDEX_TBS",length=255)
	public String getIndex_tbs(){
	  		return index_tbs;
	}
	
	public void setIndex_tbs(String index_tbs) {
		this.index_tbs = index_tbs;
	}		
}

