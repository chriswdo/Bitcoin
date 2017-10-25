package com.eshore.datasupport.metadata.vo;

import java.io.Serializable;

/**
 * Created by lsl on 2017/6/19.
 */
public class DataSourceTs implements Serializable{
    private String fieldName;
    private String dataType;
    private Short dataLength;
    private Short dataPrecision;
    private String primaryKey;
    private String defaultValue;
    private String nullAble;
    private String fieldComment;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Short getDataLength() {
        return dataLength;
    }

    public void setDataLength(Short dataLength) {
        this.dataLength = dataLength;
    }

    public Short getDataPrecision() {
        return dataPrecision;
    }

    public void setDataPrecision(Short dataPrecision) {
        this.dataPrecision = dataPrecision;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getNullAble() {
        return nullAble;
    }

    public void setNullAble(String nullAble) {
        this.nullAble = nullAble;
    }

    public String getFieldComment() {
        return fieldComment;
    }

    public void setFieldComment(String fieldComment) {
        this.fieldComment = fieldComment;
    }
}
