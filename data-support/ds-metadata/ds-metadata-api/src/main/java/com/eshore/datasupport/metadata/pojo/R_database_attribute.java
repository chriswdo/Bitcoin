package com.eshore.datasupport.metadata.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="R_DATABASE_ATTRIBUTE")
public class R_database_attribute implements Serializable{
	private Long id_database_attribute;//id_database_attribute
	private Long id_database;//id_database
	private String code;//code
	private String value_str;//value_str
	@Id
	
	@Column(name="ID_DATABASE_ATTRIBUTE")
	public Long getId_database_attribute(){
	  		return id_database_attribute;
	}
	
	public void setId_database_attribute(Long id_database_attribute) {
		this.id_database_attribute = id_database_attribute;
	}		
	
	@Column(name="ID_DATABASE")
	public Long getId_database(){
	  		return id_database;
	}
	
	public void setId_database(Long id_database) {
		this.id_database = id_database;
	}		
	
	@Column(name="CODE",length=255)
	public String getCode(){
	  		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}		
	
	@Column(name="VALUE_STR")
	public String getValue_str(){
	  		return value_str;
	}
	
	public void setValue_str(String value_str) {
		this.value_str = value_str;
	}		
}

