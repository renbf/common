package com.yl.utils;

import java.io.Serializable;

/**
 * @author rbf
 * @createdate 2018/12/4 17:39
 * @desriction
 */
public class BeanMapper implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4007501082692838583L;
	/**
	 * 是否主键
	 */
	private Boolean isPrimary;
	
	/**
	 * 属性的类型
	 */
	private String javaType;
	/**
	 * 属性的名称
	 */
    private String javaName;
    /**
	 * 属性的名称首字母大写
	 */
    private String javaNameUpperCase;
    /**
	 * 属性的备注
	 */
    private String javabz;
    /**
	 * 表的字段名称
	 */
    private String columnName;
    /**
	 * 表的字段类型
	 */
    private String jdbcType;

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public String getJavabz() {
        return javabz;
    }

    public void setJavabz(String javabz) {
        this.javabz = javabz;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

	public String getJavaNameUpperCase() {
		return javaNameUpperCase;
	}

	public void setJavaNameUpperCase(String javaNameUpperCase) {
		this.javaNameUpperCase = javaNameUpperCase;
	}

	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

    
}
