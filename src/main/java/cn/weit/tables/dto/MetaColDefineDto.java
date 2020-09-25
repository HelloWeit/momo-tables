package cn.weit.tables.dto;

import lombok.Data;

/**
 * @author weitong
 */
@Data
public class MetaColDefineDto {
	private String category;
	private String code;
	private String colName;
	private String colType;
	private String defineName;
	private String status;
	private String tableName;
	private String tenantId;
	private Long id;
}
