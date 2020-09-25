package cn.weit.tables.core;

import lombok.Data;

/**
 * @author weitong
 */
@Data
public class ColumnInfo {
	/**
	 * 字段名
	 */
	private String fieldName;
	/**
	 * 字段类型
	 */
	private String fieldType;
	/**
	 * 字段长度
	 */
	private Integer fieldLen;
	/**
	 * 是否是主键 默认为false
	 */
	private Boolean key = false;
	/**
	 * 是否需要自增长 默认为false
	 */
	private Boolean autoIncrement = false;
}
