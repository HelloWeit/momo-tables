package cn.weit.tables.core;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * @author weitong
 */
@Data
public class ColumnSql implements Serializable {

	public ColumnSql(ColumnInfo column, List<String> primaryKeys) {
		this.setFieldName(column.getFieldName());
		StringBuilder stringBuilder = new StringBuilder(" ");
		stringBuilder.append(column.getFieldType());
		if (column.getFieldLen() > 0) {
			stringBuilder.append("(").append(column.getFieldLen()).append(")");
		}
		if(primaryKeys.contains(column.getFieldName())){
			stringBuilder.append(" NOT NULL");
		} else {
			stringBuilder.append(" NULL");

		}
		this.setFieldType(stringBuilder.toString());
	}

	private String fieldType;

	private String fieldName;
}
