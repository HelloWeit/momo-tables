package cn.weit.tables.core;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;


/**
 * @author weitong
 */
@Data
public class TableSql {

	/**
	 * 表名
	 */
	private String name;
	/**
	 * 主键
	 */
	private String primaryKey;

	/**
	 * 字段SQL
	 */
	private List<ColumnSql> columnSqls = Lists.newArrayList();

}
